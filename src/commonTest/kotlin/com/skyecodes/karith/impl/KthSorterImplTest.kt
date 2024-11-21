package com.skyecodes.karith.impl

import app.cash.burst.Burst
import app.cash.burst.burstValues
import com.skyecodes.karith.api.KthMismatchedParenthesesException
import com.skyecodes.karith.api.KthSymbol
import com.skyecodes.karith.api.KthToken
import com.skyecodes.karith.api.builtin.Functions
import com.skyecodes.karith.api.builtin.Operators
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

@Burst
internal class KthSorterImplTest {
    @Test
    fun testSort_ShouldReturnCorrectExpression(args: Arguments = burstValues(
        Arguments(
            listOf(
                num(3),
                Operators.PLUS,
                num(4),
                Operators.TIMES,
                num(2),
                Operators.DIVIDE,
                KthSymbol.LEFT_PARENTHESIS,
                num(1),
                Operators.MINUS,
                num(5),
                KthSymbol.RIGHT_PARENTHESIS,
                Operators.POWER,
                num(2),
                Operators.POWER,
                num(3)
            ),
            listOf(
                num(3),
                num(4),
                num(2),
                Operators.TIMES,
                num(1),
                num(5),
                Operators.MINUS,
                num(2),
                num(3),
                Operators.POWER,
                Operators.POWER,
                Operators.DIVIDE,
                Operators.PLUS
            ),
        ),
        Arguments(
            listOf(
                num(3),
                Operators.TIMES,
                Functions.ABS,
                KthSymbol.LEFT_PARENTHESIS,
                num(2),
                KthSymbol.RIGHT_PARENTHESIS
            ),
            listOf(num(3), num(2), Functions.ABS, Operators.TIMES),
        )
    )) {
        val (input, output) = args
        assertContentEquals(output, KthSorterImpl.sort(input))
    }

    @Test
    fun testSort_ShouldThrowException_WhenMismatchingParentheses() {
        val input = listOf(
            num(3),
            Operators.TIMES,
            KthSymbol.LEFT_PARENTHESIS,
            num(1),
            Operators.MINUS,
            num(4),
            KthSymbol.RIGHT_PARENTHESIS,
            Operators.PLUS,
            num(2),
            KthSymbol.RIGHT_PARENTHESIS
        )
        assertFailsWith<KthMismatchedParenthesesException> { KthSorterImpl.sort(input) }
    }

    data class Arguments(val input: List<KthToken>, val output: List<KthToken>)
}