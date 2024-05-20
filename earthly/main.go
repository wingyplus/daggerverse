// Provides a functionallity to run Earth

package main

import (
	"context"
)

// TODO: support mTLS.
// TODO: support custom registry auth.

const workspacePath = "/workspace"

func New(
	// Source directory.
	source *Directory,
) *Earthly {
	return &Earthly{Source: source, Version: "v0.8.11"}
}

// Earthly module for dagger.
type Earthly struct {
	Source  *Directory
	Version string
}

// Run earthly.
func (m *Earthly) Run(ctx context.Context, args []string) error {
	_, err := m.WithEarthly().
		WithExec(append([]string{"earthly"}, args...)).
		Sync(ctx)

	return err
}

func (m *Earthly) WithEarthly() *Container {
	config := `
global:
  tls_enabled: false
`
	return dag.Container().
		From("earthly/earthly:"+m.Version).
		WithServiceBinding("dockerd", m.DockerEngine()).
		WithServiceBinding("buildkitd", m.Buildkitd()).
		WithEnvVariable("DOCKER_HOST", "tcp://dockerd:2375").
		WithEnvVariable("NO_BUILDKIT", "1").
		WithEnvVariable("EARTHLY_BUILDKIT_HOST", "tcp://buildkitd:8372").
		WithEnvVariable("BUILDKIT_TLS_ENABLED", "false").
		WithNewFile("/root/.earthly/config.yml", ContainerWithNewFileOpts{
			Contents: config,
		}).
		WithMountedDirectory(workspacePath, m.Source).
		WithWorkdir(workspacePath).
		WithoutEntrypoint()
}

func (m *Earthly) DockerEngine() *Service {
	return dag.Docker().Engine(DockerEngineOpts{Version: "26"})
}

// Start the Earthly Buildkitd as a service.
func (m *Earthly) Buildkitd() *Service {
	return dag.Container().
		From("earthly/buildkitd:"+m.Version).
		WithEnvVariable("BUILDKIT_TCP_TRANSPORT_ENABLED", "true").
		WithEnvVariable("BUILDKIT_TLS_ENABLED", "false").
		WithExec([]string{"rootlesskit", "buildkitd"}, ContainerWithExecOpts{
			InsecureRootCapabilities: true,
		}).
		WithoutEntrypoint().
		WithExposedPort(8372).
		AsService()
}
