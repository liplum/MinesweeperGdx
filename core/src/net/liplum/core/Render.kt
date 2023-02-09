package net.liplum.core

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer


val emptyTR = TextureRegion()

sealed class Render {
    companion object : Render() {
        const val zScale = 1
    }

    lateinit var batch: SpriteBatch
    lateinit var shape: ShapeRenderer
    lateinit var camera: Camera
    fun render(
        tr: TextureRegion,
        x: Float, y: Float,
        scaleX: Float = 1f, scaleY: Float = 1f,
        rotation: Float = 0f,
        z: Float,
    ) {
        batch.render(
            tr,
            x = x, y = y,
            scaleX = scaleX * z, scaleY = scaleY * z,
            rotation = rotation,
        )
    }

    fun begin() {
        batch.begin()
        batch.projectionMatrix = camera.combined
    }

    fun end() = batch.end()

    fun dispose() = batch.dispose()

}

fun SpriteBatch.render(
    region: TextureRegion,
    x: Float, y: Float,
    originX: Float = 0f, originY: Float = 0f,
    width: Float = region.regionWidth.toFloat(),
    height: Float = region.regionHeight.toFloat(),
    scaleX: Float = 1f, scaleY: Float = 1f,
    rotation: Float = 0f,
) {
    draw(
        region,
        x, y,
        originX, originY,
        width, height,
        scaleX, scaleY,
        rotation
    )
}
