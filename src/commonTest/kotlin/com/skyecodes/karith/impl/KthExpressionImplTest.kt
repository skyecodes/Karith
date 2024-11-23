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

import com.skyecodes.karith.KthUndefinedVariableException
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import kotlin.test.*

internal class KthExpressionImplTest {
    @Test
    fun testResult_ShouldCallCalculaterAndSaveResult_ThenReturnCorrectResult() {
        val kthCalculator: KthCalculator = mock<KthCalculator>()
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false, kthCalculator)
        every { kthCalculator(listOf(num(0)), emptyMap()) } returns -1.0
        assertEquals(-1.0, expression.calculateResult().orThrow())
        assertEquals(-1.0, expression.singleResult!!.orThrow())
        assertEquals(-1.0, expression.calculateResult().orThrow())
        verify(exactly(1)) { kthCalculator(listOf(num(0)), emptyMap()) }
    }

    @Test
    fun testResult_ShouldThrowException_WhenVariablesRequiredAndNoInputVariables() {
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), false)
        assertFailsWith<KthUndefinedVariableException> { expression.calculateResult().orThrow() }
    }

    @Test
    fun testCalculateResultWith_ShouldCallCalculaterTwice_WhenCacheDisabled() {
        val kthCalculator: KthCalculator = mock<KthCalculator>()
        val expression = KthExpressionImpl(listOf(num(0)), emptySet(), false, kthCalculator)
        every { kthCalculator(listOf(num(0)), emptyMap()) } returns -1.0
        expression.calculateResultWith()
        assertTrue { expression.resultCache.isEmpty() }
        expression.calculateResultWith()
        verify(exactly(2)) { kthCalculator(listOf(num(0)), emptyMap()) }
    }

    @Test
    fun testCalculateResultWith_ShouldCallCalculaterOnce_WhenSameParamsAndCacheEnabled() {
        val kthCalculator: KthCalculator = mock<KthCalculator>()
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), true, kthCalculator)
        every { kthCalculator(listOf(num(0)), any()) } returns -1.0
        expression.calculateResultWith("a" to 1)
        assertFalse { expression.resultCache.isEmpty() }
        expression.calculateResultWith("a" to 1)
        verify(exactly(1)) { kthCalculator(listOf(num(0)), mapOf("a" to 1)) }
        expression.calculateResultWith("a" to 2)
        verify(exactly(1)) { kthCalculator(listOf(num(0)), mapOf("a" to 2)) }
    }

    @Test
    fun testCalculateResultWith_ShouldThrowException_WhenRequiredVariableNotSet() {
        val expression = KthExpressionImpl(listOf(num(0)), setOf("a"), false)
        assertFailsWith<KthUndefinedVariableException> { expression.calculateResultWith().orThrow() }
    }
}