package com.github.wingyplus.dagger.codegen

import com.github.wingyplus.dagger.graphql.FullType
import com.squareup.kotlinpoet.TypeSpec

class ObjectBuilder {
    fun build(type: FullType): TypeSpec {
        return TypeSpec.classBuilder(type.name).build()
    }

}