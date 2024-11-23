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
import com.skyecodes.karith.KthFunction
import kotlin.math.*
import kotlin.test.Test
import kotlin.test.assertEquals

@Burst
class FunctionsTest {
    @Test
    fun testFunction_ShouldReturnCorrectValue(
        args: Arguments = burstValues(
            Arguments(abs(-1.0), Functions.ABS, -1.0),
            Arguments(acos(1.0), Functions.ACOS, 1.0),
            Arguments(acosh(1.0), Functions.ACOSH, 1.0),
            Arguments(asin(1.0), Functions.ASIN, 1.0),
            Arguments(asinh(1.0), Functions.ASINH, 1.0),
            Arguments(atan(1.0), Functions.ATAN, 1.0),
            Arguments(atan2(1.0, 2.0), Functions.ATAN2, 1.0, 2.0),
            Arguments(atanh(1.0), Functions.ATANH, 1.0),
            Arguments(ceil(1.0), Functions.CEIL, 1.0),
            Arguments(cos(1.0), Functions.COS, 1.0),
            Arguments(cosh(1.0), Functions.COSH, 1.0),
            Arguments(exp(1.0), Functions.EXP, 1.0),
            Arguments(expm1(1.0), Functions.EXPM1, 1.0),
            Arguments(floor(1.0), Functions.FLOOR, 1.0),
            Arguments(hypot(1.0, 2.0), Functions.HYPOT, 1.0, 2.0),
            Arguments(1.0.toLong().inv().toDouble(), Functions.NOT, 1.0),
            Arguments(ln(1.0), Functions.LN, 1.0),
            Arguments(ln1p(1.0), Functions.LN1P, 1.0),
            Arguments(log(1.0, 2.0), Functions.LOG, 1.0, 2.0),
            Arguments(log10(1.0), Functions.LOG10, 1.0),
            Arguments(log2(1.0), Functions.LOG2, 1.0),
            Arguments(max(1.0, 2.0), Functions.MAX, 1.0, 2.0),
            Arguments(min(1.0, 2.0), Functions.MIN, 1.0, 2.0),
            Arguments(1.0.nextDown(), Functions.NEXT_DOWN, 1.0),
            Arguments(1.0.nextTowards(2.0), Functions.NEXT_TOWARDS, 1.0, 2.0),
            Arguments(1.0.nextUp(), Functions.NEXT_UP, 1.0),
            Arguments(1.0.pow(2.0), Functions.POW, 1.0, 2.0),
            Arguments(round(1.0), Functions.ROUND, 1.0),
            Arguments(sign(1.0), Functions.SIGN, 1.0),
            Arguments(sin(1.0), Functions.SIN, 1.0),
            Arguments(sinh(1.0), Functions.SINH, 1.0),
            Arguments(sqrt(1.0), Functions.SQRT, 1.0),
            Arguments(tan(1.0), Functions.TAN, 1.0),
            Arguments(tanh(1.0), Functions.TANH, 1.0),
            Arguments(truncate(1.0), Functions.TRUNCATE, 1.0),
            Arguments(1.0.ulp, Functions.ULP, 1.0),
            Arguments(1.0.withSign(-2.0), Functions.WITH_SIGN, 1.0, -2.0),
        )
    ) {
        val (expectedValue, function, args) = args
        assertEquals(args.size, function.argCount)
        assertEquals(expectedValue, function(*args.toDoubleArray()))
    }

    data class Arguments(val expectedValue: Double, val function: KthFunction, val args: List<Double>) {
        constructor(expectedValue: Double, function: KthFunction, vararg args: Double) : this(
            expectedValue,
            function,
            args.toList()
        )
    }
}