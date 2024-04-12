package com.github.wingyplus.dagger

import com.github.wingyplus.dagger.querybuilder.Arg
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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
}