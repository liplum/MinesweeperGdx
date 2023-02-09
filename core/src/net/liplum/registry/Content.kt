package net.liplum.registry

import net.liplum.entity.Block

object Blocks {
    lateinit var dirt: Block
    fun registerAll() {
        dirt = Block("dirt")
    }
}