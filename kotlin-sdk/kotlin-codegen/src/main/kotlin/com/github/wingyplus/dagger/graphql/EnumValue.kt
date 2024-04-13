package com.github.wingyplus.dagger.graphql

import kotlinx.serialization.Serializable

@Serializable
class EnumValue(val name: String, val description: String, val isDeprecated: Boolean, val deprecationReason: String?)
