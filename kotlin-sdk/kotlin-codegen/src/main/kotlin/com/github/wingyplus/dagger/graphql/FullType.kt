package com.github.wingyplus.dagger.graphql

import kotlinx.serialization.Serializable

@Serializable
data class FullType(
    val kind: TypeKind,
    val name: String,
    val description: String,
    val fields: Array<FieldValue>?,
    val inputFields: Array<InputValue>?,
    val enumValues: Array<EnumValue>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FullType

        if (kind != other.kind) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (!fields.contentEquals(other.fields)) return false
        if (!inputFields.contentEquals(other.inputFields)) return false
        if (!enumValues.contentEquals(other.enumValues)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = kind.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + fields.contentHashCode()
        result = 31 * result + inputFields.contentHashCode()
        result = 31 * result + enumValues.contentHashCode()
        return result
    }
}