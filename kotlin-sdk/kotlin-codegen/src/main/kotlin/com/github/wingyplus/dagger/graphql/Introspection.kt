package com.github.wingyplus.dagger.graphql

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File


@ExperimentalSerializationApi
@Serializable
class Introspection(val types: Array<FullType>) {
    companion object {
        fun parse(path: String): Introspection {
            val file = File(path)
            return Json.decodeFromStream<Introspection>(file.inputStream())
        }
    }
}