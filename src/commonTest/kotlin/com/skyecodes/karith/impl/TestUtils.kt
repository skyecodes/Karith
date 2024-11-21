package com.skyecodes.karith.impl

import com.skyecodes.karith.api.*
import kotlin.math.round

internal val KthToken.value: Any
    get() = when (this) {
        is KthNumber -> if (round(value) == value) value.toInt() else value
        is KthVariable -> name
        is KthSymbol -> c
        is KthElement -> key
    }

internal fun num(value: Number) = KthNumber(value.toDouble())

internal fun variable(name: String) = KthVariable(name)