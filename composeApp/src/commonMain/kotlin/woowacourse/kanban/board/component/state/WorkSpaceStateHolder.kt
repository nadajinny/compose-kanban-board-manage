package woowacourse.kanban.board.component.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.workspace.WorkSpace
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskCardData

class WorkSpaceStateHolder(
    initialWorkSpace: WorkSpace,
) {
    var workSpace by mutableStateOf(initialWorkSpace)
        private set

    var selectedProjectId by mutableStateOf(initialWorkSpace.projects.firstOrNull()?.id)
        private set

    val selectedProject: Project?
        get() = workSpace.projects.firstOrNull { it.id == selectedProjectId }

    fun selectProject(project: Project) {
        selectedProjectId = project.id
    }

    fun addTask(task: TaskCardData) {
        updateSelectedProject { project -> project.addCard(task) }
    }

    fun updateTaskStatus(id: String, targetStatus: Status) {
        updateSelectedProject { project -> project.updateTaskStatus(id, targetStatus) }
    }

    private fun updateSelectedProject(update: (Project) -> Project) {
        val targetProjectId = selectedProjectId ?: return
        val index = workSpace.projects.indexOfFirst { it.id == targetProjectId }
        if (index == -1) return

        val updatedProjects = workSpace.projects.toMutableList().apply {
            this[index] = update(this[index])
        }.toImmutableList()

        workSpace = workSpace.copy(projects = updatedProjects)
    }
}

@Composable
fun rememberWorkSpaceStateHolder(
    workSpace: WorkSpace,
): WorkSpaceStateHolder = remember(workSpace) {
    WorkSpaceStateHolder(workSpace)
}
