package net.liplum.core

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion

typealias TR = TextureRegion

object Render {
    lateinit var batch: SpriteBatch
    fun draw(
        tr: TR,
        x: Float,
        y: Float,
    ) {
        batch.draw(tr, x, y)
    }
}