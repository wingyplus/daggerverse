package main

type Xk6 struct{}

// Create a builder object to contruct xk6 option. This API suitable for 
// building `xk6` programatically.
func (m *Xk6) Builder() *Builder {
	return &Builder{}
}

// Build k6 binary from a modules based on k6 `version`.
func (m *Xk6) Build(version string, with []string) *File {
	builder := m.Builder().WithVersion(version)
	for _, m := range with {
		builder = builder.With(m)
	}
	// Fix image unavailable on arm64.
	return builder.Build(m.FromSource()).File("/xk6/bin/k6")
}

// Build xk6 from source.
func (m *Xk6) FromSource() *Container {
	return dag.Git("https://github.com/grafana/xk6.git").Tag("v0.11.0").Tree().DockerBuild()
}
