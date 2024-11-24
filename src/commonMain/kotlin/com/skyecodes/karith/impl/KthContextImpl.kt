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
    private val elementMap: Map<String, KthElement>,
    private val combinerOperator: KthOperator?,
    private val cacheEnabled: Boolean,
    internal val tokenizer: KthTokenizer = KthTokenizer(elementMap, combinerOperator),
    private val sorter: KthSorter = KthSorter,
    private val expressionFactory: KthExpressionFactory = { sortedTokens, vars ->
        KthExpressionImpl(sortedTokens, vars, cacheEnabled)
    }
) : KthContext {
    internal val strExpressionCache = mutableMapOf<String, KthParsingResult>()
    internal val tokenizedExpressionCache = mutableMapOf<List<KthToken>, KthParsingResult>()
    internal val sortedTokenizedExpressionCache = mutableMapOf<List<KthToken>, KthParsingResult>()

    override fun parseExpression(expr: String): KthParsingResult = createExpression(expr, null)

    override fun parseExpressionWith(expr: String, vararg declaredVars: String): KthParsingResult =
        createExpression(expr, declaredVars.toList())

    private fun createExpression(expr: String, declaredVars: List<String>?): KthParsingResult {
        if (this@KthContextImpl.cacheEnabled && expr in strExpressionCache) {
            return strExpressionCache.getValue(expr)
        }

        // Tokenize
        val (tokens, vars) = try {
            tokenizer(expr, declaredVars)
        } catch (e: KthParsingException) {
            return error(e) { if (this@KthContextImpl.cacheEnabled) strExpressionCache[expr] = it }
        }
        if (this@KthContextImpl.cacheEnabled && tokens in tokenizedExpressionCache) {
            val expression = tokenizedExpressionCache.getValue(tokens)
            strExpressionCache[expr] = expression
            return expression
        }

        // Sort
        val sortedTokens: List<KthToken> = try {
            sorter(tokens)
        } catch (e: KthParsingException) {
            return error(e) {
                if (this@KthContextImpl.cacheEnabled) {
                    strExpressionCache[expr] = it
                    tokenizedExpressionCache[tokens] = it
                }
            }
        }
        if (this@KthContextImpl.cacheEnabled && sortedTokens in sortedTokenizedExpressionCache) {
            val expression = sortedTokenizedExpressionCache.getValue(sortedTokens)
            strExpressionCache[expr] = expression
            tokenizedExpressionCache[tokens] = expression
            return expression
        }

        // Create expression
        val expression: KthParsingResult = success(expressionFactory(sortedTokens, vars))
        if (this@KthContextImpl.cacheEnabled) {
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

    override fun copy(builder: KthContext.Builder.() -> Unit): KthContext {
        return KthContext {
            with(elementMap.values)
            combinerOperator?.let { withCombinerOperator(it) }
            if (!cacheEnabled) disableCache()
            builder()
        }
    }

    internal class BuilderImpl : AbstractKthBuilder(), KthContext.Builder {
        private var cacheEnabled = true

        override fun disableCache() {
            cacheEnabled = false
        }

        override fun build(): KthContext {
            val (elementMap, combinerOperator) = doBuild()
            return KthContextImpl(elementMap, combinerOperator, cacheEnabled)
        }
    }
}

internal typealias KthExpressionFactory = (List<KthToken>, Set<String>) -> KthExpression
