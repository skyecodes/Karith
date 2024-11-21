package com.skyecodes.karith.impl

import com.skyecodes.karith.api.KthModule
import com.skyecodes.karith.api.KthModuleBuilder

internal class KthModuleBuilderImpl : AbstractKthBuilder<KthModuleBuilder>(), KthModuleBuilder {
    override fun build(): KthModule {
        val elementMap = buildElementMap()
        return KthModuleImpl(elementMap, combinerOperator)
    }
}