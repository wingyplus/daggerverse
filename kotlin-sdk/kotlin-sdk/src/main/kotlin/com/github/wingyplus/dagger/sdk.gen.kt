package com.github.wingyplus.dagger
//
//import kotlin.Array
//import kotlin.Boolean
//import kotlin.Int
//import kotlin.String
//import kotlinx.serialization.Serializable
//
///**
// * Key value object that represents a build argument.
// */
//@Serializable
//public data class BuildArg(
//  public val name: String,
//  public val `value`: String,
//)
//
///**
// * Sharing mode of the cache volume.
// */
//@Serializable
//public enum class CacheSharingMode(
//  `value`: String,
//) {
//  SHARED("SHARED"),
//  PRIVATE("PRIVATE"),
//  LOCKED("LOCKED"),
//}
//
///**
// * A directory whose contents persist across runs.
// */
//public class CacheVolume {
//  /**
//   * A unique identifier for this CacheVolume.
//   */
//  public fun id(): CacheVolumeID {
//  }
//}
//
///**
// * The `CacheVolumeID` scalar type represents an identifier for an object of type CacheVolume.
// */
//@Serializable
//public typealias CacheVolumeID = String
//
///**
// * The root of the DAG.
// */
//public class Client {
//  /**
//   * Retrieves a content-addressed blob.
//   */
//  public fun blob(): Directory {
//  }
//
//  /**
//   * Retrieves a container builtin to the engine.
//   */
//  public fun builtinContainer(): Container {
//  }
//
//  /**
//   * Constructs a cache volume for a given cache key.
//   */
//  public fun cacheVolume(): CacheVolume {
//  }
//
//  /**
//   * Checks if the current Dagger Engine is compatible with an SDK's required version.
//   */
//  public fun checkVersionCompatibility(): Boolean {
//  }
//
//  public fun codegen(): Codegen {
//  }
//
//  /**
//   * Creates a scratch container.
//   *
//   * Optional platform argument initializes new containers to execute and publish as that platform.
//   * Platform defaults to that of the builder's host.
//   */
//  public fun container(): Container {
//  }
//
//  /**
//   * The FunctionCall context that the SDK caller is currently executing in.
//   *
//   * If the caller is not currently executing in a function, this will return an error.
//   */
//  public fun currentFunctionCall(): FunctionCall {
//  }
//
//  /**
//   * The module currently being served in the session, if any.
//   */
//  public fun currentModule(): CurrentModule {
//  }
//
//  /**
//   * The TypeDef representations of the objects currently being served in the session.
//   */
//  public fun currentTypeDefs(): Array<TypeDef> {
//  }
//
//  /**
//   * The default platform of the engine.
//   */
//  public fun defaultPlatform(): Platform {
//  }
//
//  /**
//   * Creates an empty directory.
//   */
//  public fun directory(): Directory {
//  }
//
//  public fun `file`(): File {
//  }
//
//  /**
//   * Creates a function.
//   */
//  public fun function(): Function {
//  }
//
//  /**
//   * Create a code generation result, given a directory containing the generated code.
//   */
//  public fun generatedCode(): GeneratedCode {
//  }
//
//  /**
//   * Queries a Git repository.
//   */
//  public fun git(): GitRepository {
//  }
//
//  /**
//   * Queries the host environment.
//   */
//  public fun host(): Host {
//  }
//
//  /**
//   * Returns a file containing an http remote url content.
//   */
//  public fun http(): File {
//  }
//
//  /**
//   * Load a CacheVolume from its ID.
//   */
//  public fun loadCacheVolumeFromID(): CacheVolume {
//  }
//
//  /**
//   * Load a CodegenEnumValue from its ID.
//   */
//  public fun loadCodegenEnumValueFromID(): CodegenEnumValue {
//  }
//
//  /**
//   * Load a CodegenExperimental from its ID.
//   */
//  public fun loadCodegenExperimentalFromID(): CodegenExperimental {
//  }
//
//  /**
//   * Load a CodegenField from its ID.
//   */
//  public fun loadCodegenFieldFromID(): CodegenField {
//  }
//
//  /**
//   * Load a Codegen from its ID.
//   */
//  public fun loadCodegenFromID(): Codegen {
//  }
//
//  /**
//   * Load a CodegenInputValue from its ID.
//   */
//  public fun loadCodegenInputValueFromID(): CodegenInputValue {
//  }
//
//  /**
//   * Load a CodegenSchema from its ID.
//   */
//  public fun loadCodegenSchemaFromID(): CodegenSchema {
//  }
//
//  /**
//   * Load a CodegenType from its ID.
//   */
//  public fun loadCodegenTypeFromID(): CodegenType {
//  }
//
//  /**
//   * Load a CodegenTypeRef from its ID.
//   */
//  public fun loadCodegenTypeRefFromID(): CodegenTypeRef {
//  }
//
//  /**
//   * Load a Container from its ID.
//   */
//  public fun loadContainerFromID(): Container {
//  }
//
//  /**
//   * Load a CurrentModule from its ID.
//   */
//  public fun loadCurrentModuleFromID(): CurrentModule {
//  }
//
//  /**
//   * Load a Directory from its ID.
//   */
//  public fun loadDirectoryFromID(): Directory {
//  }
//
//  /**
//   * Load a EnvVariable from its ID.
//   */
//  public fun loadEnvVariableFromID(): EnvVariable {
//  }
//
//  /**
//   * Load a FieldTypeDef from its ID.
//   */
//  public fun loadFieldTypeDefFromID(): FieldTypeDef {
//  }
//
//  /**
//   * Load a File from its ID.
//   */
//  public fun loadFileFromID(): File {
//  }
//
//  /**
//   * Load a FunctionArg from its ID.
//   */
//  public fun loadFunctionArgFromID(): FunctionArg {
//  }
//
//  /**
//   * Load a FunctionCallArgValue from its ID.
//   */
//  public fun loadFunctionCallArgValueFromID(): FunctionCallArgValue {
//  }
//
//  /**
//   * Load a FunctionCall from its ID.
//   */
//  public fun loadFunctionCallFromID(): FunctionCall {
//  }
//
//  /**
//   * Load a Function from its ID.
//   */
//  public fun loadFunctionFromID(): Function {
//  }
//
//  /**
//   * Load a GeneratedCode from its ID.
//   */
//  public fun loadGeneratedCodeFromID(): GeneratedCode {
//  }
//
//  /**
//   * Load a GitModuleSource from its ID.
//   */
//  public fun loadGitModuleSourceFromID(): GitModuleSource {
//  }
//
//  /**
//   * Load a GitRef from its ID.
//   */
//  public fun loadGitRefFromID(): GitRef {
//  }
//
//  /**
//   * Load a GitRepository from its ID.
//   */
//  public fun loadGitRepositoryFromID(): GitRepository {
//  }
//
//  /**
//   * Load a Host from its ID.
//   */
//  public fun loadHostFromID(): Host {
//  }
//
//  /**
//   * Load a InputTypeDef from its ID.
//   */
//  public fun loadInputTypeDefFromID(): InputTypeDef {
//  }
//
//  /**
//   * Load a InterfaceTypeDef from its ID.
//   */
//  public fun loadInterfaceTypeDefFromID(): InterfaceTypeDef {
//  }
//
//  /**
//   * Load a Label from its ID.
//   */
//  public fun loadLabelFromID(): Label {
//  }
//
//  /**
//   * Load a ListTypeDef from its ID.
//   */
//  public fun loadListTypeDefFromID(): ListTypeDef {
//  }
//
//  /**
//   * Load a LocalModuleSource from its ID.
//   */
//  public fun loadLocalModuleSourceFromID(): LocalModuleSource {
//  }
//
//  /**
//   * Load a ModuleDependency from its ID.
//   */
//  public fun loadModuleDependencyFromID(): ModuleDependency {
//  }
//
//  /**
//   * Load a Module from its ID.
//   */
//  public fun loadModuleFromID(): Module {
//  }
//
//  /**
//   * Load a ModuleSource from its ID.
//   */
//  public fun loadModuleSourceFromID(): ModuleSource {
//  }
//
//  /**
//   * Load a ModuleSourceView from its ID.
//   */
//  public fun loadModuleSourceViewFromID(): ModuleSourceView {
//  }
//
//  /**
//   * Load a ObjectTypeDef from its ID.
//   */
//  public fun loadObjectTypeDefFromID(): ObjectTypeDef {
//  }
//
//  /**
//   * Load a Port from its ID.
//   */
//  public fun loadPortFromID(): Port {
//  }
//
//  /**
//   * Load a Secret from its ID.
//   */
//  public fun loadSecretFromID(): Secret {
//  }
//
//  /**
//   * Load a Service from its ID.
//   */
//  public fun loadServiceFromID(): Service {
//  }
//
//  /**
//   * Load a Socket from its ID.
//   */
//  public fun loadSocketFromID(): Socket {
//  }
//
//  /**
//   * Load a Terminal from its ID.
//   */
//  public fun loadTerminalFromID(): Terminal {
//  }
//
//  /**
//   * Load a TypeDef from its ID.
//   */
//  public fun loadTypeDefFromID(): TypeDef {
//  }
//
//  /**
//   * Create a new module.
//   */
//  public fun module(): Module {
//  }
//
//  /**
//   * Create a new module dependency configuration from a module source and name
//   */
//  public fun moduleDependency(): ModuleDependency {
//  }
//
//  /**
//   * Create a new module source instance from a source ref string.
//   */
//  public fun moduleSource(): ModuleSource {
//  }
//
//  /**
//   * Creates a named sub-pipeline.
//   */
//  public fun pipeline(): Query {
//  }
//
//  /**
//   * Reference a secret by name.
//   */
//  public fun secret(): Secret {
//  }
//
//  /**
//   * Sets a secret given a user defined name to its plaintext and returns the secret.
//   *
//   * The plaintext value is limited to a size of 128000 bytes.
//   */
//  public fun setSecret(): Secret {
//  }
//
//  /**
//   * Loads a socket by its ID.
//   */
//  public fun socket(): Socket {
//  }
//
//  /**
//   * Create a new TypeDef.
//   */
//  public fun typeDef(): TypeDef {
//  }
//}
//
///**
// * An OCI-compatible container, also known as a Docker container.
// */
//public class Container {
//  /**
//   * Turn the container into a Service.
//   *
//   * Be sure to set any exposed ports before this conversion.
//   */
//  public fun asService(): Service {
//  }
//
//  /**
//   * Returns a File representing the container serialized to a tarball.
//   */
//  public fun asTarball(): File {
//  }
//
//  /**
//   * Initializes this container from a Dockerfile build.
//   */
//  public fun build(): Container {
//  }
//
//  /**
//   * Retrieves default arguments for future commands.
//   */
//  public fun defaultArgs(): Array<String> {
//  }
//
//  /**
//   * Retrieves a directory at the given path.
//   *
//   * Mounts are included.
//   */
//  public fun directory(): Directory {
//  }
//
//  /**
//   * Retrieves entrypoint to be prepended to the arguments of all commands.
//   */
//  public fun entrypoint(): Array<String> {
//  }
//
//  /**
//   * Retrieves the value of the specified environment variable.
//   */
//  public fun envVariable(): String? {
//  }
//
//  /**
//   * Retrieves the list of environment variables passed to commands.
//   */
//  public fun envVariables(): Array<EnvVariable> {
//  }
//
//  /**
//   * EXPERIMENTAL API! Subject to change/removal at any time.
//   *
//   * Configures all available GPUs on the host to be accessible to this container.
//   *
//   * This currently works for Nvidia devices only.
//   */
//  public fun experimentalWithAllGPUs(): Container {
//  }
//
//  /**
//   * EXPERIMENTAL API! Subject to change/removal at any time.
//   *
//   * Configures the provided list of devices to be accessible to this container.
//   *
//   * This currently works for Nvidia devices only.
//   */
//  public fun experimentalWithGPU(): Container {
//  }
//
//  /**
//   * Writes the container as an OCI tarball to the destination file path on the host.
//   *
//   * Return true on success.
//   *
//   * It can also export platform variants.
//   */
//  public fun export(): Boolean {
//  }
//
//  /**
//   * Retrieves the list of exposed ports.
//   *
//   * This includes ports already exposed by the image, even if not explicitly added with dagger.
//   */
//  public fun exposedPorts(): Array<Port> {
//  }
//
//  /**
//   * Retrieves a file at the given path.
//   *
//   * Mounts are included.
//   */
//  public fun `file`(): File {
//  }
//
//  /**
//   * Initializes this container from a pulled base image.
//   */
//  public fun from(): Container {
//  }
//
//  /**
//   * A unique identifier for this Container.
//   */
//  public fun id(): ContainerID {
//  }
//
//  /**
//   * The unique image reference which can only be retrieved immediately after the 'Container.From'
//   * call.
//   */
//  public fun imageRef(): String {
//  }
//
//  /**
//   * Reads the container from an OCI tarball.
//   */
//  public fun `import`(): Container {
//  }
//
//  /**
//   * Retrieves the value of the specified label.
//   */
//  public fun label(): String? {
//  }
//
//  /**
//   * Retrieves the list of labels passed to container.
//   */
//  public fun labels(): Array<Label> {
//  }
//
//  /**
//   * Retrieves the list of paths where a directory is mounted.
//   */
//  public fun mounts(): Array<String> {
//  }
//
//  /**
//   * Creates a named sub-pipeline.
//   */
//  public fun pipeline(): Container {
//  }
//
//  /**
//   * The platform this container executes and publishes as.
//   */
//  public fun platform(): Platform {
//  }
//
//  /**
//   * Publishes this container as a new image to the specified address.
//   *
//   * Publish returns a fully qualified ref.
//   *
//   * It can also publish platform variants.
//   */
//  public fun publish(): String {
//  }
//
//  /**
//   * Retrieves this container's root filesystem. Mounts are not included.
//   */
//  public fun rootfs(): Directory {
//  }
//
//  /**
//   * The error stream of the last executed command.
//   *
//   * Will execute default command if none is set, or error if there's no default.
//   */
//  public fun stderr(): String {
//  }
//
//  /**
//   * The output stream of the last executed command.
//   *
//   * Will execute default command if none is set, or error if there's no default.
//   */
//  public fun stdout(): String {
//  }
//
//  /**
//   * Forces evaluation of the pipeline in the engine.
//   *
//   * It doesn't run the default command if no exec has been set.
//   */
//  public fun sync(): ContainerID {
//  }
//
//  /**
//   * Return an interactive terminal for this container using its configured default terminal command
//   * if not overridden by args (or sh as a fallback default).
//   */
//  public fun terminal(): Terminal {
//  }
//
//  /**
//   * Retrieves the user to be set for all commands.
//   */
//  public fun user(): String {
//  }
//
//  /**
//   * Configures default arguments for future commands.
//   */
//  public fun withDefaultArgs(): Container {
//  }
//
//  /**
//   * Set the default command to invoke for the container's terminal API.
//   */
//  public fun withDefaultTerminalCmd(): Container {
//  }
//
//  /**
//   * Retrieves this container plus a directory written at the given path.
//   */
//  public fun withDirectory(): Container {
//  }
//
//  /**
//   * Retrieves this container but with a different command entrypoint.
//   */
//  public fun withEntrypoint(): Container {
//  }
//
//  /**
//   * Retrieves this container plus the given environment variable.
//   */
//  public fun withEnvVariable(): Container {
//  }
//
//  /**
//   * Retrieves this container after executing the specified command inside it.
//   */
//  public fun withExec(): Container {
//  }
//
//  /**
//   * Expose a network port.
//   *
//   * Exposed ports serve two purposes:
//   *
//   * - For health checks and introspection, when running services
//   *
//   * - For setting the EXPOSE OCI field when publishing the container
//   */
//  public fun withExposedPort(): Container {
//  }
//
//  /**
//   * Retrieves this container plus the contents of the given file copied to the given path.
//   */
//  public fun withFile(): Container {
//  }
//
//  /**
//   * Retrieves this container plus the contents of the given files copied to the given path.
//   */
//  public fun withFiles(): Container {
//  }
//
//  /**
//   * Indicate that subsequent operations should be featured more prominently in the UI.
//   */
//  public fun withFocus(): Container {
//  }
//
//  /**
//   * Retrieves this container plus the given label.
//   */
//  public fun withLabel(): Container {
//  }
//
//  /**
//   * Retrieves this container plus a cache volume mounted at the given path.
//   */
//  public fun withMountedCache(): Container {
//  }
//
//  /**
//   * Retrieves this container plus a directory mounted at the given path.
//   */
//  public fun withMountedDirectory(): Container {
//  }
//
//  /**
//   * Retrieves this container plus a file mounted at the given path.
//   */
//  public fun withMountedFile(): Container {
//  }
//
//  /**
//   * Retrieves this container plus a secret mounted into a file at the given path.
//   */
//  public fun withMountedSecret(): Container {
//  }
//
//  /**
//   * Retrieves this container plus a temporary directory mounted at the given path.
//   */
//  public fun withMountedTemp(): Container {
//  }
//
//  /**
//   * Retrieves this container plus a new file written at the given path.
//   */
//  public fun withNewFile(): Container {
//  }
//
//  /**
//   * Retrieves this container with a registry authentication for a given address.
//   */
//  public fun withRegistryAuth(): Container {
//  }
//
//  /**
//   * Retrieves the container with the given directory mounted to /.
//   */
//  public fun withRootfs(): Container {
//  }
//
//  /**
//   * Retrieves this container plus an env variable containing the given secret.
//   */
//  public fun withSecretVariable(): Container {
//  }
//
//  /**
//   * Establish a runtime dependency on a service.
//   *
//   * The service will be started automatically when needed and detached when it is no longer needed,
//   * executing the default command if none is set.
//   *
//   * The service will be reachable from the container via the provided hostname alias.
//   *
//   * The service dependency will also convey to any files or directories produced by the container.
//   */
//  public fun withServiceBinding(): Container {
//  }
//
//  /**
//   * Retrieves this container plus a socket forwarded to the given Unix socket path.
//   */
//  public fun withUnixSocket(): Container {
//  }
//
//  /**
//   * Retrieves this container with a different command user.
//   */
//  public fun withUser(): Container {
//  }
//
//  /**
//   * Retrieves this container with a different working directory.
//   */
//  public fun withWorkdir(): Container {
//  }
//
//  /**
//   * Retrieves this container with unset default arguments for future commands.
//   */
//  public fun withoutDefaultArgs(): Container {
//  }
//
//  /**
//   * Retrieves this container with an unset command entrypoint.
//   */
//  public fun withoutEntrypoint(): Container {
//  }
//
//  /**
//   * Retrieves this container minus the given environment variable.
//   */
//  public fun withoutEnvVariable(): Container {
//  }
//
//  /**
//   * Unexpose a previously exposed port.
//   */
//  public fun withoutExposedPort(): Container {
//  }
//
//  /**
//   * Indicate that subsequent operations should not be featured more prominently in the UI.
//   *
//   * This is the initial state of all containers.
//   */
//  public fun withoutFocus(): Container {
//  }
//
//  /**
//   * Retrieves this container minus the given environment label.
//   */
//  public fun withoutLabel(): Container {
//  }
//
//  /**
//   * Retrieves this container after unmounting everything at the given path.
//   */
//  public fun withoutMount(): Container {
//  }
//
//  /**
//   * Retrieves this container without the registry authentication of a given address.
//   */
//  public fun withoutRegistryAuth(): Container {
//  }
//
//  /**
//   * Retrieves this container with a previously added Unix socket removed.
//   */
//  public fun withoutUnixSocket(): Container {
//  }
//
//  /**
//   * Retrieves this container with an unset command user.
//   *
//   * Should default to root.
//   */
//  public fun withoutUser(): Container {
//  }
//
//  /**
//   * Retrieves this container with an unset working directory.
//   *
//   * Should default to "/".
//   */
//  public fun withoutWorkdir(): Container {
//  }
//
//  /**
//   * Retrieves the working directory for all commands.
//   */
//  public fun workdir(): String {
//  }
//}
//
///**
// * The `ContainerID` scalar type represents an identifier for an object of type Container.
// */
//@Serializable
//public typealias ContainerID = String
//
///**
// * Reflective module API provided to functions at runtime.
// */
//public class CurrentModule {
//  /**
//   * A unique identifier for this CurrentModule.
//   */
//  public fun id(): CurrentModuleID {
//  }
//
//  /**
//   * The name of the module being executed in
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * The directory containing the module's source code loaded into the engine (plus any generated
//   * code that may have been created).
//   */
//  public fun source(): Directory {
//  }
//
//  /**
//   * Load a directory from the module's scratch working directory, including any changes that may
//   * have been made to it during module function execution.
//   */
//  public fun workdir(): Directory {
//  }
//
//  /**
//   * Load a file from the module's scratch working directory, including any changes that may have
//   * been made to it during module function execution.Load a file from the module's scratch working
//   * directory, including any changes that may have been made to it during module function execution.
//   */
//  public fun workdirFile(): File {
//  }
//}
//
///**
// * The `CurrentModuleID` scalar type represents an identifier for an object of type CurrentModule.
// */
//@Serializable
//public typealias CurrentModuleID = String
//
///**
// * A directory.
// */
//public class Directory {
//  /**
//   * Load the directory as a Dagger module
//   */
//  public fun asModule(): Module {
//  }
//
//  /**
//   * Gets the difference between this directory and an another directory.
//   */
//  public fun diff(): Directory {
//  }
//
//  /**
//   * Retrieves a directory at the given path.
//   */
//  public fun directory(): Directory {
//  }
//
//  /**
//   * Builds a new Docker container from this directory.
//   */
//  public fun dockerBuild(): Container {
//  }
//
//  /**
//   * Returns a list of files and directories at the given path.
//   */
//  public fun entries(): Array<String> {
//  }
//
//  /**
//   * Writes the contents of the directory to a path on the host.
//   */
//  public fun export(): Boolean {
//  }
//
//  /**
//   * Retrieves a file at the given path.
//   */
//  public fun `file`(): File {
//  }
//
//  /**
//   * Returns a list of files and directories that matche the given pattern.
//   */
//  public fun glob(): Array<String> {
//  }
//
//  /**
//   * A unique identifier for this Directory.
//   */
//  public fun id(): DirectoryID {
//  }
//
//  /**
//   * Creates a named sub-pipeline.
//   */
//  public fun pipeline(): Directory {
//  }
//
//  /**
//   * Force evaluation in the engine.
//   */
//  public fun sync(): DirectoryID {
//  }
//
//  /**
//   * Retrieves this directory plus a directory written at the given path.
//   */
//  public fun withDirectory(): Directory {
//  }
//
//  /**
//   * Retrieves this directory plus the contents of the given file copied to the given path.
//   */
//  public fun withFile(): Directory {
//  }
//
//  /**
//   * Retrieves this directory plus the contents of the given files copied to the given path.
//   */
//  public fun withFiles(): Directory {
//  }
//
//  /**
//   * Retrieves this directory plus a new directory created at the given path.
//   */
//  public fun withNewDirectory(): Directory {
//  }
//
//  /**
//   * Retrieves this directory plus a new file written at the given path.
//   */
//  public fun withNewFile(): Directory {
//  }
//
//  /**
//   * Retrieves this directory with all file/dir timestamps set to the given time.
//   */
//  public fun withTimestamps(): Directory {
//  }
//
//  /**
//   * Retrieves this directory with the directory at the given path removed.
//   */
//  public fun withoutDirectory(): Directory {
//  }
//
//  /**
//   * Retrieves this directory with the file at the given path removed.
//   */
//  public fun withoutFile(): Directory {
//  }
//}
//
///**
// * The `DirectoryID` scalar type represents an identifier for an object of type Directory.
// */
//@Serializable
//public typealias DirectoryID = String
//
///**
// * An environment variable name and value.
// */
//public class EnvVariable {
//  /**
//   * A unique identifier for this EnvVariable.
//   */
//  public fun id(): EnvVariableID {
//  }
//
//  /**
//   * The environment variable name.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * The environment variable value.
//   */
//  public fun `value`(): String {
//  }
//}
//
///**
// * The `EnvVariableID` scalar type represents an identifier for an object of type EnvVariable.
// */
//@Serializable
//public typealias EnvVariableID = String
//
///**
// * A definition of a field on a custom object defined in a Module.
// *
// * A field on an object has a static value, as opposed to a function on an object whose value is
// * computed by invoking code (and can accept arguments).
// */
//public class FieldTypeDef {
//  /**
//   * A doc string for the field, if any.
//   */
//  public fun description(): String {
//  }
//
//  /**
//   * A unique identifier for this FieldTypeDef.
//   */
//  public fun id(): FieldTypeDefID {
//  }
//
//  /**
//   * The name of the field in lowerCamelCase format.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * The type of the field.
//   */
//  public fun typeDef(): TypeDef {
//  }
//}
//
///**
// * The `FieldTypeDefID` scalar type represents an identifier for an object of type FieldTypeDef.
// */
//@Serializable
//public typealias FieldTypeDefID = String
//
///**
// * A file.
// */
//public class File {
//  /**
//   * Retrieves the contents of the file.
//   */
//  public fun contents(): String {
//  }
//
//  /**
//   * Writes the file to a file path on the host.
//   */
//  public fun export(): Boolean {
//  }
//
//  /**
//   * A unique identifier for this File.
//   */
//  public fun id(): FileID {
//  }
//
//  /**
//   * Retrieves the name of the file.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * Retrieves the size of the file, in bytes.
//   */
//  public fun size(): Int {
//  }
//
//  /**
//   * Force evaluation in the engine.
//   */
//  public fun sync(): FileID {
//  }
//
//  /**
//   * Retrieves this file with its created/modified timestamps set to the given time.
//   */
//  public fun withTimestamps(): File {
//  }
//}
//
///**
// * The `FileID` scalar type represents an identifier for an object of type File.
// */
//@Serializable
//public typealias FileID = String
//
///**
// * Function represents a resolver provided by a Module.
// *
// * A function always evaluates against a parent object and is given a set of named arguments.
// */
//public class Function {
//  /**
//   * Arguments accepted by the function, if any.
//   */
//  public fun args(): Array<FunctionArg> {
//  }
//
//  /**
//   * A doc string for the function, if any.
//   */
//  public fun description(): String {
//  }
//
//  /**
//   * A unique identifier for this Function.
//   */
//  public fun id(): FunctionID {
//  }
//
//  /**
//   * The name of the function.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * The type returned by the function.
//   */
//  public fun returnType(): TypeDef {
//  }
//
//  /**
//   * Returns the function with the provided argument
//   */
//  public fun withArg(): Function {
//  }
//
//  /**
//   * Returns the function with the given doc string.
//   */
//  public fun withDescription(): Function {
//  }
//}
//
///**
// * An argument accepted by a function.
// *
// * This is a specification for an argument at function definition time, not an argument passed at
// * function call time.
// */
//public class FunctionArg {
//  /**
//   * A default value to use for this argument when not explicitly set by the caller, if any.
//   */
//  public fun defaultValue(): JSON {
//  }
//
//  /**
//   * A doc string for the argument, if any.
//   */
//  public fun description(): String {
//  }
//
//  /**
//   * A unique identifier for this FunctionArg.
//   */
//  public fun id(): FunctionArgID {
//  }
//
//  /**
//   * The name of the argument in lowerCamelCase format.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * The type of the argument.
//   */
//  public fun typeDef(): TypeDef {
//  }
//}
//
///**
// * The `FunctionArgID` scalar type represents an identifier for an object of type FunctionArg.
// */
//@Serializable
//public typealias FunctionArgID = String
//
///**
// * An active function call.
// */
//public class FunctionCall {
//  /**
//   * A unique identifier for this FunctionCall.
//   */
//  public fun id(): FunctionCallID {
//  }
//
//  /**
//   * The argument values the function is being invoked with.
//   */
//  public fun inputArgs(): Array<FunctionCallArgValue> {
//  }
//
//  /**
//   * The name of the function being called.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * The value of the parent object of the function being called. If the function is top-level to
//   * the module, this is always an empty object.
//   */
//  public fun parent(): JSON {
//  }
//
//  /**
//   * The name of the parent object of the function being called. If the function is top-level to the
//   * module, this is the name of the module.
//   */
//  public fun parentName(): String {
//  }
//
//  /**
//   * Set the return value of the function call to the provided value.
//   */
//  public fun returnValue(): Void? {
//  }
//}
//
///**
// * A value passed as a named argument to a function call.
// */
//public class FunctionCallArgValue {
//  /**
//   * A unique identifier for this FunctionCallArgValue.
//   */
//  public fun id(): FunctionCallArgValueID {
//  }
//
//  /**
//   * The name of the argument.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * The value of the argument represented as a JSON serialized string.
//   */
//  public fun `value`(): JSON {
//  }
//}
//
///**
// * The `FunctionCallArgValueID` scalar type represents an identifier for an object of type
// * FunctionCallArgValue.
// */
//@Serializable
//public typealias FunctionCallArgValueID = String
//
///**
// * The `FunctionCallID` scalar type represents an identifier for an object of type FunctionCall.
// */
//@Serializable
//public typealias FunctionCallID = String
//
///**
// * The `FunctionID` scalar type represents an identifier for an object of type Function.
// */
//@Serializable
//public typealias FunctionID = String
//
///**
// * The result of running an SDK's codegen.
// */
//public class GeneratedCode {
//  /**
//   * The directory containing the generated code.
//   */
//  public fun code(): Directory {
//  }
//
//  /**
//   * A unique identifier for this GeneratedCode.
//   */
//  public fun id(): GeneratedCodeID {
//  }
//
//  /**
//   * List of paths to mark generated in version control (i.e. .gitattributes).
//   */
//  public fun vcsGeneratedPaths(): Array<String> {
//  }
//
//  /**
//   * List of paths to ignore in version control (i.e. .gitignore).
//   */
//  public fun vcsIgnoredPaths(): Array<String> {
//  }
//
//  /**
//   * Set the list of paths to mark generated in version control.
//   */
//  public fun withVCSGeneratedPaths(): GeneratedCode {
//  }
//
//  /**
//   * Set the list of paths to ignore in version control.
//   */
//  public fun withVCSIgnoredPaths(): GeneratedCode {
//  }
//}
//
///**
// * The `GeneratedCodeID` scalar type represents an identifier for an object of type GeneratedCode.
// */
//@Serializable
//public typealias GeneratedCodeID = String
//
///**
// * Module source originating from a git repo.
// */
//public class GitModuleSource {
//  /**
//   * The URL from which the source's git repo can be cloned.
//   */
//  public fun cloneURL(): String {
//  }
//
//  /**
//   * The resolved commit of the git repo this source points to.
//   */
//  public fun commit(): String {
//  }
//
//  /**
//   * The directory containing everything needed to load load and use the module.
//   */
//  public fun contextDirectory(): Directory {
//  }
//
//  /**
//   * The URL to the source's git repo in a web browser
//   */
//  public fun htmlURL(): String {
//  }
//
//  /**
//   * A unique identifier for this GitModuleSource.
//   */
//  public fun id(): GitModuleSourceID {
//  }
//
//  /**
//   * The path to the root of the module source under the context directory. This directory contains
//   * its configuration file. It also contains its source code (possibly as a subdirectory).
//   */
//  public fun rootSubpath(): String {
//  }
//
//  /**
//   * The specified version of the git repo this source points to.
//   */
//  public fun version(): String {
//  }
//}
//
///**
// * The `GitModuleSourceID` scalar type represents an identifier for an object of type
// * GitModuleSource.
// */
//@Serializable
//public typealias GitModuleSourceID = String
//
///**
// * A git ref (tag, branch, or commit).
// */
//public class GitRef {
//  /**
//   * The resolved commit id at this ref.
//   */
//  public fun commit(): String {
//  }
//
//  /**
//   * A unique identifier for this GitRef.
//   */
//  public fun id(): GitRefID {
//  }
//
//  /**
//   * The filesystem tree at this ref.
//   */
//  public fun tree(): Directory {
//  }
//}
//
///**
// * The `GitRefID` scalar type represents an identifier for an object of type GitRef.
// */
//@Serializable
//public typealias GitRefID = String
//
///**
// * A git repository.
// */
//public class GitRepository {
//  /**
//   * Returns details of a branch.
//   */
//  public fun branch(): GitRef {
//  }
//
//  /**
//   * Returns details of a commit.
//   */
//  public fun commit(): GitRef {
//  }
//
//  /**
//   * Returns details for HEAD.
//   */
//  public fun head(): GitRef {
//  }
//
//  /**
//   * A unique identifier for this GitRepository.
//   */
//  public fun id(): GitRepositoryID {
//  }
//
//  /**
//   * Returns details of a ref.
//   */
//  public fun ref(): GitRef {
//  }
//
//  /**
//   * Returns details of a tag.
//   */
//  public fun tag(): GitRef {
//  }
//}
//
///**
// * The `GitRepositoryID` scalar type represents an identifier for an object of type GitRepository.
// */
//@Serializable
//public typealias GitRepositoryID = String
//
///**
// * Information about the host environment.
// */
//public class Host {
//  /**
//   * Accesses a directory on the host.
//   */
//  public fun directory(): Directory {
//  }
//
//  /**
//   * Accesses a file on the host.
//   */
//  public fun `file`(): File {
//  }
//
//  /**
//   * A unique identifier for this Host.
//   */
//  public fun id(): HostID {
//  }
//
//  /**
//   * Creates a service that forwards traffic to a specified address via the host.
//   */
//  public fun service(): Service {
//  }
//
//  /**
//   * Sets a secret given a user-defined name and the file path on the host, and returns the secret.
//   *
//   * The file is limited to a size of 512000 bytes.
//   */
//  public fun setSecretFile(): Secret {
//  }
//
//  /**
//   * Creates a tunnel that forwards traffic from the host to a service.
//   */
//  public fun tunnel(): Service {
//  }
//
//  /**
//   * Accesses a Unix socket on the host.
//   */
//  public fun unixSocket(): Socket {
//  }
//}
//
///**
// * The `HostID` scalar type represents an identifier for an object of type Host.
// */
//@Serializable
//public typealias HostID = String
//
///**
// * Compression algorithm to use for image layers.
// */
//@Serializable
//public enum class ImageLayerCompression(
//  `value`: String,
//) {
//  Gzip("Gzip"),
//  Zstd("Zstd"),
//  EStarGZ("EStarGZ"),
//  Uncompressed("Uncompressed"),
//}
//
///**
// * Mediatypes to use in published or exported image metadata.
// */
//@Serializable
//public enum class ImageMediaTypes(
//  `value`: String,
//) {
//  OCIMediaTypes("OCIMediaTypes"),
//  DockerMediaTypes("DockerMediaTypes"),
//}
//
///**
// * A graphql input type, which is essentially just a group of named args.
// * This is currently only used to represent pre-existing usage of graphql input types
// * in the core API. It is not used by user modules and shouldn't ever be as user
// * module accept input objects via their id rather than graphql input types.
// */
//public class InputTypeDef {
//  /**
//   * Static fields defined on this input object, if any.
//   */
//  public fun fields(): Array<FieldTypeDef> {
//  }
//
//  /**
//   * A unique identifier for this InputTypeDef.
//   */
//  public fun id(): InputTypeDefID {
//  }
//
//  /**
//   * The name of the input object.
//   */
//  public fun name(): String {
//  }
//}
//
///**
// * The `InputTypeDefID` scalar type represents an identifier for an object of type InputTypeDef.
// */
//@Serializable
//public typealias InputTypeDefID = String
//
///**
// * A definition of a custom interface defined in a Module.
// */
//public class InterfaceTypeDef {
//  /**
//   * The doc string for the interface, if any.
//   */
//  public fun description(): String {
//  }
//
//  /**
//   * Functions defined on this interface, if any.
//   */
//  public fun functions(): Array<Function> {
//  }
//
//  /**
//   * A unique identifier for this InterfaceTypeDef.
//   */
//  public fun id(): InterfaceTypeDefID {
//  }
//
//  /**
//   * The name of the interface.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * If this InterfaceTypeDef is associated with a Module, the name of the module. Unset otherwise.
//   */
//  public fun sourceModuleName(): String {
//  }
//}
//
///**
// * The `InterfaceTypeDefID` scalar type represents an identifier for an object of type
// * InterfaceTypeDef.
// */
//@Serializable
//public typealias InterfaceTypeDefID = String
//
///**
// * An arbitrary JSON-encoded value.
// */
//@Serializable
//public typealias JSON = String
//
///**
// * A simple key value object that represents a label.
// */
//public class Label {
//  /**
//   * A unique identifier for this Label.
//   */
//  public fun id(): LabelID {
//  }
//
//  /**
//   * The label name.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * The label value.
//   */
//  public fun `value`(): String {
//  }
//}
//
///**
// * The `LabelID` scalar type represents an identifier for an object of type Label.
// */
//@Serializable
//public typealias LabelID = String
//
///**
// * A definition of a list type in a Module.
// */
//public class ListTypeDef {
//  /**
//   * The type of the elements in the list.
//   */
//  public fun elementTypeDef(): TypeDef {
//  }
//
//  /**
//   * A unique identifier for this ListTypeDef.
//   */
//  public fun id(): ListTypeDefID {
//  }
//}
//
///**
// * The `ListTypeDefID` scalar type represents an identifier for an object of type ListTypeDef.
// */
//@Serializable
//public typealias ListTypeDefID = String
//
///**
// * Module source that that originates from a path locally relative to an arbitrary directory.
// */
//public class LocalModuleSource {
//  /**
//   * The directory containing everything needed to load load and use the module.
//   */
//  public fun contextDirectory(): Directory? {
//  }
//
//  /**
//   * A unique identifier for this LocalModuleSource.
//   */
//  public fun id(): LocalModuleSourceID {
//  }
//
//  /**
//   * The path to the root of the module source under the context directory. This directory contains
//   * its configuration file. It also contains its source code (possibly as a subdirectory).
//   */
//  public fun rootSubpath(): String {
//  }
//}
//
///**
// * The `LocalModuleSourceID` scalar type represents an identifier for an object of type
// * LocalModuleSource.
// */
//@Serializable
//public typealias LocalModuleSourceID = String
//
///**
// * A Dagger module.
// */
//public class Module {
//  /**
//   * Modules used by this module.
//   */
//  public fun dependencies(): Array<Module> {
//  }
//
//  /**
//   * The dependencies as configured by the module.
//   */
//  public fun dependencyConfig(): Array<ModuleDependency> {
//  }
//
//  /**
//   * The doc string of the module, if any
//   */
//  public fun description(): String {
//  }
//
//  /**
//   * The generated files and directories made on top of the module source's context directory.
//   */
//  public fun generatedContextDiff(): Directory {
//  }
//
//  /**
//   * The module source's context plus any configuration and source files created by codegen.
//   */
//  public fun generatedContextDirectory(): Directory {
//  }
//
//  /**
//   * A unique identifier for this Module.
//   */
//  public fun id(): ModuleID {
//  }
//
//  /**
//   * Retrieves the module with the objects loaded via its SDK.
//   */
//  public fun initialize(): Module {
//  }
//
//  /**
//   * Interfaces served by this module.
//   */
//  public fun interfaces(): Array<TypeDef> {
//  }
//
//  /**
//   * The name of the module
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * Objects served by this module.
//   */
//  public fun objects(): Array<TypeDef> {
//  }
//
//  /**
//   * The container that runs the module's entrypoint. It will fail to execute if the module doesn't
//   * compile.
//   */
//  public fun runtime(): Container {
//  }
//
//  /**
//   * The SDK used by this module. Either a name of a builtin SDK or a module source ref string
//   * pointing to the SDK's implementation.
//   */
//  public fun sdk(): String {
//  }
//
//  /**
//   * Serve a module's API in the current session.
//   *
//   * Note: this can only be called once per session. In the future, it could return a stream or
//   * service to remove the side effect.
//   */
//  public fun serve(): Void? {
//  }
//
//  /**
//   * The source for the module.
//   */
//  public fun source(): ModuleSource {
//  }
//
//  /**
//   * Retrieves the module with the given description
//   */
//  public fun withDescription(): Module {
//  }
//
//  /**
//   * This module plus the given Interface type and associated functions
//   */
//  public fun withInterface(): Module {
//  }
//
//  /**
//   * This module plus the given Object type and associated functions.
//   */
//  public fun withObject(): Module {
//  }
//
//  /**
//   * Retrieves the module with basic configuration loaded if present.
//   */
//  public fun withSource(): Module {
//  }
//}
//
///**
// * The configuration of dependency of a module.
// */
//public class ModuleDependency {
//  /**
//   * A unique identifier for this ModuleDependency.
//   */
//  public fun id(): ModuleDependencyID {
//  }
//
//  /**
//   * The name of the dependency module.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * The source for the dependency module.
//   */
//  public fun source(): ModuleSource {
//  }
//}
//
///**
// * The `ModuleDependencyID` scalar type represents an identifier for an object of type
// * ModuleDependency.
// */
//@Serializable
//public typealias ModuleDependencyID = String
//
///**
// * The `ModuleID` scalar type represents an identifier for an object of type Module.
// */
//@Serializable
//public typealias ModuleID = String
//
///**
// * The source needed to load and run a module, along with any metadata about the source such as
// * versions/urls/etc.
// */
//public class ModuleSource {
//  /**
//   * If the source is a of kind git, the git source representation of it.
//   */
//  public fun asGitSource(): GitModuleSource? {
//  }
//
//  /**
//   * If the source is of kind local, the local source representation of it.
//   */
//  public fun asLocalSource(): LocalModuleSource? {
//  }
//
//  /**
//   * Load the source as a module. If this is a local source, the parent directory must have been
//   * provided during module source creation
//   */
//  public fun asModule(): Module {
//  }
//
//  /**
//   * A human readable ref string representation of this module source.
//   */
//  public fun asString(): String {
//  }
//
//  /**
//   * Returns whether the module source has a configuration file.
//   */
//  public fun configExists(): Boolean {
//  }
//
//  /**
//   * The directory containing everything needed to load load and use the module.
//   */
//  public fun contextDirectory(): Directory {
//  }
//
//  /**
//   * The dependencies of the module source. Includes dependencies from the configuration and any
//   * extras from withDependencies calls.
//   */
//  public fun dependencies(): Array<ModuleDependency> {
//  }
//
//  /**
//   * The directory containing the module configuration and source code (source code may be in a
//   * subdir).
//   */
//  public fun directory(): Directory {
//  }
//
//  /**
//   * A unique identifier for this ModuleSource.
//   */
//  public fun id(): ModuleSourceID {
//  }
//
//  /**
//   * The kind of source (e.g. local, git, etc.)
//   */
//  public fun kind(): ModuleSourceKind {
//  }
//
//  /**
//   * If set, the name of the module this source references, including any overrides at runtime by
//   * callers.
//   */
//  public fun moduleName(): String {
//  }
//
//  /**
//   * The original name of the module this source references, as defined in the module configuration.
//   */
//  public fun moduleOriginalName(): String {
//  }
//
//  /**
//   * The path to the module source's context directory on the caller's filesystem. Only valid for
//   * local sources.
//   */
//  public fun resolveContextPathFromCaller(): String {
//  }
//
//  /**
//   * Resolve the provided module source arg as a dependency relative to this module source.
//   */
//  public fun resolveDependency(): ModuleSource {
//  }
//
//  /**
//   * Load a directory from the caller optionally with a given view applied.
//   */
//  public fun resolveDirectoryFromCaller(): Directory {
//  }
//
//  /**
//   * Load the source from its path on the caller's filesystem, including only needed+configured
//   * files and directories. Only valid for local sources.
//   */
//  public fun resolveFromCaller(): ModuleSource {
//  }
//
//  /**
//   * The path relative to context of the root of the module source, which contains dagger.json. It
//   * also contains the module implementation source code, but that may or may not being a subdir of
//   * this root.
//   */
//  public fun sourceRootSubpath(): String {
//  }
//
//  /**
//   * The path relative to context of the module implementation source code.
//   */
//  public fun sourceSubpath(): String {
//  }
//
//  /**
//   * Retrieve a named view defined for this module source.
//   */
//  public fun view(): ModuleSourceView {
//  }
//
//  /**
//   * The named views defined for this module source, which are sets of directory filters that can be
//   * applied to directory arguments provided to functions.
//   */
//  public fun views(): Array<ModuleSourceView> {
//  }
//
//  /**
//   * Update the module source with a new context directory. Only valid for local sources.
//   */
//  public fun withContextDirectory(): ModuleSource {
//  }
//
//  /**
//   * Append the provided dependencies to the module source's dependency list.
//   */
//  public fun withDependencies(): ModuleSource {
//  }
//
//  /**
//   * Update the module source with a new name.
//   */
//  public fun withName(): ModuleSource {
//  }
//
//  /**
//   * Update the module source with a new SDK.
//   */
//  public fun withSDK(): ModuleSource {
//  }
//
//  /**
//   * Update the module source with a new source subpath.
//   */
//  public fun withSourceSubpath(): ModuleSource {
//  }
//
//  /**
//   * Update the module source with a new named view.
//   */
//  public fun withView(): ModuleSource {
//  }
//}
//
///**
// * The `ModuleSourceID` scalar type represents an identifier for an object of type ModuleSource.
// */
//@Serializable
//public typealias ModuleSourceID = String
//
///**
// * The kind of module source.
// */
//@Serializable
//public enum class ModuleSourceKind(
//  `value`: String,
//) {
//  LOCAL_SOURCE("LOCAL_SOURCE"),
//  GIT_SOURCE("GIT_SOURCE"),
//}
//
///**
// * A named set of path filters that can be applied to directory arguments provided to functions.
// */
//public class ModuleSourceView {
//  /**
//   * A unique identifier for this ModuleSourceView.
//   */
//  public fun id(): ModuleSourceViewID {
//  }
//
//  /**
//   * The name of the view
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * The patterns of the view used to filter paths
//   */
//  public fun patterns(): Array<String> {
//  }
//}
//
///**
// * The `ModuleSourceViewID` scalar type represents an identifier for an object of type
// * ModuleSourceView.
// */
//@Serializable
//public typealias ModuleSourceViewID = String
//
///**
// * Transport layer network protocol associated to a port.
// */
//@Serializable
//public enum class NetworkProtocol(
//  `value`: String,
//) {
//  TCP("TCP"),
//  UDP("UDP"),
//}
//
///**
// * A definition of a custom object defined in a Module.
// */
//public class ObjectTypeDef {
//  /**
//   * The function used to construct new instances of this object, if any
//   */
//  public fun `constructor`(): Function? {
//  }
//
//  /**
//   * The doc string for the object, if any.
//   */
//  public fun description(): String {
//  }
//
//  /**
//   * Static fields defined on this object, if any.
//   */
//  public fun fields(): Array<FieldTypeDef> {
//  }
//
//  /**
//   * Functions defined on this object, if any.
//   */
//  public fun functions(): Array<Function> {
//  }
//
//  /**
//   * A unique identifier for this ObjectTypeDef.
//   */
//  public fun id(): ObjectTypeDefID {
//  }
//
//  /**
//   * The name of the object.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * If this ObjectTypeDef is associated with a Module, the name of the module. Unset otherwise.
//   */
//  public fun sourceModuleName(): String {
//  }
//}
//
///**
// * The `ObjectTypeDefID` scalar type represents an identifier for an object of type ObjectTypeDef.
// */
//@Serializable
//public typealias ObjectTypeDefID = String
//
///**
// * Key value object that represents a pipeline label.
// */
//@Serializable
//public data class PipelineLabel(
//  public val name: String,
//  public val `value`: String,
//)
//
///**
// * The platform config OS and architecture in a Container.
// *
// * The format is [os]/[platform]/[version] (e.g., "darwin/arm64/v7", "windows/amd64",
// * "linux/arm64").
// */
//@Serializable
//public typealias Platform = String
//
///**
// * A port exposed by a container.
// */
//public class Port {
//  /**
//   * The port description.
//   */
//  public fun description(): String? {
//  }
//
//  /**
//   * Skip the health check when run as a service.
//   */
//  public fun experimentalSkipHealthcheck(): Boolean {
//  }
//
//  /**
//   * A unique identifier for this Port.
//   */
//  public fun id(): PortID {
//  }
//
//  /**
//   * The port number.
//   */
//  public fun port(): Int {
//  }
//
//  /**
//   * The transport layer protocol.
//   */
//  public fun protocol(): NetworkProtocol {
//  }
//}
//
///**
// * Port forwarding rules for tunneling network traffic.
// */
//@Serializable
//public data class PortForward(
//  public val backend: Int,
//  public val frontend: Int?,
//  public val protocol: NetworkProtocol?,
//)
//
///**
// * The `PortID` scalar type represents an identifier for an object of type Port.
// */
//@Serializable
//public typealias PortID = String
//
///**
// * A reference to a secret value, which can be handled more safely than the value itself.
// */
//public class Secret {
//  /**
//   * A unique identifier for this Secret.
//   */
//  public fun id(): SecretID {
//  }
//
//  /**
//   * The name of this secret.
//   */
//  public fun name(): String {
//  }
//
//  /**
//   * The value of this secret.
//   */
//  public fun plaintext(): String {
//  }
//}
//
///**
// * The `SecretID` scalar type represents an identifier for an object of type Secret.
// */
//@Serializable
//public typealias SecretID = String
//
///**
// * A content-addressed service providing TCP connectivity.
// */
//public class Service {
//  /**
//   * Retrieves an endpoint that clients can use to reach this container.
//   *
//   * If no port is specified, the first exposed port is used. If none exist an error is returned.
//   *
//   * If a scheme is specified, a URL is returned. Otherwise, a host:port pair is returned.
//   */
//  public fun endpoint(): String {
//  }
//
//  /**
//   * Retrieves a hostname which can be used by clients to reach this container.
//   */
//  public fun hostname(): String {
//  }
//
//  /**
//   * A unique identifier for this Service.
//   */
//  public fun id(): ServiceID {
//  }
//
//  /**
//   * Retrieves the list of ports provided by the service.
//   */
//  public fun ports(): Array<Port> {
//  }
//
//  /**
//   * Start the service and wait for its health checks to succeed.
//   *
//   * Services bound to a Container do not need to be manually started.
//   */
//  public fun start(): ServiceID {
//  }
//
//  /**
//   * Stop the service.
//   */
//  public fun stop(): ServiceID {
//  }
//
//  /**
//   * Creates a tunnel that forwards traffic from the caller's network to this service.
//   */
//  public fun up(): Void? {
//  }
//}
//
///**
// * The `ServiceID` scalar type represents an identifier for an object of type Service.
// */
//@Serializable
//public typealias ServiceID = String
//
///**
// * A Unix or TCP/IP socket that can be mounted into a container.
// */
//public class Socket {
//  /**
//   * A unique identifier for this Socket.
//   */
//  public fun id(): SocketID {
//  }
//}
//
///**
// * The `SocketID` scalar type represents an identifier for an object of type Socket.
// */
//@Serializable
//public typealias SocketID = String
//
///**
// * An interactive terminal that clients can connect to.
// */
//public class Terminal {
//  /**
//   * A unique identifier for this Terminal.
//   */
//  public fun id(): TerminalID {
//  }
//
//  /**
//   * An http endpoint at which this terminal can be connected to over a websocket.
//   */
//  public fun websocketEndpoint(): String {
//  }
//}
//
///**
// * The `TerminalID` scalar type represents an identifier for an object of type Terminal.
// */
//@Serializable
//public typealias TerminalID = String
//
///**
// * A definition of a parameter or return type in a Module.
// */
//public class TypeDef {
//  /**
//   * If kind is INPUT, the input-specific type definition. If kind is not INPUT, this will be null.
//   */
//  public fun asInput(): InputTypeDef? {
//  }
//
//  /**
//   * If kind is INTERFACE, the interface-specific type definition. If kind is not INTERFACE, this
//   * will be null.
//   */
//  public fun asInterface(): InterfaceTypeDef? {
//  }
//
//  /**
//   * If kind is LIST, the list-specific type definition. If kind is not LIST, this will be null.
//   */
//  public fun asList(): ListTypeDef? {
//  }
//
//  /**
//   * If kind is OBJECT, the object-specific type definition. If kind is not OBJECT, this will be
//   * null.
//   */
//  public fun asObject(): ObjectTypeDef? {
//  }
//
//  /**
//   * A unique identifier for this TypeDef.
//   */
//  public fun id(): TypeDefID {
//  }
//
//  /**
//   * The kind of type this is (e.g. primitive, list, object).
//   */
//  public fun kind(): TypeDefKind {
//  }
//
//  /**
//   * Whether this type can be set to null. Defaults to false.
//   */
//  public fun optional(): Boolean {
//  }
//
//  /**
//   * Adds a function for constructing a new instance of an Object TypeDef, failing if the type is
//   * not an object.
//   */
//  public fun withConstructor(): TypeDef {
//  }
//
//  /**
//   * Adds a static field for an Object TypeDef, failing if the type is not an object.
//   */
//  public fun withField(): TypeDef {
//  }
//
//  /**
//   * Adds a function for an Object or Interface TypeDef, failing if the type is not one of those
//   * kinds.
//   */
//  public fun withFunction(): TypeDef {
//  }
//
//  /**
//   * Returns a TypeDef of kind Interface with the provided name.
//   */
//  public fun withInterface(): TypeDef {
//  }
//
//  /**
//   * Sets the kind of the type.
//   */
//  public fun withKind(): TypeDef {
//  }
//
//  /**
//   * Returns a TypeDef of kind List with the provided type for its elements.
//   */
//  public fun withListOf(): TypeDef {
//  }
//
//  /**
//   * Returns a TypeDef of kind Object with the provided name.
//   *
//   * Note that an object's fields and functions may be omitted if the intent is only to refer to an
//   * object. This is how functions are able to return their own object, or any other circular
//   * reference.
//   */
//  public fun withObject(): TypeDef {
//  }
//
//  /**
//   * Sets whether this type can be set to null.
//   */
//  public fun withOptional(): TypeDef {
//  }
//}
//
///**
// * The `TypeDefID` scalar type represents an identifier for an object of type TypeDef.
// */
//@Serializable
//public typealias TypeDefID = String
//
///**
// * Distinguishes the different kinds of TypeDefs.
// */
//@Serializable
//public enum class TypeDefKind(
//  `value`: String,
//) {
//  STRING_KIND("STRING_KIND"),
//  INTEGER_KIND("INTEGER_KIND"),
//  BOOLEAN_KIND("BOOLEAN_KIND"),
//  LIST_KIND("LIST_KIND"),
//  OBJECT_KIND("OBJECT_KIND"),
//  INTERFACE_KIND("INTERFACE_KIND"),
//  INPUT_KIND("INPUT_KIND"),
//  VOID_KIND("VOID_KIND"),
//}
//
///**
// * The absence of a value.
// *
// * A Null Void is used as a placeholder for resolvers that do not return anything.
// */
//@Serializable
//public typealias Void = String
