defmodule Main do
  require EEx

  def run(["gen_mix_exs", module]) do
    module = normalize_name(module)

    render_mix_exs(
      module: Macro.camelize(module),
      application: ":#{Macro.underscore(module)}"
    )
    |> IO.puts()
  end

  def run(["gen_module", module]) do
    module = normalize_name(module)

    render_module(module: Macro.camelize(module))
    |> IO.puts()
  end

  defp normalize_name(module) do
    String.replace(module, "-", "_")
  end

  @mix_exs """
  defmodule <%= @module %>.MixProject do
    use Mix.Project

    def project do
      [
        app: <%= @application %>,
        version: "0.1.0",
        elixir: "~> 1.16",
        start_permanent: Mix.env() == :prod,
        deps: deps()
      ]
    end

    def application do
      [
        extra_applications: [:logger]
      ]
    end

    defp deps do
      [
        {:dagger, path: "../dagger", override: true},
        {:dagger_module_runtime, path: "../dagger_module_runtime"}
      ]
    end
  end
  """

  EEx.function_from_string(:def, :render_mix_exs, @mix_exs, [:assigns])

  @module_ex """
  defmodule <%= @module %> do
    @moduledoc \"\"\"
    Documentation for `<%= @module %>`.
    \"\"\"

    use Dagger.ModuleRuntime, name: "<%= @module %>"

    defstruct [:dag]

    @function [
      args: [
        string_arg: [type: :string]
      ],
      return: Dagger.Container
    ]
    def container_echo(self, args) do
      self.dag
      |> Dagger.Client.container()
      |> Dagger.Container.from("alpine:latest")
      |> Dagger.Container.with_exec(~w"echo \#{args.string_arg}")
    end
  end
  """

  EEx.function_from_string(:def, :render_module, @module_ex, [:assigns])
end

Main.run(System.argv())
