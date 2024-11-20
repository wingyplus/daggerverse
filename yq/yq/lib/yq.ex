defmodule Yq do
  use Dagger.Mod.Object, name: "Yq"

  defn yq(content: String.t(), expression: String.t()) :: String.t() do
    dag()
    |> Dagger.Client.container()
    |> Dagger.Container.from("alpine:latest")
    |> Dagger.Container.with_exec(~w"apk add yq")
    |> Dagger.Container.with_new_file("/file", content)
    |> Dagger.Container.with_exec(["yq", expression, "/file"])
    |> Dagger.Container.stdout()
  end
end
