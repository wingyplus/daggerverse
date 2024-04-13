package com.github.wingyplus.dagger

import com.github.wingyplus.dagger.graphql.Client
import com.github.wingyplus.dagger.graphql.Query
import io.ktor.util.reflect.*
import kotlinx.serialization.json.*
import java.io.Closeable

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
        return unpack(data, queryBuilder.path())
    }

    inline fun <reified T> unpack(data: JsonElement?, path: List<String>): T {
        var json = data
        for (selector in path) {
            json = json!!.jsonObject[selector]
            if (json!!.instanceOf(JsonArray::class)) {
                break
            }
        }

        return Json.decodeFromJsonElement<T>(json!!)
    }

    override fun close() {
        client.close()
    }
}