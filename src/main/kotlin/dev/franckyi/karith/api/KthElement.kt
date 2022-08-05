package dev.franckyi.karith.api

/**
 * Common interface for [KthOperator], [KthFunction] and [KthConstant].
 */
sealed interface KthElement : KthToken {
    /**
     * The key of the element. Used to parse the element in an expression.
     */
    val key: String
}
