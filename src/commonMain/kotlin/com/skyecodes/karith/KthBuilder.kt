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
interface KthBuilder<T : KthBuilder<T>> {
    /**
     * The modules included in this context or module.
     */
    var modules: MutableList<KthModule>

    /**
     * The operators included in this context or module.
     */
    var operators: MutableList<KthOperator>

    /**
     * The functions included in this context or module.
     */
    var functions: MutableList<KthFunction>

    /**
     * The constants included in this context or module.
     */
    var constants: MutableList<KthConstant>

    /**
     * The combiner operator set in this context or module.
     */
    var combinerOperator: KthOperator?

    /**
     * Includes a module to this context or module.
     *
     * @param module the module to include
     * @return this builder
     */
    fun include(module: KthModule) = doApply { this.modules += module }

    /**
     * Includes modules to this context or module.
     *
     * @param modules the modules to include
     * @return this builder
     */
    fun includeAll(vararg modules: KthModule) = doApply { this.modules += modules }

    /**
     * Includes an element to this context or module.
     *
     * @param element the element to include
     * @return this builder
     */
    fun with(element: KthContextualToken) = doApply {
        when (element) {
            is KthOperator -> operators += element
            is KthFunction -> functions += element
            is KthConstant -> constants += element
        }
    }

    /**
     * Includes elements to this context or module.
     *
     * @param elements the elements to include
     * @return this builder
     */
    fun withAll(vararg elements: KthContextualToken) = doApply { elements.forEach { with(it) } }

    /**
     * Includes an operator to this context or module.
     *
     * @param operator the operator to include
     * @return this builder
     */
    fun withOperator(operator: KthOperator) = doApply { this.operators += operator }

    /**
     * Includes operators to this context or module.
     *
     * @param operators the operators to include
     * @return this builder
     */
    fun withOperators(vararg operators: KthOperator) = doApply { this.operators += operators }

    /**
     * Includes a function to this context or module.
     *
     * @param function the function to include
     * @return this builder
     */
    fun withFunction(function: KthFunction) = doApply { this.functions += function }

    /**
     * Includes functions to this context or module.
     *
     * @param functions the functions to include
     * @return this builder
     */
    fun withFunctions(vararg functions: KthFunction) = doApply { this.functions += functions }

    /**
     * Includes a constant to this context or module.
     *
     * @param constant the constant to include
     * @return this builder
     */
    fun withConstant(constant: KthConstant) = doApply { this.constants += constant }

    /**
     * Includes constants to this context or module.
     *
     * @param constants the constants to include
     * @return this builder
     */
    fun withConstants(vararg constants: KthConstant) = doApply { this.constants += constants }

    /**
     * Sets the combiner operator of this context or module.
     *
     * @param operator the combiner operator
     * @return this builder
     */
    fun withCombinerOperator(operator: KthOperator) = doApply { combinerOperator = operator }

    private fun doApply(block: T.() -> Unit): T = self().apply(block)

    @Suppress("UNCHECKED_CAST")
    private fun self() = this as T
}