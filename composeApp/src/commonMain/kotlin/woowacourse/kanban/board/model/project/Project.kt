package woowacourse.kanban.board.model.project

import java.util.UUID
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskCardData

class Project(
    val title: String,
    private val tasks: ImmutableList<TaskCardData>,
    val id: String = UUID.randomUUID().toString(),
) {
    val allTasksCount get() = tasks.size

    fun filterTasksbyStatus(status: Status): ImmutableList<TaskCardData> =
        tasks.filter { it.status == status }.toImmutableList()

    fun addCard(data: TaskCardData): Project =
        Project(
            title = title,
            tasks = (tasks + data).toImmutableList(),
            id = id,
        )

    fun calculateDoneRate(): Float {
        val totalTasks = allTasksCount
        if (totalTasks == 0) return 0f
        return filterTasksbyStatus(Status.DONE).size.toFloat() / totalTasks.toFloat()
    }

    fun findTaskById(id: String): TaskCardData? = tasks.firstOrNull { it.id == id }

    fun updateTaskStatus(id: String, targetStatus: Status): Project {
        if (tasks.none { it.id == id }) return this

        val updatedTasks = tasks.map { task ->
            if (task.id == id) task.copy(status = targetStatus) else task
        }.toImmutableList()

        return Project(
            title = title,
            tasks = updatedTasks,
            id = this.id,
        )
    }
}
