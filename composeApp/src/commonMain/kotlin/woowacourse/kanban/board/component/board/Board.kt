package woowacourse.kanban.board.component.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.collections.immutable.ImmutableList
import woowacourse.kanban.board.component.modal.Modal
import woowacourse.kanban.board.component.util.ComponentText
import woowacourse.kanban.board.component.util.Gray80
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskCard

@Composable
fun Board(
    project: Project,
    profiles: ImmutableList<Profile>,
    onCreateTask: (TaskCard) -> Unit,
    onUpdateTask: (String, TaskCard) -> Unit,
    onDeleteTask: (String) -> Unit,
    onUpdateTaskStatus: (String, Status) -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    var shouldShowCreateSnackbar by remember { mutableStateOf(false) }
    var shouldShowMoveSnackbar by remember { mutableStateOf(false) }

    var shouldShowModifySnackbar by remember { mutableStateOf(false) }
    var shouldShowDeleteSnackbar by remember { mutableStateOf(false) }

    var isShowModal by remember { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<TaskCard?>(null) }
    val closeModal = {
        selectedTask = null
        isShowModal = false
    }

    LaunchedEffect(shouldShowCreateSnackbar) {
        if (shouldShowCreateSnackbar) {
            snackbarHostState.showSnackbar(
                message = ComponentText.BOARD_TASK_CREATE_SNACKBAR,
                withDismissAction = true,
            )
            shouldShowCreateSnackbar = false
        }
    }

    LaunchedEffect(shouldShowMoveSnackbar) {
        if (shouldShowMoveSnackbar) {
            snackbarHostState.showSnackbar(
                message = ComponentText.BOARD_TASK_MOVE_SNACKBAR,
                withDismissAction = true,
            )
            shouldShowMoveSnackbar = false
        }
    }

    LaunchedEffect(shouldShowModifySnackbar) {
        if (shouldShowModifySnackbar) {
            snackbarHostState.showSnackbar(
                message = ComponentText.BOARD_TASK_MODIFY_SNACKBAR,
                withDismissAction = true,
            )
            shouldShowModifySnackbar = false
        }
    }

    LaunchedEffect(shouldShowDeleteSnackbar) {
        if (shouldShowDeleteSnackbar) {
            snackbarHostState.showSnackbar(
                message = ComponentText.BOARD_TASK_DELETE_SNACKBAR,
                withDismissAction = true,
            )
            shouldShowDeleteSnackbar = false
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(Gray80),
        ) {
            if (isShowModal) {
                Dialog(
                    onDismissRequest = closeModal,
                    properties = DialogProperties(
                        usePlatformDefaultWidth = false,
                    ),
                ) {
                    Modal(
                        profiles = profiles,
                        initialTask = selectedTask,
                        onClickClose = closeModal,
                        onCreateTask = { task ->
                            onCreateTask(task)
                            shouldShowCreateSnackbar = true
                            closeModal()
                        },
                        onUpdateTask = { id, task ->
                            onUpdateTask(id, task)
                            shouldShowModifySnackbar = true
                            closeModal()
                        },
                        onDeleteTask = { id ->
                            onDeleteTask(id)
                            shouldShowDeleteSnackbar = true
                            closeModal()
                        },
                    )
                }
            }
            BoardHeader(
                title = project.title,
                doneRate = project.calculateDoneRate(),
                doneTasks = project.filterTasksbyStatus(Status.DONE).size,
                totalTasks = project.allTasksCount,
                onClickCreateTask = {
                    selectedTask = null
                    isShowModal = true
                },
            )
            TaskColumnSection(
                project = project,
                onMoveSnackBar = { shouldShowMoveSnackbar = true },
                onUpdateTaskStatus = onUpdateTaskStatus,
                onTaskClick = { task ->
                    selectedTask = task
                    isShowModal = true
                },
            )
        }
    }
}

//@Preview(showBackground = true, widthDp = 1000)
//@Composable
//private fun BoardPreview() {
//    val project = ProjectPreviewData().values.toMutableList()[0]
//    val profiles = ProfilePreviewData().values.toImmutableList()
//    MaterialTheme {
//        Board(
//            project = project,
//            profiles = profiles,
//            onCreateTask = {},
//            onUpdateTask = { _, _ -> },
//            onDeleteTask = {},
//            onUpdateTaskStatus = { _, _ -> },
//        )
//    }
//}
