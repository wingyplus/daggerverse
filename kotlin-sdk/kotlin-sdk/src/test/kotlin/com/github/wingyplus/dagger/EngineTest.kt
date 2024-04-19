package com.github.wingyplus.dagger

import com.github.wingyplus.dagger.querybuilder.Arg
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.decodeFromJsonElement
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull

class EngineTest {
    @Test
    fun `execute query`() = runBlocking {
        val queryBuilder = QueryBuilder()
            .select("container")
            .select("from", arrayOf(Arg("address", "alpine")))
            .select("withExec", arrayOf(Arg("args", listOf("echo", "hello"))))
            .select("stdout")

        val result = Engine().use { engine ->
            engine.execute<String>(queryBuilder)
        }

        assertEquals("hello\n", result)
    }

    @Test
    fun `execute envVariables`() = runBlocking {
        val queryBuilder = QueryBuilder()
            .select("container")
            .select("from", arrayOf(Arg("address", "alpine")))
            .select("envVariables")
            .select("id")

        val result = Engine().use { engine ->
            engine.execute<List<Map<String, String>>>(queryBuilder)
        }
    }

    @Test
    fun `unpack null object`() {
        assertIs<JsonNull>(
            unpack(
                "{\"currentFunctionCall\":{\"returnValue\":null}}",
                listOf("currentFunctionCall", "returnValue")
            )
        )
    }

    private fun unpack(json: String, path: List<String>): JsonElement {
        return Engine().unpack(Json.parseToJsonElement(json), path)
    }
}