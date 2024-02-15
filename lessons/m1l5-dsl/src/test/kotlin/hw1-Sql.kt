@file:Suppress("unused")

package ru.otus.otuskotlin.m1l5

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

data class Query(
    val from: String
)

@DslMarker
annotation class QueryDsl

fun query(block: SqlSelectBuilder.() -> Unit) = SqlSelectBuilder().apply(block)

@QueryDsl
class SqlSelectBuilder {
    private var selectQuery = "*"
    private var fromQuery = ""
    private var whereQuery = ""

    fun from(tableArgs: String) {
        fromQuery = tableArgs
    }

    fun select(vararg selectArgs: String) {
        selectQuery = selectArgs.joinToString(", ")
    }

    fun where(block: WhereBuilder.() -> Unit) {
        val ctx = WhereBuilder().apply(block).build()
        if (ctx.isNotEmpty()) {
            whereQuery = " $ctx"
        }
    }

    private fun validate() {
        if (fromQuery.isEmpty()) {
            throw Exception("You should pass table")
        }
    }

    fun build(): String {
        validate()
        return "select $selectQuery from $fromQuery$whereQuery"
    }
}

@QueryDsl
class WhereBuilder {
    private var whereQuery = ""

    infix fun<T> String.eq(arg: T) {
       whereQuery = eq(this, arg)
    }

    infix fun<T> String.nonEq(arg: T) {
        whereQuery = nonEq(this, arg)
    }

    fun or(block: WhereOrQuery.() -> Unit) {
        val ctx = WhereOrQuery().apply(block).build()
        whereQuery = ctx
    }

    fun build(): String {
        if (whereQuery.isEmpty()) {
            return ""
        }

        return "where $whereQuery"
    }
}

@QueryDsl
class WhereOrQuery {
    private var whereQuery = mutableListOf<String>()

    infix fun<T> String.eq(arg: T) {
        whereQuery.add(eq(this, arg))
    }

    infix fun<T> String.nonEq(arg: T) {
        whereQuery.add(nonEq(this, arg))
    }

    fun build(): String {
        if (whereQuery.isEmpty()) {
            return ""
        }

        return whereQuery.joinToString(" or ", "(", ")")
    }
}

fun<T> eq(param: String,arg: T): String = when (arg) {
    is Int -> "$param = $arg"
    is String -> "$param = '$arg'"
    null -> "$param is null"
    else -> throw Exception("Unsupported type")
}

fun<T> nonEq(param: String, arg: T): String = when (arg) {
    is Int -> "$param != $arg"
    is String -> "$param != '$arg'"
    null -> "$param !is null"
    else -> throw Exception("Unsupported type")
}

// Реализуйте dsl для составления sql запроса, чтобы все тесты стали зелеными.
class Hw1Sql {
    private fun checkSQL(expected: String, sql: SqlSelectBuilder) {
        assertEquals(expected, sql.build())
    }

    @Test
    fun `simple select all from table`() {
        val expected = "select * from table"

        val real = query {
            from("table")
        }

        checkSQL(expected, real)
    }

    @Test
    fun `check that select can't be used without table`() {
        assertFailsWith<Exception> {
            query {
                select("col_a")
            }.build()
        }
    }

    @Test
    fun `select certain columns from table`() {
        val expected = "select col_a, col_b from table"

        val real = query {
            select("col_a", "col_b")
            from("table")
        }

        checkSQL(expected, real)
    }

    @Test
    fun `select certain columns from table 1`() {
        val expected = "select col_a, col_b from table"

        val real = query {
            select("col_a", "col_b")
            from("table")
        }

        checkSQL(expected, real)
    }

    /**
     * __eq__ is "equals" function. Must be one of char:
     *  - for strings - "="
     *  - for numbers - "="
     *  - for null - "is"
     */
    @Test
    fun `select with complex where condition with one condition`() {
        val expected = "select * from table where col_a = 'id'"

        val real = query {
            from("table")
            where { "col_a" eq "id" }
        }

        checkSQL(expected, real)
    }

    /**
     * __nonEq__ is "non equals" function. Must be one of chars:
     *  - for strings - "!="
     *  - for numbers - "!="
     *  - for null - "!is"
     */
    @Test
    fun `select with complex where condition with two conditions`() {
        val expected = "select * from table where col_a != 0"

        val real = query {
            from("table")
            where {
                "col_a" nonEq 0
            }
        }

        checkSQL(expected, real)
    }

    @Test
    fun `when 'or' conditions are specified then they are respected`() {
        val expected = "select * from table where (col_a = 4 or col_b !is null)"

        val real = query {
            from("table")
            where {
                or {
                    "col_a" eq 4
                    "col_b" nonEq null
                }
            }
        }

        checkSQL(expected, real)
    }
}
