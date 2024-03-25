defmodule FunctionTest do
  @moduledoc """
  Dagger Elixir Module integration tests.
  """

  use Dagger.ModuleRuntime, name: "FunctionTest"

  defstruct [:dag]

  @function [
    args: [
      text: [type: :string]
    ],
    return: Dagger.Container
  ]
  def test_return_dagger_type(self, %{text: text}) do
    self.dag
    |> Dagger.Client.container()
    |> Dagger.Container.from("alpine:latest")
    |> Dagger.Container.with_exec(~w"echo #{text}")
  end

  @function [
    args: [
      directory: [type: Dagger.Directory]
    ],
    return: :string
  ]
  def test_accept_dagger_type(_self, %{directory: directory}) do
    with {:ok, content} <-
           Dagger.Directory.file(directory, "dagger.json") |> Dagger.File.contents(),
         {:ok, json} <- Jason.decode(content),
         %{"name" => "function-test"} <- json do
      "ok"
    end
  end
end
