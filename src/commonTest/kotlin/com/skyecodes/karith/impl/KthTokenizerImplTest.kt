package com.skyecodes.karith.impl

import app.cash.burst.Burst
import app.cash.burst.burstValues
import com.skyecodes.karith.api.KthIllegalTokenException
import com.skyecodes.karith.api.buildMathContext
import com.skyecodes.karith.api.withPowerOperator
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

@Burst
class KthTokenizerImplTest {
    private val ctx = buildMathContext { withPowerOperator() } as KthContextImpl

    @Test
    fun testInvoke_ShouldReturnCorrectTokens(
        args: TokensArguments = burstValues(
            TokensArguments("3+2", null, listOf(3, "+", 2)),
            TokensArguments("3+2a", null, listOf(3, "+", 2, "*", "a")),
            TokensArguments("3pi+2a", null, listOf(3, "*", "pi", "+", 2, "*", "a")),
            TokensArguments("4sin(pi/2)", null, listOf(4, "*", "sin", '(', "pi", "/", 2, ')')),
            TokensArguments("2atan2(1,2)", null, listOf(2, "*", "atan2", '(', 1, ',', 2, ')')),
            TokensArguments("1-6/a", listOf("a"), listOf(1, "-", 6, "/", "a")),
            TokensArguments("(2+4) * (4+6)", null, listOf('(', 2, "+", 4, ')', "*", '(', 4, "+", 6, ')')),
            TokensArguments("3(-2)", null, listOf(3, "*", '(', -2, ')')),
            TokensArguments("-3-4", null, listOf(-3, "-", 4)),
            TokensArguments("+2", null, listOf(2)),
        )
    ) {
        val (input, declaredVars, output) = args
        assertContentEquals(output, ctx.tokenizer(input, declaredVars).first.map { it.value })
    }

    @Test
    fun testInvoke_ShouldReturnCorrectVariables(
        args: VariablesArguments = burstValues(
            VariablesArguments("3+2", emptySet<String>()),
            VariablesArguments("3+2a", setOf("a")),
            VariablesArguments("3pi+2a", setOf("a")),
            VariablesArguments("4sin(pi/2)", emptySet<String>()),
            VariablesArguments("2atan2(x,y)", setOf("x", "y"))
        )
    ) {
        val (input, output) = args
        assertContentEquals(output, ctx.tokenizer(input, null).second.asIterable())
    }

    @Test
    fun testInvoke_ShouldThrowException_WhenUnknownToken() {
        ctx.tokenizer("3+2", emptyList())
        ctx.tokenizer("3+2a", null)
        assertFailsWith<KthIllegalTokenException> { ctx.tokenizer("3+2a", emptyList()) }
    }

    data class TokensArguments(val input: String, val declaredVars: List<String>?, val output: List<Any>)
    data class VariablesArguments(val input: String, val output: Set<String>)
}
