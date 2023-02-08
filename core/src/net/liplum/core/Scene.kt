package net.liplum.core

interface IScene {
    /**
     * Update the state.
     */
    fun updateLogic(ctx: UpdateLogicContext)
    /**
     * Render current state.
     */
    fun render(ctx: RenderContext)

    object Empty : IScene {
        override fun updateLogic(ctx: UpdateLogicContext) {

        }

        override fun render(ctx: RenderContext) {
        }

    }
}

enum class TransitionState {
    Last2Blank, Blank2Cur
}

class SceneManger(
    initialScene: IScene = IScene.Empty,
    val transitionTime: Second = 2f,
) {
    var lastScene: IScene = IScene.Empty
    val curScene = initialScene
    var transitionState = TransitionState.Last2Blank
    private var transitingTime: Second = 0f
        set(value) {
            field = value.coerceIn(0f, transitionTime)
        }

    fun updateLogic(ctx: UpdateLogicContext) {
        curScene.updateLogic(ctx)
    }


    fun render(ctx: RenderContext) {
        curScene.render(ctx)
        curScene
    }
}