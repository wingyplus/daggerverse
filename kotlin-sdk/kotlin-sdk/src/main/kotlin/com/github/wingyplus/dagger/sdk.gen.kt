package com.github.wingyplus.dagger

import kotlin.String
import kotlinx.serialization.Serializable

/**
 * Key value object that represents a build argument.
 */
@Serializable
public data class BuildArg(
  public val name: String,
  public val `value`: String,
)

/**
 * Sharing mode of the cache volume.
 */
@Serializable
public enum class CacheSharingMode(
  `value`: String,
) {
  SHARED("SHARED"),
  PRIVATE("PRIVATE"),
  LOCKED("LOCKED"),
}

public class CacheVolume

/**
 * The `CacheVolumeID` scalar type represents an identifier for an object of type CacheVolume.
 */
public typealias CacheVolumeID = String

public class Client

public class Container

/**
 * The `ContainerID` scalar type represents an identifier for an object of type Container.
 */
public typealias ContainerID = String

public class CurrentModule

/**
 * The `CurrentModuleID` scalar type represents an identifier for an object of type CurrentModule.
 */
public typealias CurrentModuleID = String

public class Directory

/**
 * The `DirectoryID` scalar type represents an identifier for an object of type Directory.
 */
public typealias DirectoryID = String

public class EnvVariable

/**
 * The `EnvVariableID` scalar type represents an identifier for an object of type EnvVariable.
 */
public typealias EnvVariableID = String

public class FieldTypeDef

/**
 * The `FieldTypeDefID` scalar type represents an identifier for an object of type FieldTypeDef.
 */
public typealias FieldTypeDefID = String

public class File

/**
 * The `FileID` scalar type represents an identifier for an object of type File.
 */
public typealias FileID = String

public class Function

public class FunctionArg

/**
 * The `FunctionArgID` scalar type represents an identifier for an object of type FunctionArg.
 */
public typealias FunctionArgID = String

public class FunctionCall

public class FunctionCallArgValue

/**
 * The `FunctionCallArgValueID` scalar type represents an identifier for an object of type
 * FunctionCallArgValue.
 */
public typealias FunctionCallArgValueID = String

/**
 * The `FunctionCallID` scalar type represents an identifier for an object of type FunctionCall.
 */
public typealias FunctionCallID = String

/**
 * The `FunctionID` scalar type represents an identifier for an object of type Function.
 */
public typealias FunctionID = String

public class GeneratedCode

/**
 * The `GeneratedCodeID` scalar type represents an identifier for an object of type GeneratedCode.
 */
public typealias GeneratedCodeID = String

public class GitModuleSource

/**
 * The `GitModuleSourceID` scalar type represents an identifier for an object of type
 * GitModuleSource.
 */
public typealias GitModuleSourceID = String

public class GitRef

/**
 * The `GitRefID` scalar type represents an identifier for an object of type GitRef.
 */
public typealias GitRefID = String

public class GitRepository

/**
 * The `GitRepositoryID` scalar type represents an identifier for an object of type GitRepository.
 */
public typealias GitRepositoryID = String

public class Host

/**
 * The `HostID` scalar type represents an identifier for an object of type Host.
 */
public typealias HostID = String

/**
 * Compression algorithm to use for image layers.
 */
@Serializable
public enum class ImageLayerCompression(
  `value`: String,
) {
  Gzip("Gzip"),
  Zstd("Zstd"),
  EStarGZ("EStarGZ"),
  Uncompressed("Uncompressed"),
}

/**
 * Mediatypes to use in published or exported image metadata.
 */
@Serializable
public enum class ImageMediaTypes(
  `value`: String,
) {
  OCIMediaTypes("OCIMediaTypes"),
  DockerMediaTypes("DockerMediaTypes"),
}

public class InputTypeDef

/**
 * The `InputTypeDefID` scalar type represents an identifier for an object of type InputTypeDef.
 */
public typealias InputTypeDefID = String

public class InterfaceTypeDef

/**
 * The `InterfaceTypeDefID` scalar type represents an identifier for an object of type
 * InterfaceTypeDef.
 */
public typealias InterfaceTypeDefID = String

/**
 * An arbitrary JSON-encoded value.
 */
public typealias JSON = String

public class Label

/**
 * The `LabelID` scalar type represents an identifier for an object of type Label.
 */
public typealias LabelID = String

public class ListTypeDef

/**
 * The `ListTypeDefID` scalar type represents an identifier for an object of type ListTypeDef.
 */
public typealias ListTypeDefID = String

public class LocalModuleSource

/**
 * The `LocalModuleSourceID` scalar type represents an identifier for an object of type
 * LocalModuleSource.
 */
public typealias LocalModuleSourceID = String

public class Module

public class ModuleDependency

/**
 * The `ModuleDependencyID` scalar type represents an identifier for an object of type
 * ModuleDependency.
 */
public typealias ModuleDependencyID = String

/**
 * The `ModuleID` scalar type represents an identifier for an object of type Module.
 */
public typealias ModuleID = String

public class ModuleSource

/**
 * The `ModuleSourceID` scalar type represents an identifier for an object of type ModuleSource.
 */
public typealias ModuleSourceID = String

/**
 * The kind of module source.
 */
@Serializable
public enum class ModuleSourceKind(
  `value`: String,
) {
  LOCAL_SOURCE("LOCAL_SOURCE"),
  GIT_SOURCE("GIT_SOURCE"),
}

public class ModuleSourceView

/**
 * The `ModuleSourceViewID` scalar type represents an identifier for an object of type
 * ModuleSourceView.
 */
public typealias ModuleSourceViewID = String

/**
 * Transport layer network protocol associated to a port.
 */
@Serializable
public enum class NetworkProtocol(
  `value`: String,
) {
  TCP("TCP"),
  UDP("UDP"),
}

public class ObjectTypeDef

/**
 * The `ObjectTypeDefID` scalar type represents an identifier for an object of type ObjectTypeDef.
 */
public typealias ObjectTypeDefID = String

/**
 * Key value object that represents a pipeline label.
 */
@Serializable
public data class PipelineLabel(
  public val name: String,
  public val `value`: String,
)

/**
 * The platform config OS and architecture in a Container.
 *
 * The format is [os]/[platform]/[version] (e.g., "darwin/arm64/v7", "windows/amd64",
 * "linux/arm64").
 */
public typealias Platform = String

public class Port

/**
 * Port forwarding rules for tunneling network traffic.
 */
@Serializable
public data class PortForward(
  public val backend: Int,
  public val frontend: Int?,
  public val protocol: NetworkProtocol?,
)

/**
 * The `PortID` scalar type represents an identifier for an object of type Port.
 */
public typealias PortID = String

public class Secret

/**
 * The `SecretID` scalar type represents an identifier for an object of type Secret.
 */
public typealias SecretID = String

public class Service

/**
 * The `ServiceID` scalar type represents an identifier for an object of type Service.
 */
public typealias ServiceID = String

public class Socket

/**
 * The `SocketID` scalar type represents an identifier for an object of type Socket.
 */
public typealias SocketID = String

public class Terminal

/**
 * The `TerminalID` scalar type represents an identifier for an object of type Terminal.
 */
public typealias TerminalID = String

public class TypeDef

/**
 * The `TypeDefID` scalar type represents an identifier for an object of type TypeDef.
 */
public typealias TypeDefID = String

/**
 * Distinguishes the different kinds of TypeDefs.
 */
@Serializable
public enum class TypeDefKind(
  `value`: String,
) {
  STRING_KIND("STRING_KIND"),
  INTEGER_KIND("INTEGER_KIND"),
  BOOLEAN_KIND("BOOLEAN_KIND"),
  LIST_KIND("LIST_KIND"),
  OBJECT_KIND("OBJECT_KIND"),
  INTERFACE_KIND("INTERFACE_KIND"),
  INPUT_KIND("INPUT_KIND"),
  VOID_KIND("VOID_KIND"),
}

/**
 * The absence of a value.
 *
 * A Null Void is used as a placeholder for resolvers that do not return anything.
 */
public typealias Void = String
