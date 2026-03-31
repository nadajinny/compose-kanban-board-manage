package woowacourse.kanban.board.component.taskcard

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class ScriptTest {

    @Test
    fun `Script에 빈 값이 들어오면 Script 컴포넌트가 출력되지 않는다`() = runComposeUiTest {
        setContent {
            Description(
                description = "",
                modifier = Modifier.testTag("script"),
            )
        }
        onNodeWithTag("script").assertDoesNotExist()
    }

    @Test
    fun `Script에 빈 값이 들어오지 않으면 Script 컴포넌트가 출력된다`() = runComposeUiTest {
        setContent {
            Description(
                description = "script",
                modifier = Modifier.testTag("script"),
            )
        }
        onNodeWithTag("script").assertExists()
    }
}
