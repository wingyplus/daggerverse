# This file generated by `dagger_codegen`. Please DO NOT EDIT.
defmodule Dagger.Service do
  @moduledoc "A content-addressed service providing TCP connectivity."

  use Dagger.Core.QueryBuilder

  @derive Dagger.ID

  defstruct [:selection, :client]

  @type t() :: %__MODULE__{}

  @doc """
  Retrieves an endpoint that clients can use to reach this container.

  If no port is specified, the first exposed port is used. If none exist an error is returned.

  If a scheme is specified, a URL is returned. Otherwise, a host:port pair is returned.
  """
  @spec endpoint(t(), [{:port, integer() | nil}, {:scheme, String.t() | nil}]) ::
          {:ok, String.t()} | {:error, term()}
  def endpoint(%__MODULE__{} = service, optional_args \\ []) do
    selection =
      service.selection
      |> select("endpoint")
      |> maybe_put_arg("port", optional_args[:port])
      |> maybe_put_arg("scheme", optional_args[:scheme])

    execute(selection, service.client)
  end

  @doc "Retrieves a hostname which can be used by clients to reach this container."
  @spec hostname(t()) :: {:ok, String.t()} | {:error, term()}
  def hostname(%__MODULE__{} = service) do
    selection =
      service.selection |> select("hostname")

    execute(selection, service.client)
  end

  @doc "A unique identifier for this Service."
  @spec id(t()) :: {:ok, Dagger.ServiceID.t()} | {:error, term()}
  def id(%__MODULE__{} = service) do
    selection =
      service.selection |> select("id")

    execute(selection, service.client)
  end

  @doc "Retrieves the list of ports provided by the service."
  @spec ports(t()) :: {:ok, [Dagger.Port.t()]} | {:error, term()}
  def ports(%__MODULE__{} = service) do
    selection =
      service.selection |> select("ports") |> select("id")

    with {:ok, items} <- execute(selection, service.client) do
      {:ok,
       for %{"id" => id} <- items do
         %Dagger.Port{
           selection:
             query()
             |> select("loadPortFromID")
             |> arg("id", id),
           client: service.client
         }
       end}
    end
  end

  @doc """
  Start the service and wait for its health checks to succeed.

  Services bound to a Container do not need to be manually started.
  """
  @spec start(t()) :: {:ok, Dagger.ServiceID.t()} | {:error, term()}
  def start(%__MODULE__{} = service) do
    selection =
      service.selection |> select("start")

    execute(selection, service.client)
  end

  @doc "Stop the service."
  @spec stop(t(), [{:kill, boolean() | nil}]) :: {:ok, Dagger.ServiceID.t()} | {:error, term()}
  def stop(%__MODULE__{} = service, optional_args \\ []) do
    selection =
      service.selection |> select("stop") |> maybe_put_arg("kill", optional_args[:kill])

    execute(selection, service.client)
  end

  @doc "Creates a tunnel that forwards traffic from the caller's network to this service."
  @spec up(t(), [{:ports, [Dagger.PortForward.t()]}, {:random, boolean() | nil}]) ::
          {:ok, Dagger.Void.t() | nil} | {:error, term()}
  def up(%__MODULE__{} = service, optional_args \\ []) do
    selection =
      service.selection
      |> select("up")
      |> maybe_put_arg("ports", optional_args[:ports])
      |> maybe_put_arg("random", optional_args[:random])

    execute(selection, service.client)
  end
end