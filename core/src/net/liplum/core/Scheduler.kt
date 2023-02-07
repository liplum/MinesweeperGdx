package net.liplum.core

import com.badlogic.gdx.utils.Array
import ktx.collections.GdxArray
import java.lang.ref.WeakReference

typealias Second = Float

data class ScheduledTask(
    val task: WeakReference<() -> Unit>,
    val deadline: Second,
)

open class Scheduler {
    companion object : Scheduler()

    var accumulator: Second = 0f
    val tasks = GdxArray<ScheduledTask>()
    fun update(delta: Second) {
        accumulator += delta
        val it = Array.ArrayIterator(tasks)
        while (it.hasNext()) {
            val entry = it.next()
            val task = entry.task.get()
            if (task == null) {
                it.remove()
                continue
            }
            if (accumulator >= entry.deadline) {
                task()
            }
        }
    }

    fun add(after: Second = 0f, task: () -> Unit) {
        val scheduled = ScheduledTask(
            task = WeakReference(task),
            deadline = accumulator + after,
        )
        tasks.add(scheduled)
    }
}
