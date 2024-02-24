defmodule Mix.Tasks.Dagger.Invoke do
  use Mix.Task

  def run(_args) do
    Application.ensure_all_started(:dagger)
    do_run(Dagger.connect!())
  end

  def do_run(dag) do
    fn_call = Dagger.Client.current_function_call(dag)

    with {:ok, parent_name} <- Dagger.FunctionCall.parent_name(fn_call),
         {:ok, fn_name} <- Dagger.FunctionCall.name(fn_call),
         {:ok, parent_json} <- Dagger.FunctionCall.parent(fn_call),
         {:ok, parent} <- Jason.decode(parent_json),
         {:ok, input_args} <- Dagger.FunctionCall.input_args(fn_call),
         {:ok, result} <-
           invoke(dag, parent, parent_name, fn_name, input_args),
         json = encode(result),
         {:ok, _} <- Dagger.FunctionCall.return_value(fn_call, json) do
      File.write!("/.daggermod/output.json", json)
      :ok
    else
      {:error, reason} ->
        IO.puts(reason)
        System.halt(2)
    end
  after
    Dagger.close(dag)
  end

  defp encode({:ok, result}) do
    encode(result)
  end

  defp encode(result) do
    Jason.encode!(result)
  end

  def invoke(dag, parent, "Wttr", fn_name, input_args) do
    invoke_function(%Wttr{dag: dag}, parent, fn_name, input_args)
  end

  def invoke(dag, _parent, "", _fn_name, _input_args) do
    dag
    |> Dagger.Client.module()
    |> Dagger.Module.with_object(
      dag
      |> Dagger.Client.type_def()
      |> Dagger.TypeDef.with_object("Wttr")
      |> Dagger.TypeDef.with_function(
        dag
        |> Dagger.Client.function(
          "Wttr",
          dag
          |> Dagger.Client.type_def()
          |> Dagger.TypeDef.with_kind(Dagger.TypeDefKind.string_kind())
        )
        # TODO: fetch document with `Code.fetch_docs/1`.
        |> Dagger.Function.with_description("""
        Reports the weather.

        ## Example

        ```
        $ dagger call wttr
        ```
        """)
      )
    )
    |> Dagger.Module.id()
  end

  def invoke(_, parent_name, _, _) do
    {:error, "unknown object #{parent_name}"}
  end

  def invoke_function(ctx, _parent, "Wttr", _input_args) do
    {:ok, Wttr.wttr(ctx)}
  end
end
