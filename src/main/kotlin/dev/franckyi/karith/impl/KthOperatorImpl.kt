package dev.franckyi.karith.impl

import dev.franckyi.karith.api.KthOperator

internal data class KthOperatorImpl(
    override val key: String,
    override val precedence: Int,
    override val leftAssociative: Boolean,
    private val operation: (Double, Double) -> Double
) : KthOperator {
    override fun apply(left: Double, right: Double): Double = operation(left, right)
}