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

internal fun interface KthComputer {
    operator fun invoke(tokens: List<KthToken>, variables: Map<String, Number>): Double

    companion object : KthComputer {
        override fun invoke(tokens: List<KthToken>, variables: Map<String, Number>): Double {
            val stack = ArrayDeque<Double>()
            tokens.forEach {
                when (it) {
                    is KthNumber -> stack += it.value
                    is KthConstant -> stack += it.value.toDouble()
                    is KthVariable -> stack += variables[it.name]?.toDouble()
                        ?: throw KthUndefinedVariableException(it.name)

                    is KthOperator -> {
                        if (stack.size < 2) {
                            throw KthInsufficientOperandsException("operator", it.key)
                        }
                        val right = stack.removeLast()
                        val left = stack.removeLast()
                        stack += it(left, right)
                    }

                    is KthFunction -> {
                        if (stack.size < it.argCount) {
                            throw KthInsufficientOperandsException("function", it.key)
                        }
                        stack += it(*(DoubleArray(it.argCount) { stack.removeLast() }).reversedArray())
                    }

                    else -> throw KthUnknownTokenException(it)
                }
            }
            return stack[0]
        }
    }
}