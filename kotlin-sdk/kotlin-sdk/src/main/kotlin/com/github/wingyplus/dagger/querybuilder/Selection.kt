package com.github.wingyplus.dagger.querybuilder

data class Selection(val field: String, val args: Array<Arg>? = null) {
    fun format(): String {
        var builder = StringBuilder(field)
        if (args != null) {
            builder
                .append('(')
                .append(args
                    .map { arg -> formatArg(arg) }
                    .joinToString(separator = ",")
                )
                .append(')')
        }
        return builder.toString()
    }

    private fun formatArg(arg: Arg): String {
        return "${arg.first}:${formatValue(arg.second)}"
    }

    private fun formatValue(value: Any): String {
        return when (value) {
            is Int -> "$value"
            is Boolean -> "$value"
            is String -> formatStringValue(value)
            is List<*> -> formatListValue(value)
            is ObjectArg -> formatObjectValue(value)
            else -> TODO()
        }
    }

    private fun formatStringValue(value: String): String {
        return "\"${value}\""
    }

    private fun formatObjectValue(value: ObjectArg): String {
        val builder = StringBuilder()
        builder.append('{')
        builder.append(
            value.toPairs().joinToString(separator = ",") { pair -> "${pair.first}:${formatValue(pair.second)}" }
        )
        builder.append('}')
        return builder.toString()
    }

    private fun <T> formatListValue(value: List<T>): String {
        val builder = StringBuilder()
        builder.append('[')
        builder.append(
            value.joinToString(separator = ",") { v -> formatValue(v as Any) }
        )
        builder.append(']')
        return builder.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Selection

        if (field != other.field) return false
        if (args != null) {
            if (other.args == null) return false
            if (!args.contentEquals(other.args)) return false
        } else if (other.args != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = field.hashCode()
        result = 31 * result + (args?.contentHashCode() ?: 0)
        return result
    }
}