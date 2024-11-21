package com.skyecodes.karith.api

/**
 * Constant element to be included in a [KthModule] or [KthContext] and used in an expression.
 *
 * A constant simply gets replaced by its value during the evaluation of an expression.
 *
 * Constants can be built using the helper function [constant].
 */
interface KthConstant : KthElement {
    /**
     * The value of the constant.
     */
    val value: Number
}