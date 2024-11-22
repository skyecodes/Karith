package com.skyecodes.karith.api.builtin

import com.skyecodes.karith.api.createConstant

/**
 * Object containing some useful constants.
 *
 * @see com.skyecodes.karith.api.KthConstant
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
        createConstant("e", kotlin.math.E)
    }

    /**
     * Ratio of the circumference of a circle to its diameter, approximately 3.14159.
     *
     * Usage: `pi`
     *
     * @see kotlin.math.PI
     */
    val PI by lazy {
        createConstant("pi", kotlin.math.PI)
    }
}