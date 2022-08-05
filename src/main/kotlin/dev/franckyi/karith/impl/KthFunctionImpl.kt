package dev.franckyi.karith.impl

import dev.franckyi.karith.api.KthFunction

internal data class KthFunctionImpl(
    override val key: String,
    override val argCount: Int,
    private val function: (DoubleArray) -> Double
) : KthFunction {
    override fun apply(args: DoubleArray): Double = function(args)
}
