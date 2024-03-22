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

  @doc """
  Invoke a function.
  """
  def invoke(dag \\ Dagger.connect!()) do
    fn_call = Dagger.Client.current_function_call(dag)

    with {:ok, parent_name} <- Dagger.FunctionCall.parent_name(fn_call),
         {:ok, fn_name} <- Dagger.FunctionCall.name(fn_call),
         {:ok, parent_json} <- Dagger.FunctionCall.parent(fn_call),
         {:ok, parent} <- Jason.decode(parent_json),
         {:ok, input_args} <- Dagger.FunctionCall.input_args(fn_call),
         # TODO: `result` needs to verify pattern before encoding it.
         {:ok, result} <-
           invoke(dag, parent, parent_name, fn_name, input_args),
         {:ok, json} = encode(result),
         {:ok, _} <- Dagger.FunctionCall.return_value(fn_call, json) do
      :ok
    else
      {:error, reason} ->
        IO.puts(inspect(reason))
        System.halt(2)
    end
  after
    Dagger.close(dag)
  end

  defp encode({:ok, result}) do
    encode(result)
  end

  defp encode(result) do
    Jason.encode(result)
  end

  # NOTE: We can have only 1 module.
  def invoke(dag, _parent, "", _fn_name, _input_args) do
    # TODO: find the way on how to register multiple modules.
    [module] = Dagger.ModuleRuntime.Registry.all()

    dag
    |> Dagger.ModuleRuntime.Module.define(module)
    |> Dagger.Module.id()
  end

  def invoke(dag, parent, parent_name, fn_name, input_args) do
    case Dagger.ModuleRuntime.Registry.get(parent_name) do
      nil ->
        {:error,
         "unknown object #{parent_name}, all have #{inspect(Dagger.ModuleRuntime.Registry.all())}"}

      module ->
        invoke_function(module, struct(module, dag: dag), parent, fn_name, input_args)
    end
  end

  def invoke_function(module, ctx, _parent, fn_name, input_args) do
    fun = fn_name |> Macro.underscore() |> String.to_existing_atom()

    args =
      Enum.into(input_args, %{}, fn arg ->
        {:ok, name} = Dagger.FunctionCallArgValue.name(arg)
        {:ok, value} = Dagger.FunctionCallArgValue.value(arg)
        {String.to_existing_atom(name), Jason.decode!(value)}
      end)

    {:ok, apply(module, fun, [ctx, args])}
  end

  defmacro __using__(opt) do
    unless opt[:name] do
      raise "Module name must be define."
    end

    name = opt[:name]

    quote bind_quoted: [name: name] do
      use GenServer

      import Dagger.ModuleRuntime

      @name name
      @on_definition Dagger.ModuleRuntime
      @functions []

      Module.register_attribute(__MODULE__, :name, persist: true)
      Module.register_attribute(__MODULE__, :functions, persist: true)

      def start_link(_) do
        GenServer.start_link(__MODULE__, [], name: __MODULE__)
      end

      def init([]) do
        Dagger.ModuleRuntime.Registry.register(__MODULE__)
        {:ok, []}
      end
    end
  end
end
