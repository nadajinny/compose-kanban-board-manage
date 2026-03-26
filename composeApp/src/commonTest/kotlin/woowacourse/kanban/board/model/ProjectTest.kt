package woowacourse.kanban.board.model

import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlinx.collections.immutable.immutableListOf
import org.assertj.core.api.Assertions.assertThat
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.TaskCardData
import kotlin.test.Test
import woowacourse.kanban.board.model.taskcard.Title
import woowacourse.kanban.board.model.taskcard.Description
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.taskcard.Tag
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.Profile
import kotlin.test.assertTrue


class ProjectTest {
    @Test
    fun `입력한 id를 가진 태스크 카드가 변경값으로 입력한 status로 변경된다`() {
        val project = Project(
            title = "테스트 프로젝트",
            initialTasks = immutableListOf<TaskCardData>(
                TaskCardData(
                    id = "테스트",
                    title = Title("제목"),
                    description = Description("설명"),
                    tags = Tags(immutableListOf<Tag>(Tag("태그1"),Tag("태그1"))),
                    status = Status.TODO,
                    profile = Profile("다이노",Res.drawable.profile),
                )
            )
        )
        project.updateTaskStatus("테스트", Status.PROGRESS)
        assertThat(project.todoTasks.size).isEqualTo(0)
        assertThat(project.progressTasks.size).isEqualTo(1)
    }

    @Test
    fun `찾고자 하는 태스크 카드의 id값을 넣었을 때 해당 id 값을 가진 TaskCardData를 찾을 수 있다`() {
        val task = TaskCardData(
            id = "테스트",
            title = Title("제목"),
            description = Description("설명"),
            tags = Tags(immutableListOf<Tag>(Tag("태그1"),Tag("태그1"))),
            status = Status.TODO,
            profile = Profile("다이노",Res.drawable.profile),
        )
        val project = Project(
            title = "테스트 프로젝트",
            initialTasks = immutableListOf<TaskCardData>(
                task
            )
        )
        assertTrue { project.findTaskById("테스트") == task }
    }
}