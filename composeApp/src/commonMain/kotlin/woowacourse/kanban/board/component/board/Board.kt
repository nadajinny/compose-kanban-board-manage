package woowacourse.kanban.board.component.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.Gray80
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.component.modal.Modal
import woowacourse.kanban.board.component.sample.ProfilePreviewData
import woowacourse.kanban.board.component.sample.ProjectPreviewData
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskCardData

@Composable
fun Board(
    project: Project,
    profiles: ImmutableList<Profile>,
    onCreateTask: (TaskCardData) -> Unit,
    onUpdateTaskStatus: (String, Status) -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    var shouldShowSnackbar by remember { mutableStateOf(false) }
    var shouldShowMoveSnackbar by remember { mutableStateOf(false) }
    var isShowModal by remember { mutableStateOf(false) }

    LaunchedEffect(shouldShowSnackbar) {
        if (shouldShowSnackbar) {
            snackbarHostState.showSnackbar(
                message = ComponentText.BOARD_TASK_CREATE_SNACKBAR,
                withDismissAction = true,
            )
            shouldShowSnackbar = false
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
                    onDismissRequest = { isShowModal = false },
                    properties = DialogProperties(
                        usePlatformDefaultWidth = false,
                    ),
                ) {
                    Modal(
                        profiles = profiles,
                        onClickClose = { isShowModal = false },
                        onClickTaskCreate = { task ->
                            onCreateTask(task)
                            shouldShowSnackbar = true
                            isShowModal = false
                        },
                    )
                }
            }
            BoardHeader(
                title = project.title,
                doneRate = project.calculateDoneRate(),
                doneTasks = project.filterTasksbyStatus(Status.DONE).size,
                totalTasks = project.allTasksCount,
                onClickCreateTask = { isShowModal = isShowModal.not() },
            )
            TaskColumnSection(
                project = project,
                onMoveSnackBar = { shouldShowMoveSnackbar = true },
                onUpdateTaskStatus = onUpdateTaskStatus,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
private fun BoardPreview() {
    val project = ProjectPreviewData().values.toMutableList()[0]
    val profiles = ProfilePreviewData().values.toImmutableList()
    MaterialTheme {
        Board(
            project = project,
            profiles = profiles,
            onCreateTask = {},
            onUpdateTaskStatus = { _, _ -> },
        )
    }
}
