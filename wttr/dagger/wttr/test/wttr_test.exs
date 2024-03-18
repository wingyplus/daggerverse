defmodule WttrTest do
  use ExUnit.Case
  doctest Wttr

  test "greets the world" do
    assert Wttr.hello() == :world
  end
end
