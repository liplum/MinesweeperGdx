package net.liplum.entity

import net.liplum.core.*

class Block : IContent {

    override fun loadAssets() {
    }

    override fun init() {

    }

    inner class BlockEntity : IEntity, IUpdate, IRender {

        override lateinit var group: Group
        override var isAdded: Boolean = false
        override fun updateLogic(ctx: UpdateLogicContext) {

        }

        override fun updateRender(ctx: UpdateRenderContext) {

        }

        override fun render(ctx: RenderContext) {
        }

    }
}