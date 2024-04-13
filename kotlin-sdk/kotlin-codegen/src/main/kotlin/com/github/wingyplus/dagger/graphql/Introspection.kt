package com.github.wingyplus.dagger.graphql

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNames
import kotlinx.serialization.json.decodeFromStream
import java.io.File


@ExperimentalSerializationApi
@Serializable
data class Introspection(@JsonNames("__schema") val schema: Schema) {
    companion object {
        fun parse(path: String): Introspection {
            val file = File(path)
            return Json { ignoreUnknownKeys = true }.decodeFromStream<Introspection>(file.inputStream())
        }
    }
}