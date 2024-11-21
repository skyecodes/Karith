package com.skyecodes.karith.impl

import com.skyecodes.karith.api.*
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
        val ctx = mathContext { withPowerOperator() } as KthContextImpl
        ctx.tokenizer = tokenizer
        ctx.sorter = sorter
        ctx.expressionFactory = expressionFactory
        // Somehow the next line crashes on WASM targets so we're using a fake instead of a mock
        //every { tokenizer.tokenize("0", emptyList()) } returns (listOf(num(0)) to emptySet())
        every { sorter.sort(listOf(num(0))) } returns listOf(num(0))
        every { expressionFactory.invoke(listOf(num(0)), emptySet()) } returns expression
        every { expression.intResult() } returns -1
        every { expression.expressionVars } returns emptySet()
        val expr = ctx.expressionWith("0")
        assertEquals(-1, expr.intResult())
        assertEquals(emptySet<String>(), expr.expressionVars)
        //verify { tokenizer.tokenize("0", emptyList()) }
        verify { sorter.sort(listOf(num(0))) }
        verify { expressionFactory.invoke(listOf(num(0)), emptySet()) }
        verify { expression.intResult() }
        verify { expression.expressionVars }
    }

    @Test
    fun testExpression_ShouldUseExpressionCache() {
        val ctx = mathContext { withPowerOperator() } as KthContextImpl
        assertTrue { ctx.expression("1+2") === ctx.expression("1+2") }
        assertFalse { ctx.strExpressionCache.isEmpty() }
        assertTrue { ctx.expression("1+2") === ctx.expression("1 + 2") }
        assertFalse { ctx.tokenizedExpressionCache.isEmpty() }
        assertTrue { ctx.expression("1+2") === ctx.expression("1+(2)") }
        assertFalse { ctx.sortedTokenizedExpressionCache.isEmpty() }
    }

    @Test
    fun testExpression_ShouldNotUseExpressionCache_WhenCacheDisabled() {
        val ctx = mathContext { withPowerOperator(); disableCache() } as KthContextImpl
        assertFalse { ctx.expression("1+2") === ctx.expression("1+2") }
        assertTrue { ctx.strExpressionCache.isEmpty() }
        assertFalse { ctx.expression("1+2") === ctx.expression("1 + 2") }
        assertTrue { ctx.tokenizedExpressionCache.isEmpty() }
        assertFalse { ctx.expression("1+2") === ctx.expression("1+(2)") }
        assertTrue { ctx.sortedTokenizedExpressionCache.isEmpty() }
    }
}