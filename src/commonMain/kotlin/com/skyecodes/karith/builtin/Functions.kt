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

import com.skyecodes.karith.createFunction
import kotlin.math.*

/**
 * Object containing some useful functions.
 *
 * @see com.skyecodes.karith.KthFunction
 */
object Functions {
    /**
     * Returns the absolute value of the given value `x`.
     *
     * Usage: `abs(x)`
     *
     * @see abs
     */
    val ABS by lazy {
        createFunction("abs", ::abs)
    }

    /**
     * Calculates the arc cosine of the value `x`.
     * The returned value is an angle in the range from 0.0 to `PI` radians.
     *
     * Usage: `acos(x)`
     *
     * @see acos
     */
    val ACOS by lazy {
        createFunction("acos", ::acos)
    }

    /**
     * Calculates the inverse hyperbolic cosine of the value `x`.
     * The returned value is positive `y` such that `cosh(y) == x`.
     *
     * Usage: `acosh(x)`
     *
     * @see acosh
     */
    val ACOSH by lazy {
        createFunction("acosh", ::acosh)
    }

    /**
     * Calculates the arc sine of the value `x`.
     * The returned value is an angle in the range from `-PI/2` to `PI/2` radians.
     *
     * Usage: `asin(x)`
     *
     * @see asin
     */
    val ASIN by lazy {
        createFunction("asin", ::asin)
    }

    /**
     * Calculates the inverse hyperbolic sine of the value `x`.
     * The returned value is `y` such that `sinh(y) == x`.
     *
     * Usage: `asinh(x)`
     *
     * @see asinh
     */
    val ASINH by lazy {
        createFunction("asinh", ::asinh)
    }

    /**
     * Calculates the arc tangent of the value `x`.
     * The returned value is an angle in the range from `-PI/2` to `PI/2` radians.
     *
     * Usage: `atan(x)`
     *
     * @see atan
     */
    val ATAN by lazy {
        createFunction("atan", ::atan)
    }

    /**
     * Returns the angle theta of the polar coordinates `(r, theta)` that correspond to
     * the rectangular coordinates `(x, y)` by calculating the arc tangent of the value `y / x`;
     * the returned value is an angle in the range from `-PI` to `PI` radians.
     *
     * Usage: `atan2(y, x)`
     *
     * @see atan2
     */
    val ATAN2 by lazy {
        createFunction("atan2", ::atan2)
    }

    /**
     * Calculates the inverse hyperbolic tangent of the value `x`.
     * The returned value is `y` such that `tanh(y) == x`.
     *
     * Usage: `atanh(x)`
     *
     * @see atanh
     */
    val ATANH by lazy {
        createFunction("atanh", ::atanh)
    }

    /**
     * Rounds the given value `x` to an integer towards positive infinity.
     * Returns the smallest double value that is greater than or equal to the given value `x` and is a mathematical integer.
     *
     * Usage: `ceil(x)`
     *
     * @see ceil
     */
    val CEIL by lazy {
        createFunction("ceil", ::ceil)
    }

    /**
     * Calculates the cosine of the angle `x` given in radians.
     *
     * Usage: `cos(x)`
     *
     * @see cos
     */
    val COS by lazy {
        createFunction("cos", ::cos)
    }

    /**
     * Calculates the hyperbolic cosine of the value `x`.
     *
     * Usage: `cosh(x)`
     *
     * @see cosh
     */
    val COSH by lazy {
        createFunction("cosh", ::cosh)
    }

    /**
     * Calculates Euler's number `e` raised to the power of the value `x`.
     *
     * Usage: `exp(x)`
     *
     * @see exp
     */
    val EXP by lazy {
        createFunction("exp", ::exp)
    }

    /**
     * Calculates `exp(x) - 1`.
     * This function can be implemented to produce more precise result for `x` near zero.
     *
     * Usage: `expm1(x)`
     *
     * @see expm1
     */
    val EXPM1 by lazy {
        createFunction("expm1", ::expm1)
    }

    /**
     * Rounds the given value `x` to an integer towards negative infinity.
     * Returns the largest double value that is smaller than or equal to the given value `x` and is a mathematical integer.
     *
     * Usage: `floor(x)`
     *
     * @see floor
     */
    val FLOOR by lazy {
        createFunction("floor", ::floor)
    }

    /**
     * Calculates `sqrt(x^2 + y^2)` without intermediate overflow or underflow.
     *
     * Usage: `hypot(x, y)`
     *
     * @see hypot
     */
    val HYPOT by lazy {
        createFunction("hypot", ::hypot)
    }

    /**
     * Calculates the natural logarithm (base E) of the value `x`.
     *
     * Usage: `ln(x)`
     *
     * @see ln
     */
    val LN by lazy {
        createFunction("ln", ::ln)
    }

    /**
     * Calculates ln(x + 1).
     * This function can be implemented to produce more precise result for `x` near zero.
     *
     * Usage: `ln1p(x)`
     *
     * @see ln1p
     */
    val LN1P by lazy {
        createFunction("ln1p", ::ln1p)
    }

    /**
     * Calculates the logarithm of the value `x` to the given `base`.
     *
     * Usage: `log(x, base)`
     *
     * @see log
     */
    val LOG by lazy {
        createFunction("log", ::log)
    }

    /**
     * Calculates the common logarithm (base 10) of the value `x`.
     *
     * Usage: `log10(x)`
     *
     * @see log10
     */
    val LOG10 by lazy {
        createFunction("log10", ::log10)
    }

    /**
     * Calculates the binary logarithm (base 2) of the value `x`.
     *
     * Usage: `log2(x)`
     *
     * @see log2
     */
    val LOG2 by lazy {
        createFunction("log2", ::log2)
    }

    /**
     * Returns the greater of two values.
     *
     * Usage: `max(x, y)`
     *
     * @see max
     */
    val MAX by lazy {
        createFunction("max", ::max)
    }

    /**
     * Returns the smaller of two values.
     *
     * Usage: `min(x, y)`
     *
     * @see min
     */
    val MIN by lazy {
        createFunction("min", ::min)
    }

    /**
     * Returns the Double value nearest to the value `x` in direction of negative infinity.
     *
     * Usage: `nextDown(x)`
     *
     * @see nextDown
     */
    val NEXT_DOWN by lazy {
        createFunction("nextDown", Double::nextDown)
    }

    /**
     * Returns the Double value nearest to the value `x` in direction from this value towards the value `to`.
     *
     * Usage: `nextTowards(x, to)`
     *
     * @see nextTowards
     */
    val NEXT_TOWARDS by lazy {
        createFunction("nextTowards", Double::nextTowards)
    }

    /**
     * Returns the Double value nearest to the value `x` in direction of positive infinity.
     *
     * Usage: `nextUp(x)`
     *
     * @see nextUp
     */
    val NEXT_UP by lazy {
        createFunction("nextUp", Double::nextUp)
    }

    /**
     * Inverts the bits of the value `x`.
     *
     * Usage: `not(x)`
     *
     * @see Long.inv
     */
    val NOT by lazy {
        createFunction("not") { it.toLong().inv().toDouble() }
    }

    /**
     * Raises the value `x` to the power `n`.
     *
     * Usage: `pow(x, n)`
     *
     * @see pow
     */
    val POW by lazy {
        createFunction("pow", Double::pow)
    }

    /**
     * Rounds the given value `x` towards the closest integer with ties rounded towards even integer.
     *
     * Usage: `round(x)`
     *
     * @see round
     */
    val ROUND by lazy {
        createFunction("round", ::round)
    }

    /**
     * Returns the sign of the given value `x`:
     * * `-1.0` if the value is negative,
     * * `zero` if the value is zero,
     * * `1.0` if the value is positive
     *
     * Usage: `sign(x)`
     *
     * @see sign
     */
    val SIGN by lazy {
        createFunction("sign", ::sign)
    }

    /**
     * Calculates the sine of the angle `x` given in radians.
     *
     * Usage: `sin(x)`
     *
     * @see sin
     */
    val SIN by lazy {
        createFunction("sin", ::sin)
    }

    /**
     * Calculates the hyperbolic sine of the value `x`.
     *
     * Usage: `sinh(x)`
     *
     * @see sinh
     */
    val SINH by lazy {
        createFunction("sinh", ::sinh)
    }

    /**
     * Calculates the positive square root of the value `x`.
     *
     * Usage: `sqrt(x)`
     *
     * @see sqrt
     */
    val SQRT by lazy {
        createFunction("sqrt", ::sqrt)
    }

    /**
     * Calculates the tangent of the angle `x` given in radians.
     *
     * Usage: `tan(x)`
     *
     * @see tan
     */
    val TAN by lazy {
        createFunction("tan", ::tan)
    }

    /**
     * Calculates the hyperbolic tangent of the value `x`.
     *
     * Usage: `tanh(x)`
     *
     * @see tanh
     */
    val TANH by lazy {
        createFunction("tanh", ::tanh)
    }

    /**
     * Rounds the given value `x` to an integer towards zero.
     * Returns the value `x` having its fractional part truncated.
     *
     * Usage: `truncate(x)`
     *
     * @see truncate
     */
    val TRUNCATE by lazy {
        createFunction("truncate", ::truncate)
    }

    /**
     * Returns the ulp (unit in the last place) of the value `x`.
     * An ulp is a positive distance between this value and the next nearest Double value larger in magnitude.
     *
     * Usage: `ulp(x)`
     *
     * @see ulp
     */
    val ULP by lazy {
        createFunction("ulp", Double::ulp)
    }

    /**
     * Returns the value `x` with the sign bit same as of the sign value.
     *
     * Usage: `withSign(x, sign)`
     *
     * @see withSign
     */
    val WITH_SIGN by lazy {
        createFunction("withSign", Double::withSign)
    }
}