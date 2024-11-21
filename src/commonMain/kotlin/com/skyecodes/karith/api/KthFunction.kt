package com.skyecodes.karith.api

/**
 * Function element to be included in a [KthModule] or [KthContext] and used in an expression.
 *
 * A function takes a fixed amount of arguments and returns a value.
 *
 * Functions can be built using the helper functions [function], [function] and [function].
 */
interface KthFunction : KthElement {
    /**
     * The number of arguments of the function
     */
    val argCount: Int

    /**
     * Applies the function with the specified arguments and returns its result.
     *
     * @param args the arguments of the function
     * @return the result of the function
     */
    operator fun invoke(vararg args: Double): Double
}