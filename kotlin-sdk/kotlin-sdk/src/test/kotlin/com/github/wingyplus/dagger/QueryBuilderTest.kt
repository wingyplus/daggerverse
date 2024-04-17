package com.github.wingyplus.dagger

import com.github.wingyplus.dagger.graphql.Query
import com.github.wingyplus.dagger.querybuilder.Arg
import com.github.wingyplus.dagger.querybuilder.ObjectArg
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class QueryBuilderTest {
    data class ObjectArgClz(val name: String, val value: String) : ObjectArg {
        override fun toPairs(): List<Pair<String, Any>> {
            return listOf(Pair("name", name), Pair("value", value))
        }
    }

    @Test
    fun build_building_query() {
        assertEquals(
            Query(query = "query{container}"),
            QueryBuilder
                .builder()
                .select("container")
                .build()
        )
    }

    @Test
    fun build_building_argument() {
        assertEquals(
            Query(query = "query{container{from(address:\"nginx:latest\")}}"),
            QueryBuilder
                .builder()
                .select("container")
                .select("from", args = arrayOf(Arg("address", "nginx:latest")))
                .build()
        )

        assertEquals(
            Query(query = "query{container{from(address:\"alpine\"){withExec(args:[\"echo\",\"hello\"])}}}"),
            QueryBuilder
                .builder()
                .select("container")
                .select("from", args = arrayOf(Arg("address", "alpine")))
                .select("withExec", args = arrayOf(Arg("args", listOf("echo", "hello"))))
                .build()
        )

        assertEquals(
            Query(query = "query{withArgs(obj:{name:\"n\",value:\"v\"})}"),
            QueryBuilder
                .builder()
                .select("withArgs", args = arrayOf(Arg("obj", ObjectArgClz("n", "v"))))
                .build()
        )

        assertEquals(
            Query(query = "query{withArgs(bvalue:true)}"),
            QueryBuilder
                .builder()
                .select("withArgs", args = arrayOf(Arg("bvalue", true)))
                .build()
        )
    }

    @Test
    fun path_list_all_selection() {
        var selectors = QueryBuilder
            .builder()
            .select("container")
            .select("from", args = arrayOf(Arg("address", "nginx:latest")))
            .path()

        assertEquals(listOf("container", "from"), selectors)
    }

    @Test
    fun select_immutable() {
        val builder = QueryBuilder
            .builder()
            .select("currentFunctionCall")

        assertEquals(Query(query = "query{currentFunctionCall{parentName}}"), builder.select("parentName").build())
        assertEquals(Query(query = "query{currentFunctionCall{name}}"), builder.select("name").build())
    }

}