package dev.franckyi.karith.api

internal interface KthTokenizer {
    fun tokenize(expr: String, declaredVars: Array<out String>?): Pair<List<KthToken>, Set<String>>
}