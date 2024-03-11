defmodule Dagger.ModuleRuntime do
  @moduledoc """
  `Dagger.ModuleRuntime` is a runtime for `Dagger` module for Elixir.
  """

  def __on_definition__(env, :def, name, args, _guards, _body) do
    case Module.get_attribute(env.module, :function) do
      nil ->
        :ok

      function ->
        unless length(args) == 2 do
          raise """
          A function must have 2 arguments.
          """
        end

        functions = Module.get_attribute(env.module, :functions)
        functions = [{name, function} | functions]
        Module.put_attribute(env.module, :functions, functions)
        Module.delete_attribute(env.module, :function)
    end
  end

  def __on_definition__(env, :defp, name, _args, _guards, _body) do
    case Module.get_attribute(env.module, :function) do
      nil ->
        :ok

      _ ->
        raise """
        Define `@function` on private function (#{name}) is not supported.
        """
    end
  end

  defmacro __using__(_) do
    quote do
      import Dagger.ModuleRuntime

      @on_definition Dagger.ModuleRuntime
      @functions []

      Module.register_attribute(__MODULE__, :functions, persist: true)
    end
  end
end
