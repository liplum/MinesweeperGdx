package net.liplum.scene

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import ktx.graphics.copy
import ktx.graphics.use
import net.liplum.Var
import net.liplum.core.*

class LoadingScene : IScene {
    override fun init() {
        Thread {
            Thread.sleep(2000)
            Scheduler.add {
                Var.sceneManager.goto(MinesweeperScene())
            }
        }.start()
    }

    var radius = 30
    var gameTime = 0f

    override fun updateLogic(ctx: UpdateLogicContext) {
        gameTime += ctx.delta
    }

    override fun render(ctx: RenderContext) {
        Render.shape.use(ShapeRenderer.ShapeType.Filled) {
            it.color = it.color.copy(alpha = ctx.alpha)
            it.circle(100f, 20f, radius * MathUtils.sin(gameTime))
        }
    }
}