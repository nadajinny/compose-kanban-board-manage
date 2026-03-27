package woowacourse.kanban.board.component.taskcard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlin.test.Test
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.model.taskcard.Description
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.Tag
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.taskcard.TaskCardData
import woowacourse.kanban.board.model.taskcard.Title

@OptIn(ExperimentalTestApi::class)
class TaskCardTest {

    @Test
    fun `모든 필드가 있는 카드 - 제목, 설명, 태그, 닉네임 출력`() = runComposeUiTest {
        val tags = listOf("컴포넌트", "성능")
        val taskCardData = TaskCardData(
            title = Title("LazyColumn 컴포넌트 구현"),
            description = Description("세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다."),
            tags = Tags(listOf(Tag("컴포넌트"), Tag("성능")).toImmutableList()),
            status = Status.PROGRESS,
            profile = Profile("다이노", Res.drawable.profile),
        )
        setContent {
            TaskCard(data = taskCardData)
        }

        onNodeWithText("LazyColumn 컴포넌트 구현").assertExists()
        onNodeWithText("세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.").assertExists()
        tags.forEach { tag ->
            onNodeWithText(tag).assertExists()
        }
        onNodeWithText("다이노").assertExists()
    }
}
