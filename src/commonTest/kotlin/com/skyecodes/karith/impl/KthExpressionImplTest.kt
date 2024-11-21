package com.skyecodes.karith.impl

import com.skyecodes.karith.api.KthComputer
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
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false)
        expression.computer = kthComputer
        every { kthComputer.compute(listOf(num(0)), emptyMap()) } returns -1.0
        assertEquals(-1.0, expression.result())
        assertEquals(-1.0, expression.singleResult)
        assertEquals(-1.0, expression.result())
        verify(exactly(1)) { kthComputer.compute(listOf(num(0)), emptyMap()) }
    }

    @Test
    fun testResult_ShouldThrowException_WhenVariablesRequiredAndNoInputVariables() {
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), false)
        assertFailsWith<KthUndefinedVariableException> { expression.result() }
    }

    @Test
    fun testIntResult_ShouldReturnCorrectResult() {
        val kthComputer: KthComputer = mock<KthComputer>()
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false)
        expression.computer = kthComputer
        every { kthComputer.compute(listOf(num(0)), emptyMap()) } returns -1.0
        assertEquals(-1, expression.intResult())
    }

    @Test
    fun testResultWith_ShouldCallComputerTwice_WhenCacheDisabled() {
        val kthComputer: KthComputer = mock<KthComputer>()
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false)
        expression.computer = kthComputer
        every { kthComputer.compute(listOf(num(0)), emptyMap()) } returns -1.0
        expression.resultWith()
        assertTrue { expression.resultCache.isEmpty() }
        expression.resultWith()
        verify(exactly(2)) { kthComputer.compute(listOf(num(0)), emptyMap()) }
    }

    @Test
    fun testResultWith_ShouldCallComputerOnce_WhenSameParamsAndCacheEnabled() {
        val kthComputer: KthComputer = mock<KthComputer>()
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), true)
        expression.computer = kthComputer
        every { kthComputer.compute(listOf(num(0)), any()) } returns -1.0
        expression.resultWith("a" to 1)
        assertFalse { expression.resultCache.isEmpty() }
        expression.resultWith("a" to 1)
        verify(exactly(1)) { kthComputer.compute(listOf(num(0)), mapOf("a" to 1)) }
        expression.resultWith("a" to 2)
        verify(exactly(1)) { kthComputer.compute(listOf(num(0)), mapOf("a" to 2)) }
    }

    @Test
    fun testResultWith_ShouldThrowException_WhenRequiredVariableNotSet() {
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), false)
        assertFailsWith<KthUndefinedVariableException> { expression.resultWith() }
    }

    @Test
    fun testIntResultWith_ShouldReturnCorrectResult() {
        val kthComputer: KthComputer = mock<KthComputer>()
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false)
        expression.computer = kthComputer
        every { kthComputer.compute(listOf(num(0)), emptyMap()) } returns -1.0
        assertEquals(-1, expression.intResultWith())
    }
}