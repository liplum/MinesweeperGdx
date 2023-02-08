package net.liplum.core

interface IScene {
    /**
     * Update the state.
     */
    fun updateLogic(ctx: UpdateLogicContext)
    /**
     * Update the rendering process.
     */
    fun updateRender(ctx: UpdateRenderContext)
    /**
     * Render current state.
     */
    fun render(ctx: RenderContext)

    object Empty : IScene {
        override fun updateLogic(ctx: UpdateLogicContext) {

        }

        override fun updateRender(ctx: UpdateRenderContext) {
        }

        override fun render(ctx: RenderContext) {
        }

    }
}

class SceneManger(
    initialScene: IScene = IScene.Empty,
    val transitionTime: Second = 2f,
) {
    var lastScene: IScene = IScene.Empty
    val curScene = initialScene
    private var last2BlankTransitingTime: Second = 0f
        set(value) {
            field = value.coerceIn(0f, transitionTime)
        }
    private var blank2CurTransitingTime: Second = 0f
        set(value) {
            field = value.coerceIn(0f, transitionTime)
        }
    fun updateLogic(ctx: UpdateLogicContext) {
        curScene.updateLogic(ctx)
    }

    fun updateRender(ctx: UpdateRenderContext) {
        if(last2BlankTransitingTime){

        }
        transitingTime += ctx.delta
        curScene.updateRender(ctx)
    }

    fun render(ctx: RenderContext) {
        curScene.render(ctx)
        curScene
    }
}