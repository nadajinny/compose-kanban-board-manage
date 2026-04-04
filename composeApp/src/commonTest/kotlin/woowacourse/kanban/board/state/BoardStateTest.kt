package woowacourse.kanban.board.state

import kotlin.test.Test
import kotlinx.collections.immutable.toImmutableList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import woowacourse.kanban.board.component.state.WorkSpaceStateHolder
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.Description
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.Tag
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.taskcard.TaskCard
import woowacourse.kanban.board.model.taskcard.Title
import woowacourse.kanban.board.model.workspace.WorkSpace

class BoardStateTest {
    private lateinit var workSpaceStateHolder: WorkSpaceStateHolder

    @Before
    fun setUp() {
        workSpaceStateHolder = WorkSpaceStateHolder(
            WorkSpace(
                listOf<Project>(
                    Project("Compose1", listOf<TaskCard>().toImmutableList()),
                    Project("Compose2", listOf<TaskCard>().toImmutableList()),
                    Project("Compose3너무너무긴문장은말줄임표로표시합니다", listOf<TaskCard>().toImmutableList()),
                ).toImmutableList()
            )
        )
    }

    @Test
    fun `Todo TaskCard를 추가하면 todoList에 저장된다`() {
        val data = createData(Status.TODO)
        workSpaceStateHolder.addTask(data)
        assertThat(workSpaceStateHolder.selectedProject?.filterTasksbyStatus(Status.TODO)).contains(data)
    }

    @Test
    fun `Progress TaskCard를 추가하면 progressList에 저장된다`() {
        val data = createData(Status.PROGRESS)
        workSpaceStateHolder.addTask(data)
        assertThat(workSpaceStateHolder.selectedProject?.filterTasksbyStatus(Status.PROGRESS)).contains(data)
    }

    @Test
    fun `Done TaskCard를 추가하면 doneList에 저장된다`() {
        val data = createData(Status.DONE)
        workSpaceStateHolder.addTask(data)
        assertThat(workSpaceStateHolder.selectedProject?.filterTasksbyStatus(Status.DONE)).contains(data)
    }

    @Test
    fun `4개 업무 중 2개를 완료했을 때 완료율은 50%로 계산된다`() {
        val task1 = createData(Status.DONE)
        val task2 = createData(Status.TODO)
        val task3 = createData(Status.TODO)

        workSpaceStateHolder.addTask(task1)
        workSpaceStateHolder.addTask(task1)
        workSpaceStateHolder.addTask(task2)
        workSpaceStateHolder.addTask(task3)

        assertThat(workSpaceStateHolder.selectedProject?.calculateDoneRate()).isEqualTo(0.50f)
    }

    @Test
    fun `진행 상태가 모두 다른 3개 업무가 등록되면 totalTasks는 3으로 계산된다`() {
        val task1 = createData(Status.TODO)
        val task2 = createData(Status.PROGRESS)
        val task3 = createData(Status.DONE)

        workSpaceStateHolder.addTask(task1)
        workSpaceStateHolder.addTask(task2)
        workSpaceStateHolder.addTask(task3)

        assertThat(workSpaceStateHolder.selectedProject?.allTasksCount).isEqualTo(3)
    }

    @Test
    fun `등록된 업무가 0개일 때 완료율은 0%으로 계산된다`() {
        assertThat(workSpaceStateHolder.selectedProject?.calculateDoneRate()).isEqualTo(0.0f)
    }

    @Test
    fun `3개 업무 중 0개를 완료했을 때 완료율은 0%으로 계산된다`() {
        val task1 = createData(Status.TODO)
        val task2 = createData(Status.TODO)
        val task3 = createData(Status.TODO)

        workSpaceStateHolder.addTask(task1)
        workSpaceStateHolder.addTask(task2)
        workSpaceStateHolder.addTask(task3)

        assertThat(workSpaceStateHolder.selectedProject?.calculateDoneRate()).isEqualTo(0.0f)
    }

    private fun createData(status: Status): TaskCard {
        return TaskCard(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = status,
            profile = Profile("다이노")
        )
    }
}
