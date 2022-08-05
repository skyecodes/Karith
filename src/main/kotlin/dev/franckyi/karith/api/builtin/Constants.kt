package dev.franckyi.karith.api.builtin

import dev.franckyi.karith.api.constant

/**
 * Object containing some useful constants.
 *
 * @see dev.franckyi.karith.api.KthConstant
 */
object Constants {
    /**
     * Base of the natural logarithms, approximately 2.71828.
     *
     * Usage: `e`
     *
     * @see kotlin.math.E
     */
    val E by lazy {
        "e" constant kotlin.math.E
    }

    /**
     * Ratio of the circumference of a circle to its diameter, approximately 3.14159.
     *
     * Usage: `pi`
     *
     * @see kotlin.math.PI
     */
    val PI by lazy {
        "pi" constant kotlin.math.PI
    }
}