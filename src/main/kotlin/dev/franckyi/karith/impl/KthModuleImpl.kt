package dev.franckyi.karith.impl

import dev.franckyi.karith.api.KthElement
import dev.franckyi.karith.api.KthModule
import dev.franckyi.karith.api.KthOperator

internal data class KthModuleImpl(
    override val elementMap: MutableMap<String, KthElement>,
    override val combinerOperator: KthOperator?
) : KthModule