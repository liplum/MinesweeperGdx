package net.liplum.scene

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.graphics.use
import net.liplum.core.IScene
import net.liplum.core.Render
import net.liplum.core.RenderContext
import net.liplum.core.UpdateLogicContext

class LoadingScene : IScene {

    override fun updateLogic(ctx: UpdateLogicContext) {
    }

    override fun render(ctx: RenderContext) {
        Render.shape.use(ShapeRenderer.ShapeType.Filled) {
            it.circle(20f, 30f, 5f)
        }
    }
}