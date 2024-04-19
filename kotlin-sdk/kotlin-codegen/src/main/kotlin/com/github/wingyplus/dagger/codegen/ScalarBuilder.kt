package com.github.wingyplus.dagger.codegen

import com.github.wingyplus.dagger.graphql.FullType
import com.squareup.kotlinpoet.TypeAliasSpec
import com.squareup.kotlinpoet.asTypeName

class ScalarBuilder {
    fun build(type: FullType): TypeAliasSpec {
        return TypeAliasSpec
            .builder(
                type.name,
                // Dagger will respond with `null` for any function that return Void type.
                if (type.name == "Void") {
                    String::class.asTypeName().copy(nullable = true)
                } else {
                    String::class.asTypeName()
                }
            )
            .addKdoc(type.description)
            .build()
    }
}