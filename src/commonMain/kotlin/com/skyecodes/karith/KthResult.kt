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

import com.skyecodes.karith.KthResult.Error
import com.skyecodes.karith.KthResult.Success

/**
 * The base result class returned by all API methods used in parsing an expression or computing a result.
 * Can be either [Success] or [Error].
 */
sealed interface KthResult<T, E : KthException> {
    /**
     * Result returned in case of [Success].
     *
     * @property value The result value.
     */
    data class Success<T, E : KthException>(val value: T) : KthResult<T, E>


    /**
     * Result returned in case of [Error].
     *
     * @property error The error.
     */
    data class Error<T, E : KthException>(val error: E) : KthResult<T, E>

    /**
     * Executes a block of code when the result is [Success], otherwise does nothing.
     *
     * @param block The block to execute.
     */
    fun onSuccess(block: (T) -> Unit): KthResult<T, E> = apply { if (this is Success) block(value) }

    /**
     * Executes a block of code when the result is [Error], otherwise does nothing.
     *
     * @param block The block to execute.
     */
    fun onError(block: (E) -> Unit): KthResult<T, E> = apply { if (this is Error) block(error) }

    /**
     * Returns the result value in case of [Success], otherwise throws an exception related to the error.
     *
     * @return The result value in case of [Success].
     * @throws KthException If the result is [Error].
     */
    fun orThrow(): T = when (this) {
        is Success -> value
        is Error -> throw error
    }

    /**
     * Returns the result value in case of [Success], otherwise returns `null`.
     *
     * @return The result value in case of [Success], otherwise `null`.
     */
    fun orNull(): T? = when (this) {
        is Success -> value
        is Error -> null
    }

    /**
     * Returns the result value in case of [Success], otherwise returns the other value.
     *
     * @param other The other value.
     * @return The result value in case of [Success], otherwise [other].
     */
    fun orElse(other: T): T = when (this) {
        is Success -> value
        is Error -> other
    }

    /**
     * Returns the result value in case of [Success], otherwise returns the result of the block of code.
     *
     * @param block The block to execute.
     * @return The result value in case of [Success], otherwise the result of [block].
     */
    fun orElseGet(block: (E) -> T): T = when (this) {
        is Success -> value
        is Error -> block(error)
    }
}

internal inline fun <T, E : KthException, T2, E2 : KthException> KthResult<T, E>.map(block: (T) -> KthResult<T2, E2>): KthResult<T2, out KthException> =
    when (this) {
        is Success -> block(value)
        is Error -> Error(error)
    }

typealias KthExpressionResult = KthResult<KthExpression, KthParseException>

typealias KthValueResult = KthResult<Double, KthComputeException>

typealias KthExpressionValueResult = KthResult<Double, out KthException>