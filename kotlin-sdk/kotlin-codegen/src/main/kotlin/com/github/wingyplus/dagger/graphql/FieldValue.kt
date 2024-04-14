package com.github.wingyplus.dagger.graphql

import kotlinx.serialization.Serializable

@Serializable
data class FieldValue(
    val name: String,
    val description: String,
    val args: Array<InputValue>,
    val type: TypeRef,
    val isDeprecated: Boolean,
    val deprecationReason: String?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FieldValue

        if (name != other.name) return false
        if (description != other.description) return false
        if (!args.contentEquals(other.args)) return false
        if (type != other.type) return false
        if (isDeprecated != other.isDeprecated) return false
        if (deprecationReason != other.deprecationReason) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + args.contentHashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + isDeprecated.hashCode()
        result = 31 * result + (deprecationReason?.hashCode() ?: 0)
        return result
    }

}
