package net.liplum.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

interface IContent {
    val contentID: String
    fun loadAssets()
    fun init()
}

fun IContent.loadTexture(name: String): TextureRegion {
    return TextureRegion(Texture("$contentID.name"))
}