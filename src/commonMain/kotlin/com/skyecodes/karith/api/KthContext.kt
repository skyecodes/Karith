package com.skyecodes.karith.api

/**
 * Contexts are the main entrypoint of the Karith API.
 * Their primary function is to parse strings into expressions, which can then be computed to obtain a result.
 *
 * A context defines a certain set of operators, functions and constants that can be used in expressions.
 *
 * A context can be built using helper functions such as [context], [baseContext], [mathContext] or [defaultContext].
 *
 * You can parse a string into an expression using the [expression] or [expressionWith] method.
 * Then you can compute the expression's result using the [KthExpression.result] method.
 *
 * A global context [Kth] is available, but it should only be used for testing purposes.
 * It is highly recommended to build your own context, which also allows for more customization.
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
     * @see expressionWith
     */
    fun expression(expr: String): KthExpression

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
     * @see expr
     */
    fun expressionWith(expr: String, vararg declaredVars: String): KthExpression

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
     * @see KthContext.expression
     * @see KthExpression.result
     */
    fun result(expr: String): Double = expression(expr).result()

    /**
     * Parses an arithmetic expression from the given string, then return its result as an integer.
     *
     * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
     *
     * @param expr the arithmetic expression to parse
     * @return the result of the expression as an integer
     * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     * @see KthContext.expression
     * @see KthExpression.intResult
     */
    fun intResult(expr: String): Int = expression(expr).intResult()

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
     * @see KthContext.expressionWith
     * @see KthExpression.resultWith
     */
    fun resultWith(expr: String, vararg inputVars: Pair<String, Number>): Double =
        expressionWith(expr, *inputVars.map { it.first }.toTypedArray()).resultWith(*inputVars)

    /**
     * Parses an arithmetic expression from the given string, then return its result as an integer.
     *
     * Any unknown alphanumeric token that is not a number, an element or an input variable is considered illegal.
     *
     * @param expr the arithmetic expression to parse
     * @param inputVars the input variables that are used in the expression
     * @return the result of the expression as an integer
     * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     * @see KthContext.expressionWith
     * @see KthExpression.intResultWith
     */
    fun intResultWith(expr: String, vararg inputVars: Pair<String, Number>): Int =
        expressionWith(expr, *inputVars.map { it.first }.toTypedArray()).intResultWith(*inputVars)

    /**
     * Clears the expression cache of the context.
     */
    fun clearCache()
}