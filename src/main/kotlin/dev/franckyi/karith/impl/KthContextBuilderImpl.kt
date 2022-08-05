package dev.franckyi.karith.impl

import dev.franckyi.karith.api.KthContext
import dev.franckyi.karith.api.KthContextBuilder

internal class KthContextBuilderImpl : AbstractKthBuilder<KthContextBuilder>(), KthContextBuilder {
    override var enableCache = true

    override fun build(): KthContext {
        val elementMap = buildElementMap()
        return KthContextImpl(elementMap, combinerOperator!!, enableCache)
    }
}