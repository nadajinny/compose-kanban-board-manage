package woowacourse.kanban.board.component.modal.state

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.collections.immutable.ImmutableList
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.taskcard.Title

class ModalState(
    profiles: ImmutableList<Profile>,
) {
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var tags by mutableStateOf("")
    var status by mutableStateOf(Status.TODO)
    var profile by mutableStateOf(profiles.first())

    val isTitleValid by derivedStateOf { Title.isTitleValid(title) }
    val isTagsValid by derivedStateOf { Tags.isValidInput(tags) }
}
