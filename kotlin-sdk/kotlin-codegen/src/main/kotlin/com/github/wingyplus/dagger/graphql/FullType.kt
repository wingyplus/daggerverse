package com.github.wingyplus.dagger.graphql

import kotlinx.serialization.Serializable

@Serializable
data class FullType(
    val kind: TypeKind,
    val name: String,
    val description: String,
    val fields: Array<FieldValue>,
    val inputFields: Array<InputValue>,
    val enumValues: Array<EnumValue>
)