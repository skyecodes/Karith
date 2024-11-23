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

package com.skyecodes.karith.builtin

import com.skyecodes.karith.createOperator
import kotlin.math.pow

/**
 * Object containing some useful operators.
 *
 * @see com.skyecodes.karith.KthOperator
 */
object Operators {
    /**
     * Raises this value to the other value.
     *
     * Usage: `a ^ b`
     *
     * **Note:** this operator is not compatible with [Operators.XOR]. They can't be included in the same context.
     *
     * @see pow
     */
    val POWER by lazy {
        createOperator("^", 15, false) { a, b ->
            a.pow(b)
        }
    }

    /**
     * Multiplies this value by the other value.
     *
     * Usage: `a * b`
     *
     * @see Double.times
     */
    val TIMES by lazy {
        createOperator("*", 12) { a, b ->
            a * b
        }
    }

    /**
     * Divides this value by the other value.
     *
     * Usage: `a / b`
     *
     * @see Double.div
     */
    val DIVIDE by lazy {
        createOperator("/", 12) { a, b ->
            a / b
        }
    }

    /**
     * Calculates the remainder of truncating division of this value by the other value.
     *
     * Usage: `a % b`
     *
     * @see Double.rem
     */
    val REMAINDER by lazy {
        createOperator("%", 12) { a, b ->
            a % b
        }
    }

    /**
     * Adds the other value to this value.
     *
     * Usage: `a + b`
     *
     * @see Double.plus
     */
    val PLUS by lazy {
        createOperator("+", 11) { a, b ->
            a + b
        }
    }

    /**
     * Subtracts the other value from this value.
     *
     * Usage: `a - b`
     *
     * @see Double.minus
     */
    val MINUS by lazy {
        createOperator("-", 11) { a, b ->
            a - b
        }
    }

    /**
     * Shifts this value left by the bitCount number of bits.
     *
     * Usage: `a << b`
     *
     * @see Int.shl
     */
    val SHIFT_LEFT by lazy {
        createOperator("<<", 10) { a, b ->
            (a.toInt() shl b.toInt()).toDouble()
        }
    }

    /**
     * Shifts this value right by the bitCount number of bits, filling the leftmost bits with copies of the sign bit.
     *
     * Usage: `a >> b`
     *
     * @see Int.shr
     */
    val SHIFT_RIGHT by lazy {
        createOperator(">>", 10) { a, b ->
            (a.toInt() shr b.toInt()).toDouble()
        }
    }

    /**
     * Shifts this value right by the bitCount number of bits, filling the leftmost bits with zeros.
     *
     * Usage: `a >>> b`
     *
     * @see Int.ushr
     */
    val UNSIGNED_SHIFT_RIGHT by lazy {
        createOperator(">>>", 10) { a, b ->
            (a.toInt() ushr b.toInt()).toDouble()
        }
    }

    /**
     * Performs a bitwise AND operation between the two values.
     *
     * Usage: `a & b`
     *
     * @see Int.and
     */
    val AND by lazy {
        createOperator("&", 7) { a, b ->
            (a.toInt() and b.toInt()).toDouble()
        }
    }

    /**
     * Performs a bitwise XOR operation between the two values.
     *
     * Usage: `a ^ b`
     *
     * **Note:** this operator is not compatible with [Operators.POWER]. They can't be included in the same context.
     *
     * @see Int.or
     */
    val XOR by lazy {
        createOperator("^", 6) { a, b ->
            (a.toInt() xor b.toInt()).toDouble()
        }
    }

    /**
     * Performs a bitwise OR operation between the two values.
     *
     * Usage: `a | b`
     *
     * @see Int.or
     */
    val OR by lazy {
        createOperator("|", 5) { a, b ->
            (a.toInt() or b.toInt()).toDouble()
        }
    }
}