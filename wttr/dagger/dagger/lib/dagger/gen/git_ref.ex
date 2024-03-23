# This file generated by `dagger_codegen`. Please DO NOT EDIT.
defmodule Dagger.GitRef do
  @moduledoc "A git ref (tag, branch, or commit)."

  use Dagger.Core.QueryBuilder

  @derive Dagger.ID

  defstruct [:selection, :client]

  @type t() :: %__MODULE__{}

  @doc "The resolved commit id at this ref."
  @spec commit(t()) :: {:ok, String.t()} | {:error, term()}
  def commit(%__MODULE__{} = git_ref) do
    selection =
      git_ref.selection |> select("commit")

    execute(selection, git_ref.client)
  end

  @doc "A unique identifier for this GitRef."
  @spec id(t()) :: {:ok, Dagger.GitRefID.t()} | {:error, term()}
  def id(%__MODULE__{} = git_ref) do
    selection =
      git_ref.selection |> select("id")

    execute(selection, git_ref.client)
  end

  @doc "The filesystem tree at this ref."
  @spec tree(t(), [
          {:ssh_known_hosts, String.t() | nil},
          {:ssh_auth_socket, Dagger.SocketID.t() | nil}
        ]) :: Dagger.Directory.t()
  def tree(%__MODULE__{} = git_ref, optional_args \\ []) do
    selection =
      git_ref.selection
      |> select("tree")
      |> maybe_put_arg("sshKnownHosts", optional_args[:ssh_known_hosts])
      |> maybe_put_arg("sshAuthSocket", optional_args[:ssh_auth_socket])

    %Dagger.Directory{
      selection: selection,
      client: git_ref.client
    }
  end
end