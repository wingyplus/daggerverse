package main

import (
	"context"
	"os"
	"path/filepath"

	"github.com/compose-spec/compose-go/loader"
	"github.com/compose-spec/compose-go/types"
)

// Compose is root GraphQL type for Docker Compose.
type Compose struct{}

// WithFile setup service binding from the given compose path.
//
// Returns a container with pre-setup services.
func (m *Compose) WithFile(ctx context.Context, path string) (*Container, error) {
	p, err := filepath.Abs(path)
	if err != nil {
		return nil, err
	}
	yaml, err := os.ReadFile(p)
	if err != nil {
		return nil, err
	}
	wd, err := os.Getwd()
	if err != nil {
		return nil, err
	}

	proj, err := loader.LoadWithContext(
		ctx,
		types.ConfigDetails{
			// TODO: do not hard coding version.
			Version:    "3",
			WorkingDir: wd,
			ConfigFiles: []types.ConfigFile{
				{Filename: filepath.Base(path), Content: yaml},
			},
			Environment: make(types.Mapping),
		},
		// TODO: correct me.
		func(options *loader.Options) {
			options.SkipConsistencyCheck = true
			options.SkipNormalization = true
			options.ResolvePaths = true
		},
	)
	if err != nil {
		return nil, err
	}

	container := dag.Container()
	for _, svc := range proj.Services {
		svc := svc
		container = container.WithServiceBinding(svc.Name, buildContainer(dag, svc).AsService())
	}

	return container, nil
}

// Construct container service from compose service spec.
func buildContainer(client *Client, svc types.ServiceConfig) *Container {
	container := client.Container().From(svc.Image)

	for _, port := range svc.Ports {
		container = container.WithExposedPort(int(port.Target))
	}

	for k, v := range svc.Environment {
		container = container.WithEnvVariable(k, *v)
	}

	return container
}
