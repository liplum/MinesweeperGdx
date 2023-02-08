package net.liplum.core

import com.badlogic.gdx.utils.Array
import ktx.collections.GdxArray
import ktx.collections.GdxMap
import java.lang.ref.WeakReference

typealias EventSubscriber<T> = (event: T) -> Unit
private typealias EventSubscriberList = GdxArray<WeakReference<EventSubscriber<*>>>

/**
 * An event dispatcher with [WeakReference].
 */
sealed class Events {
    /**
     * Global event dispatcher.
     */
    companion object : Events()

    val type2Subscribers = GdxMap<Any, EventSubscriberList>()
    inline fun <reified T> on(noinline subscriber: EventSubscriber<T>) {
        var subscribers: EventSubscriberList? = type2Subscribers[T::class.java]
        if (subscribers == null) {
            subscribers = GdxArray()
        }
        subscribers.add(WeakReference(subscriber))
    }

    inline fun <reified T> off(noinline subscriber: EventSubscriber<T>) {
        val subscribers = type2Subscribers[T::class.java] ?: return
        subscribers.removeAll {
            it.get() == subscriber
        }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> fire(event: T) {
        val subscribers = type2Subscribers[T::class.java] ?: return
        val it = Array.ArrayIterator(subscribers)
        while (it.hasNext()) {
            val subscriber = it.next().get()
            if (subscriber == null) {
                it.remove()
            } else {
                (subscriber as EventSubscriber<T>)(event)
            }
        }
    }
}