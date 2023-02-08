package net.liplum.core

import com.badlogic.gdx.utils.ObjectSet
import ktx.collections.GdxSet

class Group {
    val update = GdxSet<IUpdate>()
    val render = GdxSet<IRender>()
    fun updateLogic(ctx: UpdateLogicContext) {
        ObjectSet.ObjectSetIterator(update).forEach {
            it.updateLogic(ctx)
        }
    }

    fun render(ctx: RenderContext) {
        ObjectSet.ObjectSetIterator(render).forEach {
            it.render(ctx)
        }
    }

    fun add(entity: Any) {
        if (entity is IUpdate) {
            update.add(entity)
        }
        if (entity is IRender) {
            render.add(entity)
        }
    }

    fun remove(entity: Any) {
        if (entity is IUpdate) {
            update.remove(entity)
        }
        if (entity is IRender) {
            render.remove(entity)
        }
    }
}