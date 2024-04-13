package com.github.wingyplus.dagger.codegen

import com.squareup.kotlinpoet.ClassName

const val DAGGER_PACKAGE = "com.github.wingyplus.dagger"
const val DAGGER_QUERYBUILDER_PACKAGE = "com.github.wingyplus.dagger.querybuilder"

val ArgClassName = ClassName(DAGGER_QUERYBUILDER_PACKAGE, "Arg")
val EngineClassName = ClassName(DAGGER_PACKAGE, "Engine")
val QueryBuilderClassName = ClassName(DAGGER_PACKAGE, "QueryBuilder")

// Borrow this code from KotlinPoet (https://github.com/square/kotlinpoet/blob/main/kotlinpoet/src/commonMain/kotlin/com/squareup/kotlinpoet/Util.kt#L181).
// https://kotlinlang.org/docs/reference/keyword-reference.html
internal val KEYWORDS = setOf(
    // Hard keywords
    "as",
    "break",
    "class",
    "continue",
    "do",
    "else",
    "false",
    "for",
    "fun",
    "if",
    "in",
    "interface",
    "is",
    "null",
    "object",
    "package",
    "return",
    "super",
    "this",
    "throw",
    "true",
    "try",
    "typealias",
    "typeof",
    "val",
    "var",
    "when",
    "while",

    // Soft keywords
    "by",
    "catch",
    "constructor",
    "delegate",
    "dynamic",
    "field",
    "file",
    "finally",
    "get",
    "import",
    "init",
    "param",
    "property",
    "receiver",
    "set",
    "setparam",
    "where",

    // Modifier keywords
    "actual",
    "abstract",
    "annotation",
    "companion",
    "const",
    "crossinline",
    "data",
    "enum",
    "expect",
    "external",
    "final",
    "infix",
    "inline",
    "inner",
    "internal",
    "lateinit",
    "noinline",
    "open",
    "operator",
    "out",
    "override",
    "private",
    "protected",
    "public",
    "reified",
    "sealed",
    "suspend",
    "tailrec",
    "value",
    "vararg",

    // These aren't keywords anymore but still break some code if unescaped. https://youtrack.jetbrains.com/issue/KT-52315
    "header",
    "impl",

    // Other reserved keywords
    "yield",
)
