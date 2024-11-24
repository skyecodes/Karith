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

package com.skyecodes.karith.impl

import app.cash.burst.Burst
import app.cash.burst.burstValues
import com.skyecodes.karith.KthContext
import com.skyecodes.karith.KthIllegalTokenException
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

@Burst
class KthTokenizerImplTest {
    private val ctx = KthContext.copy {} as KthContextImpl

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
        assertContentEquals(output, ctx.tokenizer(input, declaredVars).tokens.map { it.value })
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
        assertContentEquals(output, ctx.tokenizer(input, null).detectedVariables.asIterable())
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
