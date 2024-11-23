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

/**
 * Expressions contain all the data needed to evaluate an arithmetic expression
 * when given input variables (if any are needed).
 *
 * You can compute an expression's result with the [getResult] or [getResultWith] method.
 *
 * Expressions are built using a [KthContext].
 */
interface KthExpression {
    /**
     * Contains the different variables used in the expression.
     */
    val expressionVars: Set<String>

    /**
     * Whether caching is enabled or not. Default is based on the context's cache property.
     *
     * Caching avoids having to compute the same expression several times
     * when using the same input variables.
     */
    var cacheEnabled: Boolean

    /**
     * Disables the cache for this expression.
     *
     * @return this expression
     * @see cacheEnabled
     */
    fun disableCache() = apply { cacheEnabled = false }

    /**
     * Enables the cache for this expression.
     *
     * @return this expression
     * @see cacheEnabled
     */
    fun enableCache() = apply { cacheEnabled = true }

    /**
     * Evaluates the expression and returns its result.
     *
     * @return the result of the expression
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     */
    fun getResult(): KthValueResult

    /**
     * Evaluates the expression using the input variables and return its result.
     *
     * @param inputVars the input variables
     * @return the result of the expression
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     */
    fun getResultWith(vararg inputVars: Pair<String, Number>): KthValueResult

    /**
     * Clears the result cache of the expression.
     */
    fun clearCache()
}
