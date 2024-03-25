defmodule FunctionTest.MixProject do
  use Mix.Project

  def project do
    [
      app: :function_test,
      version: "0.1.0",
      elixir: "~> 1.16",
      start_permanent: Mix.env() == :prod,
      deps: deps()
    ]
  end

  def application do
    [
      extra_applications: [:logger],
      mod: {FunctionTest.Application, []}
    ]
  end

  defp deps do
    [
      {:dagger, path: "../dagger", override: true},
      {:dagger_module_runtime, path: "../dagger_module_runtime"},
      {:jason, "~> 1.0"}
    ]
  end
end

