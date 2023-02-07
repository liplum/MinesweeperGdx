package net.liplum.core

import com.badlogic.gdx.utils.Array
import ktx.collections.GdxArray
import java.lang.ref.WeakReference

typealias Second = Float
typealias TaskRef = WeakReference<() -> Unit>

data class ScheduledTask(
    val task: TaskRef,
    val deadline: Second,
)

interface ILoopedTask {
    val task: TaskRef
    val loopTime: Second
    var timer: Second
}

open class Scheduler {
    companion object : Scheduler()

    var accumulator: Second = 0f
    val scheduledTasks = GdxArray<ScheduledTask>()
    fun update(delta: Second) {
        accumulator += delta
        val it = Array.ArrayIterator(scheduledTasks)
        while (it.hasNext()) {
            val entry = it.next()
            val task = entry.task.get()
            if (task == null) {
                it.remove()
                continue
            }
            if (accumulator >= entry.deadline) {
                task()
                it.remove()
            }
        }
    }

    fun add(after: Second = 0f, task: () -> Unit) {
        val scheduled = ScheduledTask(
            task = WeakReference(task),
            deadline = accumulator + after,
        )
        scheduledTasks.add(scheduled)
    }
}


data class LifecycleLoopedTask(
    override val task: TaskRef,
    override val loopTime: Second,
    override var timer: Second = 0f,
) : ILoopedTask

data class CountedLoopedTask(
    override val task: TaskRef,
    override val loopTime: Second,
    override var timer: Second = 0f,
) : ILoopedTask
