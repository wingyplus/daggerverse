# Earthly module for Dagger

This module allows the Earthly to run inside Dagger.

## Getting Started

Let's try this command belows:

```
$ dagger call -m github.com/wingyplus/daggerverse/earthly --source=. run --args='--verbose' --args='./test+run'
```

* The module required `--source` option to mount source code directory into the 
  workspace.
* The function `run` run the earthly task against directory defined in `--source`.

## Authenticating with Docker registry

This module support docker config to allows you to setup registry auth by using 
`--docker-config` option:

```
$ dagger call -m github.com/wingyplus/daggerverse/earthly --source=. --docker-config=./config.json run --args='--verbose' --args='./test+run'
```

## Limitation

* It doesn't support `GIT CLONE` authentication.
* It cannot custom `.env` and `.secret` file.

