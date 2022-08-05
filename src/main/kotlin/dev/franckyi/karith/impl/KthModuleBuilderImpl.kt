package dev.franckyi.karith.impl

import dev.franckyi.karith.api.KthModule
import dev.franckyi.karith.api.KthModuleBuilder

internal class KthModuleBuilderImpl : AbstractKthBuilder<KthModuleBuilder>(), KthModuleBuilder {
    override fun build(): KthModule {
        val elementMap = buildElementMap()
        return KthModuleImpl(elementMap, combinerOperator)
    }
}