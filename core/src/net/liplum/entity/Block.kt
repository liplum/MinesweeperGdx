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
        override fun update(ctx: UpdateContext) {

        }

        override fun render(ctx: RenderContext) {
        }

    }
}