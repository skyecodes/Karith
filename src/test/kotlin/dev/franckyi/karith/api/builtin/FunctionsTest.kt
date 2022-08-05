package dev.franckyi.karith.api.builtin

import dev.franckyi.karith.api.KthFunction
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.*
import kotlin.test.assertEquals

class FunctionsTest {
    companion object {
        @JvmStatic
        fun provideTestFunction_ShouldReturnCorrectValue(): Stream<Arguments> = Stream.of(
            args(abs(-1.0), Functions.ABS, -1.0),
            args(acos(1.0), Functions.ACOS, 1.0),
            args(acosh(1.0), Functions.ACOSH, 1.0),
            args(asin(1.0), Functions.ASIN, 1.0),
            args(asinh(1.0), Functions.ASINH, 1.0),
            args(atan(1.0), Functions.ATAN, 1.0),
            args(atan2(1.0, 2.0), Functions.ATAN2, 1.0, 2.0),
            args(atanh(1.0), Functions.ATANH, 1.0),
            args(ceil(1.0), Functions.CEIL, 1.0),
            args(cos(1.0), Functions.COS, 1.0),
            args(cosh(1.0), Functions.COSH, 1.0),
            args(exp(1.0), Functions.EXP, 1.0),
            args(expm1(1.0), Functions.EXPM1, 1.0),
            args(floor(1.0), Functions.FLOOR, 1.0),
            args(hypot(1.0, 2.0), Functions.HYPOT, 1.0, 2.0),
            args(1.0.toLong().inv().toDouble(), Functions.NOT, 1.0),
            args(ln(1.0), Functions.LN, 1.0),
            args(ln1p(1.0), Functions.LN1P, 1.0),
            args(log(1.0, 2.0), Functions.LOG, 1.0, 2.0),
            args(log10(1.0), Functions.LOG10, 1.0),
            args(log2(1.0), Functions.LOG2, 1.0),
            args(max(1.0, 2.0), Functions.MAX, 1.0, 2.0),
            args(min(1.0, 2.0), Functions.MIN, 1.0, 2.0),
            args(1.0.nextDown(), Functions.NEXT_DOWN, 1.0),
            args(1.0.nextTowards(2.0), Functions.NEXT_TOWARDS, 1.0, 2.0),
            args(1.0.nextUp(), Functions.NEXT_UP, 1.0),
            args(1.0.pow(2.0), Functions.POW, 1.0, 2.0),
            args(round(1.0), Functions.ROUND, 1.0),
            args(sign(1.0), Functions.SIGN, 1.0),
            args(sin(1.0), Functions.SIN, 1.0),
            args(sinh(1.0), Functions.SINH, 1.0),
            args(sqrt(1.0), Functions.SQRT, 1.0),
            args(tan(1.0), Functions.TAN, 1.0),
            args(tanh(1.0), Functions.TANH, 1.0),
            args(truncate(1.0), Functions.TRUNCATE, 1.0),
            args(1.0.ulp, Functions.ULP, 1.0),
            args(1.0.withSign(-2.0), Functions.WITH_SIGN, 1.0, -2.0),
        )

        private fun args(expectedValue: Double, function: KthFunction, vararg args: Double) =
            Arguments.of(function, args, expectedValue)
    }

    @ParameterizedTest
    @MethodSource("provideTestFunction_ShouldReturnCorrectValue")
    fun testFunction_ShouldReturnCorrectValue(function: KthFunction, args: DoubleArray, expectedValue: Double) {
        assertEquals(args.size, function.argCount)
        assertEquals(expectedValue, function.apply(args))
    }
}