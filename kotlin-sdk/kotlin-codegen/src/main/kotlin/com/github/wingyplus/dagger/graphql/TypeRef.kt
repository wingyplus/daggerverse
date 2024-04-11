package com.github.wingyplus.dagger.graphql

import kotlinx.serialization.Serializable




@Serializable
data class TypeRef(val kind: TypeKind, val name: String, val ofType: TypeRef?)
