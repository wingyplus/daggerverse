package com.github.wingyplus.dagger

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
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

/**
 * A directory whose contents persist across runs.
 */
public class CacheVolume(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A unique identifier for this CacheVolume.
   */
  public suspend fun id(): CacheVolumeID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `CacheVolumeID` scalar type represents an identifier for an object of type CacheVolume.
 */
public typealias CacheVolumeID = String

/**
 * The root of the DAG.
 */
public class Client(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * Retrieves a content-addressed blob.
   */
  public fun blob(
    digest: String,
    mediaType: String,
    size: Int,
    uncompressed: String,
  ): Directory {
    val newQueryBuilder = queryBuilder.select("blob")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves a container builtin to the engine.
   */
  public fun builtinContainer(digest: String): Container {
    val newQueryBuilder = queryBuilder.select("builtinContainer")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Constructs a cache volume for a given cache key.
   */
  public fun cacheVolume(key: String): CacheVolume {
    val newQueryBuilder = queryBuilder.select("cacheVolume")
    return CacheVolume(newQueryBuilder, engineClient)
  }

  /**
   * Checks if the current Dagger Engine is compatible with an SDK's required version.
   */
  public suspend fun checkVersionCompatibility(version: String): Boolean {
    val newQueryBuilder = queryBuilder.select("checkVersionCompatibility")
    return engineClient.execute(newQueryBuilder)
  }

  public fun codegen(): Codegen {
    val newQueryBuilder = queryBuilder.select("codegen")
    return Codegen(newQueryBuilder, engineClient)
  }

  /**
   * Creates a scratch container.
   *
   * Optional platform argument initializes new containers to execute and publish as that platform.
   * Platform defaults to that of the builder's host.
   */
  public fun container(id: ContainerID?, platform: Platform?): Container {
    val newQueryBuilder = queryBuilder.select("container")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * The FunctionCall context that the SDK caller is currently executing in.
   *
   * If the caller is not currently executing in a function, this will return an error.
   */
  public fun currentFunctionCall(): FunctionCall {
    val newQueryBuilder = queryBuilder.select("currentFunctionCall")
    return FunctionCall(newQueryBuilder, engineClient)
  }

  /**
   * The module currently being served in the session, if any.
   */
  public fun currentModule(): CurrentModule {
    val newQueryBuilder = queryBuilder.select("currentModule")
    return CurrentModule(newQueryBuilder, engineClient)
  }

  /**
   * The TypeDef representations of the objects currently being served in the session.
   */
  public suspend fun currentTypeDefs(): List<TypeDef> {
    val newQueryBuilder = queryBuilder.select("currentTypeDefs")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The default platform of the engine.
   */
  public suspend fun defaultPlatform(): Platform {
    val newQueryBuilder = queryBuilder.select("defaultPlatform")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Creates an empty directory.
   */
  public fun directory(id: DirectoryID?): Directory {
    val newQueryBuilder = queryBuilder.select("directory")
    return Directory(newQueryBuilder, engineClient)
  }

  public fun `file`(id: FileID): File {
    val newQueryBuilder = queryBuilder.select("file")
    return File(newQueryBuilder, engineClient)
  }

  /**
   * Creates a function.
   */
  public fun function(name: String, returnType: TypeDefID): Function {
    val newQueryBuilder = queryBuilder.select("function")
    return Function(newQueryBuilder, engineClient)
  }

  /**
   * Create a code generation result, given a directory containing the generated code.
   */
  public fun generatedCode(code: DirectoryID): GeneratedCode {
    val newQueryBuilder = queryBuilder.select("generatedCode")
    return GeneratedCode(newQueryBuilder, engineClient)
  }

  /**
   * Queries a Git repository.
   */
  public fun git(
    url: String,
    experimentalServiceHost: ServiceID?,
    keepGitDir: Boolean?,
    sshAuthSocket: SocketID?,
    sshKnownHosts: String?,
  ): GitRepository {
    val newQueryBuilder = queryBuilder.select("git")
    return GitRepository(newQueryBuilder, engineClient)
  }

  /**
   * Queries the host environment.
   */
  public fun host(): Host {
    val newQueryBuilder = queryBuilder.select("host")
    return Host(newQueryBuilder, engineClient)
  }

  /**
   * Returns a file containing an http remote url content.
   */
  public fun http(url: String, experimentalServiceHost: ServiceID?): File {
    val newQueryBuilder = queryBuilder.select("http")
    return File(newQueryBuilder, engineClient)
  }

  /**
   * Load a CacheVolume from its ID.
   */
  public fun loadCacheVolumeFromID(id: CacheVolumeID): CacheVolume {
    val newQueryBuilder = queryBuilder.select("loadCacheVolumeFromID")
    return CacheVolume(newQueryBuilder, engineClient)
  }

  /**
   * Load a CodegenEnumValue from its ID.
   */
  public fun loadCodegenEnumValueFromID(id: CodegenEnumValueID): CodegenEnumValue {
    val newQueryBuilder = queryBuilder.select("loadCodegenEnumValueFromID")
    return CodegenEnumValue(newQueryBuilder, engineClient)
  }

  /**
   * Load a CodegenExperimental from its ID.
   */
  public fun loadCodegenExperimentalFromID(id: CodegenExperimentalID): CodegenExperimental {
    val newQueryBuilder = queryBuilder.select("loadCodegenExperimentalFromID")
    return CodegenExperimental(newQueryBuilder, engineClient)
  }

  /**
   * Load a CodegenField from its ID.
   */
  public fun loadCodegenFieldFromID(id: CodegenFieldID): CodegenField {
    val newQueryBuilder = queryBuilder.select("loadCodegenFieldFromID")
    return CodegenField(newQueryBuilder, engineClient)
  }

  /**
   * Load a Codegen from its ID.
   */
  public fun loadCodegenFromID(id: CodegenID): Codegen {
    val newQueryBuilder = queryBuilder.select("loadCodegenFromID")
    return Codegen(newQueryBuilder, engineClient)
  }

  /**
   * Load a CodegenInputValue from its ID.
   */
  public fun loadCodegenInputValueFromID(id: CodegenInputValueID): CodegenInputValue {
    val newQueryBuilder = queryBuilder.select("loadCodegenInputValueFromID")
    return CodegenInputValue(newQueryBuilder, engineClient)
  }

  /**
   * Load a CodegenSchema from its ID.
   */
  public fun loadCodegenSchemaFromID(id: CodegenSchemaID): CodegenSchema {
    val newQueryBuilder = queryBuilder.select("loadCodegenSchemaFromID")
    return CodegenSchema(newQueryBuilder, engineClient)
  }

  /**
   * Load a CodegenType from its ID.
   */
  public fun loadCodegenTypeFromID(id: CodegenTypeID): CodegenType {
    val newQueryBuilder = queryBuilder.select("loadCodegenTypeFromID")
    return CodegenType(newQueryBuilder, engineClient)
  }

  /**
   * Load a CodegenTypeRef from its ID.
   */
  public fun loadCodegenTypeRefFromID(id: CodegenTypeRefID): CodegenTypeRef {
    val newQueryBuilder = queryBuilder.select("loadCodegenTypeRefFromID")
    return CodegenTypeRef(newQueryBuilder, engineClient)
  }

  /**
   * Load a Container from its ID.
   */
  public fun loadContainerFromID(id: ContainerID): Container {
    val newQueryBuilder = queryBuilder.select("loadContainerFromID")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Load a CurrentModule from its ID.
   */
  public fun loadCurrentModuleFromID(id: CurrentModuleID): CurrentModule {
    val newQueryBuilder = queryBuilder.select("loadCurrentModuleFromID")
    return CurrentModule(newQueryBuilder, engineClient)
  }

  /**
   * Load a Directory from its ID.
   */
  public fun loadDirectoryFromID(id: DirectoryID): Directory {
    val newQueryBuilder = queryBuilder.select("loadDirectoryFromID")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Load a EnvVariable from its ID.
   */
  public fun loadEnvVariableFromID(id: EnvVariableID): EnvVariable {
    val newQueryBuilder = queryBuilder.select("loadEnvVariableFromID")
    return EnvVariable(newQueryBuilder, engineClient)
  }

  /**
   * Load a FieldTypeDef from its ID.
   */
  public fun loadFieldTypeDefFromID(id: FieldTypeDefID): FieldTypeDef {
    val newQueryBuilder = queryBuilder.select("loadFieldTypeDefFromID")
    return FieldTypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Load a File from its ID.
   */
  public fun loadFileFromID(id: FileID): File {
    val newQueryBuilder = queryBuilder.select("loadFileFromID")
    return File(newQueryBuilder, engineClient)
  }

  /**
   * Load a FunctionArg from its ID.
   */
  public fun loadFunctionArgFromID(id: FunctionArgID): FunctionArg {
    val newQueryBuilder = queryBuilder.select("loadFunctionArgFromID")
    return FunctionArg(newQueryBuilder, engineClient)
  }

  /**
   * Load a FunctionCallArgValue from its ID.
   */
  public fun loadFunctionCallArgValueFromID(id: FunctionCallArgValueID): FunctionCallArgValue {
    val newQueryBuilder = queryBuilder.select("loadFunctionCallArgValueFromID")
    return FunctionCallArgValue(newQueryBuilder, engineClient)
  }

  /**
   * Load a FunctionCall from its ID.
   */
  public fun loadFunctionCallFromID(id: FunctionCallID): FunctionCall {
    val newQueryBuilder = queryBuilder.select("loadFunctionCallFromID")
    return FunctionCall(newQueryBuilder, engineClient)
  }

  /**
   * Load a Function from its ID.
   */
  public fun loadFunctionFromID(id: FunctionID): Function {
    val newQueryBuilder = queryBuilder.select("loadFunctionFromID")
    return Function(newQueryBuilder, engineClient)
  }

  /**
   * Load a GeneratedCode from its ID.
   */
  public fun loadGeneratedCodeFromID(id: GeneratedCodeID): GeneratedCode {
    val newQueryBuilder = queryBuilder.select("loadGeneratedCodeFromID")
    return GeneratedCode(newQueryBuilder, engineClient)
  }

  /**
   * Load a GitModuleSource from its ID.
   */
  public fun loadGitModuleSourceFromID(id: GitModuleSourceID): GitModuleSource {
    val newQueryBuilder = queryBuilder.select("loadGitModuleSourceFromID")
    return GitModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Load a GitRef from its ID.
   */
  public fun loadGitRefFromID(id: GitRefID): GitRef {
    val newQueryBuilder = queryBuilder.select("loadGitRefFromID")
    return GitRef(newQueryBuilder, engineClient)
  }

  /**
   * Load a GitRepository from its ID.
   */
  public fun loadGitRepositoryFromID(id: GitRepositoryID): GitRepository {
    val newQueryBuilder = queryBuilder.select("loadGitRepositoryFromID")
    return GitRepository(newQueryBuilder, engineClient)
  }

  /**
   * Load a Host from its ID.
   */
  public fun loadHostFromID(id: HostID): Host {
    val newQueryBuilder = queryBuilder.select("loadHostFromID")
    return Host(newQueryBuilder, engineClient)
  }

  /**
   * Load a InputTypeDef from its ID.
   */
  public fun loadInputTypeDefFromID(id: InputTypeDefID): InputTypeDef {
    val newQueryBuilder = queryBuilder.select("loadInputTypeDefFromID")
    return InputTypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Load a InterfaceTypeDef from its ID.
   */
  public fun loadInterfaceTypeDefFromID(id: InterfaceTypeDefID): InterfaceTypeDef {
    val newQueryBuilder = queryBuilder.select("loadInterfaceTypeDefFromID")
    return InterfaceTypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Load a Label from its ID.
   */
  public fun loadLabelFromID(id: LabelID): Label {
    val newQueryBuilder = queryBuilder.select("loadLabelFromID")
    return Label(newQueryBuilder, engineClient)
  }

  /**
   * Load a ListTypeDef from its ID.
   */
  public fun loadListTypeDefFromID(id: ListTypeDefID): ListTypeDef {
    val newQueryBuilder = queryBuilder.select("loadListTypeDefFromID")
    return ListTypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Load a LocalModuleSource from its ID.
   */
  public fun loadLocalModuleSourceFromID(id: LocalModuleSourceID): LocalModuleSource {
    val newQueryBuilder = queryBuilder.select("loadLocalModuleSourceFromID")
    return LocalModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Load a ModuleDependency from its ID.
   */
  public fun loadModuleDependencyFromID(id: ModuleDependencyID): ModuleDependency {
    val newQueryBuilder = queryBuilder.select("loadModuleDependencyFromID")
    return ModuleDependency(newQueryBuilder, engineClient)
  }

  /**
   * Load a Module from its ID.
   */
  public fun loadModuleFromID(id: ModuleID): Module {
    val newQueryBuilder = queryBuilder.select("loadModuleFromID")
    return Module(newQueryBuilder, engineClient)
  }

  /**
   * Load a ModuleSource from its ID.
   */
  public fun loadModuleSourceFromID(id: ModuleSourceID): ModuleSource {
    val newQueryBuilder = queryBuilder.select("loadModuleSourceFromID")
    return ModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Load a ModuleSourceView from its ID.
   */
  public fun loadModuleSourceViewFromID(id: ModuleSourceViewID): ModuleSourceView {
    val newQueryBuilder = queryBuilder.select("loadModuleSourceViewFromID")
    return ModuleSourceView(newQueryBuilder, engineClient)
  }

  /**
   * Load a ObjectTypeDef from its ID.
   */
  public fun loadObjectTypeDefFromID(id: ObjectTypeDefID): ObjectTypeDef {
    val newQueryBuilder = queryBuilder.select("loadObjectTypeDefFromID")
    return ObjectTypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Load a Port from its ID.
   */
  public fun loadPortFromID(id: PortID): Port {
    val newQueryBuilder = queryBuilder.select("loadPortFromID")
    return Port(newQueryBuilder, engineClient)
  }

  /**
   * Load a Secret from its ID.
   */
  public fun loadSecretFromID(id: SecretID): Secret {
    val newQueryBuilder = queryBuilder.select("loadSecretFromID")
    return Secret(newQueryBuilder, engineClient)
  }

  /**
   * Load a Service from its ID.
   */
  public fun loadServiceFromID(id: ServiceID): Service {
    val newQueryBuilder = queryBuilder.select("loadServiceFromID")
    return Service(newQueryBuilder, engineClient)
  }

  /**
   * Load a Socket from its ID.
   */
  public fun loadSocketFromID(id: SocketID): Socket {
    val newQueryBuilder = queryBuilder.select("loadSocketFromID")
    return Socket(newQueryBuilder, engineClient)
  }

  /**
   * Load a Terminal from its ID.
   */
  public fun loadTerminalFromID(id: TerminalID): Terminal {
    val newQueryBuilder = queryBuilder.select("loadTerminalFromID")
    return Terminal(newQueryBuilder, engineClient)
  }

  /**
   * Load a TypeDef from its ID.
   */
  public fun loadTypeDefFromID(id: TypeDefID): TypeDef {
    val newQueryBuilder = queryBuilder.select("loadTypeDefFromID")
    return TypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Create a new module.
   */
  public fun module(): Module {
    val newQueryBuilder = queryBuilder.select("module")
    return Module(newQueryBuilder, engineClient)
  }

  /**
   * Create a new module dependency configuration from a module source and name
   */
  public fun moduleDependency(source: ModuleSourceID, name: String?): ModuleDependency {
    val newQueryBuilder = queryBuilder.select("moduleDependency")
    return ModuleDependency(newQueryBuilder, engineClient)
  }

  /**
   * Create a new module source instance from a source ref string.
   */
  public fun moduleSource(refString: String, stable: Boolean?): ModuleSource {
    val newQueryBuilder = queryBuilder.select("moduleSource")
    return ModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Creates a named sub-pipeline.
   */
  public fun pipeline(
    name: String,
    description: String?,
    labels: List<PipelineLabel>,
  ): Client {
    val newQueryBuilder = queryBuilder.select("pipeline")
    return Client(newQueryBuilder, engineClient)
  }

  /**
   * Reference a secret by name.
   */
  public fun secret(name: String, accessor: String?): Secret {
    val newQueryBuilder = queryBuilder.select("secret")
    return Secret(newQueryBuilder, engineClient)
  }

  /**
   * Sets a secret given a user defined name to its plaintext and returns the secret.
   *
   * The plaintext value is limited to a size of 128000 bytes.
   */
  public fun setSecret(name: String, plaintext: String): Secret {
    val newQueryBuilder = queryBuilder.select("setSecret")
    return Secret(newQueryBuilder, engineClient)
  }

  /**
   * Loads a socket by its ID.
   */
  public fun socket(id: SocketID): Socket {
    val newQueryBuilder = queryBuilder.select("socket")
    return Socket(newQueryBuilder, engineClient)
  }

  /**
   * Create a new TypeDef.
   */
  public fun typeDef(): TypeDef {
    val newQueryBuilder = queryBuilder.select("typeDef")
    return TypeDef(newQueryBuilder, engineClient)
  }
}

/**
 * An OCI-compatible container, also known as a Docker container.
 */
public class Container(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * Turn the container into a Service.
   *
   * Be sure to set any exposed ports before this conversion.
   */
  public fun asService(): Service {
    val newQueryBuilder = queryBuilder.select("asService")
    return Service(newQueryBuilder, engineClient)
  }

  /**
   * Returns a File representing the container serialized to a tarball.
   */
  public fun asTarball(
    forcedCompression: ImageLayerCompression?,
    mediaTypes: ImageMediaTypes?,
    platformVariants: List<ContainerID>,
  ): File {
    val newQueryBuilder = queryBuilder.select("asTarball")
    return File(newQueryBuilder, engineClient)
  }

  /**
   * Initializes this container from a Dockerfile build.
   */
  public fun build(
    context: DirectoryID,
    buildArgs: List<BuildArg>,
    dockerfile: String?,
    secrets: List<SecretID>,
    target: String?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("build")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves default arguments for future commands.
   */
  public suspend fun defaultArgs(): List<String> {
    val newQueryBuilder = queryBuilder.select("defaultArgs")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves a directory at the given path.
   *
   * Mounts are included.
   */
  public fun directory(path: String): Directory {
    val newQueryBuilder = queryBuilder.select("directory")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves entrypoint to be prepended to the arguments of all commands.
   */
  public suspend fun entrypoint(): List<String> {
    val newQueryBuilder = queryBuilder.select("entrypoint")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves the value of the specified environment variable.
   */
  public suspend fun envVariable(name: String): String {
    val newQueryBuilder = queryBuilder.select("envVariable")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves the list of environment variables passed to commands.
   */
  public suspend fun envVariables(): List<EnvVariable> {
    val newQueryBuilder = queryBuilder.select("envVariables")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * EXPERIMENTAL API! Subject to change/removal at any time.
   *
   * Configures all available GPUs on the host to be accessible to this container.
   *
   * This currently works for Nvidia devices only.
   */
  public fun experimentalWithAllGPUs(): Container {
    val newQueryBuilder = queryBuilder.select("experimentalWithAllGPUs")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * EXPERIMENTAL API! Subject to change/removal at any time.
   *
   * Configures the provided list of devices to be accessible to this container.
   *
   * This currently works for Nvidia devices only.
   */
  public fun experimentalWithGPU(devices: List<String>): Container {
    val newQueryBuilder = queryBuilder.select("experimentalWithGPU")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Writes the container as an OCI tarball to the destination file path on the host.
   *
   * Return true on success.
   *
   * It can also export platform variants.
   */
  public suspend fun export(
    path: String,
    forcedCompression: ImageLayerCompression?,
    mediaTypes: ImageMediaTypes?,
    platformVariants: List<ContainerID>,
  ): Boolean {
    val newQueryBuilder = queryBuilder.select("export")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves the list of exposed ports.
   *
   * This includes ports already exposed by the image, even if not explicitly added with dagger.
   */
  public suspend fun exposedPorts(): List<Port> {
    val newQueryBuilder = queryBuilder.select("exposedPorts")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves a file at the given path.
   *
   * Mounts are included.
   */
  public fun `file`(path: String): File {
    val newQueryBuilder = queryBuilder.select("file")
    return File(newQueryBuilder, engineClient)
  }

  /**
   * Initializes this container from a pulled base image.
   */
  public fun from(address: String): Container {
    val newQueryBuilder = queryBuilder.select("from")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * A unique identifier for this Container.
   */
  public suspend fun id(): ContainerID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The unique image reference which can only be retrieved immediately after the 'Container.From'
   * call.
   */
  public suspend fun imageRef(): String {
    val newQueryBuilder = queryBuilder.select("imageRef")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Reads the container from an OCI tarball.
   */
  public fun `import`(source: FileID, tag: String?): Container {
    val newQueryBuilder = queryBuilder.select("import")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves the value of the specified label.
   */
  public suspend fun label(name: String): String {
    val newQueryBuilder = queryBuilder.select("label")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves the list of labels passed to container.
   */
  public suspend fun labels(): List<Label> {
    val newQueryBuilder = queryBuilder.select("labels")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves the list of paths where a directory is mounted.
   */
  public suspend fun mounts(): List<String> {
    val newQueryBuilder = queryBuilder.select("mounts")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Creates a named sub-pipeline.
   */
  public fun pipeline(
    name: String,
    description: String?,
    labels: List<PipelineLabel>,
  ): Container {
    val newQueryBuilder = queryBuilder.select("pipeline")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * The platform this container executes and publishes as.
   */
  public suspend fun platform(): Platform {
    val newQueryBuilder = queryBuilder.select("platform")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Publishes this container as a new image to the specified address.
   *
   * Publish returns a fully qualified ref.
   *
   * It can also publish platform variants.
   */
  public suspend fun publish(
    address: String,
    forcedCompression: ImageLayerCompression?,
    mediaTypes: ImageMediaTypes?,
    platformVariants: List<ContainerID>,
  ): String {
    val newQueryBuilder = queryBuilder.select("publish")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves this container's root filesystem. Mounts are not included.
   */
  public fun rootfs(): Directory {
    val newQueryBuilder = queryBuilder.select("rootfs")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * The error stream of the last executed command.
   *
   * Will execute default command if none is set, or error if there's no default.
   */
  public suspend fun stderr(): String {
    val newQueryBuilder = queryBuilder.select("stderr")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The output stream of the last executed command.
   *
   * Will execute default command if none is set, or error if there's no default.
   */
  public suspend fun stdout(): String {
    val newQueryBuilder = queryBuilder.select("stdout")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Forces evaluation of the pipeline in the engine.
   *
   * It doesn't run the default command if no exec has been set.
   */
  public suspend fun sync(): ContainerID {
    val newQueryBuilder = queryBuilder.select("sync")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Return an interactive terminal for this container using its configured default terminal command
   * if not overridden by args (or sh as a fallback default).
   */
  public fun terminal(
    cmd: List<String>,
    experimentalPrivilegedNesting: Boolean?,
    insecureRootCapabilities: Boolean?,
  ): Terminal {
    val newQueryBuilder = queryBuilder.select("terminal")
    return Terminal(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves the user to be set for all commands.
   */
  public suspend fun user(): String {
    val newQueryBuilder = queryBuilder.select("user")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Configures default arguments for future commands.
   */
  public fun withDefaultArgs(args: List<String>): Container {
    val newQueryBuilder = queryBuilder.select("withDefaultArgs")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Set the default command to invoke for the container's terminal API.
   */
  public fun withDefaultTerminalCmd(
    args: List<String>,
    experimentalPrivilegedNesting: Boolean?,
    insecureRootCapabilities: Boolean?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withDefaultTerminalCmd")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus a directory written at the given path.
   */
  public fun withDirectory(
    directory: DirectoryID,
    path: String,
    exclude: List<String>,
    include: List<String>,
    owner: String?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withDirectory")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container but with a different command entrypoint.
   */
  public fun withEntrypoint(args: List<String>, keepDefaultArgs: Boolean?): Container {
    val newQueryBuilder = queryBuilder.select("withEntrypoint")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus the given environment variable.
   */
  public fun withEnvVariable(
    name: String,
    `value`: String,
    expand: Boolean?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withEnvVariable")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container after executing the specified command inside it.
   */
  public fun withExec(
    args: List<String>,
    experimentalPrivilegedNesting: Boolean?,
    insecureRootCapabilities: Boolean?,
    redirectStderr: String?,
    redirectStdout: String?,
    skipEntrypoint: Boolean?,
    stdin: String?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withExec")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Expose a network port.
   *
   * Exposed ports serve two purposes:
   *
   * - For health checks and introspection, when running services
   *
   * - For setting the EXPOSE OCI field when publishing the container
   */
  public fun withExposedPort(
    port: Int,
    description: String?,
    experimentalSkipHealthcheck: Boolean?,
    protocol: NetworkProtocol?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withExposedPort")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus the contents of the given file copied to the given path.
   */
  public fun withFile(
    path: String,
    source: FileID,
    owner: String?,
    permissions: Int?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withFile")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus the contents of the given files copied to the given path.
   */
  public fun withFiles(
    path: String,
    sources: List<FileID>,
    owner: String?,
    permissions: Int?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withFiles")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Indicate that subsequent operations should be featured more prominently in the UI.
   */
  public fun withFocus(): Container {
    val newQueryBuilder = queryBuilder.select("withFocus")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus the given label.
   */
  public fun withLabel(name: String, `value`: String): Container {
    val newQueryBuilder = queryBuilder.select("withLabel")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus a cache volume mounted at the given path.
   */
  public fun withMountedCache(
    cache: CacheVolumeID,
    path: String,
    owner: String?,
    sharing: CacheSharingMode?,
    source: DirectoryID?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withMountedCache")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus a directory mounted at the given path.
   */
  public fun withMountedDirectory(
    path: String,
    source: DirectoryID,
    owner: String?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withMountedDirectory")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus a file mounted at the given path.
   */
  public fun withMountedFile(
    path: String,
    source: FileID,
    owner: String?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withMountedFile")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus a secret mounted into a file at the given path.
   */
  public fun withMountedSecret(
    path: String,
    source: SecretID,
    mode: Int?,
    owner: String?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withMountedSecret")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus a temporary directory mounted at the given path.
   */
  public fun withMountedTemp(path: String): Container {
    val newQueryBuilder = queryBuilder.select("withMountedTemp")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus a new file written at the given path.
   */
  public fun withNewFile(
    path: String,
    contents: String?,
    owner: String?,
    permissions: Int?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withNewFile")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container with a registry authentication for a given address.
   */
  public fun withRegistryAuth(
    address: String,
    secret: SecretID,
    username: String,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withRegistryAuth")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves the container with the given directory mounted to /.
   */
  public fun withRootfs(directory: DirectoryID): Container {
    val newQueryBuilder = queryBuilder.select("withRootfs")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus an env variable containing the given secret.
   */
  public fun withSecretVariable(name: String, secret: SecretID): Container {
    val newQueryBuilder = queryBuilder.select("withSecretVariable")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Establish a runtime dependency on a service.
   *
   * The service will be started automatically when needed and detached when it is no longer needed,
   * executing the default command if none is set.
   *
   * The service will be reachable from the container via the provided hostname alias.
   *
   * The service dependency will also convey to any files or directories produced by the container.
   */
  public fun withServiceBinding(alias: String, service: ServiceID): Container {
    val newQueryBuilder = queryBuilder.select("withServiceBinding")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container plus a socket forwarded to the given Unix socket path.
   */
  public fun withUnixSocket(
    path: String,
    source: SocketID,
    owner: String?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("withUnixSocket")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container with a different command user.
   */
  public fun withUser(name: String): Container {
    val newQueryBuilder = queryBuilder.select("withUser")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container with a different working directory.
   */
  public fun withWorkdir(path: String): Container {
    val newQueryBuilder = queryBuilder.select("withWorkdir")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container with unset default arguments for future commands.
   */
  public fun withoutDefaultArgs(): Container {
    val newQueryBuilder = queryBuilder.select("withoutDefaultArgs")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container with an unset command entrypoint.
   */
  public fun withoutEntrypoint(keepDefaultArgs: Boolean?): Container {
    val newQueryBuilder = queryBuilder.select("withoutEntrypoint")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container minus the given environment variable.
   */
  public fun withoutEnvVariable(name: String): Container {
    val newQueryBuilder = queryBuilder.select("withoutEnvVariable")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Unexpose a previously exposed port.
   */
  public fun withoutExposedPort(port: Int, protocol: NetworkProtocol?): Container {
    val newQueryBuilder = queryBuilder.select("withoutExposedPort")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Indicate that subsequent operations should not be featured more prominently in the UI.
   *
   * This is the initial state of all containers.
   */
  public fun withoutFocus(): Container {
    val newQueryBuilder = queryBuilder.select("withoutFocus")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container minus the given environment label.
   */
  public fun withoutLabel(name: String): Container {
    val newQueryBuilder = queryBuilder.select("withoutLabel")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container after unmounting everything at the given path.
   */
  public fun withoutMount(path: String): Container {
    val newQueryBuilder = queryBuilder.select("withoutMount")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container without the registry authentication of a given address.
   */
  public fun withoutRegistryAuth(address: String): Container {
    val newQueryBuilder = queryBuilder.select("withoutRegistryAuth")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container with a previously added Unix socket removed.
   */
  public fun withoutUnixSocket(path: String): Container {
    val newQueryBuilder = queryBuilder.select("withoutUnixSocket")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container with an unset command user.
   *
   * Should default to root.
   */
  public fun withoutUser(): Container {
    val newQueryBuilder = queryBuilder.select("withoutUser")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this container with an unset working directory.
   *
   * Should default to "/".
   */
  public fun withoutWorkdir(): Container {
    val newQueryBuilder = queryBuilder.select("withoutWorkdir")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves the working directory for all commands.
   */
  public suspend fun workdir(): String {
    val newQueryBuilder = queryBuilder.select("workdir")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `ContainerID` scalar type represents an identifier for an object of type Container.
 */
public typealias ContainerID = String

/**
 * Reflective module API provided to functions at runtime.
 */
public class CurrentModule(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A unique identifier for this CurrentModule.
   */
  public suspend fun id(): CurrentModuleID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the module being executed in
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The directory containing the module's source code loaded into the engine (plus any generated
   * code that may have been created).
   */
  public fun source(): Directory {
    val newQueryBuilder = queryBuilder.select("source")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Load a directory from the module's scratch working directory, including any changes that may
   * have been made to it during module function execution.
   */
  public fun workdir(
    path: String,
    exclude: List<String>,
    include: List<String>,
  ): Directory {
    val newQueryBuilder = queryBuilder.select("workdir")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Load a file from the module's scratch working directory, including any changes that may have
   * been made to it during module function execution.Load a file from the module's scratch working
   * directory, including any changes that may have been made to it during module function execution.
   */
  public fun workdirFile(path: String): File {
    val newQueryBuilder = queryBuilder.select("workdirFile")
    return File(newQueryBuilder, engineClient)
  }
}

/**
 * The `CurrentModuleID` scalar type represents an identifier for an object of type CurrentModule.
 */
public typealias CurrentModuleID = String

/**
 * A directory.
 */
public class Directory(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * Load the directory as a Dagger module
   */
  public fun asModule(sourceRootPath: String?): Module {
    val newQueryBuilder = queryBuilder.select("asModule")
    return Module(newQueryBuilder, engineClient)
  }

  /**
   * Gets the difference between this directory and an another directory.
   */
  public fun diff(other: DirectoryID): Directory {
    val newQueryBuilder = queryBuilder.select("diff")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves a directory at the given path.
   */
  public fun directory(path: String): Directory {
    val newQueryBuilder = queryBuilder.select("directory")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Builds a new Docker container from this directory.
   */
  public fun dockerBuild(
    buildArgs: List<BuildArg>,
    dockerfile: String?,
    platform: Platform?,
    secrets: List<SecretID>,
    target: String?,
  ): Container {
    val newQueryBuilder = queryBuilder.select("dockerBuild")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * Returns a list of files and directories at the given path.
   */
  public suspend fun entries(path: String?): List<String> {
    val newQueryBuilder = queryBuilder.select("entries")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Writes the contents of the directory to a path on the host.
   */
  public suspend fun export(path: String, wipe: Boolean?): Boolean {
    val newQueryBuilder = queryBuilder.select("export")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves a file at the given path.
   */
  public fun `file`(path: String): File {
    val newQueryBuilder = queryBuilder.select("file")
    return File(newQueryBuilder, engineClient)
  }

  /**
   * Returns a list of files and directories that matche the given pattern.
   */
  public suspend fun glob(pattern: String): List<String> {
    val newQueryBuilder = queryBuilder.select("glob")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this Directory.
   */
  public suspend fun id(): DirectoryID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Creates a named sub-pipeline.
   */
  public fun pipeline(
    name: String,
    description: String?,
    labels: List<PipelineLabel>,
  ): Directory {
    val newQueryBuilder = queryBuilder.select("pipeline")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Force evaluation in the engine.
   */
  public suspend fun sync(): DirectoryID {
    val newQueryBuilder = queryBuilder.select("sync")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves this directory plus a directory written at the given path.
   */
  public fun withDirectory(
    directory: DirectoryID,
    path: String,
    exclude: List<String>,
    include: List<String>,
  ): Directory {
    val newQueryBuilder = queryBuilder.select("withDirectory")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this directory plus the contents of the given file copied to the given path.
   */
  public fun withFile(
    path: String,
    source: FileID,
    permissions: Int?,
  ): Directory {
    val newQueryBuilder = queryBuilder.select("withFile")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this directory plus the contents of the given files copied to the given path.
   */
  public fun withFiles(
    path: String,
    sources: List<FileID>,
    permissions: Int?,
  ): Directory {
    val newQueryBuilder = queryBuilder.select("withFiles")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this directory plus a new directory created at the given path.
   */
  public fun withNewDirectory(path: String, permissions: Int?): Directory {
    val newQueryBuilder = queryBuilder.select("withNewDirectory")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this directory plus a new file written at the given path.
   */
  public fun withNewFile(
    contents: String,
    path: String,
    permissions: Int?,
  ): Directory {
    val newQueryBuilder = queryBuilder.select("withNewFile")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this directory with all file/dir timestamps set to the given time.
   */
  public fun withTimestamps(timestamp: Int): Directory {
    val newQueryBuilder = queryBuilder.select("withTimestamps")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this directory with the directory at the given path removed.
   */
  public fun withoutDirectory(path: String): Directory {
    val newQueryBuilder = queryBuilder.select("withoutDirectory")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves this directory with the file at the given path removed.
   */
  public fun withoutFile(path: String): Directory {
    val newQueryBuilder = queryBuilder.select("withoutFile")
    return Directory(newQueryBuilder, engineClient)
  }
}

/**
 * The `DirectoryID` scalar type represents an identifier for an object of type Directory.
 */
public typealias DirectoryID = String

/**
 * An environment variable name and value.
 */
public class EnvVariable(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A unique identifier for this EnvVariable.
   */
  public suspend fun id(): EnvVariableID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The environment variable name.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The environment variable value.
   */
  public suspend fun `value`(): String {
    val newQueryBuilder = queryBuilder.select("value")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `EnvVariableID` scalar type represents an identifier for an object of type EnvVariable.
 */
public typealias EnvVariableID = String

/**
 * A definition of a field on a custom object defined in a Module.
 *
 * A field on an object has a static value, as opposed to a function on an object whose value is
 * computed by invoking code (and can accept arguments).
 */
public class FieldTypeDef(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A doc string for the field, if any.
   */
  public suspend fun description(): String {
    val newQueryBuilder = queryBuilder.select("description")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this FieldTypeDef.
   */
  public suspend fun id(): FieldTypeDefID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the field in lowerCamelCase format.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The type of the field.
   */
  public fun typeDef(): TypeDef {
    val newQueryBuilder = queryBuilder.select("typeDef")
    return TypeDef(newQueryBuilder, engineClient)
  }
}

/**
 * The `FieldTypeDefID` scalar type represents an identifier for an object of type FieldTypeDef.
 */
public typealias FieldTypeDefID = String

/**
 * A file.
 */
public class File(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * Retrieves the contents of the file.
   */
  public suspend fun contents(): String {
    val newQueryBuilder = queryBuilder.select("contents")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Writes the file to a file path on the host.
   */
  public suspend fun export(path: String, allowParentDirPath: Boolean?): Boolean {
    val newQueryBuilder = queryBuilder.select("export")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this File.
   */
  public suspend fun id(): FileID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves the name of the file.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves the size of the file, in bytes.
   */
  public suspend fun size(): Int {
    val newQueryBuilder = queryBuilder.select("size")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Force evaluation in the engine.
   */
  public suspend fun sync(): FileID {
    val newQueryBuilder = queryBuilder.select("sync")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves this file with its created/modified timestamps set to the given time.
   */
  public fun withTimestamps(timestamp: Int): File {
    val newQueryBuilder = queryBuilder.select("withTimestamps")
    return File(newQueryBuilder, engineClient)
  }
}

/**
 * The `FileID` scalar type represents an identifier for an object of type File.
 */
public typealias FileID = String

/**
 * Function represents a resolver provided by a Module.
 *
 * A function always evaluates against a parent object and is given a set of named arguments.
 */
public class Function(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * Arguments accepted by the function, if any.
   */
  public suspend fun args(): List<FunctionArg> {
    val newQueryBuilder = queryBuilder.select("args")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A doc string for the function, if any.
   */
  public suspend fun description(): String {
    val newQueryBuilder = queryBuilder.select("description")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this Function.
   */
  public suspend fun id(): FunctionID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the function.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The type returned by the function.
   */
  public fun returnType(): TypeDef {
    val newQueryBuilder = queryBuilder.select("returnType")
    return TypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Returns the function with the provided argument
   */
  public fun withArg(
    name: String,
    typeDef: TypeDefID,
    defaultValue: JSON?,
    description: String?,
  ): Function {
    val newQueryBuilder = queryBuilder.select("withArg")
    return Function(newQueryBuilder, engineClient)
  }

  /**
   * Returns the function with the given doc string.
   */
  public fun withDescription(description: String): Function {
    val newQueryBuilder = queryBuilder.select("withDescription")
    return Function(newQueryBuilder, engineClient)
  }
}

/**
 * An argument accepted by a function.
 *
 * This is a specification for an argument at function definition time, not an argument passed at
 * function call time.
 */
public class FunctionArg(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A default value to use for this argument when not explicitly set by the caller, if any.
   */
  public suspend fun defaultValue(): JSON {
    val newQueryBuilder = queryBuilder.select("defaultValue")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A doc string for the argument, if any.
   */
  public suspend fun description(): String {
    val newQueryBuilder = queryBuilder.select("description")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this FunctionArg.
   */
  public suspend fun id(): FunctionArgID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the argument in lowerCamelCase format.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The type of the argument.
   */
  public fun typeDef(): TypeDef {
    val newQueryBuilder = queryBuilder.select("typeDef")
    return TypeDef(newQueryBuilder, engineClient)
  }
}

/**
 * The `FunctionArgID` scalar type represents an identifier for an object of type FunctionArg.
 */
public typealias FunctionArgID = String

/**
 * An active function call.
 */
public class FunctionCall(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A unique identifier for this FunctionCall.
   */
  public suspend fun id(): FunctionCallID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The argument values the function is being invoked with.
   */
  public suspend fun inputArgs(): List<FunctionCallArgValue> {
    val newQueryBuilder = queryBuilder.select("inputArgs")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the function being called.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The value of the parent object of the function being called. If the function is top-level to
   * the module, this is always an empty object.
   */
  public suspend fun parent(): JSON {
    val newQueryBuilder = queryBuilder.select("parent")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the parent object of the function being called. If the function is top-level to the
   * module, this is the name of the module.
   */
  public suspend fun parentName(): String {
    val newQueryBuilder = queryBuilder.select("parentName")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Set the return value of the function call to the provided value.
   */
  public suspend fun returnValue(`value`: JSON): Void {
    val newQueryBuilder = queryBuilder.select("returnValue")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * A value passed as a named argument to a function call.
 */
public class FunctionCallArgValue(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A unique identifier for this FunctionCallArgValue.
   */
  public suspend fun id(): FunctionCallArgValueID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the argument.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The value of the argument represented as a JSON serialized string.
   */
  public suspend fun `value`(): JSON {
    val newQueryBuilder = queryBuilder.select("value")
    return engineClient.execute(newQueryBuilder)
  }
}

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

/**
 * The result of running an SDK's codegen.
 */
public class GeneratedCode(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * The directory containing the generated code.
   */
  public fun code(): Directory {
    val newQueryBuilder = queryBuilder.select("code")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * A unique identifier for this GeneratedCode.
   */
  public suspend fun id(): GeneratedCodeID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * List of paths to mark generated in version control (i.e. .gitattributes).
   */
  public suspend fun vcsGeneratedPaths(): List<String> {
    val newQueryBuilder = queryBuilder.select("vcsGeneratedPaths")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * List of paths to ignore in version control (i.e. .gitignore).
   */
  public suspend fun vcsIgnoredPaths(): List<String> {
    val newQueryBuilder = queryBuilder.select("vcsIgnoredPaths")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Set the list of paths to mark generated in version control.
   */
  public fun withVCSGeneratedPaths(paths: List<String>): GeneratedCode {
    val newQueryBuilder = queryBuilder.select("withVCSGeneratedPaths")
    return GeneratedCode(newQueryBuilder, engineClient)
  }

  /**
   * Set the list of paths to ignore in version control.
   */
  public fun withVCSIgnoredPaths(paths: List<String>): GeneratedCode {
    val newQueryBuilder = queryBuilder.select("withVCSIgnoredPaths")
    return GeneratedCode(newQueryBuilder, engineClient)
  }
}

/**
 * The `GeneratedCodeID` scalar type represents an identifier for an object of type GeneratedCode.
 */
public typealias GeneratedCodeID = String

/**
 * Module source originating from a git repo.
 */
public class GitModuleSource(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * The URL from which the source's git repo can be cloned.
   */
  public suspend fun cloneURL(): String {
    val newQueryBuilder = queryBuilder.select("cloneURL")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The resolved commit of the git repo this source points to.
   */
  public suspend fun commit(): String {
    val newQueryBuilder = queryBuilder.select("commit")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The directory containing everything needed to load load and use the module.
   */
  public fun contextDirectory(): Directory {
    val newQueryBuilder = queryBuilder.select("contextDirectory")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * The URL to the source's git repo in a web browser
   */
  public suspend fun htmlURL(): String {
    val newQueryBuilder = queryBuilder.select("htmlURL")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this GitModuleSource.
   */
  public suspend fun id(): GitModuleSourceID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The path to the root of the module source under the context directory. This directory contains
   * its configuration file. It also contains its source code (possibly as a subdirectory).
   */
  public suspend fun rootSubpath(): String {
    val newQueryBuilder = queryBuilder.select("rootSubpath")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The specified version of the git repo this source points to.
   */
  public suspend fun version(): String {
    val newQueryBuilder = queryBuilder.select("version")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `GitModuleSourceID` scalar type represents an identifier for an object of type
 * GitModuleSource.
 */
public typealias GitModuleSourceID = String

/**
 * A git ref (tag, branch, or commit).
 */
public class GitRef(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * The resolved commit id at this ref.
   */
  public suspend fun commit(): String {
    val newQueryBuilder = queryBuilder.select("commit")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this GitRef.
   */
  public suspend fun id(): GitRefID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The filesystem tree at this ref.
   */
  public fun tree(sshAuthSocket: SocketID?, sshKnownHosts: String?): Directory {
    val newQueryBuilder = queryBuilder.select("tree")
    return Directory(newQueryBuilder, engineClient)
  }
}

/**
 * The `GitRefID` scalar type represents an identifier for an object of type GitRef.
 */
public typealias GitRefID = String

/**
 * A git repository.
 */
public class GitRepository(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * Returns details of a branch.
   */
  public fun branch(name: String): GitRef {
    val newQueryBuilder = queryBuilder.select("branch")
    return GitRef(newQueryBuilder, engineClient)
  }

  /**
   * Returns details of a commit.
   */
  public fun commit(id: String): GitRef {
    val newQueryBuilder = queryBuilder.select("commit")
    return GitRef(newQueryBuilder, engineClient)
  }

  /**
   * Returns details for HEAD.
   */
  public fun head(): GitRef {
    val newQueryBuilder = queryBuilder.select("head")
    return GitRef(newQueryBuilder, engineClient)
  }

  /**
   * A unique identifier for this GitRepository.
   */
  public suspend fun id(): GitRepositoryID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Returns details of a ref.
   */
  public fun ref(name: String): GitRef {
    val newQueryBuilder = queryBuilder.select("ref")
    return GitRef(newQueryBuilder, engineClient)
  }

  /**
   * Returns details of a tag.
   */
  public fun tag(name: String): GitRef {
    val newQueryBuilder = queryBuilder.select("tag")
    return GitRef(newQueryBuilder, engineClient)
  }
}

/**
 * The `GitRepositoryID` scalar type represents an identifier for an object of type GitRepository.
 */
public typealias GitRepositoryID = String

/**
 * Information about the host environment.
 */
public class Host(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * Accesses a directory on the host.
   */
  public fun directory(
    path: String,
    exclude: List<String>,
    include: List<String>,
  ): Directory {
    val newQueryBuilder = queryBuilder.select("directory")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Accesses a file on the host.
   */
  public fun `file`(path: String): File {
    val newQueryBuilder = queryBuilder.select("file")
    return File(newQueryBuilder, engineClient)
  }

  /**
   * A unique identifier for this Host.
   */
  public suspend fun id(): HostID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Creates a service that forwards traffic to a specified address via the host.
   */
  public fun service(ports: List<PortForward>, host: String?): Service {
    val newQueryBuilder = queryBuilder.select("service")
    return Service(newQueryBuilder, engineClient)
  }

  /**
   * Sets a secret given a user-defined name and the file path on the host, and returns the secret.
   *
   * The file is limited to a size of 512000 bytes.
   */
  public fun setSecretFile(name: String, path: String): Secret {
    val newQueryBuilder = queryBuilder.select("setSecretFile")
    return Secret(newQueryBuilder, engineClient)
  }

  /**
   * Creates a tunnel that forwards traffic from the host to a service.
   */
  public fun tunnel(
    service: ServiceID,
    native: Boolean?,
    ports: List<PortForward>,
  ): Service {
    val newQueryBuilder = queryBuilder.select("tunnel")
    return Service(newQueryBuilder, engineClient)
  }

  /**
   * Accesses a Unix socket on the host.
   */
  public fun unixSocket(path: String): Socket {
    val newQueryBuilder = queryBuilder.select("unixSocket")
    return Socket(newQueryBuilder, engineClient)
  }
}

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

/**
 * A graphql input type, which is essentially just a group of named args.
 * This is currently only used to represent pre-existing usage of graphql input types
 * in the core API. It is not used by user modules and shouldn't ever be as user
 * module accept input objects via their id rather than graphql input types.
 */
public class InputTypeDef(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * Static fields defined on this input object, if any.
   */
  public suspend fun fields(): List<FieldTypeDef> {
    val newQueryBuilder = queryBuilder.select("fields")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this InputTypeDef.
   */
  public suspend fun id(): InputTypeDefID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the input object.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `InputTypeDefID` scalar type represents an identifier for an object of type InputTypeDef.
 */
public typealias InputTypeDefID = String

/**
 * A definition of a custom interface defined in a Module.
 */
public class InterfaceTypeDef(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * The doc string for the interface, if any.
   */
  public suspend fun description(): String {
    val newQueryBuilder = queryBuilder.select("description")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Functions defined on this interface, if any.
   */
  public suspend fun functions(): List<Function> {
    val newQueryBuilder = queryBuilder.select("functions")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this InterfaceTypeDef.
   */
  public suspend fun id(): InterfaceTypeDefID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the interface.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * If this InterfaceTypeDef is associated with a Module, the name of the module. Unset otherwise.
   */
  public suspend fun sourceModuleName(): String {
    val newQueryBuilder = queryBuilder.select("sourceModuleName")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `InterfaceTypeDefID` scalar type represents an identifier for an object of type
 * InterfaceTypeDef.
 */
public typealias InterfaceTypeDefID = String

/**
 * An arbitrary JSON-encoded value.
 */
public typealias JSON = String

/**
 * A simple key value object that represents a label.
 */
public class Label(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A unique identifier for this Label.
   */
  public suspend fun id(): LabelID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The label name.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The label value.
   */
  public suspend fun `value`(): String {
    val newQueryBuilder = queryBuilder.select("value")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `LabelID` scalar type represents an identifier for an object of type Label.
 */
public typealias LabelID = String

/**
 * A definition of a list type in a Module.
 */
public class ListTypeDef(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * The type of the elements in the list.
   */
  public fun elementTypeDef(): TypeDef {
    val newQueryBuilder = queryBuilder.select("elementTypeDef")
    return TypeDef(newQueryBuilder, engineClient)
  }

  /**
   * A unique identifier for this ListTypeDef.
   */
  public suspend fun id(): ListTypeDefID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `ListTypeDefID` scalar type represents an identifier for an object of type ListTypeDef.
 */
public typealias ListTypeDefID = String

/**
 * Module source that that originates from a path locally relative to an arbitrary directory.
 */
public class LocalModuleSource(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * The directory containing everything needed to load load and use the module.
   */
  public fun contextDirectory(): Directory {
    val newQueryBuilder = queryBuilder.select("contextDirectory")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * A unique identifier for this LocalModuleSource.
   */
  public suspend fun id(): LocalModuleSourceID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The path to the root of the module source under the context directory. This directory contains
   * its configuration file. It also contains its source code (possibly as a subdirectory).
   */
  public suspend fun rootSubpath(): String {
    val newQueryBuilder = queryBuilder.select("rootSubpath")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `LocalModuleSourceID` scalar type represents an identifier for an object of type
 * LocalModuleSource.
 */
public typealias LocalModuleSourceID = String

/**
 * A Dagger module.
 */
public class Module(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * Modules used by this module.
   */
  public suspend fun dependencies(): List<Module> {
    val newQueryBuilder = queryBuilder.select("dependencies")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The dependencies as configured by the module.
   */
  public suspend fun dependencyConfig(): List<ModuleDependency> {
    val newQueryBuilder = queryBuilder.select("dependencyConfig")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The doc string of the module, if any
   */
  public suspend fun description(): String {
    val newQueryBuilder = queryBuilder.select("description")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The generated files and directories made on top of the module source's context directory.
   */
  public fun generatedContextDiff(): Directory {
    val newQueryBuilder = queryBuilder.select("generatedContextDiff")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * The module source's context plus any configuration and source files created by codegen.
   */
  public fun generatedContextDirectory(): Directory {
    val newQueryBuilder = queryBuilder.select("generatedContextDirectory")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * A unique identifier for this Module.
   */
  public suspend fun id(): ModuleID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves the module with the objects loaded via its SDK.
   */
  public fun initialize(): Module {
    val newQueryBuilder = queryBuilder.select("initialize")
    return Module(newQueryBuilder, engineClient)
  }

  /**
   * Interfaces served by this module.
   */
  public suspend fun interfaces(): List<TypeDef> {
    val newQueryBuilder = queryBuilder.select("interfaces")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the module
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Objects served by this module.
   */
  public suspend fun objects(): List<TypeDef> {
    val newQueryBuilder = queryBuilder.select("objects")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The container that runs the module's entrypoint. It will fail to execute if the module doesn't
   * compile.
   */
  public fun runtime(): Container {
    val newQueryBuilder = queryBuilder.select("runtime")
    return Container(newQueryBuilder, engineClient)
  }

  /**
   * The SDK used by this module. Either a name of a builtin SDK or a module source ref string
   * pointing to the SDK's implementation.
   */
  public suspend fun sdk(): String {
    val newQueryBuilder = queryBuilder.select("sdk")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Serve a module's API in the current session.
   *
   * Note: this can only be called once per session. In the future, it could return a stream or
   * service to remove the side effect.
   */
  public suspend fun serve(): Void {
    val newQueryBuilder = queryBuilder.select("serve")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The source for the module.
   */
  public fun source(): ModuleSource {
    val newQueryBuilder = queryBuilder.select("source")
    return ModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves the module with the given description
   */
  public fun withDescription(description: String): Module {
    val newQueryBuilder = queryBuilder.select("withDescription")
    return Module(newQueryBuilder, engineClient)
  }

  /**
   * This module plus the given Interface type and associated functions
   */
  public fun withInterface(iface: TypeDefID): Module {
    val newQueryBuilder = queryBuilder.select("withInterface")
    return Module(newQueryBuilder, engineClient)
  }

  /**
   * This module plus the given Object type and associated functions.
   */
  public fun withObject(`object`: TypeDefID): Module {
    val newQueryBuilder = queryBuilder.select("withObject")
    return Module(newQueryBuilder, engineClient)
  }

  /**
   * Retrieves the module with basic configuration loaded if present.
   */
  public fun withSource(source: ModuleSourceID): Module {
    val newQueryBuilder = queryBuilder.select("withSource")
    return Module(newQueryBuilder, engineClient)
  }
}

/**
 * The configuration of dependency of a module.
 */
public class ModuleDependency(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A unique identifier for this ModuleDependency.
   */
  public suspend fun id(): ModuleDependencyID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the dependency module.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The source for the dependency module.
   */
  public fun source(): ModuleSource {
    val newQueryBuilder = queryBuilder.select("source")
    return ModuleSource(newQueryBuilder, engineClient)
  }
}

/**
 * The `ModuleDependencyID` scalar type represents an identifier for an object of type
 * ModuleDependency.
 */
public typealias ModuleDependencyID = String

/**
 * The `ModuleID` scalar type represents an identifier for an object of type Module.
 */
public typealias ModuleID = String

/**
 * The source needed to load and run a module, along with any metadata about the source such as
 * versions/urls/etc.
 */
public class ModuleSource(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * If the source is a of kind git, the git source representation of it.
   */
  public fun asGitSource(): GitModuleSource {
    val newQueryBuilder = queryBuilder.select("asGitSource")
    return GitModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * If the source is of kind local, the local source representation of it.
   */
  public fun asLocalSource(): LocalModuleSource {
    val newQueryBuilder = queryBuilder.select("asLocalSource")
    return LocalModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Load the source as a module. If this is a local source, the parent directory must have been
   * provided during module source creation
   */
  public fun asModule(): Module {
    val newQueryBuilder = queryBuilder.select("asModule")
    return Module(newQueryBuilder, engineClient)
  }

  /**
   * A human readable ref string representation of this module source.
   */
  public suspend fun asString(): String {
    val newQueryBuilder = queryBuilder.select("asString")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Returns whether the module source has a configuration file.
   */
  public suspend fun configExists(): Boolean {
    val newQueryBuilder = queryBuilder.select("configExists")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The directory containing everything needed to load load and use the module.
   */
  public fun contextDirectory(): Directory {
    val newQueryBuilder = queryBuilder.select("contextDirectory")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * The dependencies of the module source. Includes dependencies from the configuration and any
   * extras from withDependencies calls.
   */
  public suspend fun dependencies(): List<ModuleDependency> {
    val newQueryBuilder = queryBuilder.select("dependencies")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The directory containing the module configuration and source code (source code may be in a
   * subdir).
   */
  public fun directory(path: String): Directory {
    val newQueryBuilder = queryBuilder.select("directory")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * A unique identifier for this ModuleSource.
   */
  public suspend fun id(): ModuleSourceID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The kind of source (e.g. local, git, etc.)
   */
  public suspend fun kind(): ModuleSourceKind {
    val newQueryBuilder = queryBuilder.select("kind")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * If set, the name of the module this source references, including any overrides at runtime by
   * callers.
   */
  public suspend fun moduleName(): String {
    val newQueryBuilder = queryBuilder.select("moduleName")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The original name of the module this source references, as defined in the module configuration.
   */
  public suspend fun moduleOriginalName(): String {
    val newQueryBuilder = queryBuilder.select("moduleOriginalName")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The path to the module source's context directory on the caller's filesystem. Only valid for
   * local sources.
   */
  public suspend fun resolveContextPathFromCaller(): String {
    val newQueryBuilder = queryBuilder.select("resolveContextPathFromCaller")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Resolve the provided module source arg as a dependency relative to this module source.
   */
  public fun resolveDependency(dep: ModuleSourceID): ModuleSource {
    val newQueryBuilder = queryBuilder.select("resolveDependency")
    return ModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Load a directory from the caller optionally with a given view applied.
   */
  public fun resolveDirectoryFromCaller(path: String, viewName: String?): Directory {
    val newQueryBuilder = queryBuilder.select("resolveDirectoryFromCaller")
    return Directory(newQueryBuilder, engineClient)
  }

  /**
   * Load the source from its path on the caller's filesystem, including only needed+configured
   * files and directories. Only valid for local sources.
   */
  public fun resolveFromCaller(): ModuleSource {
    val newQueryBuilder = queryBuilder.select("resolveFromCaller")
    return ModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * The path relative to context of the root of the module source, which contains dagger.json. It
   * also contains the module implementation source code, but that may or may not being a subdir of
   * this root.
   */
  public suspend fun sourceRootSubpath(): String {
    val newQueryBuilder = queryBuilder.select("sourceRootSubpath")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The path relative to context of the module implementation source code.
   */
  public suspend fun sourceSubpath(): String {
    val newQueryBuilder = queryBuilder.select("sourceSubpath")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieve a named view defined for this module source.
   */
  public fun view(name: String): ModuleSourceView {
    val newQueryBuilder = queryBuilder.select("view")
    return ModuleSourceView(newQueryBuilder, engineClient)
  }

  /**
   * The named views defined for this module source, which are sets of directory filters that can be
   * applied to directory arguments provided to functions.
   */
  public suspend fun views(): List<ModuleSourceView> {
    val newQueryBuilder = queryBuilder.select("views")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Update the module source with a new context directory. Only valid for local sources.
   */
  public fun withContextDirectory(dir: DirectoryID): ModuleSource {
    val newQueryBuilder = queryBuilder.select("withContextDirectory")
    return ModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Append the provided dependencies to the module source's dependency list.
   */
  public fun withDependencies(dependencies: List<ModuleDependencyID>): ModuleSource {
    val newQueryBuilder = queryBuilder.select("withDependencies")
    return ModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Update the module source with a new name.
   */
  public fun withName(name: String): ModuleSource {
    val newQueryBuilder = queryBuilder.select("withName")
    return ModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Update the module source with a new SDK.
   */
  public fun withSDK(sdk: String): ModuleSource {
    val newQueryBuilder = queryBuilder.select("withSDK")
    return ModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Update the module source with a new source subpath.
   */
  public fun withSourceSubpath(path: String): ModuleSource {
    val newQueryBuilder = queryBuilder.select("withSourceSubpath")
    return ModuleSource(newQueryBuilder, engineClient)
  }

  /**
   * Update the module source with a new named view.
   */
  public fun withView(name: String, patterns: List<String>): ModuleSource {
    val newQueryBuilder = queryBuilder.select("withView")
    return ModuleSource(newQueryBuilder, engineClient)
  }
}

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

/**
 * A named set of path filters that can be applied to directory arguments provided to functions.
 */
public class ModuleSourceView(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A unique identifier for this ModuleSourceView.
   */
  public suspend fun id(): ModuleSourceViewID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the view
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The patterns of the view used to filter paths
   */
  public suspend fun patterns(): List<String> {
    val newQueryBuilder = queryBuilder.select("patterns")
    return engineClient.execute(newQueryBuilder)
  }
}

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

/**
 * A definition of a custom object defined in a Module.
 */
public class ObjectTypeDef(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * The function used to construct new instances of this object, if any
   */
  public fun `constructor`(): Function {
    val newQueryBuilder = queryBuilder.select("constructor")
    return Function(newQueryBuilder, engineClient)
  }

  /**
   * The doc string for the object, if any.
   */
  public suspend fun description(): String {
    val newQueryBuilder = queryBuilder.select("description")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Static fields defined on this object, if any.
   */
  public suspend fun fields(): List<FieldTypeDef> {
    val newQueryBuilder = queryBuilder.select("fields")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Functions defined on this object, if any.
   */
  public suspend fun functions(): List<Function> {
    val newQueryBuilder = queryBuilder.select("functions")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this ObjectTypeDef.
   */
  public suspend fun id(): ObjectTypeDefID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of the object.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * If this ObjectTypeDef is associated with a Module, the name of the module. Unset otherwise.
   */
  public suspend fun sourceModuleName(): String {
    val newQueryBuilder = queryBuilder.select("sourceModuleName")
    return engineClient.execute(newQueryBuilder)
  }
}

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

/**
 * A port exposed by a container.
 */
public class Port(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * The port description.
   */
  public suspend fun description(): String {
    val newQueryBuilder = queryBuilder.select("description")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Skip the health check when run as a service.
   */
  public suspend fun experimentalSkipHealthcheck(): Boolean {
    val newQueryBuilder = queryBuilder.select("experimentalSkipHealthcheck")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this Port.
   */
  public suspend fun id(): PortID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The port number.
   */
  public suspend fun port(): Int {
    val newQueryBuilder = queryBuilder.select("port")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The transport layer protocol.
   */
  public suspend fun protocol(): NetworkProtocol {
    val newQueryBuilder = queryBuilder.select("protocol")
    return engineClient.execute(newQueryBuilder)
  }
}

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

/**
 * A reference to a secret value, which can be handled more safely than the value itself.
 */
public class Secret(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A unique identifier for this Secret.
   */
  public suspend fun id(): SecretID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The name of this secret.
   */
  public suspend fun name(): String {
    val newQueryBuilder = queryBuilder.select("name")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The value of this secret.
   */
  public suspend fun plaintext(): String {
    val newQueryBuilder = queryBuilder.select("plaintext")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `SecretID` scalar type represents an identifier for an object of type Secret.
 */
public typealias SecretID = String

/**
 * A content-addressed service providing TCP connectivity.
 */
public class Service(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * Retrieves an endpoint that clients can use to reach this container.
   *
   * If no port is specified, the first exposed port is used. If none exist an error is returned.
   *
   * If a scheme is specified, a URL is returned. Otherwise, a host:port pair is returned.
   */
  public suspend fun endpoint(port: Int?, scheme: String?): String {
    val newQueryBuilder = queryBuilder.select("endpoint")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves a hostname which can be used by clients to reach this container.
   */
  public suspend fun hostname(): String {
    val newQueryBuilder = queryBuilder.select("hostname")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * A unique identifier for this Service.
   */
  public suspend fun id(): ServiceID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Retrieves the list of ports provided by the service.
   */
  public suspend fun ports(): List<Port> {
    val newQueryBuilder = queryBuilder.select("ports")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Start the service and wait for its health checks to succeed.
   *
   * Services bound to a Container do not need to be manually started.
   */
  public suspend fun start(): ServiceID {
    val newQueryBuilder = queryBuilder.select("start")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Stop the service.
   */
  public suspend fun stop(kill: Boolean?): ServiceID {
    val newQueryBuilder = queryBuilder.select("stop")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Creates a tunnel that forwards traffic from the caller's network to this service.
   */
  public suspend fun up(ports: List<PortForward>, random: Boolean?): Void {
    val newQueryBuilder = queryBuilder.select("up")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `ServiceID` scalar type represents an identifier for an object of type Service.
 */
public typealias ServiceID = String

/**
 * A Unix or TCP/IP socket that can be mounted into a container.
 */
public class Socket(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A unique identifier for this Socket.
   */
  public suspend fun id(): SocketID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `SocketID` scalar type represents an identifier for an object of type Socket.
 */
public typealias SocketID = String

/**
 * An interactive terminal that clients can connect to.
 */
public class Terminal(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * A unique identifier for this Terminal.
   */
  public suspend fun id(): TerminalID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * An http endpoint at which this terminal can be connected to over a websocket.
   */
  public suspend fun websocketEndpoint(): String {
    val newQueryBuilder = queryBuilder.select("websocketEndpoint")
    return engineClient.execute(newQueryBuilder)
  }
}

/**
 * The `TerminalID` scalar type represents an identifier for an object of type Terminal.
 */
public typealias TerminalID = String

/**
 * A definition of a parameter or return type in a Module.
 */
public class TypeDef(
  public val queryBuilder: QueryBuilder,
  public val engineClient: Engine,
) {
  /**
   * If kind is INPUT, the input-specific type definition. If kind is not INPUT, this will be null.
   */
  public fun asInput(): InputTypeDef {
    val newQueryBuilder = queryBuilder.select("asInput")
    return InputTypeDef(newQueryBuilder, engineClient)
  }

  /**
   * If kind is INTERFACE, the interface-specific type definition. If kind is not INTERFACE, this
   * will be null.
   */
  public fun asInterface(): InterfaceTypeDef {
    val newQueryBuilder = queryBuilder.select("asInterface")
    return InterfaceTypeDef(newQueryBuilder, engineClient)
  }

  /**
   * If kind is LIST, the list-specific type definition. If kind is not LIST, this will be null.
   */
  public fun asList(): ListTypeDef {
    val newQueryBuilder = queryBuilder.select("asList")
    return ListTypeDef(newQueryBuilder, engineClient)
  }

  /**
   * If kind is OBJECT, the object-specific type definition. If kind is not OBJECT, this will be
   * null.
   */
  public fun asObject(): ObjectTypeDef {
    val newQueryBuilder = queryBuilder.select("asObject")
    return ObjectTypeDef(newQueryBuilder, engineClient)
  }

  /**
   * A unique identifier for this TypeDef.
   */
  public suspend fun id(): TypeDefID {
    val newQueryBuilder = queryBuilder.select("id")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * The kind of type this is (e.g. primitive, list, object).
   */
  public suspend fun kind(): TypeDefKind {
    val newQueryBuilder = queryBuilder.select("kind")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Whether this type can be set to null. Defaults to false.
   */
  public suspend fun optional(): Boolean {
    val newQueryBuilder = queryBuilder.select("optional")
    return engineClient.execute(newQueryBuilder)
  }

  /**
   * Adds a function for constructing a new instance of an Object TypeDef, failing if the type is
   * not an object.
   */
  public fun withConstructor(function: FunctionID): TypeDef {
    val newQueryBuilder = queryBuilder.select("withConstructor")
    return TypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Adds a static field for an Object TypeDef, failing if the type is not an object.
   */
  public fun withField(
    name: String,
    typeDef: TypeDefID,
    description: String?,
  ): TypeDef {
    val newQueryBuilder = queryBuilder.select("withField")
    return TypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Adds a function for an Object or Interface TypeDef, failing if the type is not one of those
   * kinds.
   */
  public fun withFunction(function: FunctionID): TypeDef {
    val newQueryBuilder = queryBuilder.select("withFunction")
    return TypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Returns a TypeDef of kind Interface with the provided name.
   */
  public fun withInterface(name: String, description: String?): TypeDef {
    val newQueryBuilder = queryBuilder.select("withInterface")
    return TypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Sets the kind of the type.
   */
  public fun withKind(kind: TypeDefKind): TypeDef {
    val newQueryBuilder = queryBuilder.select("withKind")
    return TypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Returns a TypeDef of kind List with the provided type for its elements.
   */
  public fun withListOf(elementType: TypeDefID): TypeDef {
    val newQueryBuilder = queryBuilder.select("withListOf")
    return TypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Returns a TypeDef of kind Object with the provided name.
   *
   * Note that an object's fields and functions may be omitted if the intent is only to refer to an
   * object. This is how functions are able to return their own object, or any other circular
   * reference.
   */
  public fun withObject(name: String, description: String?): TypeDef {
    val newQueryBuilder = queryBuilder.select("withObject")
    return TypeDef(newQueryBuilder, engineClient)
  }

  /**
   * Sets whether this type can be set to null.
   */
  public fun withOptional(optional: Boolean): TypeDef {
    val newQueryBuilder = queryBuilder.select("withOptional")
    return TypeDef(newQueryBuilder, engineClient)
  }
}

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
