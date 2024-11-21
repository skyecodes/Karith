package com.skyecodes.karith.impl

import com.skyecodes.karith.api.KthFunction

internal data class KthFunctionImpl(
    override val key: String,
    override val argCount: Int,
    private val function: (DoubleArray) -> Double
) : KthFunction {
    override fun invoke(vararg args: Double): Double = function(args)
}
