package dev.franckyi.karith.impl

import dev.franckyi.karith.api.*

internal class KthContextImpl(
    elementMap: MutableMap<String, KthElement>,
    combinerOperator: KthOperator,
    override val cacheEnabled: Boolean
) : KthContext {
    internal val strExpressionCache = mutableMapOf<String, KthExpression>()
    internal val tokenizedExpressionCache = mutableMapOf<List<KthToken>, KthExpression>()
    internal val sortedTokenizedExpressionCache = mutableMapOf<List<KthToken>, KthExpression>()
    internal var tokenizer: KthTokenizer = KthTokenizerImpl(elementMap, combinerOperator)
    internal var sorter: KthSorter = KthSorterImpl
    internal var expressionFactory: (List<KthToken>, Set<String>) -> KthExpression =
        { sortedTokens, vars -> KthExpressionImpl(sortedTokens, vars, cacheEnabled) }

    override fun expression(expr: String): KthExpression = createExpression(expr, null)

    override fun expressionWith(expr: String, vararg declaredVars: String): KthExpression =
        createExpression(expr, declaredVars)

    private fun createExpression(expr: String, declaredVars: Array<out String>?): KthExpression {
        if (cacheEnabled && expr in strExpressionCache) {
            return strExpressionCache[expr]!!
        }
        val (tokens, vars) = tokenizer.tokenize(expr, declaredVars)
        if (cacheEnabled && tokens in tokenizedExpressionCache) {
            val expression = tokenizedExpressionCache[tokens]!!
            strExpressionCache[expr] = expression
            return expression
        }
        val sortedTokens = sorter.sort(tokens)
        if (cacheEnabled && sortedTokens in sortedTokenizedExpressionCache) {
            val expression = sortedTokenizedExpressionCache[sortedTokens]!!
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