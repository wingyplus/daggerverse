package main

import (
	"context"
	"fmt"
)

type KotlinSdkCi struct{}

// Generate SDK sources into `kotlin-sdk`.
func (m *KotlinSdkCi) Generate(ctx context.Context, introspectionJson *File) *Directory {
	return m.Codegen(introspectionJson).
		WithExec([]string{"sh", "-c", "./gradlew run --args='-i introspection.json -o gen'"}).
		WithExec([]string{"java", "-jar", "/ktfmt.jar", "--kotlinlang-style", "gen"}).
		Directory("gen")
}

// Codegen base.
func (m *KotlinSdkCi) Codegen(introspectionJson *File) *Container {
	dotGradle := dag.CacheVolume("dot-gradle")
	codegen := dag.CurrentModule().Source().Directory("kotlin-codegen")
	return dag.Container().
		From("openjdk:23-jdk-slim-bookworm").
		WithMountedDirectory("/codegen", codegen).
		WithMountedFile("/ktfmt.jar", m.Ktfmt("0.47")).
		WithMountedFile("/codegen/introspection.json", introspectionJson).
		WithMountedCache("/codegen/.gradle", dotGradle).
		WithWorkdir("/codegen")
}

// Download and build `ktfmt`.
//
// Returns ktfmt artifact as a JAR file.
func (m *KotlinSdkCi) Ktfmt(version string) *File {
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

// TODO: Running under dagger binary.
func (m *KotlinSdkCi) Test(ctx context.Context) error { return nil }
