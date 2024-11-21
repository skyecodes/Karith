package com.skyecodes.karith.impl

import com.skyecodes.karith.api.*

internal object KthSorterImpl : KthSorter {
    override fun sort(tokens: List<KthToken>): List<KthToken> {
        val outputQueue = mutableListOf<KthToken>()
        val operatorStack = ArrayDeque<KthToken>()
        var index = 0
        tokens.forEach { token ->
            when (token) {
                is KthFunction -> operatorStack += token
                is KthOperator -> {
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

                is KthSymbol -> {
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
