package main

type Prettier struct{}

// Format a file.
func (m *Prettier) FormatFile(path *File) *File {
	return m.
		Container().
		WithMountedFile("/f.json", path).
		WithExec([]string{"npx", "prettier", "--write", "/f.json"}).
		File("/f.json")
}

// Returns a container that contains `prettier` binary.
func (m *Prettier) Container() *Container {
	return dag.
		Container().
		From("node:21-alpine").
		WithExec([]string{"npm", "install", "-g", "prettier"})
}
