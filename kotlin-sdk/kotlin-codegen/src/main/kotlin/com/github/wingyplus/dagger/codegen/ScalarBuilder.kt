package com.github.wingyplus.dagger.codegen

import com.github.wingyplus.dagger.graphql.FullType
import com.squareup.kotlinpoet.TypeAliasSpec
import kotlinx.serialization.Serializable

class ScalarBuilder {
    fun build(type: FullType): TypeAliasSpec {
        return TypeAliasSpec
            .builder(type.name, String::class)
            .addAnnotation(Serializable::class)
            .addKdoc(type.description)
            .build()
    }
}