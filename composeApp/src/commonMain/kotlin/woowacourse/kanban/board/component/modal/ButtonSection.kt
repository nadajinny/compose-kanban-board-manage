package woowacourse.kanban.board.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.component.sample.ProfilePreviewData
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.Profile

@Composable
fun ButtonSection(
    state: Status,
    currentProfile: Profile,
    profiles: ImmutableList<Profile>,
    onStateClick: (Status) -> Unit,
    onProfileClick: (Profile) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = ComponentText.STATE_BUTTON_LABEL,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Gray20,
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            StateButton(currentState = state, myState = Status.TODO, onClick = { onStateClick(Status.TODO) })
            StateButton(currentState = state, myState = Status.PROGRESS, onClick = { onStateClick(Status.PROGRESS) })
            StateButton(currentState = state, myState = Status.DONE, onClick = { onStateClick(Status.DONE) })
        }
        Text(
            text = ComponentText.PROFILE_BUTTON_LABEL,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Gray20,
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            profiles.forEach { profile ->
                ProfileButton(
                    currentState = currentProfile,
                    myState = profile,
                    onClick = { onProfileClick(profile) }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ButtonSectionTodoStatusPreview() {
    val profiles = ProfilePreviewData().values.toImmutableList()
    ButtonSection(
        state = Status.TODO,
        currentProfile = profiles[0],
        profiles = profiles,
        onStateClick = { },
        onProfileClick = {},
    )
}
