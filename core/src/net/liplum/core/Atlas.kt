package net.liplum.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.PixmapPacker
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.collections.GdxMap

sealed class Atlas {
    companion object : Atlas()

    val name2Region = GdxMap<String, TextureRegion>()
    fun addPixmap() {
        val packer = PixmapPacker(2048, 2048, Pixmap.Format.RGBA8888, 0, false)
        //packer.pack()
    }

    operator fun get(name: String) {

    }
}