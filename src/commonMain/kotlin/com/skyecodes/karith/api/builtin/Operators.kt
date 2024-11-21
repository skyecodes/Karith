package com.skyecodes.karith.api.builtin

import com.skyecodes.karith.api.operator
import kotlin.math.pow

/**
 * Object containing some useful operators.
 *
 * @see com.skyecodes.karith.api.KthOperator
 */
object Operators {
    /**
     * Raises this value to the other value.
     *
     * Usage: `a ^ b`
     *
     * **Note:** this operator is not compatible with [Operators.XOR]. They can't be included in the same context.
     *
     * @see kotlin.math.pow
     */
    val POWER by lazy {
        operator("^", 15, false) { a, b ->
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
        operator("*", 12) { a, b ->
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
        operator("/", 12) { a, b ->
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
        operator("%", 12) { a, b ->
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
        operator("+", 11) { a, b ->
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
        operator("-", 11) { a, b ->
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
        operator("<<", 10) { a, b ->
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
        operator(">>", 10) { a, b ->
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
        operator(">>>", 10) { a, b ->
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
        operator("&", 7) { a, b ->
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
        operator("^", 6) { a, b ->
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
        operator("|", 5) { a, b ->
            (a.toInt() or b.toInt()).toDouble()
        }
    }
}