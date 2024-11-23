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
                if (operatorStack.last() is KthSymbol) {
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