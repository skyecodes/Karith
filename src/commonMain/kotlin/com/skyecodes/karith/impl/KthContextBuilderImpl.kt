package com.skyecodes.karith.impl

import com.skyecodes.karith.api.KthContext
import com.skyecodes.karith.api.KthContextBuilder

internal class KthContextBuilderImpl : AbstractKthBuilder<KthContextBuilder>(), KthContextBuilder {
    override var enableCache = true

    override fun build(): KthContext {
        val elementMap = buildElementMap()
        return KthContextImpl(elementMap, combinerOperator!!, enableCache)
    }
}