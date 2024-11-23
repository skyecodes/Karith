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
import com.skyecodes.karith.KthSymbol
import com.skyecodes.karith.KthToken
import com.skyecodes.karith.KthUndefinedVariableException
import com.skyecodes.karith.KthUnknownTokenException
import com.skyecodes.karith.builtin.Constants
import com.skyecodes.karith.builtin.Functions
import com.skyecodes.karith.builtin.Operators
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Burst
internal class KthComputerTest {
    @Test
    fun testInvoke_ShouldReturnCorrectResult(
        args: Arguments = burstValues(
            Arguments(listOf(num(2), Constants.PI, Operators.PLUS), emptyMap<String, Number>(), 2 + PI),
            Arguments(listOf(num(2), variable("a"), Operators.TIMES), mapOf("a" to 3), 6.0),
            Arguments(listOf(num(2), variable("a"), Functions.POW), mapOf("a" to 3), 8.0),
        )
    ) {
        val (tokens, variables, expectedResult) = args
        assertEquals(KthComputer(tokens, variables), expectedResult)
    }

    @Test
    fun testInvoke_ShouldThrowException_WhenVariableNotSet() {
        assertFailsWith<KthUndefinedVariableException> { KthComputer(listOf(variable("a")), emptyMap()) }
    }

    @Test
    fun testInvoke_ShouldThrowException_WhenUnknownToken() {
        assertFailsWith<KthUnknownTokenException> {
            KthComputer(
                listOf(
                    KthSymbol.LEFT_PARENTHESIS,
                    num(3),
                    KthSymbol.RIGHT_PARENTHESIS
                ), emptyMap()
            )
        }
    }

    data class Arguments(val tokens: List<KthToken>, val variables: Map<String, Number>, val expectedResult: Double)
}