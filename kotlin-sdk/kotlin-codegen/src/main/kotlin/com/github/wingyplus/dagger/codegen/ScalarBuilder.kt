package com.github.wingyplus.dagger.codegen

import com.github.wingyplus.dagger.graphql.FullType
import com.squareup.kotlinpoet.TypeAliasSpec

class ScalarBuilder {
    fun build(type: FullType): TypeAliasSpec {
        return TypeAliasSpec
            .builder(type.name, String::class)
            .addKdoc(type.description)
            .build()
    }
}