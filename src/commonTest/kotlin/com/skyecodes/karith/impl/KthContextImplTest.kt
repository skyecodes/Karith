package com.skyecodes.karith.impl

import com.skyecodes.karith.api.KthExpression
import com.skyecodes.karith.api.KthToken
import com.skyecodes.karith.api.buildMathContext
import com.skyecodes.karith.api.builtin.Operators
import com.skyecodes.karith.api.withPowerOperator
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import dev.mokkery.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class KthContextImplTest {

    @Test
    fun testExpressionWith_ShouldCallTokenizerAndSorter_ThenReturnExpression() {
        //val tokenizer: KthTokenizer = mock()
        val tokenizer = KthTokenizer { _, _ -> listOf(num(0)) to emptySet() }
        val sorter: KthSorter = mock()
        val expressionFactory: (List<KthToken>, Set<String>) -> KthExpression = mock()
        val expression: KthExpression = mock()
        val ctx = KthContextImpl(emptyMap(), Operators.TIMES, false, tokenizer, sorter, expressionFactory)
        // Somehow the next line crashes on WASM targets so we're using a fake instead of a mock
        //every { tokenizer.tokenize("0", emptyList()) } returns (listOf(num(0)) to emptySet())
        every { sorter(listOf(num(0))) } returns listOf(num(0))
        every { expressionFactory(listOf(num(0)), emptySet()) } returns expression
        every { expression.getResultAsInt() } returns -1
        every { expression.expressionVars } returns emptySet()
        val expr = ctx.expressionOf("0")
        assertEquals(-1, expr.getResultAsInt())
        assertEquals(emptySet<String>(), expr.expressionVars)
        //verify { tokenizer.tokenize("0", emptyList()) }
        verify { sorter(listOf(num(0))) }
        verify { expressionFactory(listOf(num(0)), emptySet()) }
        verify { expression.getResultAsInt() }
        verify { expression.expressionVars }
    }

    @Test
    fun testExpression_ShouldUseExpressionCache() {
        val ctx = buildMathContext { withPowerOperator() } as KthContextImpl
        assertTrue { ctx.expressionOf("1+2") === ctx.expressionOf("1+2") }
        assertFalse { ctx.strExpressionCache.isEmpty() }
        assertTrue { ctx.expressionOf("1+2") === ctx.expressionOf("1 + 2") }
        assertFalse { ctx.tokenizedExpressionCache.isEmpty() }
        assertTrue { ctx.expressionOf("1+2") === ctx.expressionOf("1+(2)") }
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