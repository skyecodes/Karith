package com.skyecodes.karith.api

internal fun interface KthTokenizer {
    fun tokenize(expr: String, declaredVars: List<String>?): Pair<List<KthToken>, Set<String>>
}