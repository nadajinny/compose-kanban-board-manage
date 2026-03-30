package woowacourse.kanban.board.model.project

import androidx.compose.runtime.mutableStateListOf
import java.util.UUID
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskCardData

class Project(
    val title: String,
    initialTasks: ImmutableList<TaskCardData>,
    val id: String = UUID.randomUUID().toString()
) {
    private val tasks = mutableStateListOf<TaskCardData>().apply {
        addAll(initialTasks)
    }

    val allTasksCount get() = tasks.size

    fun filterTasksbyStatus(status: Status): ImmutableList<TaskCardData> {
        return tasks.filter { it.status == status }.toImmutableList()
    }

    fun addCard(data: TaskCardData) = tasks.add(data)

    fun calculateDoneRate(): Float {
        val totalTasks = allTasksCount
        if (totalTasks == 0) return 0f
        return filterTasksbyStatus(Status.DONE).size.toFloat() / totalTasks.toFloat()
    }

    fun findTaskById(id: String): TaskCardData? = tasks.firstOrNull { it.id == id }

    fun updateTaskStatus(id: String, targetStatus: Status) {
        val idx = tasks.indexOfFirst { it.id == id }
        if (idx == -1) return

        tasks[idx] = tasks[idx].copy(status = targetStatus)
    }
}
