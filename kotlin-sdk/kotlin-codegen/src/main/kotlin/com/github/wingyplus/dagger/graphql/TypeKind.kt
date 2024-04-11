package com.github.wingyplus.dagger.graphql

import kotlinx.serialization.Serializable

@Serializable
enum class TypeKind {
    ENUM,
    INPUT_OBJECT,
    LIST,
    NON_NULL,
    OBJECT,
    SCALAR,
}