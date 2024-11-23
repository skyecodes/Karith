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

package com.skyecodes.karith

import com.skyecodes.karith.KthSymbol.entries


/**
 * Base interface for all tokens can be parsed in a [KthExpression].
 */
sealed interface KthToken

/**
 * Common interface for [KthOperator], [KthFunction] and [KthConstant].
 */
sealed interface KthElement : KthToken {
    /**
     * The key of the token. Used to parse the token in an expression.
     */
    val key: String
}

/**
 * Operator token to be included in a [KthModule] or [KthContext] and used in an expression.
 *
 * An operator takes two arguments (one on the left and one on the right) and returns a value.
 *
 * Operators can be built using the helper functions [createOperator] and [asOperator].
 */
interface KthOperator : KthElement {
    /**
     * The operator precedence, which is used to determine the order of operations.
     */
    val precedence: Int

    /**
     * Whether the operator is left associative, which is used to determine the order of operations.
     */
    val leftAssociative: Boolean

    /**
     * Applies the operator to the given arguments.
     *
     * @param left the left argument
     * @param right the right argument
     * @return the result of the operation
     */
    operator fun invoke(left: Double, right: Double): Double
}

/**
 * Function token to be included in a [KthModule] or [KthContext] and used in an expression.
 *
 * A function takes a fixed amount of arguments and returns a value.
 *
 * Functions can be built using the helper functions [createFunction] and [asFunction].
 */
interface KthFunction : KthElement {
    /**
     * The number of arguments of the function
     */
    val argCount: Int

    /**
     * Applies the function with the specified arguments and returns its result.
     *
     * @param args the arguments of the function
     * @return the result of the function
     */
    operator fun invoke(vararg args: Double): Double
}

/**
 * Constant token to be included in a [KthModule] or [KthContext] and used in an expression.
 *
 * A constant simply gets replaced by its value during the evaluation of an expression.
 *
 * Constants can be built using the helper functions [createConstant] and [asConstant].
 */
interface KthConstant : KthElement {
    /**
     * The value of the constant.
     */
    val value: Number
}


internal enum class KthSymbol(val c: Char) : KthToken {

    LEFT_PARENTHESIS('('), RIGHT_PARENTHESIS(')'), COMMA(',');

    companion object {
        val inverse = entries.associateBy({ it.c }) { it }
    }
}

internal data class KthNumber(val value: Double) : KthToken

internal data class KthVariable(val name: String) : KthToken
