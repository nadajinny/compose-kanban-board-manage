package woowacourse.kanban.board.component

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlinx.collections.immutable.toImmutableList
import org.junit.Assert
import woowacourse.kanban.board.component.sample.ProjectPreviewData
import woowacourse.kanban.board.model.state.WorkSpaceState
import woowacourse.kanban.board.model.taskcard.Profile
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class WorkSpaceTest {

    @Test
    fun `사이트탭에 등록된 프로젝트의 타이틀이 모두 출력된다`() = runComposeUiTest {
    val projects = ProjectPreviewData().values.toImmutableList()
        val workSpace = WorkSpaceState(projects)
        val profiles = listOf(
            Profile("다이노", Res.drawable.profile),
            Profile("페임스", Res.drawable.profile)
        ).toImmutableList()
        setContent {
            WorkSpace(
                workSpaceState = workSpace,
                profiles = profiles
                )
        }
        onAllNodesWithText("Compose1").assertCountEquals(2)
        onNodeWithText("Compose2").assertIsDisplayed()
        onNodeWithText("Compose3너무너무길경우에는 말줄임표로 표시됩니다.").assertIsDisplayed()
    }
}