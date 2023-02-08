package net.liplum.core

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import ktx.graphics.copy

typealias Curve = Interpolation
typealias Progress = Float

operator fun Curve.invoke(start: Float, end: Float, progress: Progress) =
    apply(start, end, progress)

interface ITween<T> {
    val value: T
    fun update(delta: Float)
    fun conformTo(begin: T, end: T): ITween<T>
}

class FloatTween(
    val begin: Float = 0f,
    val end: Float = 0f,
    val duration: Second = 0f,
    val curve: Curve = Curve.linear,
) : ITween<Float> {
    override val value: Float
        get() = curve(begin, end, progress)
    private var progress = if (duration == 0f) 1f else 0f
        set(value) {
            field = value.coerceIn(0f, 1f)
        }

    override fun update(delta: Float) {
        if (duration != 0f) {
            progress += delta / duration
        }
    }

    override fun conformTo(begin: Float, end: Float): FloatTween {
        return if (this.begin == begin && this.end == end) {
            this
        } else {
            FloatTween(value, end, duration, curve)
        }
    }
}

fun Color.lerpCurved(target: Color, progress: Float, curve: Curve): Color {
    this.r = curve(this.r, target.r, progress)
    this.g = curve(this.g, target.g, progress)
    this.b = curve(this.b, target.b, progress)
    this.a = curve(this.a, target.a, progress)
    return clamp()
}

class ColorTween(
    val begin: Color = Color.CLEAR,
    val end: Color = Color.CLEAR,
    val duration: Second = 0f,
    val curve: Curve = Curve.linear,
) : ITween<Color> {
    private var progress = if (duration == 0f) 1f else 0f
        set(value) {
            field = value.coerceIn(0f, 1f)
        }


    override val value: Color
        get() = begin.copy().lerpCurved(end, progress, curve)

    override fun update(delta: Float) {
        if (duration != 0f) {
            progress += delta / duration
        }
    }

    override fun conformTo(begin: Color, end: Color): ColorTween {
        return if (this.begin == begin && this.end == end) {
            this
        } else {
            ColorTween(value, end, duration, curve)
        }
    }
}

fun Vector2.lerpCurved(target: Vector2, progress: Float, curve: Curve): Vector2 {
    this.x = curve(this.x, target.x, progress)
    this.y = curve(this.y, target.y, progress)
    return this
}

class Vector2Tween(
    val begin: Vector2 = Vector2.Zero,
    val end: Vector2 = Vector2.Zero,
    val duration: Second = 0f,
    val curve: Curve = Curve.linear,
) : ITween<Vector2> {
    override val value: Vector2
        get() = begin.cpy().lerpCurved(end, progress, curve)
    private var progress = if (duration == 0f) 1f else 0f
        set(value) {
            field = value.coerceIn(0f, 1f)
        }

    override fun update(delta: Float) {
        if (duration != 0f) {
            progress += delta / duration
        }
    }

    override fun conformTo(begin: Vector2, end: Vector2): Vector2Tween {
        return if (this.begin == begin && this.end == end) {
            this
        } else {
            Vector2Tween(value, end, duration, curve)
        }
    }
}