package net.liplum.core

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