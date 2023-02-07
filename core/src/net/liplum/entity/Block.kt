package net.liplum.entity

import net.liplum.core.*

class Block : IContent {

    override fun load() {
    }

    inner class BlockEntity : IUpdate, IRender {
        override fun update(ctx: UpdateContext) {

        }

        override fun render(ctx: RenderContext) {
        }
    }

}