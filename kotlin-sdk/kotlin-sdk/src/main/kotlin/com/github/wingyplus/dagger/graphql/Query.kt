package com.github.wingyplus.dagger.graphql

import kotlinx.serialization.Serializable

@Serializable
data class Query(val query: String)