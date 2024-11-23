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

import com.skyecodes.karith.builtin.Modules

/**
 * A module is a collection of [KthContextualToken]s to be included in a [KthContext].
 *
 * Karith has a few builtin module:
 * * [Modules.BASE]
 * * [Modules.BITWISE]
 * * [Modules.MATH_UTIL]
 * * [Modules.MATH_LOG]
 * * [Modules.MATH_TRIG]
 * * [Modules.MATH]
 */
interface KthModule {
    /**
     * All the elements contained in this module: [KthOperator]s, [KthFunction]s and [KthConstant]s.
     */
    val elementMap: Map<String, KthContextualToken>

    /**
     * The combiner operator defines which operator is used to combine two tokens that don't have an operator between
     * them.
     */
    val combinerOperator: KthOperator?

    /**
     * Builder interface for [KthModule].
     *
     * A module builder can be used with [buildModule].
     */
    interface Builder : KthBuilder<Builder> {
        /**
         * Builds the module.
         *
         * @return the module
         */
        fun build(): KthModule
    }
}
