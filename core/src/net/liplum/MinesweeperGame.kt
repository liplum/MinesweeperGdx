package net.liplum

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.ScreenUtils
import net.liplum.core.*
import net.liplum.registry.Blocks
import net.liplum.scene.LoadingScene

class MinesweeperGame : ApplicationAdapter() {

    override fun create() {
        Render.batch = SpriteBatch()
        Render.shape = ShapeRenderer()
        Render.camera = OrthographicCamera()
        Blocks.registerAll()
        Var.sceneManager.goto(LoadingScene())
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        val deltaTime = Gdx.graphics.deltaTime
        val updateLogicCtx = UpdateLogicContext(delta = deltaTime)
        Var.sceneManager.updateLogic(updateLogicCtx)
        Render.begin()
        val updateCtx = RenderContext(delta = deltaTime)
        Var.sceneManager.render(updateCtx)
        val tex = Texture("texture/dirt-base.png")
        Render.render(TextureRegion(tex), x = 50f, y = 50f, z = Layer.tile)
        Render.end()
    }

    override fun dispose() {
        Render.dispose()
    }
}