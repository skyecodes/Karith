package com.skyecodes.karith.impl

import com.skyecodes.karith.api.KthExpression
import com.skyecodes.karith.api.KthToken
import com.skyecodes.karith.api.KthUndefinedVariableException

internal class KthExpressionImpl(
    internal val tokens: List<KthToken>,
    override val expressionVars: Set<String>,
    override var cacheEnabled: Boolean,
    private val computer: KthComputer = KthComputer
) : KthExpression {
    internal var singleResult: Double? = null
    internal val resultCache = mutableMapOf<Map<String, Number>, Double>()

    override fun getResult(): Double {
        return singleResult ?: getResultWith().also { singleResult = it }
    }

    override fun getResultWith(vararg inputVars: Pair<String, Number>): Double {
        val variables = filterInputVariables(inputVars)
        if (cacheEnabled) {
            resultCache.forEach { (cachedVars, cachedResult) ->
                if (cachedVars.all { variables[it.key] == it.value }) {
                    return cachedResult
                }
            }
        }
        val result = computer(tokens, variables)
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