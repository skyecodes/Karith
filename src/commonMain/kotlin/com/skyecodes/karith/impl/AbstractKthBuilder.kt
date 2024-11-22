package com.skyecodes.karith.impl

import com.skyecodes.karith.api.*

internal abstract class AbstractKthBuilder<T : KthBuilder<T>> : KthBuilder<T> {
    override var modules: MutableList<KthModule> = mutableListOf()
    override var operators: MutableList<KthOperator> = mutableListOf()
    override var functions: MutableList<KthFunction> = mutableListOf()
    override var constants: MutableList<KthConstant> = mutableListOf()
    override var combinerOperator: KthOperator? = null

    protected fun buildElementMap() = buildMap {
        putAllIfAbsent(operators, functions, constants)
        modules.forEach { lib ->
            lib.elementMap.filter { it.key !in this }.forEach { put(it.key, it.value) }
            if (combinerOperator == null) combinerOperator = lib.combinerOperator
        }
    }

    private fun MutableMap<String, KthElement>.putAllIfAbsent(vararg elements: Collection<KthElement>) =
        elements.flatMap { it }.filter { it.key !in this }.forEach { put(it.key, it) }
}