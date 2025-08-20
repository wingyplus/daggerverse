package main

import (
	"context"
	"dagger/compose/internal/dagger"

	"github.com/compose-spec/compose-go/v2/loader"
	"github.com/compose-spec/compose-go/v2/types"
)

func ComposeToServices(ctx context.Context, composeFile *dagger.File) (services map[string]*dagger.Service, err error) {
	services = make(map[string]*dagger.Service)
	contents, err := composeFile.Contents(ctx)
	if err != nil {
		return nil, err
	}
	project, err := parseComposeFile(ctx, contents)
	if err != nil {
		return nil, err
	}

	for name, svc := range project.Services {
		services[name] = toDaggerContainer(svc).AsService(dagger.ContainerAsServiceOpts{
			UseEntrypoint: true,
		})
	}

	return
}

func parseComposeFile(ctx context.Context, contents string) (*types.Project, error) {
	// Create a config source from the file contents
	configDetails := types.ConfigDetails{
		ConfigFiles: []types.ConfigFile{
			{
				Filename: "docker-compose.yml",
				Content:  []byte(contents),
			},
		},
		Environment: map[string]string{}, // You can add environment variables here
	}

	// Load and parse the compose file
	project, err := loader.LoadWithContext(ctx, configDetails, func(options *loader.Options) {
		options.SetProjectName("dagger-compose", false)
		options.ResolvePaths = true
	})
	if err != nil {
		return nil, err
	}

	return project, nil
}

func toDaggerContainer(svc types.ServiceConfig) *dagger.Container {
	ctr := dag.Container().From(svc.Image)

	if len(svc.Entrypoint) > 0 {
		ctr.WithEntrypoint(svc.Entrypoint)
	}

	for _, port := range svc.Ports {
		// TODO: support port protocol.
		ctr = ctr.WithExposedPort(int(port.Target))
	}

	for name, value := range svc.Environment {
		ctr = ctr.WithEnvVariable(name, *value)
	}

	return ctr
}
