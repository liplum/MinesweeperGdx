package net.liplum.core

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

interface IUpdate {
    /**
     * Update the state.
     */
    fun updateLogic(ctx: UpdateLogicContext)
}

interface IRender {
    /**
     * Render current state.
     */
    fun render(ctx: RenderContext)
}

interface IRectBody {
    val hitBox: Rectangle
}

interface IPositioned {
    var x: Float
    var y: Float
}

interface IMouseListener {

}

interface IEntity {
    var group: Group
    var isAdded: Boolean
    fun add(group: Group) {
        if (!isAdded) {
            this.group = group
            group.add(this)
            isAdded = true
        }
    }

    fun remove(group: Group) {
        if (isAdded) {
            group.remove(this)
            isAdded = false
        }
    }
}