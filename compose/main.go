package main

import (
	"context"
	"os"

	"github.com/compose-spec/compose-go/loader"
	"github.com/compose-spec/compose-go/types"
)

// Compose is root GraphQL type for Docker Compose.
type Compose struct{}

// WithFile setup service binding from the given compose path.
//
// Returns a container with pre-setup services.
func (m *Compose) WithFile(ctx context.Context, path string) (*Container, error) {
	yaml, err := os.ReadFile(path)
	if err != nil {
		return nil, err
	}

	wd, err := os.Getwd()
	if err != nil {
		return nil, err
	}
	// TODO: use filename from path.
	proj, err := loader.LoadWithContext(
		ctx,
		types.ConfigDetails{
			// TODO: do not hard coding version.
			Version:    "3",
			WorkingDir: wd,
			ConfigFiles: []types.ConfigFile{
				{Filename: "nofile", Content: yaml},
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
		container = container.WithServiceBinding(svc.Name, buildContainer(dag, svc))
	}

	return container, nil
}

func buildContainer(client *Client, svc types.ServiceConfig) *Container {
	container := client.Container().From(svc.Image)

	for _, port := range svc.Ports {
		container = container.WithExposedPort(int(port.Target))
	}

	return container
}
