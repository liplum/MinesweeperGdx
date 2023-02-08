package net.liplum.core

data class UpdateLogicContext(
    val delta: Second,
)

data class UpdateRenderContext(
    val delta: Second,
)

data class RenderContext(
    /**
     * The alpha from parent.
     */
    val alpha: Progress = 1f,
)