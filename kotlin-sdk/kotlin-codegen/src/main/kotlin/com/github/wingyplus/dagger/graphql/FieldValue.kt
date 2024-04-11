package com.github.wingyplus.dagger.graphql

import com.sun.org.apache.xpath.internal.operations.Bool
import kotlinx.serialization.Serializable

@Serializable
data class FieldValue(
    val name: String,
    val description: String,
    val args: Array<InputValue>,
    val type: TypeRef,
    val isDeprecated: Boolean,
    val deprecationReason: String,
    val nameWords: String
)
