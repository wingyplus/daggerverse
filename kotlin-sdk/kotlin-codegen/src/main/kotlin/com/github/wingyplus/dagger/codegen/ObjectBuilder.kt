package com.github.wingyplus.dagger.codegen

import com.github.wingyplus.dagger.graphql.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

class ObjectBuilder {
    fun build(type: FullType): TypeSpec {
        val functions = type
            .fields
            .map { field ->
                val (requiredArgs, optionalArgs) = splitArgs(field.args)

                val requiredArgParameterSpecs =
                    requiredArgs
                        .sortedBy { it.name }
                        .map { ParameterSpec(it.name, typeOf(it.type)) }

                val optionalArgParameterSpecs =
                    optionalArgs
                        .sortedBy { it.name }
                        .map {
                            ParameterSpec
                                .builder(it.name, typeOf(it.type).copy(nullable = true))
                                .defaultValue("%L", null)
                                .build()
                        }

                val funSpec = FunSpec
                    .builder(field.name)
                    .addKdoc(field.description)
                    .addParameters(requiredArgParameterSpecs + optionalArgParameterSpecs)
                    .addCode(codeBlockFromField(field))

                if (returnScalar(field) || returnList(field) || returnEnum(field)) {
                    funSpec
                        .addModifiers(KModifier.SUSPEND)
                        .returns(typeOf(field.type).copy(nullable = false))
                } else if (returnObject(field)) {
                    funSpec
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
                    .addParameter("queryBuilder", QueryBuilderClassName)
                    .addParameter("engineClient", EngineClassName)
                    .build()
            )
            .addFunctions(functions)
            .addProperty(
                PropertySpec
                    .builder("queryBuilder", QueryBuilderClassName)
                    .initializer("queryBuilder")
                    .build()
            )
            .addProperty(
                PropertySpec
                    .builder("engineClient", EngineClassName)
                    .initializer("engineClient")
                    .build()
            )
            .build()
    }

    private fun normalizeName(name: String): String {
        if (name == "Query") {
            return "Client"
        }
        return name
    }

    private fun codeBlockFromField(field: FieldValue): CodeBlock {
        val (requiredArgs, optionalArgs) = splitArgs(field.args)
        val builder = CodeBlock.builder()

        if (requiredArgs.isEmpty() && optionalArgs.isEmpty()) {
            builder.addStatement("val newQueryBuilder = queryBuilder.select(%S)", field.name)
        } else {
            builder.addStatement(
                "var args = emptyArray<%T>()",
                ArgClassName
            )
            for (arg in requiredArgs) {
                builder.addStatement("args += %L", toArgCodeBlock(arg))
            }
            for (arg in optionalArgs) {
                builder.beginControlFlow("if (%L != null)", arg.name)
                builder.addStatement("args += %L", toArgCodeBlock(arg))
                builder.endControlFlow()
            }
            builder.addStatement("val newQueryBuilder = queryBuilder.select(%S, args = args)", field.name)
        }

        if (returnList(field, TypeKind.OBJECT)) {
            builder.add(".select(%S)", "id")
        }

        if (returnScalar(field) || returnEnum(field) || returnList(field, TypeKind.SCALAR)) {
            builder
                .addStatement("return engineClient.execute(newQueryBuilder)", typeOf(field.type))
        } else if (returnList(field, TypeKind.OBJECT)) {
            builder
                .addStatement(
                    """
                    return engineClient
                        .execute<List<Map<String, String>>>(newQueryBuilder)
                        .map {
                            %T(
                                %T
                                    .builder()
                                    .select(%S, args = arrayOf(Arg("id", it["id"]!!))),
                                engineClient
                            )
                        }
                    """,
                    typeOfList(field.type),
                    QueryBuilderClassName,
                    loadFunction(field.type)
                )
        } else {
            val type = typeOf(field.type)
            builder
                .addStatement(
                    "return %T(newQueryBuilder, engineClient)",
                    if (returnObject(field)) type.copy(nullable = false) else type
                )
        }

        return builder.build()
    }

    private fun loadFunction(type: TypeRef): String {
        // NON_NULL -> LIST -> NON_NULL -> TYPE!
        val aType = type.ofType!!.ofType!!.name
        return "load${aType}FromID"
    }

    private fun toArgCodeBlock(arg: InputValue): CodeBlock {
        return CodeBlock
            .builder()
            .add(
                "%T(%S, %L)",
                ArgClassName,
                arg.name,
                // The literal doesn't escape this variable, but it does in function/method arguments.
                arg.name.escapeIfKeyword()
            ).build()
    }

    private fun splitArgs(args: Array<InputValue>): Pair<List<InputValue>, List<InputValue>> {
        return args.partition { arg -> arg.type.kind == TypeKind.NON_NULL }
    }

    private fun returnList(field: FieldValue) =
        field.type.kind == TypeKind.NON_NULL && field.type.ofType!!.kind == TypeKind.LIST

    private fun returnList(field: FieldValue, nestedTypeKind: TypeKind): Boolean {
        if (returnList(field)) {
            return field.type.ofType!!.ofType!!.ofType!!.kind == nestedTypeKind
        }
        return false
    }

    private fun returnScalar(field: FieldValue) =
        field.type.kind == TypeKind.SCALAR || field.type.kind == TypeKind.NON_NULL && field.type.ofType!!.kind == TypeKind.SCALAR

    private fun returnEnum(field: FieldValue) =
        field.type.kind == TypeKind.ENUM || field.type.kind == TypeKind.NON_NULL && field.type.ofType!!.kind == TypeKind.ENUM


    private fun returnObject(field: FieldValue) =
        field.type.kind == TypeKind.OBJECT

    // Extract type inside the list.
    private fun typeOfList(type: TypeRef): TypeName {
        if (type.kind == TypeKind.NON_NULL) {
            return typeOfList(type.ofType!!).copy(nullable = false)
        }
        if (type.kind == TypeKind.LIST) {
            return typeOfList(type.ofType!!)
        }
        return typeToClassName(normalizeName(type.name!!)).copy(nullable = true)
    }

    private fun typeOf(type: TypeRef): TypeName {
        if (type.kind == TypeKind.NON_NULL) {
            return typeOf(type.ofType!!).copy(nullable = false)
        }
        if (type.kind == TypeKind.LIST) {
            return LIST.parameterizedBy(typeOf(type.ofType!!))
        }
        return typeToClassName(normalizeName(type.name!!)).copy(nullable = true)
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