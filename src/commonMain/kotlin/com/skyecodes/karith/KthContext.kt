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

import com.skyecodes.karith.KthContext.Companion.copy
import com.skyecodes.karith.KthContext.Companion.parseExpression
import com.skyecodes.karith.KthContext.Companion.parseExpressionWith
import com.skyecodes.karith.builtin.Modules
import com.skyecodes.karith.builtin.Operators
import com.skyecodes.karith.impl.KthContextImpl
import kotlin.jvm.JvmName

/**
 * Contexts are the main entrypoint of the Karith API.
 * Their primary function is to parse strings into expressions, which can then be calculated to obtain a result.
 *
 * A context defines a certain set of operators, functions and constants that can be used in expressions.
 *
 * A context can be built using the [KthContext] method, or by [copy]ing an existing context like [KthContext].
 *
 * You can parse a string into an expression using the [parseExpression] or [parseExpressionWith] method.
 * Then you can calculate the expression's result using the [KthExpression.calculateResult] method.
 *
 * A default context [KthContext] is available.
 * Functions from this class can be used directly without having to create a context: the default context will be used.
 */
interface KthContext {
    /**
     * Parses an arithmetic expression from the given string.
     *
     * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
     *
     * @param expr the arithmetic expression to parse
     * @return the parsed [KthExpression]
     * @see parseExpressionWith
     */
    fun parseExpression(expr: String): KthParsingResult

    /**
     * Parses an arithmetic expression from the receiving string.
     *
     * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
     *
     * @return the parsed [KthExpression]
     * @see parseExpressionWith
     */
    @JvmName("parseExpressionExt")
    @Suppress("INAPPLICABLE_JVM_NAME")
    fun String.parseExpression(): KthParsingResult = parseExpression(this)

    /**
     * Parses an arithmetic expression from the given string and declared variables.
     *
     * Any unknown alphanumeric token that is not a number, an element or a declared variable is considered illegal.
     *
     * @param expr the arithmetic expression to parse
     * @param declaredVars the declared vars that can be used in the expression
     * @return the parsed [KthExpression]
     * @see parseExpression
     */
    fun parseExpressionWith(expr: String, vararg declaredVars: String): KthParsingResult

    /**
     * Parses an arithmetic expression from the receiving string and given declared variables.
     *
     * Any unknown alphanumeric token that is not a number, an element or a declared variable is considered illegal.
     *
     * @param declaredVars the declared vars that can be used in the expression
     * @return the parsed [KthExpression]
     * @see parseExpression
     */
    @JvmName("parseExpressionWithExt")
    @Suppress("INAPPLICABLE_JVM_NAME")
    fun String.parseExpressionWith(vararg declaredVars: String): KthParsingResult =
        parseExpressionWith(expr = this, *declaredVars)

    /**
     * Parses an arithmetic expression from the given string, then return its result.
     *
     * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
     *
     * @param expr the arithmetic expression to parse
     * @return the result of the expression
     * @see KthContext.parseExpression
     * @see KthExpression.calculateResult
     */
    fun calculateResult(expr: String): KthParsingAndCalculationResult =
        parseExpression(expr).map { it.calculateResult() }

    /**
     * Parses an arithmetic expression from the receiving string, then return its result.
     *
     * Any unknown alphanumeric token that is not a number or an element is considered as a variable.
     *
     * @return the result of the expression
     * @see KthContext.parseExpression
     * @see KthExpression.calculateResult
     */
    @JvmName("calculateResultExt")
    @Suppress("INAPPLICABLE_JVM_NAME")
    fun String.calculateResult(): KthParsingAndCalculationResult = calculateResult(this)

    /**
     * Parses an arithmetic expression from the given string, then return its result.
     *
     * Any unknown alphanumeric token that is not a number, an element or an input variable is considered illegal.
     *
     * @param expr the arithmetic expression to parse
     * @param inputVars the input variables that are used in the expression
     * @return the result of the expression
     * @see KthContext.parseExpressionWith
     * @see KthExpression.calculateResultWith
     */
    fun calculateResultWith(expr: String, vararg inputVars: Pair<String, Number>): KthParsingAndCalculationResult =
        parseExpressionWith(expr, *inputVars.map { it.first }.toTypedArray()).map { it.calculateResultWith(*inputVars) }

    /**
     * Parses an arithmetic expression from the receiving string, then return its result.
     *
     * Any unknown alphanumeric token that is not a number, an element or an input variable is considered illegal.
     *
     * @param inputVars the input variables that are used in the expression
     * @return the result of the expression
     * @see KthContext.parseExpressionWith
     * @see KthExpression.calculateResultWith
     */
    @JvmName("calculateResultWithExt")
    @Suppress("INAPPLICABLE_JVM_NAME")
    fun String.calculateResultWith(vararg inputVars: Pair<String, Number>): KthParsingAndCalculationResult =
        calculateResultWith(this, *inputVars)

    /**
     * Clears the expression cache of the context.
     */
    fun clearCache()

    /**
     * Copies this context to create a new context containing the same elements.
     *
     * @param builder The context builder to add more elements to it.
     */
    fun copy(builder: Builder.() -> Unit): KthContext

    /**
     * Builder interface for [KthContext].
     *
     * A context builder can be used with the [KthContext] method.
     */
    interface Builder : KthBuilder {
        /**
         * Disables the cache.
         *
         * Caching avoids having to parse the same expression several times when given the same input string or tokens and
         * can greatly increase the performance.
         *
         * By default, caching also avoids having to calculate the same expression several times when given the same input
         * variables, though the caching capability can also be modified at the expression level.
         *
         * @return this builder
         */
        fun disableCache()

        /**
         * Builds the context.
         *
         * @return the context
         */
        fun build(): KthContext
    }

    /**
     * A default context that includes [Modules.BASE], [Modules.MATH] and [Operators.POWER].
     *
     * Expression and result caching is enabled for this context. Result caching can be manually turned off for any
     * expression parsed by this context using [KthExpression.disableCache].
     */
    companion object : KthContext by KthContext({
        include(Modules.BASE, Modules.MATH)
        withPowerOperator()
    })
}

fun KthContext(builder: KthContext.Builder.() -> Unit): KthContext = KthContextImpl.BuilderImpl().apply(builder).build()