package com.github.wingyplus.dagger

import com.github.wingyplus.dagger.graphql.Client
import com.github.wingyplus.dagger.graphql.Query
import io.ktor.util.reflect.*
import kotlinx.serialization.json.*
import java.io.Closeable
import kotlin.reflect.typeOf

class Engine(
    token: String = System.getenv("DAGGER_SESSION_TOKEN"),
    port: Int = System.getenv("DAGGER_SESSION_PORT").toInt()
) : Closeable {
    val client = Client(port = port, token = token)

    /**
     * Execute the GraphQL request through the engine session.
     */
    suspend inline fun <reified T> execute(queryBuilder: QueryBuilder): T {
        val data = Json.parseToJsonElement(client.execute(queryBuilder.build())).jsonObject["data"]
        val json = unpack(data, queryBuilder.path())
        return Json.decodeFromJsonElement<T>(json)
    }

    fun unpack(data: JsonElement?, path: List<String>): JsonElement {
        var json = data
        for (selector in path) {
            println(json!!::class)
            json = when (json!!) {
                is JsonArray -> break
                is JsonNull -> json.jsonNull
                is JsonObject -> json.jsonObject[selector]
                else -> throw Exception("Found incorrect type ${json::class}")
            }
        }
        return json!!
    }

    override fun close() {
        client.close()
    }
}