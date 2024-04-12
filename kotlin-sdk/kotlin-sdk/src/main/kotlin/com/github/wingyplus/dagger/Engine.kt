package com.github.wingyplus.dagger

import com.github.wingyplus.dagger.graphql.Client
import com.github.wingyplus.dagger.graphql.Query
import java.io.Closeable

class Engine(
    token: String = System.getenv("DAGGER_SESSION_TOKEN"),
    port: Int = System.getenv("DAGGER_SESSION_PORT").toInt()
) : Closeable {
    private val client = Client(port = port, token = token)

    /**
     * Execute the GraphQL request through the engine session.
     */
    suspend fun execute(query: Query): String {
        return client.execute(query)
    }

    override fun close() {
        client.close()
    }
}