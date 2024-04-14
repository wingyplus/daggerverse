package com.github.wingyplus.dagger

import java.io.Closeable

class Dagger(private val engine: Engine) : Closeable {

    /**
     * Returns a Dagger Client API instance.
     */
    fun client() = Client(QueryBuilder(), engine)

    override fun close() {
        engine.close()
    }

    companion object {
        fun connect(): Dagger {
            return Dagger(Engine())
        }
    }
}