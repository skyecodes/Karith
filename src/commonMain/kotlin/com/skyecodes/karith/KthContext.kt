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
 * Contexts are the main entrypoint of the Karith API.
 * Their primary function is to parse strings into expressions, which can then be computed to obtain a result.
 *
 * A context defines a certain set of operators, functions and constants that can be used in expressions.
 *
 * A context can be built using helper functions such as [buildContext], [buildBaseContext], [buildMathContext] or [buildDefaultContext].
 *
 * You can parse a string into an expression using the [expressionOf] or [asExpressionWith] method.
 * Then you can compute the expression's result using the [KthExpression.getResult] method.
 *
 * A global context [defaultCtx] is available.
 * Functions from this class can be used directly without having to create a context: the default context will be used.
 */
interface KthContext {
    /**
     * Whether caching is enabled or not. Default is true.
     *
     * Caching avoids having to parse the same expression several times when given the same input string or tokens and
     * can greatly increase the performance.
     *
     * By default, caching also avoids having to compute the same expression several times when given the same input
     * variables, though the caching capability can also be modified at the expression level.
     */
    val cacheEnabled: Boolean

    /**
     * Parses an arithmetic expression from the given string.
     *
     * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
     *
     * @param expr the arithmetic expression to parse
     * @return the parsed [KthExpression]
     * @throws KthIllegalTokenException if an illegal token is found in the expression
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @see asExpressionWith
     */
    fun expressionOf(expr: String): KthExpressionResult

    /**
     * Parses an arithmetic expression from the receiving string.
     *
     * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
     *
     * @return the parsed [KthExpression]
     * @throws KthIllegalTokenException if an illegal token is found in the expression
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @see asExpressionWith
     */
    fun String.asExpression(): KthExpressionResult = expressionOf(this)

    /**
     * Parses an arithmetic expression from the given string and declared variables.
     *
     * Any unknown alphanumeric token that is not a number, an element or a declared variable is considered illegal.
     *
     * @param expr the arithmetic expression to parse
     * @param declaredVars the declared vars that can be used in the expression
     * @return the parsed [KthExpression]
     * @throws KthIllegalTokenException if an illegal token is found in the expression
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @see expressionOf
     */
    fun expressionOfWith(expr: String, vararg declaredVars: String): KthExpressionResult

    /**
     * Parses an arithmetic expression from the receiving string and given declared variables.
     *
     * Any unknown alphanumeric token that is not a number, an element or a declared variable is considered illegal.
     *
     * @param declaredVars the declared vars that can be used in the expression
     * @return the parsed [KthExpression]
     * @throws KthIllegalTokenException if an illegal token is found in the expression
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @see expressionOf
     */
    fun String.asExpressionWith(vararg declaredVars: String): KthExpressionResult =
        expressionOfWith(this, *declaredVars)

    /**
     * Parses an arithmetic expression from the given string, then return its result.
     *
     * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
     *
     * @param expr the arithmetic expression to parse
     * @return the result of the expression
     * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     * @see KthContext.expressionOf
     * @see KthExpression.getResult
     */
    fun resultOf(expr: String): KthExpressionValueResult = expressionOf(expr).map { it.getResult() }

    /**
     * Parses an arithmetic expression from the receiving string, then return its result.
     *
     * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
     *
     * @return the result of the expression
     * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     * @see KthContext.expressionOf
     * @see KthExpression.getResult
     */
    fun String.getResult(): KthExpressionValueResult = resultOf(this)

    /**
     * Parses an arithmetic expression from the given string, then return its result.
     *
     * Any unknown alphanumeric token that is not a number, an element or an input variable is considered illegal.
     *
     * @param expr the arithmetic expression to parse
     * @param inputVars the input variables that are used in the expression
     * @return the result of the expression
     * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     * @see KthContext.asExpressionWith
     * @see KthExpression.getResultWith
     */
    fun resultOfWith(expr: String, vararg inputVars: Pair<String, Number>): KthExpressionValueResult =
        expressionOfWith(expr, *inputVars.map { it.first }.toTypedArray()).map { it.getResultWith(*inputVars) }

    /**
     * Parses an arithmetic expression from the receiving string, then return its result.
     *
     * Any unknown alphanumeric token that is not a number, an element or an input variable is considered illegal.
     *
     * @param inputVars the input variables that are used in the expression
     * @return the result of the expression
     * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     * @see KthContext.asExpressionWith
     * @see KthExpression.getResultWith
     */
    fun String.getResultWith(vararg inputVars: Pair<String, Number>): KthExpressionValueResult =
        resultOfWith(this, *inputVars)

    /**
     * Clears the expression cache of the context.
     */
    fun clearCache()

    /**
     * Builder interface for [KthContext].
     *
     * A context builder can be used with [buildContext], [buildBaseContext], [buildMathContext] and [buildDefaultContext].
     */
    interface Builder : KthBuilder<Builder> {
        /**
         * Whether caching is enabled or not. Default is true.
         *
         * Caching avoids having to parse the same expression several times when given the same input string or tokens and
         * can greatly increase the performance.
         *
         * By default, caching also avoids having to compute the same expression several times when given the same input
         * variables, though the caching capability can also be modified at the expression level.
         */
        var enableCache: Boolean

        /**
         * Disables the cache.
         *
         * @return this builder
         * @see enableCache
         */
        fun disableCache() = apply { enableCache = false }

        /**
         * Builds the context.
         *
         * @return the context
         */
        fun build(): KthContext
    }
}