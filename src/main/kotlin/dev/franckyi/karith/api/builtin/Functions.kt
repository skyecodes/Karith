package dev.franckyi.karith.api.builtin

import dev.franckyi.karith.api.function1
import dev.franckyi.karith.api.function2
import kotlin.math.*

/**
 * Object containing some useful functions.
 *
 * @see dev.franckyi.karith.api.KthFunction
 */
object Functions {
    /**
     * Returns the absolute value of the given value `x`.
     *
     * Usage: `abs(x)`
     *
     * @see kotlin.math.abs
     */
    val ABS by lazy {
        "abs" function1 ::abs
    }

    /**
     * Computes the arc cosine of the value `x`.
     * The returned value is an angle in the range from 0.0 to `PI` radians.
     *
     * Usage: `acos(x)`
     *
     * @see kotlin.math.acos
     */
    val ACOS by lazy {
        "acos" function1 ::acos
    }

    /**
     * Computes the inverse hyperbolic cosine of the value `x`.
     * The returned value is positive `y` such that `cosh(y) == x`.
     *
     * Usage: `acosh(x)`
     *
     * @see kotlin.math.acosh
     */
    val ACOSH by lazy {
        "acosh" function1 ::acosh
    }

    /**
     * Computes the arc sine of the value `x`.
     * The returned value is an angle in the range from `-PI/2` to `PI/2` radians.
     *
     * Usage: `asin(x)`
     *
     * @see kotlin.math.asin
     */
    val ASIN by lazy {
        "asin" function1 ::asin
    }

    /**
     * Computes the inverse hyperbolic sine of the value `x`.
     * The returned value is `y` such that `sinh(y) == x`.
     *
     * Usage: `asinh(x)`
     *
     * @see kotlin.math.asinh
     */
    val ASINH by lazy {
        "asinh" function1 ::asinh
    }

    /**
     * Computes the arc tangent of the value `x`.
     * The returned value is an angle in the range from `-PI/2` to `PI/2` radians.
     *
     * Usage: `atan(x)`
     *
     * @see kotlin.math.atan
     */
    val ATAN by lazy {
        "atan" function1 ::atan
    }

    /**
     * Returns the angle theta of the polar coordinates `(r, theta)` that correspond to
     * the rectangular coordinates `(x, y)` by computing the arc tangent of the value `y / x`;
     * the returned value is an angle in the range from `-PI` to `PI` radians.
     *
     * Usage: `atan2(y, x)`
     *
     * @see kotlin.math.atan2
     */
    val ATAN2 by lazy {
        "atan2" function2 ::atan2
    }

    /**
     * Computes the inverse hyperbolic tangent of the value `x`.
     * The returned value is `y` such that `tanh(y) == x`.
     *
     * Usage: `atanh(x)`
     *
     * @see kotlin.math.atanh
     */
    val ATANH by lazy {
        "atanh" function1 ::atanh
    }

    /**
     * Rounds the given value `x` to an integer towards positive infinity.
     * Returns the smallest double value that is greater than or equal to the given value `x` and is a mathematical integer.
     *
     * Usage: `ceil(x)`
     *
     * @see kotlin.math.ceil
     */
    val CEIL by lazy {
        "ceil" function1 ::ceil
    }

    /**
     * Computes the cosine of the angle `x` given in radians.
     *
     * Usage: `cos(x)`
     *
     * @see kotlin.math.cos
     */
    val COS by lazy {
        "cos" function1 ::cos
    }

    /**
     * Computes the hyperbolic cosine of the value `x`.
     *
     * Usage: `cosh(x)`
     *
     * @see kotlin.math.cosh
     */
    val COSH by lazy {
        "cosh" function1 ::cosh
    }

    /**
     * Computes Euler's number `e` raised to the power of the value `x`.
     *
     * Usage: `exp(x)`
     *
     * @see kotlin.math.exp
     */
    val EXP by lazy {
        "exp" function1 ::exp
    }

    /**
     * Computes `exp(x) - 1`.
     * This function can be implemented to produce more precise result for `x` near zero.
     *
     * Usage: `expm1(x)`
     *
     * @see kotlin.math.expm1
     */
    val EXPM1 by lazy {
        "expm1" function1 ::expm1
    }

    /**
     * Rounds the given value `x` to an integer towards negative infinity.
     * Returns the largest double value that is smaller than or equal to the given value `x` and is a mathematical integer.
     *
     * Usage: `floor(x)`
     *
     * @see kotlin.math.floor
     */
    val FLOOR by lazy {
        "floor" function1 ::floor
    }

    /**
     * Computes `sqrt(x^2 + y^2)` without intermediate overflow or underflow.
     *
     * Usage: `hypot(x, y)`
     *
     * @see kotlin.math.hypot
     */
    val HYPOT by lazy {
        "hypot" function2 ::hypot
    }

    /**
     * Computes the natural logarithm (base E) of the value `x`.
     *
     * Usage: `ln(x)`
     *
     * @see kotlin.math.ln
     */
    val LN by lazy {
        "ln" function1 ::ln
    }

    /**
     * Computes ln(x + 1).
     * This function can be implemented to produce more precise result for `x` near zero.
     *
     * Usage: `ln1p(x)`
     *
     * @see kotlin.math.ln1p
     */
    val LN1P by lazy {
        "ln1p" function1 ::ln1p
    }

    /**
     * Computes the logarithm of the value `x` to the given `base`.
     *
     * Usage: `log(x, base)`
     *
     * @see kotlin.math.log
     */
    val LOG by lazy {
        "log" function2 ::log
    }

    /**
     * Computes the common logarithm (base 10) of the value `x`.
     *
     * Usage: `log10(x)`
     *
     * @see kotlin.math.log10
     */
    val LOG10 by lazy {
        "log10" function1 ::log10
    }

    /**
     * Computes the binary logarithm (base 2) of the value `x`.
     *
     * Usage: `log2(x)`
     *
     * @see kotlin.math.log2
     */
    val LOG2 by lazy {
        "log2" function1 ::log2
    }

    /**
     * Returns the greater of two values.
     *
     * Usage: `max(x, y)`
     *
     * @see kotlin.math.max
     */
    val MAX by lazy {
        "max" function2 ::max
    }

    /**
     * Returns the smaller of two values.
     *
     * Usage: `min(x, y)`
     *
     * @see kotlin.math.min
     */
    val MIN by lazy {
        "min" function2 ::min
    }

    /**
     * Returns the Double value nearest to the value `x` in direction of negative infinity.
     *
     * Usage: `nextDown(x)`
     *
     * @see kotlin.math.nextDown
     */
    val NEXT_DOWN by lazy {
        "nextDown" function1 Double::nextDown
    }

    /**
     * Returns the Double value nearest to the value `x` in direction from this value towards the value `to`.
     *
     * Usage: `nextTowards(x, to)`
     *
     * @see kotlin.math.nextTowards
     */
    val NEXT_TOWARDS by lazy {
        "nextTowards" function2 Double::nextTowards
    }

    /**
     * Returns the Double value nearest to the value `x` in direction of positive infinity.
     *
     * Usage: `nextUp(x)`
     *
     * @see kotlin.math.nextUp
     */
    val NEXT_UP by lazy {
        "nextUp" function1 Double::nextUp
    }

    /**
     * Inverts the bits of the value `x`.
     *
     * Usage: `not(x)`
     *
     * @see Long.inv
     */
    val NOT by lazy {
        "not" function1 { it.toLong().inv().toDouble() }
    }

    /**
     * Raises the value `x` to the power `n`.
     *
     * Usage: `pow(x, n)`
     *
     * @see kotlin.math.pow
     */
    val POW by lazy {
        "pow" function2 Double::pow
    }

    /**
     * Rounds the given value `x` towards the closest integer with ties rounded towards even integer.
     *
     * Usage: `round(x)`
     *
     * @see kotlin.math.round
     */
    val ROUND by lazy {
        "round" function1 ::round
    }

    /**
     * Returns the sign of the given value `x`:
     * * `-1.0` if the value is negative,
     * * `zero` if the value is zero,
     * * `1.0` if the value is positive
     *
     * Usage: `sign(x)`
     *
     * @see kotlin.math.sign
     */
    val SIGN by lazy {
        "sign" function1 ::sign
    }

    /**
     * Computes the sine of the angle `x` given in radians.
     *
     * Usage: `sin(x)`
     *
     * @see kotlin.math.sin
     */
    val SIN by lazy {
        "sin" function1 ::sin
    }

    /**
     * Computes the hyperbolic sine of the value `x`.
     *
     * Usage: `sinh(x)`
     *
     * @see kotlin.math.sinh
     */
    val SINH by lazy {
        "sinh" function1 ::sinh
    }

    /**
     * Computes the positive square root of the value `x`.
     *
     * Usage: `sqrt(x)`
     *
     * @see kotlin.math.sqrt
     */
    val SQRT by lazy {
        "sqrt" function1 ::sqrt
    }

    /**
     * Computes the tangent of the angle `x` given in radians.
     *
     * Usage: `tan(x)`
     *
     * @see kotlin.math.tan
     */
    val TAN by lazy {
        "tan" function1 ::tan
    }

    /**
     * Computes the hyperbolic tangent of the value `x`.
     *
     * Usage: `tanh(x)`
     *
     * @see kotlin.math.tanh
     */
    val TANH by lazy {
        "tanh" function1 ::tanh
    }

    /**
     * Rounds the given value `x` to an integer towards zero.
     * Returns the value `x` having its fractional part truncated.
     *
     * Usage: `truncate(x)`
     *
     * @see kotlin.math.truncate
     */
    val TRUNCATE by lazy {
        "truncate" function1 ::truncate
    }

    /**
     * Returns the ulp (unit in the last place) of the value `x`.
     * An ulp is a positive distance between this value and the next nearest Double value larger in magnitude.
     *
     * Usage: `ulp(x)`
     *
     * @see kotlin.math.ulp
     */
    val ULP by lazy {
        "ulp" function1 Double::ulp
    }

    /**
     * Returns the value `x` with the sign bit same as of the sign value.
     *
     * Usage: `withSign(x, sign)`
     *
     * @see kotlin.math.withSign
     */
    val WITH_SIGN by lazy {
        "withSign" function2 Double::withSign
    }
}