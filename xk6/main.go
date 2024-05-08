package main

type Xk6 struct{}

func (m *Xk6) Build(version string, with []string) *File {
	args := []string{"build", version, "--output", "/xk6/bin/k6"}
	for _, m := range with {
		args = append(args, "--with", m)
	}
    // Fix image unavailable on arm64.
	return m.FromSource().WithExec(args).File("/xk6/bin/k6")
}

func (m *Xk6) FromSource() *Container {
	return dag.Git("https://github.com/grafana/xk6.git").Tag("v0.11.0").Tree().DockerBuild()
}
