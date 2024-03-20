# Dagger module runtime for Elixir

This is experiment on module runtime for Elixir. Expected to merge into 
upstream when it's looks soild.

## How it works

During code is generating, the runtime will create 3 applications to your 
module:

1. `dagger_module_runtime` - provides dsl and runtime for Dagger.
2. `dagger` - the Dagger APIs.
3. Your working module.

## Using it

Initialize it with `dagger init`:

```
$ dagger init --sdk=github.com/wingyplus/daggerverse/elixir-sdk <name>
```

TBC
