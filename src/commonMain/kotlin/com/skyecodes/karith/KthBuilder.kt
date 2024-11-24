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

/**
 * Common builder interface for [KthContext.Builder] and [KthModule.Builder].
 */
interface KthBuilder {
    /**
     * Includes modules to this context or module.
     *
     * @param modules the modules to include
     * @return this builder
     */
    fun include(vararg modules: KthModule)

    /**
     * Includes modules to this context or module.
     *
     * @param modules the modules to include
     * @return this builder
     */
    fun include(modules: Iterable<KthModule>)

    /**
     * Includes elements to this context or module.
     *
     * @param elements the elements to include
     * @return this builder
     */
    fun with(vararg elements: KthElement)

    /**
     * Includes elements to this context or module.
     *
     * @param elements the elements to include
     * @return this builder
     */
    fun with(elements: Iterable<KthElement>)

    /**
     * Sets the combiner operator of this context or module.
     *
     * @param operator the combiner operator
     * @return this builder
     */
    fun withCombinerOperator(operator: KthOperator)
}