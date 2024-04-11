@file:OptIn(ExperimentalSerializationApi::class)

import com.github.wingyplus.dagger.codegen.Generator
import com.github.wingyplus.dagger.graphql.Introspection
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.io.path.Path


fun main(args: Array<String>) {
    val parser = ArgParser("kotlin-codegen")
    val introspection by parser.option(
        ArgType.String,
        fullName = "introspection",
        shortName = "i",
        description = "Introspection file path"
    )
        .required()
    val output by parser.option(
        ArgType.String,
        fullName = "output",
        shortName = "o",
        description = "Output to file. If this option is not specify, it output to stdout."
    )
    parser.parse(args)

    val file = Generator.generate(Introspection.parse(introspection))

    if (output != null) {
        file.writeTo(Path(output!!))
    } else {
        file.writeTo(System.out)
    }
}