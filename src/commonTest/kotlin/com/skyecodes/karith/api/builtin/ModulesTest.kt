package com.skyecodes.karith.api.builtin

import app.cash.burst.Burst
import app.cash.burst.burstValues
import com.skyecodes.karith.api.KthElement
import com.skyecodes.karith.api.KthModule
import com.skyecodes.karith.api.KthOperator
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
        val expectedElements: List<KthElement>
    ) {
        constructor(
            module: KthModule,
            expectedCombinerOperator: KthOperator?,
            vararg expectedElements: KthElement
        ) : this(module, expectedCombinerOperator, expectedElements.toList())
    }
}