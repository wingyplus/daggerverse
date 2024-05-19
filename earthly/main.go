// Provides a functionallity to run Earth

package main

import (
	"context"
)

func New(
	// Source directory.
	source *Directory,
) *Earthly {
	return &Earthly{Source: source}
}

type Earthly struct {
	Source *Directory
}

func (m *Earthly) Run(ctx context.Context, args []string) error {
	_, err := m.image().
		WithServiceBinding("docker", m.docker()).
		WithEnvVariable("DOCKER_HOST", "tcp://docker:2375").
		WithMountedDirectory("/source", m.Source).
		WithWorkdir("/source").
		WithExec(append([]string{"earthly"}, args...), ContainerWithExecOpts{
			ExperimentalPrivilegedNesting: true,
		}).
		Sync(ctx)

	return err
}

func (m *Earthly) image() *Container {
	return dag.Container().
		From("docker:cli").
		WithExec([]string{"apk", "add", "wget"}).
		WithExec([]string{"wget", "https://github.com/earthly/earthly/releases/latest/download/earthly-linux-amd64", "-O", "/usr/local/bin/earthly"}).
		WithExec([]string{"chmod", "+x", "/usr/local/bin/earthly"})
}

func (m *Earthly) docker() *Service {
	return dag.Docker().Daemon().Service()
}
