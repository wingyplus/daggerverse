package com.github.wingyplus.dagger.graphql

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import java.io.Closeable

class Client(private val host: String = "127.0.0.1", val port: Int, val token: String) : Closeable {
    private val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        install(HttpTimeout)
    }

    /**
     * Perform GraphQL request.
     */
    suspend fun execute(query: Query): String {
        val host_ = host
        val port_ = port

        return client.post {
            url {
                protocol = URLProtocol.HTTP
                host = host_
                port = port_
                path("/query")
            }
            basicAuth(token, "")
            contentType(ContentType.Application.Json)
            setBody(query)
            timeout {
                requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            }
        }.body<String>()
    }

    /**
     * Close the client.
     */
    override fun close() {
        client.close()
    }
}