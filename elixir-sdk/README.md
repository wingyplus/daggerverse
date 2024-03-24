# Dagger module runtime for Elixir

This is experiment on module runtime for Elixir. Expected to merge into 
upstream when it's looks soild.

## Create the first module

Create a new module with `dagger init` with named `potato`:

```
$ dagger init --sdk=github.com/wingyplus/daggerverse/elixir-sdk potato
Initialized module potato in potato
$ ls
potato
$ cd potato
```

Now let's see all available functions in this module.

```
$ dagger functions
Name             Description
container-echo   -
grep-dir         -
```

Test calling the function `container-echo` by using `dagger call`.

```
$ dagger call container-echo --string-arg 'Hello, World' stdout
Hello, World
```

