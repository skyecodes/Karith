package com.skyecodes.karith.impl

import com.skyecodes.karith.api.KthElement
import com.skyecodes.karith.api.KthModule
import com.skyecodes.karith.api.KthOperator

internal data class KthModuleImpl(
    override val elementMap: MutableMap<String, KthElement>,
    override val combinerOperator: KthOperator?
) : KthModule