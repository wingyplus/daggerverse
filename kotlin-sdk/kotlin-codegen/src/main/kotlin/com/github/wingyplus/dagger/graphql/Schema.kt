package com.github.wingyplus.dagger.graphql

import kotlinx.serialization.Serializable

@Serializable
data class Schema(val types: Array<FullType>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Schema

        return types.contentEquals(other.types)
    }

    override fun hashCode(): Int {
        return types.contentHashCode()
    }
}
