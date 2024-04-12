package com.github.wingyplus.dagger

import com.github.wingyplus.dagger.graphql.Query
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EngineTest {
    @Test
    fun `execute query`() = runBlocking {
        val query = """
            query {
                container {
                    from(address: "alpine") {
                        withExec(args: ["echo", "Hello, World"]) {
                            stdout
                        }
                    }
                }
            }
        """.trimIndent()

        val result = Engine().use { engine ->
            engine.execute(Query(query = query))
        }

        assertEquals("{\"data\":{\"container\":{\"from\":{\"withExec\":{\"stdout\":\"Hello, World\\n\"}}}}}", result)
    }
}