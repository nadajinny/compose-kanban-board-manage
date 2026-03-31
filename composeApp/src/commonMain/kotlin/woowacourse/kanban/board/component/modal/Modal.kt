package woowacourse.kanban.board.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.component.modal.input.TextInputState
import woowacourse.kanban.board.component.modal.section.ButtonSection
import woowacourse.kanban.board.component.modal.section.Footer
import woowacourse.kanban.board.component.modal.section.Header
import woowacourse.kanban.board.component.modal.section.TextInputSection
import woowacourse.kanban.board.component.modal.state.ModalState
import woowacourse.kanban.board.component.sample.ProfilePreviewData
import woowacourse.kanban.board.model.taskcard.Description
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.Tag
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.taskcard.TaskCardData
import woowacourse.kanban.board.model.taskcard.Title

@Composable
fun Modal(
    profiles: ImmutableList<Profile>,
    onClickClose: () -> Unit,
    onClickTaskCreate: (TaskCardData) -> Unit,
    modifier: Modifier = Modifier,
) {
    val modalState = remember { ModalState(profiles) }
    val titleInputState = TextInputState(
        value = modalState.title,
        onChange = { modalState.title = it },
        isError = modalState.isTitleValid.not(),
    )
    val descriptionInputState = TextInputState(
        value = modalState.description,
        onChange = { modalState.description = it },
    )
    val tagsInputState = TextInputState(
        value = modalState.tags,
        onChange = { modalState.tags = it },
        isError = modalState.isTagsValid.not(),
    )

    Card(
        modifier = modifier
            .width(800.dp)
            .padding(50.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Header(
                onClickClose = onClickClose,
            )
            HorizontalDivider()
            TextInputSection(
                titleInputState = titleInputState,
                descriptionInputState = descriptionInputState,
                tagsInputState = tagsInputState,
            )
            ButtonSection(
                state = modalState.status,
                currentProfile = modalState.profile,
                profiles = profiles,
                onStateClick = { modalState.status = it },
                onProfileClick = { modalState.profile = it },
            )
            Footer(
                onClickClose = onClickClose,
                onClickTaskCreate = {
                    val data = TaskCardData(
                        title = Title(value = modalState.title),
                        description = Description(value = modalState.description),
                        tags = Tags(Tag.parseAll(modalState.tags).toImmutableList()),
                        status = modalState.status,
                        profile = modalState.profile,
                    )
                    onClickTaskCreate(data)
                },
                isButtonEnabled = modalState.isTitleValid && modalState.isTagsValid,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ModalPreview() {
    val profiles = ProfilePreviewData().values.toImmutableList()
    Modal(
        profiles = profiles,
        onClickClose = {},
        onClickTaskCreate = {},
    )
}
