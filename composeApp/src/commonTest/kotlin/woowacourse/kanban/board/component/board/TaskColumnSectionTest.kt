package woowacourse.kanban.board.component.board

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlin.test.Test
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.Description
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.Tag
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.taskcard.TaskCardData
import woowacourse.kanban.board.model.taskcard.Title

@OptIn(ExperimentalTestApi::class)
class TaskColumnSectionTest {

    @Test
    fun `todoTasks에 등록된 태스크가 3개면 3이 출력된다`() = runComposeUiTest {
        val data1 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val data2 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val data3 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val todoTasks = listOf(data1, data2, data3)
        val project = Project(
            title = "title",
            initialTasks = todoTasks.toImmutableList()
        )
        setContent {
            TaskColumnSection(
                project = project,
                onMoveSnackBar = {}
            )
        }

        onNodeWithText("3").assertIsDisplayed()
    }

    @Test
    fun `progressTasks에 등록된 태스크가 5개면 5가 출력된다`() = runComposeUiTest {
        val data1 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val data2 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val data3 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val data4 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val data5 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val progressTasks = listOf(data1, data2, data3, data4, data5)
        val project = Project(
            title = "title",
            initialTasks = progressTasks.toImmutableList()
        )
        setContent {
            TaskColumnSection(
                project = project,
                onMoveSnackBar = {}
            )
        }

        onNodeWithText("5").assertIsDisplayed()
    }

    @Test
    fun `doneTasks에 등록된 태스크가 4개면 4가 출력된다`() = runComposeUiTest {
        val data1 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.DONE,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val data2 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.DONE,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val data3 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.DONE,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val data4 = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트")).toImmutableList()),
            status = Status.DONE,
            profile = Profile("다이노", Res.drawable.profile)
        )
        val doneTasks = listOf(data1, data2, data3, data4)
        val project = Project(
            title = "title",
            initialTasks = doneTasks.toImmutableList()
        )
        setContent {
            TaskColumnSection(
                project = project,
                onMoveSnackBar = {}
            )
        }

        onNodeWithText("4").assertIsDisplayed()
    }
}
