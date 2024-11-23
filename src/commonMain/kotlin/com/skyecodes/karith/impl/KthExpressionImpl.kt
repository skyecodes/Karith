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

import com.skyecodes.karith.*

internal class KthExpressionImpl(
    internal val tokens: List<KthToken>,
    override val expressionVars: Set<String>,
    override var cacheEnabled: Boolean,
    private val calculator: KthCalculator = KthCalculator
) : KthExpression {
    internal var singleResult: KthCalculationResult? = null
    internal val resultCache = mutableMapOf<Map<String, Number>, KthCalculationResult>()

    override fun calculateResult(): KthCalculationResult {
        return singleResult ?: calculateResultWith().also { singleResult = it }
    }

    override fun calculateResultWith(vararg inputVars: Pair<String, Number>): KthCalculationResult {
        val variables = try {
            filterInputVariables(inputVars)
        } catch (e: KthCalculationException) {
            return error(e)
        }
        if (cacheEnabled) {
            resultCache.forEach { (cachedVars, cachedResult) ->
                if (cachedVars.all { variables[it.key] == it.value }) {
                    return cachedResult
                }
            }
        }
        val result: KthCalculationResult = try {
            success(calculator(tokens, variables))
        } catch (e: KthCalculationException) {
            error(e)
        }
        if (cacheEnabled) {
            resultCache[variables] = result
        }
        return result
    }

    override fun clearCache() = resultCache.clear()

    private fun filterInputVariables(inputVars: Array<out Pair<String, Number>>): Map<String, Number> {
        val variables = mutableMapOf<String, Number>()
        inputVars.forEach { (key, value) ->
            if (key in expressionVars) {
                variables[key] = value
            }
        }
        expressionVars.forEach {
            if (it !in variables) {
                throw KthUndefinedVariableException(it)
            }
        }
        return variables
    }
}