package dev.franckyi.karith.impl

import dev.franckyi.karith.api.*

internal abstract class AbstractKthBuilder<T : KthBuilder<T>> : KthBuilder<T> {
    override var modules: MutableList<KthModule> = mutableListOf()
    override var operators: MutableList<KthOperator> = mutableListOf()
    override var functions: MutableList<KthFunction> = mutableListOf()
    override var constants: MutableList<KthConstant> = mutableListOf()
    override var combinerOperator: KthOperator? = null

    protected fun buildElementMap() = mutableMapOf<String, KthElement>().apply {
        putAllIfAbsent(operators, functions, constants)
        modules.forEach { lib ->
            lib.elementMap.forEach { putIfAbsent(it.key, it.value) }
            if (combinerOperator == null) combinerOperator = lib.combinerOperator
        }
    }

    private fun MutableMap<String, KthElement>.putAllIfAbsent(vararg elements: Collection<KthElement>) =
        elements.flatMap { it }.forEach { putIfAbsent(it.key, it) }
}