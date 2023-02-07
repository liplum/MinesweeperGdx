package net.liplum.core

interface IUpdate {
    fun update(ctx: UpdateContext)
}

interface IRender {
    fun render(ctx: RenderContext)
}
