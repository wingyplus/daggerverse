package main

import (
	"context"
	"fmt"
	"strings"
)

type ComposeTest struct{}

func (m *ComposeTest) TestServiceBindingPort(ctx context.Context) error {
	ctr := dag.Container().From("alpine").WithExec([]string{"apk", "add", "curl"})

	compose := dag.CurrentModule().
		Source().
		File("testdata/test-service-binding-port/docker-compose.1.yaml")
	out, err := dag.Compose(compose).
		WithBinding(ctr).
		WithExec([]string{"curl", "http://nginx"}).
		Stdout(ctx)
	if err != nil {
		return err
	}

	if !strings.Contains(out, "Welcome to nginx!") {
		return fmt.Errorf("failed to get a contents from services\n\ngot:\n%s", out)
	}
	return nil
}
