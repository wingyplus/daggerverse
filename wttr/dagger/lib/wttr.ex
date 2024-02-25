defmodule Wttr do
  @moduledoc """
  A Dagger module for [wttr.in](https://wttr.in).
  """

  defstruct [:dag]

  # Q: How to injecting arguments to the module function?
  @doc """
  Reports the weather.

  ## Example

  ```
  $ dagger call wttr
  ```
  """
  def wttr(this) do
    this.dag
    |> Dagger.Client.container()
    |> Dagger.Container.from("alpine")
    |> Dagger.Container.with_exec(~w"apk add curl")
    |> Dagger.Container.with_exec(~w"curl https://wttr.in")
    |> Dagger.Container.stdout()
  end
end
