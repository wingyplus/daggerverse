package com.github.wingyplus.dagger.codegen

import com.squareup.kotlinpoet.ClassName

const val DAGGER_PACKAGE = "com.github.wingyplus.dagger"
const val DAGGER_QUERYBUILDER_PACKAGE = "com.github.wingyplus.dagger.querybuilder"

val ArgClassName = ClassName(DAGGER_QUERYBUILDER_PACKAGE, "Arg")
val EngineClassName = ClassName(DAGGER_PACKAGE, "Engine")
val QueryBuilderClassName = ClassName(DAGGER_PACKAGE, "QueryBuilder")
