package com.skyecodes.karith.impl

import com.skyecodes.karith.api.KthUndefinedVariableException
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import kotlin.test.*

internal class KthExpressionImplTest {
    @Test
    fun testResult_ShouldCallComputerAndSaveResult_ThenReturnCorrectResult() {
        val kthComputer: KthComputer = mock<KthComputer>()
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false, kthComputer)
        every { kthComputer(listOf(num(0)), emptyMap()) } returns -1.0
        assertEquals(-1.0, expression.getResult())
        assertEquals(-1.0, expression.singleResult)
        assertEquals(-1.0, expression.getResult())
        verify(exactly(1)) { kthComputer(listOf(num(0)), emptyMap()) }
    }

    @Test
    fun testResult_ShouldThrowException_WhenVariablesRequiredAndNoInputVariables() {
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), false)
        assertFailsWith<KthUndefinedVariableException> { expression.getResult() }
    }

    @Test
    fun testGetIntResult_ShouldReturnCorrectResult() {
        val kthComputer: KthComputer = mock<KthComputer>()
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false, kthComputer)
        every { kthComputer(listOf(num(0)), emptyMap()) } returns -1.0
        assertEquals(-1, expression.getResultAsInt())
    }

    @Test
    fun testGetResultWith_ShouldCallComputerTwice_WhenCacheDisabled() {
        val kthComputer: KthComputer = mock<KthComputer>()
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false, kthComputer)
        every { kthComputer(listOf(num(0)), emptyMap()) } returns -1.0
        expression.getResultWith()
        assertTrue { expression.resultCache.isEmpty() }
        expression.getResultWith()
        verify(exactly(2)) { kthComputer(listOf(num(0)), emptyMap()) }
    }

    @Test
    fun testGetResultWith_ShouldCallComputerOnce_WhenSameParamsAndCacheEnabled() {
        val kthComputer: KthComputer = mock<KthComputer>()
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), true, kthComputer)
        every { kthComputer(listOf(num(0)), any()) } returns -1.0
        expression.getResultWith("a" to 1)
        assertFalse { expression.resultCache.isEmpty() }
        expression.getResultWith("a" to 1)
        verify(exactly(1)) { kthComputer(listOf(num(0)), mapOf("a" to 1)) }
        expression.getResultWith("a" to 2)
        verify(exactly(1)) { kthComputer(listOf(num(0)), mapOf("a" to 2)) }
    }

    @Test
    fun testGetResultWith_ShouldThrowException_WhenRequiredVariableNotSet() {
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), false)
        assertFailsWith<KthUndefinedVariableException> { expression.getResultWith() }
    }

    @Test
    fun testGetIntResultWith_ShouldReturnCorrectGetResult() {
        val kthComputer: KthComputer = mock<KthComputer>()
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false, kthComputer)
        every { kthComputer(listOf(num(0)), emptyMap()) } returns -1.0
        assertEquals(-1, expression.getResultAsIntWith())
    }
}