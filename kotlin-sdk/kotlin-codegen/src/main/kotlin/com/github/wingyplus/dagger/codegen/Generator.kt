@file:OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)

package com.github.wingyplus.dagger.codegen

import com.github.wingyplus.dagger.graphql.Introspection
import com.github.wingyplus.dagger.graphql.TypeKind
import com.squareup.kotlinpoet.FileSpec

object Generator {

    /**
     * Generate code from introspection.
     */
    fun generate(introspection: Introspection): FileSpec {
        return introspection
            .schema
            .types
            // We use type from standard library instead.
            .filter { type -> type.name !in listOf("Boolean", "String", "Int", "Float", "DateTime") }
            .filter { type -> !type.name.startsWith("__") }
            .fold(FileSpec.builder(DAGGER_PACKAGE, "sdk.gen")) { builder, type ->
                when (type.kind) {
                    TypeKind.SCALAR -> builder.addTypeAlias(ScalarBuilder().build(type))
                    TypeKind.INPUT_OBJECT -> builder.addType(InputObjectBuilder().build(type))
                    TypeKind.ENUM -> builder.addType(EnumBuilder().build(type))
                    TypeKind.OBJECT -> builder.addType(ObjectBuilder().build(type))
                    else -> throw Exception("Type kind ${type.kind} is not supported.")
                }
            }
            .build()
    }
}