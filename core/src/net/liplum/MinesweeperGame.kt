package net.liplum

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import net.liplum.core.*

class MinesweeperGame : ApplicationAdapter() {
    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        val deltaTime = Gdx.graphics.deltaTime
        val updateLogicCtx = UpdateLogicContext(delta = deltaTime)
        Var.scene.updateLogic(updateLogicCtx)
        batch.begin()
        val updateCtx = RenderContext(delta = deltaTime)
        Var.scene.render(updateCtx)
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
    }
}