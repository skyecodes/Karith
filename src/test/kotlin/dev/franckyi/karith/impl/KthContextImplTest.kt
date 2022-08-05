package dev.franckyi.karith.impl

import dev.franckyi.karith.api.*
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class KthContextImplTest {
    @MockK
    lateinit var tokenizer: KthTokenizer

    @MockK
    lateinit var sorter: KthSorter

    @MockK
    lateinit var expressionFactory: (List<KthToken>, Set<String>) -> KthExpression

    @MockK
    lateinit var expression: KthExpression

    @Test
    fun testExpressionWith_ShouldCallTokenizerAndSorter_ThenReturnExpression() {
        val ctx = mathContext { withPowerOperator() } as KthContextImpl
        ctx.tokenizer = tokenizer
        ctx.sorter = sorter
        ctx.expressionFactory = expressionFactory
        every { tokenizer.tokenize("0", emptyArray()) } returns (listOf(num(0)) to emptySet())
        every { sorter.sort(listOf(num(0))) } returns listOf(num(0))
        every { expressionFactory.invoke(listOf(num(0)), emptySet()) } returns expression
        every { expression.intResult() } returns -1
        every { expression.expressionVars } returns emptySet()
        val expr = ctx.expressionWith("0")
        assertEquals(-1, expr.intResult())
        assertEquals(emptySet<String>(), expr.expressionVars)
        verify { tokenizer.tokenize("0", emptyArray()) }
        verify { sorter.sort(listOf(num(0))) }
        verify { expressionFactory.invoke(listOf(num(0)), emptySet()) }
        verify { expression.intResult() }
        verify { expression.expressionVars }
        clearMocks(tokenizer, sorter, expressionFactory, expression)
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