# This file generated by `dagger_codegen`. Please DO NOT EDIT.
defmodule Dagger.Secret do
  @moduledoc "A reference to a secret value, which can be handled more safely than the value itself."

  use Dagger.Core.QueryBuilder

  @derive Dagger.ID

  defstruct [:selection, :client]

  @type t() :: %__MODULE__{}

  @doc "A unique identifier for this Secret."
  @spec id(t()) :: {:ok, Dagger.SecretID.t()} | {:error, term()}
  def id(%__MODULE__{} = secret) do
    selection =
      secret.selection |> select("id")

    execute(selection, secret.client)
  end

  @doc "The value of this secret."
  @spec plaintext(t()) :: {:ok, String.t()} | {:error, term()}
  def plaintext(%__MODULE__{} = secret) do
    selection =
      secret.selection |> select("plaintext")

    execute(selection, secret.client)
  end
end