package net.liplum

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import net.liplum.core.*

class MinesweeperGame : ApplicationAdapter() {
    lateinit var batch: SpriteBatch

    companion object {
        const val updateLogicInterval: Second = 1f / 20f
    }

    private var lastUpdateRenderTime: Second = 0f
    private var updateLogicDeltaAccumulator: Second = 0f
    override fun create() {
        batch = SpriteBatch()
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        val deltaTime = Gdx.graphics.deltaTime
        lastUpdateRenderTime += deltaTime
        updateLogicDeltaAccumulator += deltaTime
        if (updateLogicDeltaAccumulator >= updateLogicInterval) {
            val updateLogicCtx = UpdateLogicContext(delta = updateLogicDeltaAccumulator)
            Var.scene.updateLogic(updateLogicCtx)
            updateLogicDeltaAccumulator = 0f
        }
        val updateRenderCtx = UpdateRenderContext(delta = deltaTime)

        Var.scene.updateRender(updateRenderCtx)
        val renderCtx = RenderContext()
        batch.begin()
        Var.scene.render(renderCtx)
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
    }
}