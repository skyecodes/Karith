package dev.franckyi.karith.api

internal interface KthSorter {
    fun sort(tokens: List<KthToken>): List<KthToken>
}