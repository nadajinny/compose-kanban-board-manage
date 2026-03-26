package woowacourse.kanban.board.model.project

import androidx.compose.runtime.mutableStateListOf
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.model.taskcard.TaskCardData
import woowacourse.kanban.board.model.taskcard.Status
import java.util.UUID

data class Project(
    val title: String,
    val initialTasks: ImmutableList<TaskCardData>,
    val id: String = UUID.randomUUID().toString()
) {
    private val tasks = mutableStateListOf<TaskCardData>().apply {
        addAll(initialTasks)
    }

    val allTasksCount get() = tasks.size
    val todoTasks get() = tasks.filter { it.status == Status.TODO }.toImmutableList()
    val progressTasks get() = tasks.filter { it.status == Status.PROGRESS }.toImmutableList()
    val doneTasks get() = tasks.filter { it.status == Status.DONE }.toImmutableList()


    fun addCard(data: TaskCardData) = tasks.add(data)

    fun calculateDoneRate(): Float {
        val totalTasks = todoTasks.size + progressTasks.size + doneTasks.size
        if (totalTasks == 0) return 0f
        return doneTasks.size.toFloat() / totalTasks.toFloat()
    }

    fun findTaskById(id: String): TaskCardData? = tasks.firstOrNull { it.id == id }

    fun updateTaskStatus(id: String, targetStatus: Status) {
        val idx = tasks.indexOfFirst { it.id == id }
        if (idx == -1) return

        tasks[idx] = tasks[idx].copy(status = targetStatus)
    }
}
