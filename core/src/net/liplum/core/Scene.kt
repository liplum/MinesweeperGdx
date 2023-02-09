package net.liplum.core

import com.badlogic.gdx.utils.Disposable
import net.liplum.core.TransitionState.*

interface IScene : IUpdate, IRender, Disposable {
    fun init() {

    }

    override fun dispose() {

    }

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
    val transitionDuration: Second = 1f,
) : IUpdate, IRender {
    var lastScene: IScene = IScene.Empty
        private set
    var curScene = initialScene
        private set
    var transitionState = Blank2Cur

    init {
        assert(transitionDuration > 0f)
    }

    private var transitingTime: Second = 0f
        set(value) {
            field = value.coerceIn(0f, transitionDuration)
        }
    val transitingProgress: Progress
        get() = transitingTime / transitionDuration

    fun goto(scene: IScene) {
        lastScene = curScene
        curScene = scene
        transitionState = Last2Blank
        transitingTime = 0f
    }

    override fun updateLogic(ctx: UpdateLogicContext) {
        when (transitionState) {
            Last2Blank -> {
                if (transitingTime < transitionDuration) {
                    transitingTime += ctx.delta
                    if (transitingTime >= transitionDuration) {
                        lastScene.dispose()
                        transitionState = Blank2Cur
                    }
                } else {
                    lastScene.updateLogic(ctx)
                }
            }

            Blank2Cur -> {
                if (transitingTime < transitionDuration) {
                    transitingTime += ctx.delta
                    if (transitingTime >= transitionDuration) {
                        curScene.init()
                    }
                } else {
                    curScene.updateLogic(ctx)
                }
            }
        }
    }

    override fun render(ctx: RenderContext) {
        when (transitionState) {
            Last2Blank -> {
                lastScene.render(
                    ctx.copy(
                        alpha = ctx.alpha * (1f - transitingProgress),
                    )
                )
            }

            Blank2Cur -> {
                curScene.render(
                    ctx.copy(
                        alpha = ctx.alpha * transitingProgress,
                    )
                )
            }
        }
    }
}