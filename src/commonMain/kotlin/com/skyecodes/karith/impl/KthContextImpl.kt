package com.skyecodes.karith.impl

import com.skyecodes.karith.api.*

internal class KthContextImpl(
    elementMap: Map<String, KthElement>,
    combinerOperator: KthOperator,
    override val cacheEnabled: Boolean,
    internal val tokenizer: KthTokenizer = KthTokenizer(elementMap, combinerOperator),
    private val sorter: KthSorter = KthSorter,
    private val expressionFactory: KthExpressionFactory = { sortedTokens, vars ->
        KthExpressionImpl(
            sortedTokens,
            vars,
            cacheEnabled
        )
    }
) : KthContext {
    internal val strExpressionCache = mutableMapOf<String, KthExpression>()
    internal val tokenizedExpressionCache = mutableMapOf<List<KthToken>, KthExpression>()
    internal val sortedTokenizedExpressionCache = mutableMapOf<List<KthToken>, KthExpression>()

    override fun expressionOf(expr: String): KthExpression = createExpression(expr, null)

    override fun expressionOfWith(expr: String, vararg declaredVars: String): KthExpression =
        createExpression(expr, declaredVars.toList())

    private fun createExpression(expr: String, declaredVars: List<String>?): KthExpression {
        if (cacheEnabled && expr in strExpressionCache) {
            return strExpressionCache.getValue(expr)
        }
        val (tokens, vars) = tokenizer(expr, declaredVars)
        if (cacheEnabled && tokens in tokenizedExpressionCache) {
            val expression = tokenizedExpressionCache.getValue(tokens)
            strExpressionCache[expr] = expression
            return expression
        }
        val sortedTokens = sorter(tokens)
        if (cacheEnabled && sortedTokens in sortedTokenizedExpressionCache) {
            val expression = sortedTokenizedExpressionCache.getValue(sortedTokens)
            strExpressionCache[expr] = expression
            tokenizedExpressionCache[tokens] = expression
            return expression
        }
        val expression = expressionFactory(sortedTokens, vars)
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
}

typealias KthExpressionFactory = (List<KthToken>, Set<String>) -> KthExpression