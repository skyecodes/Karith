package com.skyecodes.karith.api

internal enum class KthSymbol(val c: Char) : KthToken {
    LEFT_PARENTHESIS('('), RIGHT_PARENTHESIS(')'), COMMA(',');

    companion object {
        val inverse = entries.associateBy({ it.c }) { it }
    }
}