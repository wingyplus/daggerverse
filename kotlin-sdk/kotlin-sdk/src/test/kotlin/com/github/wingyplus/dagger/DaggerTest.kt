package com.github.wingyplus.dagger

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DaggerTest {
    @Test
    fun `container`() = runBlocking {
        val version = Dagger.connect().use { dag ->
            dag
                .client()
                .container()
                .from("alpine:3.16.2")
                .withExec(listOf("cat", "/etc/alpine-release"))
                .stdout()
        }

        assertEquals("3.16.2\n", version)
    }

    @Test
    fun `list env variables`() = runBlocking {
        val envNames = Dagger.connect().use { dag ->
            dag
                .client()
                .container()
                .from(address = "alpine:3.16.2")
                .envVariables()
                .map { env -> env.name() }
        }

        assertEquals(listOf("PATH"), envNames)
    }
}