package woowacourse.kanban.board.component.modal

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.component.sample.ProjectPreviewData
import woowacourse.kanban.board.model.taskcard.Profile

@OptIn(ExperimentalTestApi::class)
class ModalTest {

    private lateinit var profiles: ImmutableList<Profile>

    @Before
    fun setUp() {
        profiles = listOf(
            Profile("다이노", Res.drawable.profile),
            Profile("페임스", Res.drawable.profile)
        ).toImmutableList()
    }

    @Test
    fun `초기 상태에서 생성 버튼이 비활성화된다`() = runComposeUiTest {
        setContent {
            Modal(
                profiles = profiles,
                onClickClose = {},
                onClickTaskCreate = {},
            )
        }
        onNodeWithText(ComponentText.CREATE_BUTTON).assertIsNotEnabled()
    }

    @Test
    fun `제목을 입력하면 생성 버튼이 활성화된다`() = runComposeUiTest {
        setContent {
            Modal(
                profiles = profiles,
                onClickClose = {},
                onClickTaskCreate = {},
            )
        }
        onNodeWithText(ComponentText.TITLE_PLACEHOLDER).performTextInput("하이")
        onNodeWithText(ComponentText.CREATE_BUTTON).assertIsEnabled()
    }

    @Test
    fun `제목을 입력하고 태그에 ,,을 연속으로 입력하면 생성 버튼이 비활성화된다`() = runComposeUiTest {
        setContent {
            Modal(
                profiles = profiles,
                onClickClose = {},
                onClickTaskCreate = {},
            )
        }
        onNodeWithText(ComponentText.TITLE_PLACEHOLDER).performTextInput("하이")
        onNodeWithText(ComponentText.CREATE_BUTTON).assertIsEnabled()
        onNodeWithText(ComponentText.TAG_PLACEHOLDER).performTextInput("태그,,태그2")
        onNodeWithText(ComponentText.CREATE_BUTTON).assertIsNotEnabled()
    }

    @Test
    fun `제목을 입력한 뒤 모두 지우면 생성 버튼이 비활성화된다`() = runComposeUiTest {
        setContent {
            Modal(
                profiles = profiles,
                onClickClose = {},
                onClickTaskCreate = {},
            )
        }
        onAllNodes(isEditable())[0].performTextInput("하이")
        onAllNodes(isEditable())[0].performTextClearance()
        waitForIdle()
        onNodeWithText(ComponentText.CREATE_BUTTON).assertIsNotEnabled()
    }

    @Test
    fun `Modal 헤더의 닫기 버튼을 누르면 onClickClose가 호출된다`() = runComposeUiTest {
        var close = false
        setContent {
            Modal(
                profiles = profiles,
                onClickClose = { close = true },
                onClickTaskCreate = {},
            )
        }
        onNodeWithContentDescription("닫기").performClick()
        assertThat(close).isTrue()
    }

    @Test
    fun `취소 버튼을 누르면 onClickClose가 호출된다`() = runComposeUiTest {
        var close = false
        setContent {
            Modal(
                profiles = profiles,
                onClickClose = { close = true },
                onClickTaskCreate = {},
            )
        }
        onNodeWithText(ComponentText.CANCEL_BUTTON).performSemanticsAction(SemanticsActions.OnClick)
        assertThat(close).isTrue()
    }

    @Test
    fun `제목을 입력하고 생성 버튼을 누르면 onClickTaskCreate가 호출된다`() = runComposeUiTest {
        var create = false
        setContent {
            Modal(
                profiles = profiles,
                onClickClose = { },
                onClickTaskCreate = { create = true },
            )
        }
        onAllNodes(isEditable())[0].performTextInput("하이")
        onNodeWithText(ComponentText.CREATE_BUTTON).performSemanticsAction(SemanticsActions.OnClick)
        assertThat(create).isTrue()
    }
}
