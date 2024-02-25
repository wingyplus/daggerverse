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
        |> Dagger.Function.with_description(doc_for(Wttr, :wttr))
        |> Dagger.Function.with_arg(
          "location",
          dag
          |> Dagger.Client.type_def()
          |> Dagger.TypeDef.with_kind(Dagger.TypeDefKind.string_kind()),
          description: "The location"
        )
      )
    )
    |> Dagger.Module.id()
  end

  def invoke(_, parent_name, _, _) do
    {:error, "unknown object #{parent_name}"}
  end

  def invoke_function(ctx, _parent, "Wttr", input_args) do
    args =
      input_args
      |> Enum.map(fn arg ->
        {:ok, name} = Dagger.FunctionCallArgValue.name(arg)
        {:ok, value} = Dagger.FunctionCallArgValue.value(arg)
        {name, Jason.decode!(value)}
      end)
      |> Enum.into(%{}, fn {name, value} -> {String.to_existing_atom(name), value} end)

    {:ok, Wttr.wttr(ctx, args)}
  end

  defp doc_for(module, fn_name) do
    {:docs_v1, _annotation, :elixir, _format, _module_doc, _meta, docs} = Code.fetch_docs(module)

    case Enum.find(docs, &find_doc(&1, fn_name)) do
      nil -> ""
      {_, _, _, %{"en" => doc}, _} -> doc
    end
  end

  defp find_doc({{:function, fn_name, _}, _, _, _, _}, fn_name) do
    true
  end

  defp find_doc(_, _), do: false
end
