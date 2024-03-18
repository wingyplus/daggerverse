defmodule Dagger.ModuleRuntime.Module do
  alias Dagger.ModuleRuntime.Function
  alias Dagger.ModuleRuntime.Helper

  @doc """
  Define a Dagger module.
  """
  def define(dag, module) when is_struct(dag, Dagger.Client) and is_atom(module) do
    dag
    |> Dagger.Client.module()
    |> Dagger.Module.with_object(define_object(dag, module))
  end

  defp define_object(dag, module) do
    attrs = module.__info__(:attributes)
    mod_name = Keyword.fetch!(attrs, :name)
    functions = Keyword.fetch!(attrs, :functions)

    type_def =
      dag
      |> Dagger.Client.type_def()
      |> Dagger.TypeDef.with_object(Helper.camelize(mod_name))

    functions
    |> Enum.map(&Function.define(dag, &1))
    |> Enum.reduce(
      type_def,
      &Dagger.TypeDef.with_function(&2, &1)
    )
  end
end
