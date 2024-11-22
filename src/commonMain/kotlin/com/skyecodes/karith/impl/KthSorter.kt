package com.skyecodes.karith.impl

import com.skyecodes.karith.api.*

internal fun interface KthSorter {
    operator fun invoke(tokens: List<KthToken>): List<KthToken>

    companion object : KthSorter {
        /**
         * Shunting yard algorithm implementation
         */
        override fun invoke(tokens: List<KthToken>): List<KthToken> = with(KthSorterState()) {
            tokens.forEach { token ->
                when (token) {
                    is KthFunction -> operatorStack += token
                    is KthOperator -> processOperator(token)
                    is KthSymbol -> processSymbol(token)
                    else -> outputQueue += token
                }
                index++
            }
            while (operatorStack.isNotEmpty()) {
                if (operatorStack.last() == KthSymbol.RIGHT_PARENTHESIS) {
                    throw KthMismatchedParenthesesException(index)
                }
                outputQueue += operatorStack.removeLast()
            }
            return outputQueue
        }
    }
}

internal data class KthSorterState(
    val outputQueue: MutableList<KthToken> = mutableListOf<KthToken>(),
    val operatorStack: ArrayDeque<KthToken> = ArrayDeque<KthToken>(),
    var index: Int = 0,
) {
    fun processOperator(token: KthOperator) {
        while (operatorStack.isNotEmpty() && operatorStack.last() != KthSymbol.LEFT_PARENTHESIS) {
            val last = operatorStack.last()
            val lastTokenPrecedence = if (last is KthOperator) last.precedence else Int.MAX_VALUE
            if (lastTokenPrecedence > token.precedence || lastTokenPrecedence == token.precedence && token.leftAssociative) {
                outputQueue += operatorStack.removeLast()
            } else {
                break
            }
        }
        operatorStack += token
    }

    fun processSymbol(token: KthSymbol) {
        if (token == KthSymbol.LEFT_PARENTHESIS) {
            operatorStack += token
        } else if (token == KthSymbol.RIGHT_PARENTHESIS) {
            while (operatorStack.lastOrNull() != KthSymbol.LEFT_PARENTHESIS) {
                if (operatorStack.isEmpty()) {
                    throw KthMismatchedParenthesesException(index)
                }
                outputQueue += operatorStack.removeLast()
            }
            if (operatorStack.removeLastOrNull() != KthSymbol.LEFT_PARENTHESIS) {
                throw KthMismatchedParenthesesException(index)
            }
            if (operatorStack.lastOrNull() is KthFunction) {
                outputQueue += operatorStack.removeLast()
            }
        }
    }
}