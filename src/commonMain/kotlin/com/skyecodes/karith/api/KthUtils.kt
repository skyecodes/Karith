package com.skyecodes.karith.api

import com.skyecodes.karith.api.builtin.Modules
import com.skyecodes.karith.api.builtin.Operators
import com.skyecodes.karith.impl.*

/**
 * A global context that includes [Modules.BASE], [Modules.MATH] and [Operators.POWER].
 *
 * Expression and result caching is disabled for this context. Result caching can be manually turned on for any
 * expression parsed by this context using [KthExpression.enableCache].
 *
 * **This context should only be used for testing or demonstration purposes; it is recommended to build your own context using one of the helper functions [buildContext], [buildBaseContext],
 * [buildMathContext] or [buildDefaultContext].**
 */
val Kth: KthContext by lazy { buildDefaultContext { disableCache() } }

/**
 * Parses an arithmetic expression from the given string using the global context [Kth].
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @param expressionOf the arithmetic expression to parse
 * @return the parsed [KthExpression]
 * @throws KthIllegalTokenException if an illegal token is found in the expression
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @see KthContext.expressionOf
 */
fun expressionOf(expression: String) = with(Kth) { this.expressionOf(expression) }

/**
 * Parses an arithmetic expression from the receiving string using the global context [Kth].
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @return the parsed [KthExpression]
 * @throws KthIllegalTokenException if an illegal token is found in the expression
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @see KthContext.expressionOf
 */
fun String.asExpression() = with(Kth) { this@asExpression.asExpression() }

/**
 * Parses an arithmetic expression from the given string and declared variables using the global context [Kth].
 *
 * Any unknown alphanumeric token that is not a number, an element or a declared variable is considered illegal.
 *
 * @param expression the arithmetic expression to parse
 * @param declaredVars the declared vars that can be used in the expression
 * @return the parsed [KthExpression]
 * @throws KthIllegalTokenException if an illegal token is found in the expression
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @see KthContext.asExpressionWith
 */
fun expressionOfWith(expression: String, vararg declaredVars: String) =
    with(Kth) { this.expressionOfWith(expression, *declaredVars) }

/**
 * Parses an arithmetic expression from the receiving string and declared variables using the global context [Kth].
 *
 * Any unknown alphanumeric token that is not a number, an element or a declared variable is considered illegal.
 *
 * @param declaredVars the declared vars that can be used in the expression
 * @return the parsed [KthExpression]
 * @throws KthIllegalTokenException if an illegal token is found in the expression
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @see KthContext.asExpressionWith
 */
fun String.asExpressionWith(vararg declaredVars: String) =
    with(Kth) { this@asExpressionWith.asExpressionWith(*declaredVars) }

/**
 * Parses an arithmetic expression from the given string using the global context [Kth], then return its result.
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @param expression the arithmetic expression to parse
 * @return the result of the expression
 * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
 * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
 * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
 * @see KthContext.getResult
 */
fun resultOf(expression: String) = with(Kth) { this.resultOf(expression) }

/**
 * Parses an arithmetic expression from the receiving string using the global context [Kth], then return its result.
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @return the result of the expression
 * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
 * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
 * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
 * @see KthContext.getResult
 */
fun String.getResult() = with(Kth) { this@getResult.getResult() }

/**
 * Parses an arithmetic expression from the given string using the global context [Kth],
 * then return its result as an integer.
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @param expression the arithmetic expression to parse
 * @return the result of the expression as an integer
 * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
 * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
 * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
 * @see KthContext.intResultOf
 */
fun intResultOf(expression: String) = with(Kth) { this.intResultOf(expression) }

/**
 * Parses an arithmetic expression from the receiving string using the global context [Kth],
 * then return its result as an integer.
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @return the result of the expression as an integer
 * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
 * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
 * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
 * @see KthContext.intResultOf
 */
fun String.getResultAsInt() = with(Kth) { this@getResultAsInt.getIntResult() }

/**
 * Parses an arithmetic expression from the given string using the global context [Kth], then return its result.
 *
 * Any unknown alphanumeric token that is not a number, an element or an input variable is considered illegal.
 *
 * @param expression the arithmetic expression to parse
 * @param inputVars the input variables that are used in the expression
 * @return the result of the expression
 * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
 * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
 * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
 * @see KthContext.resultOfWith
 */
fun resultOfWith(expression: String, vararg inputVars: Pair<String, Number>) =
    with(Kth) { this.resultOfWith(expression, *inputVars) }

/**
 * Parses an arithmetic expression from the receiving string using the global context [Kth], then return its result.
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
 * @see KthContext.resultOfWith
 */
fun String.getResultWith(vararg inputVars: Pair<String, Number>) =
    with(Kth) { this@getResultWith.getResultWith(*inputVars) }

/**
 * Parses an arithmetic expression from the given string using the global context [Kth],
 * then return its result as an integer.
 *
 * Any unknown alphanumeric token that is not a number, an element or an input variable is considered illegal.
 *
 * @param expression the arithmetic expression to parse
 * @param inputVars the input variables that are used in the expression
 * @return the result of the expression as an integer
 * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
 * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
 * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
 * @see KthContext.intResultOfWith
 */
fun intResultOfWith(expression: String, vararg inputVars: Pair<String, Number>) =
    with(Kth) { this.intResultOfWith(expression, *inputVars) }

/**
 * Parses an arithmetic expression from the receiving string using the global context [Kth],
 * then return its result as an integer.
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
 * @see KthContext.intResultOfWith
 */
fun String.getResultAsIntWith(vararg inputVars: Pair<String, Number>) =
    with(Kth) { this@getResultAsIntWith.getIntResultWith(*inputVars) }

/**
 * Helper function to build a [KthContext].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun buildContext(block: KthContextBuilder.() -> Unit = {}) = KthContextBuilderImpl().apply(block).build()

/**
 * Helper function to build a [KthContext] that includes [Modules.BASE].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun buildBaseContext(block: KthContextBuilder.() -> Unit = {}) = buildContext { include(Modules.BASE); block() }

/**
 * Helper function to build a [KthContext] that includes [Modules.BASE] and [Modules.MATH].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun buildMathContext(block: KthContextBuilder.() -> Unit = {}) = buildBaseContext { include(Modules.MATH); block() }

/**
 * Helper function to build a [KthContext] that includes [Modules.BASE], [Modules.MATH] and [Operators.POWER].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun buildDefaultContext(block: KthContextBuilder.() -> Unit = {}) = buildMathContext { withPowerOperator(); block() }

/**
 * Helper function to add the [Operators.POWER] operator to a [KthContext] builder.
 *
 * @return the current [KthContextBuilder]
 */
fun KthContextBuilder.withPowerOperator() = withOperator(Operators.POWER)

/**
 * Helper function to build a [KthContext].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun buildModule(block: KthModuleBuilder.() -> Unit = {}) = KthModuleBuilderImpl().apply(block).build()

/**
 * Helper function to create a [KthConstant].
 *
 * @param key the constant key
 * @param value the constant value
 * @return the created [KthConstant]
 */
fun createConstant(key: String, value: Number): KthConstant = KthConstantImpl(key, value)

/**
 * Helper function to create a [KthConstant] using the receiving String as key.
 *
 * @param value the constant value
 * @return the created [KthConstant]
 */
fun String.asConstant(value: Number): KthConstant = createConstant(this, value)

/**
 * Helper function to create a [KthOperator].
 *
 * @param key the operator key
 * @param precedence the operator precedence
 * @param leftAssociative whether the operator is left associative
 * @param operation the operator operation
 * @return the created [KthOperator]
 */
fun createOperator(
    key: String,
    precedence: Int,
    leftAssociative: Boolean = true,
    operation: (Double, Double) -> Double
): KthOperator = KthOperatorImpl(key, precedence, leftAssociative, operation)

/**
 * Helper function to create a [KthOperator] using the receiving String as key.
 *
 * @param precedence the operator precedence
 * @param leftAssociative whether the operator is left associative
 * @param operation the operator operation
 * @return the created [KthOperator]
 */
fun String.asOperator(
    precedence: Int,
    leftAssociative: Boolean = true,
    operation: (Double, Double) -> Double
): KthOperator = createOperator(this, precedence, leftAssociative, operation)

/**
 * Helper function to create a [KthFunction].
 *
 * @param key the operator key
 * @param argCount the argument count of the function
 * @param function the function
 * @return the created [KthFunction]
 */
fun createFunction(key: String, argCount: Int, function: (DoubleArray) -> Double): KthFunction =
    KthFunctionImpl(key, argCount, function)

/**
 * Helper function to create a [KthFunction] using the receiving String as key.
 *
 * @param argCount the argument count of the function
 * @param function the function
 * @return the created [KthFunction]
 */
fun String.asFunction(argCount: Int, function: (DoubleArray) -> Double): KthFunction =
    createFunction(this, argCount, function)

/**
 * Helper function to create a [KthFunction] with one argument.
 *
 * @param key the operator key
 * @param function the function
 * @return the created [KthFunction]
 */
fun createFunction(key: String, function: (Double) -> Double): KthFunction = createFunction(key, 1) { function(it[0]) }

/**
 * Helper function to create a [KthFunction] with one argument using the receiving String as key.
 *
 * @param function the function
 * @return the created [KthFunction]
 */
fun String.asFunction(function: (Double) -> Double): KthFunction = createFunction(this, function)

/**
 * Helper function to create a [KthFunction] with two arguments.
 *
 * @param key the operator key
 * @param function the function
 * @return the created [KthFunction]
 */
fun createFunction(key: String, function: (Double, Double) -> Double): KthFunction =
    createFunction(key, 2) { function(it[0], it[1]) }

/**
 * Helper function to create a [KthFunction] with two arguments using the receiving String as key.
 *
 * @param function the function
 * @return the created [KthFunction]
 */
fun String.asFunction(function: (Double, Double) -> Double): KthFunction = createFunction(this, function)
