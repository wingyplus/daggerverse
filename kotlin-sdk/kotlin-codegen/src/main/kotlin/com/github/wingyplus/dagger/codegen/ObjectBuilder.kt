package com.github.wingyplus.dagger.codegen

import com.github.wingyplus.dagger.graphql.FullType
import com.github.wingyplus.dagger.graphql.TypeKind
import com.github.wingyplus.dagger.graphql.TypeRef
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

class ObjectBuilder {
    fun build(type: FullType): TypeSpec {
        val functions = type
            .fields
            .map { field ->
                FunSpec
                    .builder(field.name)
                    .addKdoc(field.description)
                    .returns(typeOf(field.type))
                    .build()
            }

        return TypeSpec
            .classBuilder(type.name)
            .addKdoc(type.description)
            .addFunctions(functions)
            .build()
    }

    private fun typeOf(type: TypeRef): TypeName {
        if (type.kind == TypeKind.NON_NULL) {
            return typeOf(type.ofType!!).copy(nullable = false)
        }
        if (type.kind == TypeKind.LIST) {
            val type = type.ofType!!
            return ARRAY.parameterizedBy(typeOf(type))
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
            else -> ClassName("com.github.wingyplus.dagger", name)
        }
    }
}