package com.skyecodes.karith.api

/**
 * Operator element to be included in a [KthModule] or [KthContext] and used in an expression.
 *
 * An operator takes two arguments (one on the left and one on the right) and returns a value.
 *
 * Operators can be built using the helper function [operator].
 */
interface KthOperator : KthElement {
    /**
     * The operator precedence, which is used to determine the order of operations.
     */
    val precedence: Int

    /**
     * Whether the operator is left associative, which is used to determine the order of operations.
     */
    val leftAssociative: Boolean

    /**
     * Applies the operator to the given arguments.
     *
     * @param left the left argument
     * @param right the right argument
     * @return the result of the operation
     */
    operator fun invoke(left: Double, right: Double): Double
}