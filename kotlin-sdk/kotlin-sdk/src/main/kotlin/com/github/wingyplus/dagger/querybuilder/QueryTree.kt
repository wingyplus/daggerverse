package com.github.wingyplus.dagger.querybuilder

data class QueryTree(
    val selection: Selection,
    val parent: QueryTree? = null
) {
    fun path(): List<String> {
        var selectors = mutableListOf(selection.field)
        var parent = this.parent

        while (parent != null) {
            selectors.add(parent!!.selection.field)
            parent = parent!!.parent
        }

        selectors.reverse()
        return selectors
    }
}
