package com.skyecodes.karith.api

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
     * @see asExpressionWith
     */
    fun expressionOf(expr: String): KthExpression

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
    fun String.asExpression(): KthExpression = expressionOf(this)

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
    fun expressionOfWith(expr: String, vararg declaredVars: String): KthExpression

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
    fun String.asExpressionWith(vararg declaredVars: String): KthExpression = expressionOfWith(this, *declaredVars)

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
    fun resultOf(expr: String): Double = expressionOf(expr).getResult()

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
    fun String.getResult(): Double = resultOf(this)

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
     * @see KthContext.expressionOf
     * @see KthExpression.getResultAsInt
     */
    fun intResultOf(expr: String): Int = expressionOf(expr).getResultAsInt()

    /**
     * Parses an arithmetic expression from the receiving string, then return its result as an integer.
     *
     * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
     *
     * @return the result of the expression as an integer
     * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     * @see KthContext.expressionOf
     * @see KthExpression.getResultAsInt
     */
    fun String.getIntResult(): Int = intResultOf(this)

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
    fun resultOfWith(expr: String, vararg inputVars: Pair<String, Number>): Double =
        expressionOfWith(expr, *inputVars.map { it.first }.toTypedArray()).getResultWith(*inputVars)

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
    fun String.getResultWith(vararg inputVars: Pair<String, Number>): Double = resultOfWith(this, *inputVars)

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
     * @see KthContext.asExpressionWith
     * @see KthExpression.getResultAsIntWith
     */
    fun intResultOfWith(expr: String, vararg inputVars: Pair<String, Number>): Int =
        expressionOfWith(expr, *inputVars.map { it.first }.toTypedArray()).getResultAsIntWith(*inputVars)

    /**
     * Parses an arithmetic expression from the receiving string, then return its result as an integer.
     *
     * Any unknown alphanumeric token that is not a number, an element or an input variable is considered illegal.
     *
     * @param inputVars the input variables that are used in the expression
     * @return the result of the expression as an integer
     * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
     * @throws KthMismatchedParenthesesException if some parentheses are mismatched
     * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
     * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
     * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
     * @see KthContext.asExpressionWith
     * @see KthExpression.getResultAsIntWith
     */
    fun String.getIntResultWith(vararg inputVars: Pair<String, Number>): Int = intResultOfWith(this, *inputVars)

    /**
     * Clears the expression cache of the context.
     */
    fun clearCache()
}