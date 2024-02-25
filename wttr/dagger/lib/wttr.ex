defmodule Wttr do
  @moduledoc """
  A Dagger module for [wttr.in](https://wttr.in).
  """

  defstruct [:dag]

  @doc """
  Reports the weather.

  ## Example

  ```
  $ dagger call wttr
  ```
  """
  def wttr(self, args) do
    self.dag
    |> Dagger.Client.container()
    |> Dagger.Container.from("alpine")
    |> Dagger.Container.with_exec(~w"apk add curl")
    |> Dagger.Container.with_exec(~w"curl https://wttr.in/#{args.location}")
    |> Dagger.Container.stdout()
  end
end
