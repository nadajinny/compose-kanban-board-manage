package woowacourse.kanban.board.component.modal.action

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.assertj.core.api.Assertions.assertThat
import woowacourse.kanban.board.component.modal.state.ModalState
import woowacourse.kanban.board.component.util.ComponentText
import woowacourse.kanban.board.model.taskcard.Description
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.Tag
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.taskcard.TaskCard
import woowacourse.kanban.board.model.taskcard.Title

class ModalButtonActionFactoryTest {
    private lateinit var profiles: ImmutableList<Profile>

    @BeforeTest
    fun setUp() {
        profiles = listOf(
            Profile("다이노"),
            Profile("페임스"),
        ).toImmutableList()
    }

    @Test
    fun `상태 변경 액션은 전이할 수 없는 상태 변경을 거절한다`() {
        val modalState = ModalState(profiles, createTask(status = Status.DONE))
        var snackbarMessage = ""

        createFactory(
            modalState = modalState,
            initialTask = createTask(status = Status.DONE),
            onShowSnackbar = { snackbarMessage = it },
        ).createStatusChangeAction(Status.REVIEW).execute()

        assertThat(snackbarMessage).isEqualTo(ComponentText.BOARD_TASK_INVALID_STATUS_SNACKBAR)
        assertThat(modalState.status).isEqualTo(Status.DONE)
    }

    @Test
    fun `상태 변경 액션은 담당자가 필요한 상태 변경 전에 담당자를 검증한다`() {
        val initialTask = createTask(status = Status.TODO, profile = Profile.NONE)
        val modalState = ModalState(profiles, initialTask)
        var snackbarMessage = ""

        createFactory(
            modalState = modalState,
            initialTask = initialTask,
            onShowSnackbar = { snackbarMessage = it },
        ).createStatusChangeAction(Status.PROGRESS).execute()

        assertThat(snackbarMessage).isEqualTo(ComponentText.BOARD_TASK_REQUIRE_PROFILE_SNACKBAR)
        assertThat(modalState.status).isEqualTo(Status.TODO)
    }

    @Test
    fun `생성 액션은 입력이 유효할 때만 태스크를 생성한다`() {
        val modalState = ModalState(profiles, null)
        var createdTask: TaskCard? = null

        createFactory(
            modalState = modalState,
            initialTask = null,
            onCreateTask = { createdTask = it },
        ).createTaskCreateAction().execute()

        assertThat(createdTask).isNull()

        modalState.title = "업무"

        createFactory(
            modalState = modalState,
            initialTask = null,
            onCreateTask = { createdTask = it },
        ).createTaskCreateAction().execute()

        assertThat(createdTask?.title?.value).isEqualTo("업무")
    }

    @Test
    fun `삭제 액션은 삭제 불가능한 상태를 거절한다`() {
        val initialTask = createTask(status = Status.REVIEW)
        val modalState = ModalState(profiles, initialTask)
        var snackbarMessage = ""
        var deletedTaskId: String? = null

        createFactory(
            modalState = modalState,
            initialTask = initialTask,
            onShowSnackbar = { snackbarMessage = it },
            onDeleteTask = { deletedTaskId = it },
        ).createTaskDeleteAction().execute()

        assertThat(snackbarMessage).isEqualTo(ComponentText.BOARD_TASK_DELETE_DENIED_SNACKBAR)
        assertThat(deletedTaskId).isNull()
    }

    private fun createFactory(
        modalState: ModalState,
        initialTask: TaskCard?,
        onShowSnackbar: (String) -> Unit = {},
        onCreateTask: (TaskCard) -> Unit = {},
        onUpdateTask: (String, TaskCard) -> Unit = { _, _ -> },
        onDeleteTask: (String) -> Unit = {},
    ): ModalButtonActionFactory {
        return ModalButtonActionFactory(
            modalState = modalState,
            initialTask = initialTask,
            buildTaskCard = {
                TaskCard(
                    title = Title(modalState.title),
                    description = Description(modalState.description),
                    tags = Tags(Tag.parseAll(modalState.tags).toImmutableList()),
                    status = modalState.status,
                    profile = modalState.profile,
                )
            },
            onShowSnackbar = onShowSnackbar,
            onCreateTask = onCreateTask,
            onUpdateTask = onUpdateTask,
            onDeleteTask = onDeleteTask,
        )
    }

    private fun createTask(
        title: String = "업무1",
        status: Status = Status.TODO,
        profile: Profile = profiles.first(),
    ): TaskCard {
        return TaskCard(
            title = Title(title),
            description = Description("설명"),
            tags = Tags(listOf<Tag>().toImmutableList()),
            status = status,
            profile = profile,
        )
    }
}
