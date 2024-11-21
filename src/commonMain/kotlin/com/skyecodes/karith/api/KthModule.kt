package com.skyecodes.karith.api

import com.skyecodes.karith.api.builtin.Modules

/**
 * A module is a collection of [KthElement]s to be included in a [KthContext].
 *
 * Karith has a few builtin module:
 * * [Modules.BASE]
 * * [Modules.BITWISE]
 * * [Modules.MATH_UTIL]
 * * [Modules.MATH_LOG]
 * * [Modules.MATH_TRIG]
 * * [Modules.MATH]
 */
interface KthModule {
    /**
     * All the elements contained in this module: [KthOperator]s, [KthFunction]s and [KthConstant]s.
     */
    val elementMap: MutableMap<String, KthElement>

    /**
     * The combiner operator defines which operator is used to combine two tokens that don't have an operator between
     * them.
     */
    val combinerOperator: KthOperator?
}