package com.github.wingyplus.dagger.querybuilder

interface ObjectArg {
    /**
     * Convert an object into list of key and value.
     */
    fun toPairs(): List<Pair<String, Any>>
}