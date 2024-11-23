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

internal class KthContextImpl(
    elementMap: Map<String, KthContextualToken>,
    combinerOperator: KthOperator,
    override val cacheEnabled: Boolean,
    internal val tokenizer: KthTokenizer = KthTokenizer(elementMap, combinerOperator),
    private val sorter: KthSorter = KthSorter,
    private val expressionFactory: KthExpressionFactory = { sortedTokens, vars ->
        KthExpressionImpl(sortedTokens, vars, cacheEnabled)
    }
) : KthContext {
    internal val strExpressionCache = mutableMapOf<String, KthExpressionResult>()
    internal val tokenizedExpressionCache = mutableMapOf<List<KthToken>, KthExpressionResult>()
    internal val sortedTokenizedExpressionCache = mutableMapOf<List<KthToken>, KthExpressionResult>()

    override fun expressionOf(expr: String): KthExpressionResult = createExpression(expr, null)

    override fun expressionOfWith(expr: String, vararg declaredVars: String): KthExpressionResult =
        createExpression(expr, declaredVars.toList())

    private fun createExpression(expr: String, declaredVars: List<String>?): KthExpressionResult {
        if (cacheEnabled && expr in strExpressionCache) {
            return strExpressionCache.getValue(expr)
        }

        // Tokenize
        val (tokens, vars) = try {
            tokenizer(expr, declaredVars)
        } catch (e: KthParseException) {
            return error(e) { if (cacheEnabled) strExpressionCache[expr] = it }
        }
        if (cacheEnabled && tokens in tokenizedExpressionCache) {
            val expression = tokenizedExpressionCache.getValue(tokens)
            strExpressionCache[expr] = expression
            return expression
        }

        // Sort
        val sortedTokens: List<KthToken> = try {
            sorter(tokens)
        } catch (e: KthParseException) {
            return error(e) {
                if (cacheEnabled) {
                    strExpressionCache[expr] = it
                    tokenizedExpressionCache[tokens] = it
                }
            }
        }
        if (cacheEnabled && sortedTokens in sortedTokenizedExpressionCache) {
            val expression = sortedTokenizedExpressionCache.getValue(sortedTokens)
            strExpressionCache[expr] = expression
            tokenizedExpressionCache[tokens] = expression
            return expression
        }

        // Create expression
        val expression: KthExpressionResult = success(expressionFactory(sortedTokens, vars))
        if (cacheEnabled) {
            strExpressionCache[expr] = expression
            tokenizedExpressionCache[tokens] = expression
            sortedTokenizedExpressionCache[sortedTokens] = expression
        }
        return expression
    }

    override fun clearCache() {
        strExpressionCache.clear()
        tokenizedExpressionCache.clear()
        sortedTokenizedExpressionCache.clear()
    }

    internal class BuilderImpl : AbstractKthBuilder<KthContext.Builder>(), KthContext.Builder {
        override var enableCache = true

        override fun build(): KthContext {
            val elementMap = buildElementMap()
            return KthContextImpl(elementMap, combinerOperator!!, enableCache)
        }
    }
}

typealias KthExpressionFactory = (List<KthToken>, Set<String>) -> KthExpression
