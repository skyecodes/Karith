package dev.franckyi.karith.impl

import dev.franckyi.karith.api.KthMismatchedParenthesesException
import dev.franckyi.karith.api.KthSymbol
import dev.franckyi.karith.api.KthToken
import dev.franckyi.karith.api.builtin.Functions
import dev.franckyi.karith.api.builtin.Operators
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class KthSorterImplTest {
    companion object {
        @JvmStatic
        fun provideTestSort_ShouldReturnCorrectExpression(): Stream<Arguments> = Stream.of(
            Arguments.of(
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
            Arguments.of(
                listOf(
                    num(3),
                    Operators.TIMES,
                    Functions.ABS,
                    KthSymbol.LEFT_PARENTHESIS,
                    num(2),
                    KthSymbol.RIGHT_PARENTHESIS
                ),
                listOf(num(3), num(2), Functions.ABS, Operators.TIMES),
            ),
        )

        @JvmStatic
        fun provideTestSort_ShouldThrowException_WhenMismatchingParentheses(): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(
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
            ),
            //Arguments.of(listOf(num(3), Operators.TIMES, KthSymbol.LEFT_PARENTHESIS, num(1), Operators.MINUS, num(4), KthSymbol.LEFT_PARENTHESIS, num(1), Operators.PLUS, num(2), KthSymbol.RIGHT_PARENTHESIS)),
        )

    }

    @ParameterizedTest
    @MethodSource("provideTestSort_ShouldReturnCorrectExpression")
    fun testSort_ShouldReturnCorrectExpression(input: List<KthToken>, output: List<KthToken>) {
        assertIterableEquals(output, KthSorterImpl.sort(input))
    }

    @ParameterizedTest
    @MethodSource("provideTestSort_ShouldThrowException_WhenMismatchingParentheses")
    fun testSort_ShouldThrowException_WhenMismatchingParentheses(input: List<KthToken>) {
        assertThrows<KthMismatchedParenthesesException> { KthSorterImpl.sort(input) }
    }
}