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

import com.skyecodes.karith.createConstant

/**
 * Object containing some useful constants.
 *
 * @see com.skyecodes.karith.KthConstant
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