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
	genPath              = "lib/dagger/gen"
	schemaPath           = "/schema.json"
	defaultElixirVersion = "1.16.1-erlang-26.2.2-debian-bookworm-20240130-slim"
)

func New() *ElixirSdk {
	return &ElixirSdk{RequiredPaths: []string{}}
}

type ElixirSdk struct {
	RequiredPaths []string
}

func (m *ElixirSdk) ModuleRuntime(
	ctx context.Context,
	modSource *ModuleSource,
	introspectionJson string,
) (*Container, error) {
	ctr, err := m.CodegenBase(ctx, modSource, introspectionJson)
	if err != nil {
		return nil, fmt.Errorf("could not load module config: %v", err)
	}
	return ctr, nil
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
	ctr := m.Base("").
		WithExec([]string{"git", "clone", "--depth", "1", "-b", "elixir-new-codegen", "https://github.com/wingyplus/dagger.git", "/dagger"}).
		WithWorkdir("/dagger/sdk/elixir/dagger_codegen").
		WithExec([]string{"mix", "deps.get"}).
		WithExec([]string{"mix", "escript.install", "--force"}).
		WithNewFile(schemaPath, ContainerWithNewFileOpts{
			Contents: introspectionJson,
		}).
		WithWorkdir(path.Join(ModSourceDirPath, subPath)).
		WithExec([]string{
			"dagger_codegen", "generate",
			"--outdir", path.Join(sdkSrc, genPath),
			"--introspection", schemaPath,
		})

	return ctr.WithDirectory(genDir, ctr.Directory(sdkSrc)), nil
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
		WithEnvVariable("CACHE_BURST", "1").
		WithEnvVariable("PATH", "/root/.mix/escripts:$PATH", ContainerWithEnvVariableOpts{
			Expand: true,
		})
}
