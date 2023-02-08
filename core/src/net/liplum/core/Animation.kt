package net.liplum.core

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
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
    val curve: Curve = Curve.linear,
) : ITween<Float> {
    override val value: Float
        get() = curve(begin, end, progress)
    private var progress = 0f
        set(value) {
            field = value.coerceIn(0f, 1f)
        }

    override fun update(delta: Float) {
        progress += delta
    }

    override fun conformTo(begin: Float, end: Float): FloatTween {
        return if (this.begin == begin && this.end == end) {
            this
        } else {
            FloatTween(value, end, curve)
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
    val curve: Curve = Curve.linear,
) : ITween<Color> {
    private var progress = 0f
        set(value) {
            field = value.coerceIn(0f, 1f)
        }

    override val value: Color
        get() = begin.copy().lerpCurved(end, progress, curve)

    override fun update(delta: Float) {
        progress += delta
    }

    override fun conformTo(begin: Color, end: Color): ColorTween {
        return if (this.begin == begin && this.end == end) {
            this
        } else {
            ColorTween(value, end, curve)
        }
    }
}