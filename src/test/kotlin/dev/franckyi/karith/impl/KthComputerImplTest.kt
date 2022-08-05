package dev.franckyi.karith.impl

import dev.franckyi.karith.api.KthSymbol
import dev.franckyi.karith.api.KthToken
import dev.franckyi.karith.api.KthUndefinedVariableException
import dev.franckyi.karith.api.KthUnknownTokenException
import dev.franckyi.karith.api.builtin.Constants
import dev.franckyi.karith.api.builtin.Functions
import dev.franckyi.karith.api.builtin.Operators
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.Math.PI
import java.util.stream.Stream

internal class KthComputerImplTest {
    companion object {
        @JvmStatic
        fun provideTestCompute_ReturnsCorrectResult(): Stream<Arguments> = Stream.of(
            Arguments.of(listOf(num(2), Constants.PI, Operators.PLUS), emptyMap<String, Number>(), 2 + PI),
            Arguments.of(listOf(num(2), variable("a"), Operators.TIMES), mapOf("a" to 3), 6.0),
            Arguments.of(listOf(num(2), variable("a"), Functions.POW), mapOf("a" to 3), 8.0),
        )
    }

    @ParameterizedTest
    @MethodSource("provideTestCompute_ReturnsCorrectResult")
    fun testCompute_ShouldReturnCorrectResult(
        tokens: List<KthToken>,
        variables: Map<String, Number>,
        expectedResult: Double
    ) {
        assertEquals(KthComputerImpl.compute(tokens, variables), expectedResult)
    }

    @Test
    fun testCompute_ShouldThrowException_WhenVariableNotSet() {
        assertThrows<KthUndefinedVariableException> { KthComputerImpl.compute(listOf(variable("a")), emptyMap()) }
    }

    @Test
    fun testCompute_ShouldThrowException_WhenUnknownToken() {
        assertThrows<KthUnknownTokenException> {
            KthComputerImpl.compute(
                listOf(
                    KthSymbol.LEFT_PARENTHESIS,
                    num(3),
                    KthSymbol.RIGHT_PARENTHESIS
                ), emptyMap()
            )
        }
    }
}