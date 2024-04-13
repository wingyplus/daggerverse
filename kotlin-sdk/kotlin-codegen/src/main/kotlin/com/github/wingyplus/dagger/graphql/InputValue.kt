package com.github.wingyplus.dagger.graphql

import kotlinx.serialization.Serializable

@Serializable
data class InputValue(
    val name: String,
    val description: String,
    val type: TypeRef,
    val defaultValue: String?,
)
