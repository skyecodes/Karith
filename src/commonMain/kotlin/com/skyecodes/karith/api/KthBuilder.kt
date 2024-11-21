package com.skyecodes.karith.api

/**
 * Common builder interface for [KthContextBuilder] and [KthModuleBuilder].
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
    fun with(element: KthElement) = doApply {
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
    fun withAll(vararg elements: KthElement) = doApply { elements.forEach { with(it) } }

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