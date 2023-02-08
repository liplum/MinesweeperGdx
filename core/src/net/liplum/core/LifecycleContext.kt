package net.liplum.core

data class UpdateLogicContext(
    val delta: Second,
)

data class RenderContext(
    val delta: Second,
    /**
     * The alpha from parent.
     */
    val alpha: Progress = 1f,
)