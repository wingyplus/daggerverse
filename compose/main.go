// `compose` is a module for turning the docker-compose into Dagger services.

package main

import (
	"context"
	"dagger/compose/internal/dagger"
)

func New(
	// The `dagger-compose.yaml` file.
	composeFile *dagger.File,
) *Compose {
	return &Compose{
		File: composeFile,
	}
}

type Compose struct {
	File *dagger.File
}

// WithBinding binding a services from compose to the container.
func (m *Compose) WithBinding(ctx context.Context, ctr *dagger.Container) (*dagger.Container, error) {
	svcs, err := ComposeToServices(ctx, m.File)
	if err != nil {
		return nil, err
	}
	for name, svc := range svcs {
		ctr = ctr.WithServiceBinding(name, svc)
	}
	return ctr, nil
}
