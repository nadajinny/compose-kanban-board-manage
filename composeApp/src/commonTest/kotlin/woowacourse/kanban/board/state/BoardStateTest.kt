package woowacourse.kanban.board.state

import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlin.test.Test
import kotlinx.collections.immutable.toImmutableList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.state.WorkSpaceState
import woowacourse.kanban.board.model.taskcard.Description
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.Tag
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.taskcard.TaskCardData
import woowacourse.kanban.board.model.taskcard.Title

class ProjectTest {
    private lateinit var workSpace: WorkSpaceState

    @Before
    fun setUp() {
        workSpace = WorkSpaceState(
            listOf<Project>(
                Project("Compose1", listOf<TaskCardData>().toImmutableList()),
                Project("Compose2", listOf<TaskCardData>().toImmutableList()),
                Project("Compose3너무너무긴문장은말줄임표로표시합니다", listOf<TaskCardData>().toImmutableList()),
            ).toImmutableList()
        )
    }

    @Test
    fun `Todo TaskCardData를 추가하면 todoList에 저장된다`() {
        val data = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            profile = Profile("다이노", Res.drawable.profile)
        )
        workSpace.projects.first().addCard(data)
        assertThat(workSpace.projects.first().todoTasks).contains(data)
    }

    @Test
    fun `Progress TaskCardData를 추가하면 progressList에 저장된다`() {
        val data = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            profile = Profile("다이노", Res.drawable.profile)
        )
        workSpace.projects.first().addCard(data)
        assertThat(workSpace.projects.first().progressTasks).contains(data)
    }

    @Test
    fun `Done TaskCardData를 추가하면 doneList에 저장된다`() {
        val data = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.DONE,
            profile = Profile("다이노", Res.drawable.profile)
        )
        workSpace.projects.first().addCard(data)
        assertThat(workSpace.projects.first().doneTasks).contains(data)
    }

    @Test
    fun `4개 업무 중 2개를 완료했을 때 완료율은 50%로 계산된다`() {
        val task1 = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.DONE,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val task2 = TaskCardData(
            title = Title(value = "업무2"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val task3 = TaskCardData(
            title = Title(value = "업무3"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            profile = Profile("다이노", Res.drawable.profile)
        )

        workSpace.projects.first().addCard(task1)
        workSpace.projects.first().addCard(task1)
        workSpace.projects.first().addCard(task2)
        workSpace.projects.first().addCard(task3)

        assertThat(workSpace.projects.first().calculateDoneRate()).isEqualTo(0.50f)
    }

    @Test
    fun `진행 상태가 모두 다른 3개 업무가 등록되면 totalTasks는 3으로 계산된다`() {
        val task1 = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val task2 = TaskCardData(
            title = Title(value = "업무2"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.DONE,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val task3 = TaskCardData(
            title = Title(value = "업무3"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            profile = Profile("다이노", Res.drawable.profile)
        )

        workSpace.projects.first().addCard(task1)
        workSpace.projects.first().addCard(task2)
        workSpace.projects.first().addCard(task3)

        assertThat(workSpace.projects.first().allTasksCount).isEqualTo(3)
    }

    @Test
    fun `등록된 업무가 0개일 때 완료율은 0%으로 계산된다`() {
        assertThat(workSpace.projects.first().calculateDoneRate()).isEqualTo(0.0f)
    }

    @Test
    fun `3개 업무 중 0개를 완료했을 때 완료율은 0%으로 계산된다`() {
        val task1 = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val task2 = TaskCardData(
            title = Title(value = "업무2"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val task3 = TaskCardData(
            title = Title(value = "업무3"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            profile = Profile("다이노", Res.drawable.profile)
        )

        workSpace.projects.first().addCard(task1)
        workSpace.projects.first().addCard(task2)
        workSpace.projects.first().addCard(task3)

        assertThat(workSpace.projects.first().calculateDoneRate()).isEqualTo(0.0f)
    }
}
