defmodule FunctionTestTest do
  use ExUnit.Case
  doctest FunctionTest

  test "greets the world" do
    assert FunctionTest.hello() == :world
  end
end
