package dev.franckyi.karith.api

internal enum class KthSymbol(val c: Char) : KthToken {
    LEFT_PARENTHESIS('('), RIGHT_PARENTHESIS(')'), COMMA(',');

    companion object {
        val inverse = values().associateBy({ it.c }) { it }
    }
}