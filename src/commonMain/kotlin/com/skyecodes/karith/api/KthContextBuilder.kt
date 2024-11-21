package com.skyecodes.karith.api

/**
 * Builder interface for [KthContext].
 *
 * A context builder can be used with [context], [baseContext], [mathContext] and [defaultContext].
 */
interface KthContextBuilder : KthBuilder<KthContextBuilder> {
    /**
     * Whether caching is enabled or not. Default is true.
     *
     * Caching avoids having to parse the same expression several times when given the same input string or tokens and
     * can greatly increase the performance.
     *
     * By default, caching also avoids having to compute the same expression several times when given the same input
     * variables, though the caching capability can also be modified at the expression level.
     */
    var enableCache: Boolean

    /**
     * Disables the cache.
     *
     * @return this builder
     * @see enableCache
     */
    fun disableCache() = apply { enableCache = false }

    /**
     * Builds the context.
     *
     * @return the context
     */
    fun build(): KthContext
}