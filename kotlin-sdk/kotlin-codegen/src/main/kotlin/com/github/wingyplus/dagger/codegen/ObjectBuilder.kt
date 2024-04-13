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
                        .map { ParameterSpec(it.name, typeOf(it.type)) }

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
                ClassName("com.github.wingyplus.dagger.querybuilder", "Arg")
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

        if (returnScalar(field) || returnList(field) || returnEnum(field)) {
            builder
                .addStatement("return engineClient.execute(newQueryBuilder)", typeOf(field.type))
        } else if (returnObject(field)) {
            builder
                .addStatement("return %T(newQueryBuilder, engineClient)", typeOf(field.type).copy(nullable = false))
        } else {
            builder
                .addStatement("return %T(newQueryBuilder, engineClient)", typeOf(field.type))
        }

        return builder.build()
    }

    private fun toArgCodeBlock(arg: InputValue): CodeBlock {
        return CodeBlock
            .builder()
            .add(
                "%T(%S, %L)",
                ClassName("com.github.wingyplus.dagger.querybuilder", "Arg"),
                arg.name,
                arg.name
            ).build()
    }

    private fun splitArgs(args: Array<InputValue>): Pair<List<InputValue>, List<InputValue>> {
        return args.partition { arg -> arg.type.kind == TypeKind.NON_NULL }
    }

    private fun returnList(field: FieldValue) =
        field.type.kind == TypeKind.NON_NULL && field.type.ofType!!.kind == TypeKind.LIST

    private fun returnScalar(field: FieldValue) =
        field.type.kind == TypeKind.SCALAR || field.type.kind == TypeKind.NON_NULL && field.type.ofType!!.kind == TypeKind.SCALAR

    private fun returnEnum(field: FieldValue) =
        field.type.kind == TypeKind.ENUM || field.type.kind == TypeKind.NON_NULL && field.type.ofType!!.kind == TypeKind.ENUM


    private fun returnObject(field: FieldValue) =
        field.type.kind == TypeKind.OBJECT

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