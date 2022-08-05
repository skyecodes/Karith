package dev.franckyi.karith.api.builtin

import dev.franckyi.karith.api.KthElement
import dev.franckyi.karith.api.KthModule
import dev.franckyi.karith.api.KthOperator
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ModulesTest {
    companion object {
        @JvmStatic
        fun provideTestModule_ShouldContainElements(): Stream<Arguments> = Stream.of(
            Arguments.of(
                Modules.BASE,
                Operators.TIMES,
                arrayOf(Operators.TIMES, Operators.DIVIDE, Operators.REMAINDER, Operators.PLUS, Operators.MINUS)
            ),
            Arguments.of(
                Modules.BITWISE,
                null,
                arrayOf(
                    Operators.SHIFT_LEFT, Operators.SHIFT_RIGHT, Operators.UNSIGNED_SHIFT_RIGHT, Operators.AND,
                    Operators.OR, Operators.XOR, Functions.NOT
                )
            ),
            Arguments.of(
                Modules.MATH_UTIL, null, arrayOf(
                    Functions.ABS, Functions.CEIL, Functions.FLOOR, Functions.HYPOT, Functions.MAX, Functions.MIN,
                    Functions.NEXT_DOWN, Functions.NEXT_TOWARDS, Functions.NEXT_UP, Functions.POW, Functions.ROUND,
                    Functions.SIGN, Functions.SQRT, Functions.TRUNCATE, Functions.ULP, Functions.WITH_SIGN
                )
            ),
            Arguments.of(
                Modules.MATH_LOG,
                null,
                arrayOf(
                    Functions.EXP, Functions.EXPM1, Functions.LN, Functions.LN1P, Functions.LOG, Functions.LOG10,
                    Functions.LOG2, Constants.E
                )
            ),
            Arguments.of(
                Modules.MATH_TRIG, null, arrayOf(
                    Functions.ACOS, Functions.ACOSH, Functions.ASIN, Functions.ASINH, Functions.ATAN,
                    Functions.ATAN2, Functions.ATANH, Functions.COS, Functions.COSH, Functions.SIN, Functions.SINH,
                    Functions.TAN, Functions.TANH, Constants.PI
                )
            ),
            Arguments.of(
                Modules.MATH, null, arrayOf(
                    Functions.ABS, Functions.CEIL, Functions.EXP, Functions.EXPM1, Functions.FLOOR, Functions.HYPOT,
                    Functions.MAX, Functions.MIN, Functions.NEXT_DOWN, Functions.NEXT_TOWARDS, Functions.NEXT_UP,
                    Functions.POW, Functions.ROUND, Functions.SIGN, Functions.SQRT, Functions.TRUNCATE, Functions.ULP,
                    Functions.WITH_SIGN, Constants.E,
                    Functions.LN, Functions.LN1P, Functions.LOG, Functions.LOG10, Functions.LOG2,
                    Functions.ACOS, Functions.ACOSH, Functions.ASIN, Functions.ASINH, Functions.ATAN,
                    Functions.ATAN2, Functions.ATANH, Functions.COS, Functions.COSH, Functions.SIN, Functions.SINH,
                    Functions.TAN, Functions.TANH, Constants.PI
                )
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("provideTestModule_ShouldContainElements")
    fun testModule_ShouldContainElements(
        module: KthModule,
        expectedCombinerOperator: KthOperator?,
        expectedElements: Array<KthElement>
    ) {
        assertEquals(expectedCombinerOperator, module.combinerOperator)
        for (element in expectedElements) {
            assertTrue { element.key in module.elementMap }
            assertEquals(element, module.elementMap[element.key])
        }
    }
}