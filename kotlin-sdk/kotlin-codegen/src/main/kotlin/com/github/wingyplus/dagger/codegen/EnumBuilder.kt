package com.github.wingyplus.dagger.codegen

import com.github.wingyplus.dagger.graphql.FullType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import kotlinx.serialization.Serializable

class EnumBuilder {
    fun build(type: FullType): TypeSpec {
        val enumBuilder = TypeSpec.enumBuilder(type.name)
            .primaryConstructor(
                FunSpec
                    .constructorBuilder()
                    .addParameter("value", String::class)
                    .build()
            )
            .addAnnotation(Serializable::class)
            .addKdoc(type.description)
        return type
            .enumValues!!
            .fold(enumBuilder) { builder, enumValue ->
                builder
                    .addEnumConstant(
                        enumValue.name,
                        TypeSpec
                            .anonymousClassBuilder()
                            .addSuperclassConstructorParameter("%S", enumValue.name)
                            .build()
                    )
            }
            .build()
    }
}