defmodule Wttr do
  @moduledoc """
  Documentation for `Wttr`.
  """

  use Dagger.ModuleRuntime, name: "Wttr"

  defstruct [:dag]

  @function [
    args: [location: [type: :string]],
    return: :string
  ]
  def wttr(self, args) do
    self.dag
    |> Dagger.Client.container()
    |> Dagger.Container.from("alpine")
    |> Dagger.Container.with_exec(~w"apk add curl")
    |> Dagger.Container.with_exec(~w"curl https://wttr.in/#{args.location}")
    |> Dagger.Container.stdout()
  end
end
