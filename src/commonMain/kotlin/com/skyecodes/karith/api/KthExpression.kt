package com.skyecodes.karith.api

/**
 * Expressions contain all the data needed to evaluate an arithmetic expression
 * when given input variables (if any are needed).
 *
 * You can compute an expression's result with the [getResult], [getResultAsInt], [getResultWith] or [getResultAsIntWith] method.
 *
 * They are built by a [KthContext].
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
    fun getResult(): Double

    /**
     * Evaluates the expression and return its result as an integer.
     *
     * @return the result of the expression as an integer
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     */
    fun getResultAsInt(): Int = getResult().toInt()

    /**
     * Evaluates the expression using the input variables and return its result.
     *
     * @param inputVars the input variables
     * @return the result of the expression
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     */
    fun getResultWith(vararg inputVars: Pair<String, Number>): Double

    /**
     * Evaluates the expression using the input variables and return its result as an integer.
     *
     * @param inputVars the input variables
     * @return the result of the expression as an integer
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     */
    fun getResultAsIntWith(vararg inputVars: Pair<String, Number>): Int = getResultWith(*inputVars).toInt()

    /**
     * Clears the result cache of the expression.
     */
    fun clearCache()
}
