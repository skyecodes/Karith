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

package com.skyecodes.karith.builtin

import app.cash.burst.Burst
import app.cash.burst.burstValues
import com.skyecodes.karith.KthOperator
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertEquals

@Burst
class OperatorsTest {
    @Test
    fun testOperator_ShouldReturnCorrectValue(
        args: Arguments = burstValues(
            Arguments(Operators.POWER, 3, 2, 3.0.pow(2)),
            Arguments(Operators.TIMES, 3, 2, 3 * 2),
            Arguments(Operators.DIVIDE, 3, 2, 3.0 / 2.0),
            Arguments(Operators.REMAINDER, 3, 2, 3 % 2),
            Arguments(Operators.PLUS, 3, 2, 3 + 2),
            Arguments(Operators.MINUS, 3, 2, 3 - 2),
            Arguments(Operators.SHIFT_LEFT, 3, 2, 3 shl 2),
            Arguments(Operators.SHIFT_RIGHT, 3, 2, 3 shr 2),
            Arguments(Operators.UNSIGNED_SHIFT_RIGHT, 3, 2, 3 ushr 2),
            Arguments(Operators.AND, 3, 2, 3 and 2),
            Arguments(Operators.XOR, 3, 2, 3 xor 2),
            Arguments(Operators.OR, 3, 2, 3 or 2)
        )
    ) {
        val (operator, a, b, expectedValue) = args
        assertEquals(expectedValue.toDouble(), operator(a.toDouble(), b.toDouble()))
    }

    data class Arguments(val operator: KthOperator, val a: Number, val b: Number, val expectedValue: Number)
}