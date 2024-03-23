# This file generated by `dagger_codegen`. Please DO NOT EDIT.
defmodule Dagger.Host do
  @moduledoc "Information about the host environment."

  use Dagger.Core.QueryBuilder

  @derive Dagger.ID

  defstruct [:selection, :client]

  @type t() :: %__MODULE__{}

  @doc "Accesses a directory on the host."
  @spec directory(t(), String.t(), [{:exclude, [String.t()]}, {:include, [String.t()]}]) ::
          Dagger.Directory.t()
  def directory(%__MODULE__{} = host, path, optional_args \\ []) do
    selection =
      host.selection
      |> select("directory")
      |> put_arg("path", path)
      |> maybe_put_arg("exclude", optional_args[:exclude])
      |> maybe_put_arg("include", optional_args[:include])

    %Dagger.Directory{
      selection: selection,
      client: host.client
    }
  end

  @doc "Accesses a file on the host."
  @spec file(t(), String.t()) :: Dagger.File.t()
  def file(%__MODULE__{} = host, path) do
    selection =
      host.selection |> select("file") |> put_arg("path", path)

    %Dagger.File{
      selection: selection,
      client: host.client
    }
  end

  @doc "A unique identifier for this Host."
  @spec id(t()) :: {:ok, Dagger.HostID.t()} | {:error, term()}
  def id(%__MODULE__{} = host) do
    selection =
      host.selection |> select("id")

    execute(selection, host.client)
  end

  @doc "Creates a service that forwards traffic to a specified address via the host."
  @spec service(t(), [Dagger.PortForward.t()], [{:host, String.t() | nil}]) :: Dagger.Service.t()
  def service(%__MODULE__{} = host, ports, optional_args \\ []) do
    selection =
      host.selection
      |> select("service")
      |> put_arg("ports", ports)
      |> maybe_put_arg("host", optional_args[:host])

    %Dagger.Service{
      selection: selection,
      client: host.client
    }
  end

  @doc """
  Sets a secret given a user-defined name and the file path on the host, and returns the secret.

  The file is limited to a size of 512000 bytes.
  """
  @spec set_secret_file(t(), String.t(), String.t()) :: Dagger.Secret.t()
  def set_secret_file(%__MODULE__{} = host, name, path) do
    selection =
      host.selection |> select("setSecretFile") |> put_arg("name", name) |> put_arg("path", path)

    %Dagger.Secret{
      selection: selection,
      client: host.client
    }
  end

  @doc "Creates a tunnel that forwards traffic from the host to a service."
  @spec tunnel(t(), Dagger.Service.t(), [
          {:ports, [Dagger.PortForward.t()]},
          {:native, boolean() | nil}
        ]) :: Dagger.Service.t()
  def tunnel(%__MODULE__{} = host, service, optional_args \\ []) do
    selection =
      host.selection
      |> select("tunnel")
      |> put_arg("service", Dagger.ID.id!(service))
      |> maybe_put_arg("ports", optional_args[:ports])
      |> maybe_put_arg("native", optional_args[:native])

    %Dagger.Service{
      selection: selection,
      client: host.client
    }
  end

  @doc "Accesses a Unix socket on the host."
  @spec unix_socket(t(), String.t()) :: Dagger.Socket.t()
  def unix_socket(%__MODULE__{} = host, path) do
    selection =
      host.selection |> select("unixSocket") |> put_arg("path", path)

    %Dagger.Socket{
      selection: selection,
      client: host.client
    }
  end
end