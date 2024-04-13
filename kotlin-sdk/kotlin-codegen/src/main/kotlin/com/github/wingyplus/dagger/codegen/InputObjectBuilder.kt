package com.github.wingyplus.dagger.codegen

import com.github.wingyplus.dagger.graphql.FullType
import com.github.wingyplus.dagger.graphql.TypeKind
import com.github.wingyplus.dagger.graphql.TypeRef
import com.squareup.kotlinpoet.*
import kotlinx.serialization.Serializable

class InputObjectBuilder {
    fun build(type: FullType): TypeSpec {
        val funSpec = type
            .inputFields
            .fold(FunSpec.constructorBuilder()) { builder, field ->
                builder.addParameter(field.name, typeOf(field.type))
            }
            .build()

        val typeSpec = TypeSpec
            .classBuilder(type.name)
            .addModifiers(KModifier.DATA)
            .addAnnotation(Serializable::class)
            .primaryConstructor(funSpec)
            .addKdoc(type.description)

        type
            .inputFields
            .fold(typeSpec) { typeSpec, field ->
                typeSpec.addProperty(
                    PropertySpec
                        .builder(field.name, typeOf(field.type))
                        .initializer(field.name)
                        .build()
                )
            }

        return typeSpec.build()
    }

    private fun typeOf(type: TypeRef): TypeName {
        if (type.kind == TypeKind.NON_NULL) {
            return typeToClassName(type.ofType!!.name)
        }
        return typeToClassName(type.name).copy(nullable = true)
    }

    private fun typeToClassName(name: String): ClassName {
        return when (name) {
            "String" -> ClassName("kotlin", "String")
            "Int" -> ClassName("kotlin", "Int")
            "Float" -> ClassName("kotlin", "Double")
            "Boolean" -> ClassName("kotlin", "Boolean")
            "DateTime" -> TODO()
            else -> ClassName(DAGGER_PACKAGE, name)
        }
    }
}