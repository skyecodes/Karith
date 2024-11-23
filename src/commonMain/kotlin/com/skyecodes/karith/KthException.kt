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
 * Base class for all exceptions thrown by Karith.
 */
sealed class KthException(message: String) : Exception(message)

/**
 * Represents exceptions that can be thrown while parsing the expression, before computing the result.
 */
sealed class KthParseException(message: String) : KthException(message)

/**
 * Thrown during tokenization if the input string contains an illegal token.
 *
 * @property token The illegal token.
 * @property position The position of the illegal token.
 */
class KthIllegalTokenException(val token: String, val position: Int) :
    KthParseException("Illegal token \"$token\" at position $position")

/**
 * Thrown during token sorting if the input string contains mismatched parentheses.
 *
 * @property index The index of the parentheses token.
 */
class KthMismatchedParenthesesException(val index: Int) :
    KthParseException("Mismatched parentheses at token index $index")

/**
 * Represents exceptions that can be thrown while computing the result, after parsing the expression.
 */
sealed class KthComputeException(message: String) : KthException(message)

/**
 * Thrown during computing if an input variable is not defined.
 *
 * @property variable The name of variable that is not defined.
 */
class KthUndefinedVariableException(val variable: String) :
    KthComputeException("Variable \"$variable\" is not defined")

/**
 * Thrown during computing if a function / operator doesn't have enough arguments / operands.
 *
 * @property type "function" or "operator"
 * @property name The name of the function or operator.
 */
class KthInsufficientOperandsException(val type: String, val name: String) :
    KthComputeException("Stack contains too few operands to apply $type \"$name\"")

/**
 * Thrown during computing if it encounters an unknown token.
 *
 * @property token The unknown token.
 */
class KthUnknownTokenException(val token: KthToken) :
    KthComputeException("Unknown token \"$token\"")
