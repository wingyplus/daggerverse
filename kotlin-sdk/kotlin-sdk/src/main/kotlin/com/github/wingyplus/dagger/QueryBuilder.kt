package com.github.wingyplus.dagger

import com.github.wingyplus.dagger.graphql.Query
import com.github.wingyplus.dagger.querybuilder.Arg
import com.github.wingyplus.dagger.querybuilder.QueryTree
import com.github.wingyplus.dagger.querybuilder.Selection

class QueryBuilder(private var queryTree: QueryTree? = null) {
    fun select(name: String): QueryBuilder {
        queryTree = QueryTree(selection = Selection(field = name), parent = queryTree)
        return this
    }

    fun select(name: String, args: Array<Arg>): QueryBuilder {
        queryTree = QueryTree(selection = Selection(field = name, args = args), parent = queryTree)
        return this
    }

    fun build(): Query {
        var query = ""
        var tree: QueryTree? = queryTree

        while (tree != null) {
            // Assume it's leaf node.
            if (query == "") {
                query = tree.selection.format()
            } else {
                query = "${tree.selection.format()}{${query}}"
            }
            tree = tree.parent
        }
        return Query(query = "query{$query}")
    }

    fun path(): List<String> {
        if (queryTree == null) {
            return listOf()
        }
        return queryTree!!.path()
    }

    companion object {
        fun builder(): QueryBuilder {
            return QueryBuilder()
        }
    }
}