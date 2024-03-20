defmodule Dagger.ModuleRuntimeTest do
  use ExUnit.Case
  doctest Dagger.ModuleRuntime

  test "store function information" do
    defmodule A do
      use Dagger.ModuleRuntime

      @function [
        args: [
          name: [type: :string]
        ],
        return: :string
      ]
      def hello(_self, args) do
        "Hello, #{args.name}"
      end
    end

    assert functions_for(A) == [
             hello: [
               args: [
                 name: [type: :string]
               ],
               return: :string
             ]
           ]

    defmodule B do
      use Dagger.ModuleRuntime

      @function [
        args: [],
        return: :string
      ]
      def hello(_self, _args), do: "It works"
    end

    assert functions_for(B) == [
             hello: [
               args: [],
               return: :string
             ]
           ]
  end

  test "raise when define with function != 2 arities" do
    assert_raise RuntimeError, fn ->
      defmodule RaiseArityError do
        use Dagger.ModuleRuntime

        @function [
          args: [],
          return: :string
        ]
        def hello(_self, _args, _opts), do: "It works"
      end
    end
  end

  test "raise when define with defp" do
    assert_raise RuntimeError, fn ->
      defmodule RaiseDefp do
        use Dagger.ModuleRuntime

        @function [
          args: [],
          return: :string
        ]
        defp hello(_self, _args), do: "It works"

        def dummy(), do: hello(nil, %{})
      end
    end
  end

  defp functions_for(module) do
    module.__info__(:attributes) |> Keyword.fetch!(:functions)
  end
end
