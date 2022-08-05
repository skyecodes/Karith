package dev.franckyi.karith.impl

import dev.franckyi.karith.api.KthComputer
import dev.franckyi.karith.api.KthExpression
import dev.franckyi.karith.api.KthToken
import dev.franckyi.karith.api.KthUndefinedVariableException

internal class KthExpressionImpl(
    internal val tokens: List<KthToken>,
    override val expressionVars: Set<String>,
    override var cacheEnabled: Boolean
) : KthExpression {
    internal var singleResult: Double? = null
    internal val resultCache = mutableMapOf<Map<String, Number>, Double>()
    internal var computer: KthComputer = KthComputerImpl

    override fun result(): Double {
        if (singleResult == null) {
            singleResult = resultWith()
        }
        return singleResult!!
    }

    override fun resultWith(vararg inputVars: Pair<String, Number>): Double {
        val variables = filterInputVariables(inputVars)
        if (cacheEnabled) {
            resultCache.forEach { (cachedVars, cachedResult) ->
                if (cachedVars.all { variables[it.key] == it.value }) {
                    return cachedResult
                }
            }
        }
        val result = computer.compute(tokens, variables)
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