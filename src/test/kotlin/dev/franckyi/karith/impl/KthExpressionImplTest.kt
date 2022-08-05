package dev.franckyi.karith.impl

import dev.franckyi.karith.api.KthComputer
import dev.franckyi.karith.api.KthUndefinedVariableException
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
internal class KthExpressionImplTest {
    @MockK
    lateinit var kthComputer: KthComputer

    @AfterEach
    fun afterEach() {
        clearMocks(kthComputer)
    }

    @Test
    fun testResult_ShouldCallComputerAndSaveResult_ThenReturnCorrectResult() {
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false)
        expression.computer = kthComputer
        every { kthComputer.compute(listOf(num(0)), emptyMap()) } returns -1.0
        assertEquals(-1.0, expression.result())
        assertEquals(-1.0, expression.singleResult)
        assertEquals(-1.0, expression.result())
        verify(exactly = 1) { kthComputer.compute(listOf(num(0)), emptyMap()) }
    }

    @Test
    fun testResult_ShouldThrowException_WhenVariablesRequiredAndNoInputVariables() {
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), false)
        assertThrows<KthUndefinedVariableException> { expression.result() }
    }

    @Test
    fun testIntResult_ShouldReturnCorrectResult() {
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false)
        expression.computer = kthComputer
        every { kthComputer.compute(listOf(num(0)), emptyMap()) } returns -1.0
        assertEquals(-1, expression.intResult())
    }

    @Test
    fun testResultWith_ShouldCallComputerTwice_WhenCacheDisabled() {
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false)
        expression.computer = kthComputer
        every { kthComputer.compute(listOf(num(0)), emptyMap()) } returns -1.0
        expression.resultWith()
        assertTrue { expression.resultCache.isEmpty() }
        expression.resultWith()
        verify(exactly = 2) { kthComputer.compute(listOf(num(0)), emptyMap()) }
    }

    @Test
    fun testResultWith_ShouldCallComputerOnce_WhenSameParamsAndCacheEnabled() {
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), true)
        expression.computer = kthComputer
        every { kthComputer.compute(listOf(num(0)), any()) } returns -1.0
        expression.resultWith("a" to 1)
        assertFalse { expression.resultCache.isEmpty() }
        expression.resultWith("a" to 1)
        verify(exactly = 1) { kthComputer.compute(listOf(num(0)), mapOf("a" to 1)) }
        expression.resultWith("a" to 2)
        verify(exactly = 1) { kthComputer.compute(listOf(num(0)), mapOf("a" to 2)) }
    }

    @Test
    fun testResultWith_ShouldThrowException_WhenRequiredVariableNotSet() {
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), false)
        assertThrows<KthUndefinedVariableException> { expression.resultWith() }
    }

    @Test
    fun testIntResultWith_ShouldReturnCorrectResult() {
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false)
        expression.computer = kthComputer
        every { kthComputer.compute(listOf(num(0)), emptyMap()) } returns -1.0
        assertEquals(-1, expression.intResultWith())
    }
}