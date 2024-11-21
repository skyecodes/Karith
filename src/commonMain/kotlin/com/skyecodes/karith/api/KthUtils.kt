package com.skyecodes.karith.api

import com.skyecodes.karith.api.builtin.Modules
import com.skyecodes.karith.api.builtin.Operators
import com.skyecodes.karith.impl.*
import kotlin.jvm.JvmName

/**
 * A global context that includes [Modules.BASE], [Modules.MATH] and [Operators.POWER].
 *
 * Expression and result caching is disabled for this context. Result caching can be manually turned on for any
 * expression parsed by this context using [KthExpression.enableCache].
 *
 * **This context should only be used for testing or demonstration purposes; it is recommended to build your own context using one of the helper functions [context], [baseContext],
 * [mathContext] or [defaultContext].**
 */
val Kth: KthContext by lazy { defaultContext { disableCache() } }

/**
 * Parses an arithmetic expression from the given string using the global context [Kth].
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @param expression the arithmetic expression to parse
 * @return the parsed [KthExpression]
 * @throws KthIllegalTokenException if an illegal token is found in the expression
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @see KthContext.expression
 */
fun expression(expression: String) = Kth.expression(expression)

/**
 * Parses an arithmetic expression from the given string using the global context [Kth].
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @return the parsed [KthExpression]
 * @throws KthIllegalTokenException if an illegal token is found in the expression
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @see KthContext.expression
 */
@JvmName("expressionExt")
fun String.expression() = Kth.expression(this)

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
 * @see KthContext.expressionWith
 */
fun expressionWith(expression: String, vararg declaredVars: String) = Kth.expressionWith(expression, *declaredVars)

/**
 * Parses an arithmetic expression from the given string and declared variables using the global context [Kth].
 *
 * Any unknown alphanumeric token that is not a number, an element or a declared variable is considered illegal.
 *
 * @param declaredVars the declared vars that can be used in the expression
 * @return the parsed [KthExpression]
 * @throws KthIllegalTokenException if an illegal token is found in the expression
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @see KthContext.expressionWith
 */
@JvmName("expressionWithExt")
fun String.expressionWith(vararg declaredVars: String) = Kth.expressionWith(this, *declaredVars)

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
 * @see KthContext.result
 */
fun result(expression: String) = Kth.result(expression)

/**
 * Parses an arithmetic expression from the given string using the global context [Kth], then return its result.
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @return the result of the expression
 * @throws KthIllegalTokenException if an illegal token is found in the expression during parsing
 * @throws KthMismatchedParenthesesException if some parentheses are mismatched
 * @throws KthUndefinedVariableException if an expression variable doesn't have an associated value
 * @throws KthInsufficientOperandsException if an operator or function doesn't have enough operands to get applied
 * @throws KthUnknownTokenException if an unknown token is found in the expression during computing
 * @see KthContext.result
 */
@JvmName("resultExt")
fun String.result() = Kth.result(this)

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
 * @see KthContext.intResult
 */
fun intResult(expression: String) = Kth.intResult(expression)

/**
 * Parses an arithmetic expression from the given string using the global context [Kth],
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
 * @see KthContext.intResult
 */
@JvmName("intResultExt")
fun String.intResult() = Kth.intResult(this)

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
 * @see KthContext.resultWith
 */
fun resultWith(expression: String, vararg inputVars: Pair<String, Number>) = Kth.resultWith(expression, *inputVars)

/**
 * Parses an arithmetic expression from the given string using the global context [Kth], then return its result.
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
 * @see KthContext.resultWith
 */
@JvmName("resultWithExt")
fun String.resultWith(vararg inputVars: Pair<String, Number>) = Kth.resultWith(this, *inputVars)

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
 * @see KthContext.intResultWith
 */
fun intResultWith(expression: String, vararg inputVars: Pair<String, Number>) =
    Kth.intResultWith(expression, *inputVars)

/**
 * Parses an arithmetic expression from the given string using the global context [Kth],
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
 * @see KthContext.intResultWith
 */
@JvmName("intResultWithExt")
fun String.intResultWith(vararg inputVars: Pair<String, Number>) = Kth.intResultWith(this, *inputVars)

/**
 * Helper function to build a [KthContext].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun context(block: KthContextBuilder.() -> Unit = {}) = KthContextBuilderImpl().apply(block).build()

/**
 * Helper function to build a [KthContext] that includes [Modules.BASE].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun baseContext(block: KthContextBuilder.() -> Unit = {}) = context { include(Modules.BASE); block() }

/**
 * Helper function to build a [KthContext] that includes [Modules.BASE] and [Modules.MATH].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun mathContext(block: KthContextBuilder.() -> Unit = {}) = baseContext { include(Modules.MATH); block() }

/**
 * Helper function to build a [KthContext] that includes [Modules.BASE], [Modules.MATH] and [Operators.POWER].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun defaultContext(block: KthContextBuilder.() -> Unit = {}) = mathContext { withPowerOperator(); block() }

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
fun module(block: KthModuleBuilder.() -> Unit = {}) = KthModuleBuilderImpl().apply(block).build()

/**
 * Helper function to create a [KthConstant].
 *
 * @param key the constant key
 * @param value the constant value
 * @return the created [KthConstant]
 */
fun constant(key: String, value: Number): KthConstant = KthConstantImpl(key, value)

/**
 * Helper function to create a [KthConstant].
 *
 * @param value the constant value
 * @return the created [KthConstant]
 */
@JvmName("constantExt")
fun String.constant(value: Number): KthConstant = constant(this, value)

/**
 * Helper function to create a [KthOperator].
 *
 * @param key the operator key
 * @param precedence the operator precedence
 * @param leftAssociative whether the operator is left associative
 * @param operation the operator operation
 * @return the created [KthOperator]
 */
fun operator(
    key: String,
    precedence: Int,
    leftAssociative: Boolean = true,
    operation: (Double, Double) -> Double
): KthOperator = KthOperatorImpl(key, precedence, leftAssociative, operation)

/**
 * Helper function to create a [KthFunction].
 *
 * @param key the operator key
 * @param argCount the argument count of the function
 * @param function the function
 * @return the created [KthFunction]
 */
fun function(key: String, argCount: Int, function: (DoubleArray) -> Double): KthFunction =
    KthFunctionImpl(key, argCount, function)

/**
 * Helper function to create a [KthFunction] with one argument.
 *
 * @param key the operator key
 * @param function the function
 * @return the created [KthFunction]
 */
fun function(key: String, function: (Double) -> Double): KthFunction = function(key, 1) { function(it[0]) }

/**
 * Helper function to create a [KthFunction] with two arguments.
 *
 * @param key the operator key
 * @param function the function
 * @return the created [KthFunction]
 */
fun function(key: String, function: (Double, Double) -> Double): KthFunction =
    function(key, 2) { function(it[0], it[1]) }
