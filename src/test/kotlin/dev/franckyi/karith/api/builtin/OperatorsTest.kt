package dev.franckyi.karith.api.builtin

import dev.franckyi.karith.api.KthOperator
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.pow
import kotlin.test.assertEquals

class OperatorsTest {
    companion object {
        @JvmStatic
        fun provideTestOperator_ShouldReturnCorrectValue(): Stream<Arguments> = Stream.of(
            Arguments.of(Operators.POWER, 3, 2, 3.0.pow(2)),
            Arguments.of(Operators.TIMES, 3, 2, 3 * 2),
            Arguments.of(Operators.DIVIDE, 3, 2, 3.0 / 2.0),
            Arguments.of(Operators.REMAINDER, 3, 2, 3 % 2),
            Arguments.of(Operators.PLUS, 3, 2, 3 + 2),
            Arguments.of(Operators.MINUS, 3, 2, 3 - 2),
            Arguments.of(Operators.SHIFT_LEFT, 3, 2, 3 shl 2),
            Arguments.of(Operators.SHIFT_RIGHT, 3, 2, 3 shr 2),
            Arguments.of(Operators.UNSIGNED_SHIFT_RIGHT, 3, 2, 3 ushr 2),
            Arguments.of(Operators.AND, 3, 2, 3 and 2),
            Arguments.of(Operators.XOR, 3, 2, 3 xor 2),
            Arguments.of(Operators.OR, 3, 2, 3 or 2),
        )
    }

    @ParameterizedTest
    @MethodSource("provideTestOperator_ShouldReturnCorrectValue")
    fun testOperator_ShouldReturnCorrectValue(operator: KthOperator, a: Number, b: Number, expectedValue: Number) {
        assertEquals(expectedValue.toDouble(), operator.apply(a.toDouble(), b.toDouble()))
    }
}