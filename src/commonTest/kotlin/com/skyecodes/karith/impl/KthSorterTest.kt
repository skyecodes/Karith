/*
 * Copyright (c) 2024 skyecodes
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.skyecodes.karith.impl

import app.cash.burst.Burst
import app.cash.burst.burstValues
import com.skyecodes.karith.KthMismatchedParenthesesException
import com.skyecodes.karith.KthSymbol
import com.skyecodes.karith.KthToken
import com.skyecodes.karith.builtin.Functions
import com.skyecodes.karith.builtin.Operators
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

@Burst
internal class KthSorterTest {
    @Test
    fun testInvoke_ShouldReturnCorrectExpression(
        args: Arguments = burstValues(
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
        )
    ) {
        val (input, output) = args
        assertContentEquals(output, KthSorter(input))
    }

    @Test
    fun testInvoke_ShouldThrowException_WhenMismatchingParentheses() {
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
        assertFailsWith<KthMismatchedParenthesesException> { KthSorter(input) }
    }

    data class Arguments(val input: List<KthToken>, val output: List<KthToken>)
}