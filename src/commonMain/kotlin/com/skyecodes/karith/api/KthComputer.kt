package com.skyecodes.karith.api

internal interface KthComputer {
    fun compute(tokens: List<KthToken>, variables: Map<String, Number>): Double
}