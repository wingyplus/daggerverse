package main

import (
	"context"
	"fmt"
	"path"
)

const (
	ModSourceDirPath     = "/src"
	sdkSrc               = "/sdk"
	genDir               = "sdk"
	schemaPath           = "/schema.json"
	defaultElixirVersion = "1.16.1-erlang-26.2.2-debian-bookworm-20240130-slim"
)

func New(
	// +optional
	sdkSourceDir *Directory,
) *ElixirSdk {
	return &ElixirSdk{SDKSourceDir: sdkSourceDir, RequiredPaths: []string{}}
}

type ElixirSdk struct {
	SDKSourceDir  *Directory
	RequiredPaths []string
}

func (m *ElixirSdk) ModuleRuntime(
	ctx context.Context,
	modSource *ModuleSource,
	introspectionJson string,
) (*Container, error) {
	ctr, err := m.CodegenBase(ctx, modSource, introspectionJson)
	if err != nil {
		return nil, err
	}

	subPath, err := modSource.SourceSubpath(ctx)
	if err != nil {
		return nil, err
	}

	entrypoint := path.Join(ModSourceDirPath, subPath)

	return ctr.
		WithExec([]string{"mix", "deps.get"}).
		WithEntrypoint([]string{"sh", "-c", "cd " + entrypoint + " && mix do deps.get + dagger.invoke"}), nil
}

func (m *ElixirSdk) Codegen(ctx context.Context, modSource *ModuleSource, introspectionJson string) (*GeneratedCode, error) {
	ctr, err := m.CodegenBase(ctx, modSource, introspectionJson)
	if err != nil {
		return nil, fmt.Errorf("could not load module config: %v", err)
	}

	return dag.GeneratedCode(ctr.Directory(ModSourceDirPath)).
		WithVCSGeneratedPaths([]string{genDir + "/**"}).
		WithVCSIgnoredPaths([]string{genDir}), nil
}

func (m *ElixirSdk) CodegenBase(ctx context.Context, modSource *ModuleSource, introspectionJson string) (*Container, error) {
	subPath, err := modSource.SourceSubpath(ctx)
	if err != nil {
		return nil, err
	}

	sdkModules := dag.Git("https://github.com/wingyplus/dagger").Branch("elixir-new-codegen").Tree().Directory("sdk/elixir/lib")

	ctr := m.Base("").
		WithMountedDirectory(ModSourceDirPath, modSource.ContextDirectory()).
		WithExec([]string{"mix", "escript.install",
			"github", "wingyplus/dagger", "branch", "elixir-new-codegen",
			"--sparse", "sdk/elixir/dagger_codegen", "--force"}).
		WithNewFile(schemaPath, ContainerWithNewFileOpts{
			Contents: introspectionJson,
		}).
		WithWorkdir(path.Join(ModSourceDirPath, subPath)).
		WithExec([]string{"mix", "new", "."}).
		WithDirectory("lib/dagger", sdkModules.Directory("dagger"), ContainerWithDirectoryOpts{Exclude: []string{"gen"}}).
		WithFile("lib/dagger.ex", sdkModules.File("dagger.ex")).
		WithExec([]string{
			"dagger_codegen", "generate",
			"--outdir", "lib/dagger/gen",
			"--introspection", schemaPath,
		}).
		WithExec([]string{
			"mix", "format",
		})
		// WithExec([]string{"sh", "-c", "ls -lah /src && exit 1"})

	return ctr, nil
}

func (m *ElixirSdk) Base(version string) *Container {
	if version == "" {
		version = defaultElixirVersion
	}
	// TODO: Mount cache.
	return dag.Container().
		From("hexpm/elixir:"+version).
		WithExec([]string{"apt", "update"}).
		WithExec([]string{"apt", "install", "-y", "--no-install-recommends", "git"}).
		WithExec([]string{"mix", "local.hex", "--force"}).
		WithExec([]string{"mix", "local.rebar", "--force"}).
		WithEnvVariable("CACHE_BURST", "2").
		WithEnvVariable("PATH", "/root/.mix/escripts:$PATH", ContainerWithEnvVariableOpts{
			Expand: true,
		})
}
