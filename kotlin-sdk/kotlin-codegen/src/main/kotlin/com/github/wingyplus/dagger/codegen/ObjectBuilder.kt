package com.github.wingyplus.dagger.codegen

import com.github.wingyplus.dagger.graphql.FieldValue
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
                val requiredArgs = field
                    .args
                    .filter { it.type.kind == TypeKind.NON_NULL }
                    .sortedBy { it.name }

                val requiredArgParameterSpecs =
                    requiredArgs.map { ParameterSpec(it.name, typeOf(it.type)) }

                val optionalArgs = field
                    .args
                    .filterNot { it.type.kind == TypeKind.NON_NULL }
                    .sortedBy { it.name }


                val optionalArgParameterSpecs = optionalArgs.map { ParameterSpec(it.name, typeOf(it.type)) }

                val funSpec = FunSpec
                    .builder(field.name)
                    .addKdoc(field.description)
                    .addParameters(requiredArgParameterSpecs + optionalArgParameterSpecs)
                    .addCode(codeBlockFromField(field))

                if (returnScalar(field) || returnList(field)) {
                    funSpec
                        .addModifiers(KModifier.SUSPEND)
                        .returns(typeOf(field.type).copy(nullable = false))
                } else {
                    funSpec
                        .returns(typeOf(field.type))
                }


                funSpec.build()
            }

        return TypeSpec
            .classBuilder(normalizeName(type.name))
            .addKdoc(type.description)
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .addParameter("queryBuilder", ClassName("com.github.wingyplus.dagger", "QueryBuilder"))
                    .addParameter("engineClient", ClassName("com.github.wingyplus.dagger", "Engine"))
                    .build()
            )
            .addFunctions(functions)
            .addProperty(
                PropertySpec
                    .builder("queryBuilder", ClassName("com.github.wingyplus.dagger", "QueryBuilder"))
                    .initializer("queryBuilder")
                    .build()
            )
            .addProperty(
                PropertySpec
                    .builder("engineClient", ClassName("com.github.wingyplus.dagger", "Engine"))
                    .initializer("engineClient")
                    .build()
            )
            .build()
    }

    private fun normalizeName(name: String): String {
        if (name == "Query") { return "Client" }
        return name
    }

    private fun codeBlockFromField(field: FieldValue): CodeBlock {
        val builder = CodeBlock.builder()
            .addStatement("val newQueryBuilder = queryBuilder.select(%S)", field.name)

        if (returnScalar(field) || returnList(field)) {
            builder
                .addStatement("return engineClient.execute(newQueryBuilder)", typeOf(field.type))
        } else {
            builder
                .addStatement("return %T(newQueryBuilder, engineClient)", typeOf(field.type))
        }

        return builder.build()
    }

    private fun returnList(field: FieldValue) =
        field.type.kind == TypeKind.NON_NULL && field.type.ofType!!.kind == TypeKind.LIST

    private fun returnScalar(field: FieldValue) =
        field.type.kind == TypeKind.SCALAR || field.type.kind == TypeKind.NON_NULL && field.type.ofType!!.kind == TypeKind.SCALAR

    private fun typeOf(type: TypeRef): TypeName {
        if (type.kind == TypeKind.NON_NULL) {
            return typeOf(type.ofType!!).copy(nullable = false)
        }
        if (type.kind == TypeKind.LIST) {
            return LIST.parameterizedBy(typeOf(type.ofType!!))
        }
        return typeToClassName(normalizeName(type.name)).copy(nullable = true)
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