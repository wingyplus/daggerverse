// Allows the Earthly to run inside Dagger.

package main

import (
	"context"
)

// TODO: support mTLS.

const workspacePath = "/workspace"

func New(
	// Source directory.
	source *Directory,

	// The Docker configuration. It's uses for authenticate with the registry
	// auth
	//
	// +optional
	dockerConfig *File,
) *Earthly {
	return &Earthly{Source: source, Version: "v0.8.11", DockerConfig: dockerConfig}
}

// Earthly module for dagger.
type Earthly struct {
	// +private
	Source *Directory

	// +private
	Version string

	// +private
	DockerConfig *File
}

// Run earthly.
func (m *Earthly) Run(ctx context.Context, args []string) error {
	_, err := m.WithEarthly().
		WithExec(append([]string{"earthly"}, args...)).
		Sync(ctx)

	return err
}

// Setup Earthly with mounting source into workspace.
func (m *Earthly) WithEarthly() *Container {
	config := `
global:
  tls_enabled: false
`
	ctr := dag.Container().
		From("earthly/earthly:"+m.Version).
		WithServiceBinding("dockerd", m.DockerEngine()).
		WithServiceBinding("buildkitd", m.Buildkitd()).
		WithEnvVariable("DOCKER_HOST", "tcp://dockerd:2375").
		WithEnvVariable("NO_BUILDKIT", "1").
		WithEnvVariable("EARTHLY_BUILDKIT_HOST", "tcp://buildkitd:8372").
		WithNewFile("/root/.earthly/config.yml", ContainerWithNewFileOpts{
			Contents: config,
		}).
		WithMountedDirectory(workspacePath, m.Source).
		WithWorkdir(workspacePath).
		WithoutEntrypoint()

	if m.DockerConfig != nil {
		ctr = ctr.WithMountedFile("/root/.docker/config.json", m.DockerConfig)
	}

	return ctr
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
