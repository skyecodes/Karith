package dev.franckyi.karith.impl

import dev.franckyi.karith.api.*

internal object KthComputerImpl : KthComputer {
    override fun compute(tokens: List<KthToken>, variables: Map<String, Number>): Double {
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
                    stack += it.apply(left, right)
                }

                is KthFunction -> {
                    if (stack.size < it.argCount) {
                        throw KthInsufficientOperandsException("function", it.key)
                    }
                    stack += it.apply((DoubleArray(it.argCount) { stack.removeLast() }).reversedArray())
                }

                else -> throw KthUnknownTokenException(it)
            }
        }
        return stack[0]
    }

}
