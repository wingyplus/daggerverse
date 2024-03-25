ExUnit.start()

defmodule FunctionTest do
  use ExUnit.Case, async: true

  setup do
    %{dagger: System.find_executable("dagger")}
  end

  test "accept dagger type", %{dagger: dagger} do
    assert {"ok", 0} = System.cmd(dagger, ~w[call test-accept-dagger-type --directory .])
  end

  test "return dagger type", %{dagger: dagger} do
    assert {"'From `dagger call`'\n", 0} =
             System.cmd(
               dagger,
               ["call", "test-return-dagger-type", "--text", "'From `dagger call`'", "stdout"]
             )
  end
end
