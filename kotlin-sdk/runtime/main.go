package main

import (
	"context"
	"fmt"
	"path"
)

const ModSourceDirPath = "/src"

type KotlinSdk struct {
	SDKSourceDir  *Directory
	RequiredPaths []string

	Container *Container

	// Define a `ktfmt` version.
	KtfmtVersion string
}

func (m *KotlinSdk) Codegen(ctx context.Context, modSource *ModuleSource, introspectionJson string) (*GeneratedCode, error) {
	name, err := modSource.ModuleOriginalName(ctx)
	if err != nil {
		return nil, err
	}

	subPath, err := modSource.SourceSubpath(ctx)
	if err != nil {
		return nil, err
	}

	ctr, err := m.Common(ctx, introspectionJson)
	if err != nil {
		return nil, err
	}

	ctr = ctr.
		WithMountedDirectory(ModSourceDirPath, modSource.ContextDirectory()).
		WithWorkdir(path.Join(ModSourceDirPath, subPath)).
		With(m.initProject(name)).
		With(m.FormatCode)

	gc := dag.
		GeneratedCode(ctr.Directory(ModSourceDirPath)).
		WithVCSGeneratedPaths([]string{
			"app",
			"gradle",
			"gradlew",
			"gradlew.bat",
			"settings.gradle.kts",
		}).
		WithVCSIgnoredPaths([]string{
			".gradle",
			".idea",
		})

	return gc, nil
}

func (m *KotlinSdk) ModuleRuntime(
	ctx context.Context,
	modSource *ModuleSource,
	introspectionJson string,
) (*Container, error) {
	return dag.Container(), nil
}

func (m *KotlinSdk) Common(ctx context.Context, introspectionJson string) (*Container, error) {
	ctr := m.
		Base().
		WithKtfmt().
		WithCodegen(introspectionJson).
		Container

	return ctr, nil
}

// Setup base container.
func (m *KotlinSdk) Base() *KotlinSdk {
	gradleUserHome := dag.CacheVolume("gradle-user-home")

	m.Container = m.
		Container.
		From("gradle:8.7-jdk21").
		WithMountedCache("/root/.gradle", gradleUserHome)

	return m
}

// Download and build `ktfmt`.
//
// Returns ktfmt artifact as a JAR file.
// TODO: extract to new module.
func (m *KotlinSdk) Ktfmt(version string) *File {
	dotM2 := dag.CacheVolume("dot-m2")
	repository := dag.Git("https://github.com/facebook/ktfmt.git").Tag("v" + version).Tree()
	return dag.Container().
		From("maven:3").
		WithMountedCache("/root/.m2", dotM2).
		WithMountedDirectory("/ktfmt", repository).
		WithWorkdir("/ktfmt").
		WithExec([]string{"sh", "-c", "mvn install"}).
		File(fmt.Sprintf("core/target/ktfmt-%s-jar-with-dependencies.jar", version))
}

func (m *KotlinSdk) WithKtfmt() *KotlinSdk {
	script := `
#!/bin/bash

java -jar /ktfmt.jar "$@"
`

	m.Container = m.
		Container.
		WithMountedFile("/ktfmt.jar", m.Ktfmt(m.KtfmtVersion)).
		WithNewFile("/usr/local/bin/ktfmt", ContainerWithNewFileOpts{
			Contents:    script,
			Permissions: 0o755,
		})

	return m
}

// Codegen base.
func (m *KotlinSdk) WithCodegen(introspectionJson string) *KotlinSdk {
	dotGradle := dag.CacheVolume("dot-gradle")

	m.Container = m.Container.
		WithMountedDirectory("/codegen", m.SDKSourceDir.Directory("kotlin-codegen")).
		WithMountedCache("/codegen/.gradle", dotGradle).
		WithNewFile("/codegen/introspection.json", ContainerWithNewFileOpts{
			Contents: introspectionJson,
		}).
		WithWorkdir("/codegen")

	return m
}

func (m *KotlinSdk) initProject(name string) WithContainerFunc {
	return func(ctr *Container) *Container {
		return ctr.WithExec([]string{
			"gradle", "init",
			"--type", "kotlin-application",
			"--dsl", "kotlin",
			"--package", "dagger.mod",
			"--java-version", "21",
			"--no-split-project",
			"--use-defaults",
			"--project-name", name,
		})
	}
}

func (m *KotlinSdk) FormatCode(ctr *Container) *Container {
	return ctr.
		WithExec([]string{"ktfmt", "--kotlinlang-style", "app", "settings.gradle.kts"})
}

func New(
	// +optional
	sdkSourceDir *Directory,
) *KotlinSdk {
	// Use sdk source from GitHub instead.
	if sdkSourceDir == nil {
		sdkSourceDir = dag.
			Git("https://github.com/wingyplus/daggerverse.git").
			Branch("main").
			Tree().
			Directory("kotlin-sdk")
	}
	return &KotlinSdk{
		SDKSourceDir:  sdkSourceDir.WithoutDirectory("runtime"),
		RequiredPaths: []string{},
		Container:     dag.Container(),
		KtfmtVersion:  "0.47",
	}
}
