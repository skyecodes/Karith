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

package com.skyecodes.karith.impl

import com.skyecodes.karith.*

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