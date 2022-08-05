package dev.franckyi.karith.impl

import dev.franckyi.karith.api.KthIllegalTokenException
import dev.franckyi.karith.api.mathContext
import dev.franckyi.karith.api.withPowerOperator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class KthTokenizerImplTest {
    companion object {
        val ctx = mathContext { withPowerOperator() } as KthContextImpl

        @JvmStatic
        fun provideTestTokenize_ShouldReturnCorrectTokens(): Stream<Arguments> = Stream.of(
            Arguments.of("3+2", null, listOf(3, "+", 2)),
            Arguments.of("3+2a", null, listOf(3, "+", 2, "*", "a")),
            Arguments.of("3pi+2a", null, listOf(3, "*", "pi", "+", 2, "*", "a")),
            Arguments.of("4sin(pi/2)", null, listOf(4, "*", "sin", '(', "pi", "/", 2, ')')),
            Arguments.of("2atan2(1,2)", null, listOf(2, "*", "atan2", '(', 1, ',', 2, ')')),
            Arguments.of("1-6/a", arrayOf("a"), listOf(1, "-", 6, "/", "a")),
            Arguments.of("(2+4) * (4+6)", null, listOf('(', 2, "+", 4, ')', "*", '(', 4, "+", 6, ')')),
            Arguments.of("3(-2)", null, listOf(3, "*", '(', -2, ')')),
            Arguments.of("-3-4", null, listOf(-3, "-", 4)),
            Arguments.of("+2", null, listOf(2)),
        )

        @JvmStatic
        fun provideTestTokenize_ShouldReturnCorrectVariables(): Stream<Arguments> = Stream.of(
            Arguments.of("3+2", emptySet<String>()),
            Arguments.of("3+2a", setOf("a")),
            Arguments.of("3pi+2a", setOf("a")),
            Arguments.of("4sin(pi/2)", emptySet<String>()),
            Arguments.of("2atan2(x,y)", setOf("x", "y")),
        )

    }

    @ParameterizedTest
    @MethodSource("provideTestTokenize_ShouldReturnCorrectTokens")
    fun testTokenize_ShouldReturnCorrectTokens(input: String, declaredVars: Array<String>?, output: List<Any>) {
        Assertions.assertIterableEquals(output, ctx.tokenizer.tokenize(input, declaredVars).first.map { it.value })
    }

    @ParameterizedTest
    @MethodSource("provideTestTokenize_ShouldReturnCorrectVariables")
    fun testTokenize_ShouldReturnCorrectVariables(input: String, output: Set<String>) {
        Assertions.assertIterableEquals(output, ctx.tokenizer.tokenize(input, null).second)
    }

    @Test
    fun testTokenize_ShouldThrowException_WhenUnknownToken() {
        assertDoesNotThrow { ctx.tokenizer.tokenize("3+2", emptyArray()) }
        assertDoesNotThrow { ctx.tokenizer.tokenize("3+2a", null) }
        assertThrows<KthIllegalTokenException> { ctx.tokenizer.tokenize("3+2a", emptyArray()) }
    }
}
