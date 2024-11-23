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

import com.skyecodes.karith.builtin.Modules
import com.skyecodes.karith.builtin.Operators
import com.skyecodes.karith.impl.*
import kotlin.jvm.JvmName

/**
 * A default context that includes [Modules.BASE], [Modules.MATH] and [Operators.POWER].
 *
 * Expression and result caching is enabled for this context. Result caching can be manually turned off for any
 * expression parsed by this context using [KthExpression.disableCache].
 */
val defaultCtx: KthContext by lazy { buildDefaultContext() }

/**
 * Parses an arithmetic expression from the given string using the default context.
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @param parseExpression the arithmetic expression to parse
 * @return the parsed [KthExpression]
 * @see KthContext.parseExpression
 */
fun parseExpression(expression: String): KthParsingResult = with(defaultCtx) { this.parseExpression(expression) }

/**
 * Parses an arithmetic expression from the receiving string using the default context.
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @return the parsed [KthExpression]
 * @see KthContext.parseExpression
 */
@JvmName("parseExpressionExt")
fun String.parseExpression(): KthParsingResult = with(defaultCtx) { this@parseExpression.parseExpression() }

/**
 * Parses an arithmetic expression from the given string and declared variables using the default context.
 *
 * Any unknown alphanumeric token that is not a number, an element or a declared variable is considered illegal.
 *
 * @param expression the arithmetic expression to parse
 * @param declaredVars the declared vars that can be used in the expression
 * @return the parsed [KthExpression]
 * @see KthContext.parseExpressionWith
 */
fun parseExpressionWith(expression: String, vararg declaredVars: String): KthParsingResult =
    with(defaultCtx) { this.parseExpressionWith(expression, *declaredVars) }

/**
 * Parses an arithmetic expression from the receiving string and declared variables using the default context.
 *
 * Any unknown alphanumeric token that is not a number, an element or a declared variable is considered illegal.
 *
 * @param declaredVars the declared vars that can be used in the expression
 * @return the parsed [KthExpression]
 * @see KthContext.parseExpressionWith
 */
@JvmName("parseExpressionWithExt")
fun String.parseExpressionWith(vararg declaredVars: String): KthParsingResult =
    with(defaultCtx) { this@parseExpressionWith.parseExpressionWith(*declaredVars) }

/**
 * Parses an arithmetic expression from the given string using the default context, then return its result.
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @param expression the arithmetic expression to parse
 * @return the result of the expression
 * @see KthContext.calculateResult
 */
fun calculateResult(expression: String): KthParsingAndCalculationResult =
    with(defaultCtx) { this.calculateResult(expression) }

/**
 * Parses an arithmetic expression from the receiving string using the default context, then return its result.
 *
 * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
 *
 * @return the result of the expression
 * @see KthContext.calculateResult
 */
@JvmName("calculateResultExt")
fun String.calculateResult(): KthParsingAndCalculationResult =
    with(defaultCtx) { this@calculateResult.calculateResult() }

/**
 * Parses an arithmetic expression from the given string using the default context, then return its result.
 *
 * Any unknown alphanumeric token that is not a number, an element or an input variable is considered illegal.
 *
 * @param expression the arithmetic expression to parse
 * @param inputVars the input variables that are used in the expression
 * @return the result of the expression
 * @see KthContext.calculateResultWith
 */
fun calculateResultWith(expression: String, vararg inputVars: Pair<String, Number>): KthParsingAndCalculationResult =
    with(defaultCtx) { this.calculateResultWith(expression, *inputVars) }

/**
 * Parses an arithmetic expression from the receiving string using the default context, then return its result.
 *
 * Any unknown alphanumeric token that is not a number, an element or an input variable is considered illegal.
 *
 * @param inputVars the input variables that are used in the expression
 * @return the result of the expression
 * @see KthContext.calculateResultWith
 */
@JvmName("calculateResultWithExt")
fun String.calculateResultWith(vararg inputVars: Pair<String, Number>): KthParsingAndCalculationResult =
    with(defaultCtx) { this@calculateResultWith.calculateResultWith(*inputVars) }

/**
 * Helper function to build a [KthContext].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun buildContext(block: KthContext.Builder.() -> Unit = {}) = KthContextImpl.BuilderImpl().apply(block).build()

/**
 * Helper function to build a [KthContext] that includes [Modules.BASE], [Modules.MATH] and [Operators.POWER].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun buildDefaultContext(block: KthContext.Builder.() -> Unit = {}) = buildContext {
    include(Modules.BASE, Modules.MATH)
    withPowerOperator()
    block()
}

/**
 * Helper function to add the [Operators.POWER] operator to a [KthContext] builder.
 *
 * @return the current [KthContext.Builder]
 */
fun KthContext.Builder.withPowerOperator() = withOperator(Operators.POWER)

/**
 * Helper function to build a [KthContext].
 *
 * @param block the builder block
 * @return the created [KthContext]
 */
fun buildModule(block: KthModule.Builder.() -> Unit = {}) = KthModuleImpl.BuilderImpl().apply(block).build()

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
