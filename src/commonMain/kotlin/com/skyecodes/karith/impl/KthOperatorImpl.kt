package com.skyecodes.karith.impl

import com.skyecodes.karith.api.KthOperator

internal data class KthOperatorImpl(
    override val key: String,
    override val precedence: Int,
    override val leftAssociative: Boolean,
    private val operation: (Double, Double) -> Double
) : KthOperator {
    override fun invoke(left: Double, right: Double): Double = operation(left, right)
}