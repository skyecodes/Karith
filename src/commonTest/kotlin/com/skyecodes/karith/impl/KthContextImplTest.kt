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

import com.skyecodes.karith.KthExpression
import com.skyecodes.karith.KthToken
import com.skyecodes.karith.buildMathContext
import com.skyecodes.karith.builtin.Operators
import com.skyecodes.karith.withPowerOperator
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import dev.mokkery.verify
import kotlin.test.*

internal class KthContextImplTest {

    @Test
    fun testExpressionWith_ShouldCallTokenizerAndSorter_ThenReturnExpression() {
        //val tokenizer: KthTokenizer = mock()
        val tokenizer = KthTokenizer { _, _ -> KthTokenizerResult(listOf(num(0)), emptySet()) }
        val sorter: KthSorter = mock()
        val expressionFactory: (List<KthToken>, Set<String>) -> KthExpression = mock()
        val expression: KthExpression = mock()
        val ctx = KthContextImpl(emptyMap(), Operators.TIMES, false, tokenizer, sorter, expressionFactory)
        // Somehow the next line crashes on WASM targets so we're using a fake instead of a mock
        //every { tokenizer.tokenize("0", emptyList()) } returns (listOf(num(0)) to emptySet())
        every { sorter(listOf(num(0))) } returns listOf(num(0))
        every { expressionFactory(listOf(num(0)), emptySet()) } returns expression
        every { expression.getResult() } returns success(-1.0)
        every { expression.expressionVars } returns emptySet()
        val expr = ctx.expressionOf("0").orThrow()
        assertEquals(-1.0, expr.getResult().orThrow())
        assertEquals(emptySet<String>(), expr.expressionVars)
        //verify { tokenizer.tokenize("0", emptyList()) }
        verify { sorter(listOf(num(0))) }
        verify { expressionFactory(listOf(num(0)), emptySet()) }
        verify { expression.getResult() }
        verify { expression.expressionVars }
    }

    @Test
    fun testExpression_ShouldUseExpressionCache() {
        val ctx = buildMathContext { withPowerOperator() } as KthContextImpl
        ctx.expressionOf("1+2")
        ctx.expressionOf("1+2")
        assertSame(ctx.expressionOf("1+2"), ctx.expressionOf("1+2"))
        assertFalse { ctx.strExpressionCache.isEmpty() }
        assertSame(ctx.expressionOf("1+2"), ctx.expressionOf("1 + 2"))
        assertFalse { ctx.tokenizedExpressionCache.isEmpty() }
        assertSame(ctx.expressionOf("1+2"), ctx.expressionOf("1+(2)"))
        assertFalse { ctx.sortedTokenizedExpressionCache.isEmpty() }
    }

    @Test
    fun testExpression_ShouldNotUseExpressionCache_WhenCacheDisabled() {
        val ctx = buildMathContext { withPowerOperator(); disableCache() } as KthContextImpl
        assertFalse { ctx.expressionOf("1+2") === ctx.expressionOf("1+2") }
        assertTrue { ctx.strExpressionCache.isEmpty() }
        assertFalse { ctx.expressionOf("1+2") === ctx.expressionOf("1 + 2") }
        assertTrue { ctx.tokenizedExpressionCache.isEmpty() }
        assertFalse { ctx.expressionOf("1+2") === ctx.expressionOf("1+(2)") }
        assertTrue { ctx.sortedTokenizedExpressionCache.isEmpty() }
    }
}