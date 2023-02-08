package net.liplum.entity

import com.badlogic.gdx.math.Vector2
import ktx.math.component1
import ktx.math.component2
import ktx.math.vec2
import net.liplum.core.*

class Block : IContent {

    override fun loadAssets() {
    }

    override fun init() {

    }

    inner class BlockEntity : IEntity, IUpdate, IRender {

        override lateinit var group: Group
        override var isAdded: Boolean = false
        var pos = vec2(0f, 0f)
        override fun updateLogic(ctx: UpdateLogicContext) {
        }

        fun onMouseClick() {
            pos.add(0f, 10f)
        }

        var posTween = Vector2Tween(begin = pos, duration = 1f)

        override fun updateRender(ctx: UpdateRenderContext) {
            posTween = posTween.conformTo(pos, pos)
            posTween.update(ctx.delta)
        }

        override fun render(ctx: RenderContext) {
            val curPos = posTween.value
            val (x, y) = curPos
        }
    }
}