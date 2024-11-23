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
import com.skyecodes.karith.KthContextualToken
import com.skyecodes.karith.KthModule
import com.skyecodes.karith.KthOperator
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Burst
class ModulesTest {
    @Test
    fun testModule_ShouldContainElements(
        args: Arguments = burstValues(
            Arguments(
                Modules.BASE,
                Operators.TIMES,
                Operators.TIMES, Operators.DIVIDE, Operators.REMAINDER, Operators.PLUS, Operators.MINUS
            ),
            Arguments(
                Modules.BITWISE,
                null,
                Operators.SHIFT_LEFT, Operators.SHIFT_RIGHT, Operators.UNSIGNED_SHIFT_RIGHT, Operators.AND,
                Operators.OR, Operators.XOR, Functions.NOT
            ),
            Arguments(
                Modules.MATH_UTIL,
                null,
                Functions.ABS, Functions.CEIL, Functions.FLOOR, Functions.HYPOT, Functions.MAX, Functions.MIN,
                Functions.NEXT_DOWN, Functions.NEXT_TOWARDS, Functions.NEXT_UP, Functions.POW, Functions.ROUND,
                Functions.SIGN, Functions.SQRT, Functions.TRUNCATE, Functions.ULP, Functions.WITH_SIGN
            ),
            Arguments(
                Modules.MATH_LOG,
                null,
                Functions.EXP, Functions.EXPM1, Functions.LN, Functions.LN1P, Functions.LOG, Functions.LOG10,
                Functions.LOG2, Constants.E
            ),
            Arguments(
                Modules.MATH_TRIG, null,
                Functions.ACOS, Functions.ACOSH, Functions.ASIN, Functions.ASINH, Functions.ATAN,
                Functions.ATAN2, Functions.ATANH, Functions.COS, Functions.COSH, Functions.SIN, Functions.SINH,
                Functions.TAN, Functions.TANH, Constants.PI
            ),
            Arguments(
                Modules.MATH, null,
                Functions.ABS, Functions.CEIL, Functions.EXP, Functions.EXPM1, Functions.FLOOR, Functions.HYPOT,
                Functions.MAX, Functions.MIN, Functions.NEXT_DOWN, Functions.NEXT_TOWARDS, Functions.NEXT_UP,
                Functions.POW, Functions.ROUND, Functions.SIGN, Functions.SQRT, Functions.TRUNCATE, Functions.ULP,
                Functions.WITH_SIGN, Constants.E,
                Functions.LN, Functions.LN1P, Functions.LOG, Functions.LOG10, Functions.LOG2,
                Functions.ACOS, Functions.ACOSH, Functions.ASIN, Functions.ASINH, Functions.ATAN,
                Functions.ATAN2, Functions.ATANH, Functions.COS, Functions.COSH, Functions.SIN, Functions.SINH,
                Functions.TAN, Functions.TANH, Constants.PI
            ),
        )
    ) {
        val (module, expectedCombinerOperator, expectedElements) = args
        assertEquals(expectedCombinerOperator, module.combinerOperator)
        for (element in expectedElements) {
            assertTrue { element.key in module.elementMap }
            assertEquals(element, module.elementMap[element.key])
        }
    }

    data class Arguments(
        val module: KthModule,
        val expectedCombinerOperator: KthOperator?,
        val expectedElements: List<KthContextualToken>
    ) {
        constructor(
            module: KthModule,
            expectedCombinerOperator: KthOperator?,
            vararg expectedElements: KthContextualToken
        ) : this(module, expectedCombinerOperator, expectedElements.toList())
    }
}