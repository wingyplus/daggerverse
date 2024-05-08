package main

type Builder struct {
	Version string
	Modules []string
}

func (b *Builder) WithVersion(version string) *Builder {
	b.Version = version
	return b
}

func (b *Builder) With(module string) *Builder {
	b.Modules = append(b.Modules, module)
	return b
}

func (b *Builder) Build(c *Container) *Container {
	args := []string{"build", b.Version, "--output", "/xk6/bin/k6"}
	for _, m := range b.Modules {
		args = append(args, "--with", m)
	}
	return c.WithExec(args)
}
