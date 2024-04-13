package com.github.wingyplus.dagger.codegen

// Partial copy of https://github.com/square/kotlinpoet/blob/main/kotlinpoet/src/commonMain/kotlin/com/squareup/kotlinpoet/Util.kt#L299C1-L300C1.
fun String.escapeIfKeyword() = if (this in KEYWORDS) "`$this`" else this
