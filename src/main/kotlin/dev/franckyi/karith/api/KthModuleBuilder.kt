package dev.franckyi.karith.api

/**
 * Builder interface for [KthModule].
 *
 * A module builder can be used with [module].
 */
interface KthModuleBuilder : KthBuilder<KthModuleBuilder> {
    /**
     * Builds the module.
     *
     * @return the module
     */
    fun build(): KthModule
}