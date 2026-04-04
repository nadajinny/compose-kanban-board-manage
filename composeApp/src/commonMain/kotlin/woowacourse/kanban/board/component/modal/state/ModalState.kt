package woowacourse.kanban.board.component.modal.state

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.collections.immutable.ImmutableList
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.taskcard.TaskCard
import woowacourse.kanban.board.model.taskcard.Title

class ModalState(
    profiles: ImmutableList<Profile>,
    initialTask: TaskCard?,
) {
    var title by mutableStateOf(initialTask?.title?.value ?: "")
    var description by mutableStateOf(initialTask?.description?.value ?: "")
    var tags by mutableStateOf(initialTask?.tags?.value?.joinToString(",") { it.value } ?: "")
    var status by mutableStateOf(initialTask?.status ?: Status.TODO)
    var profile by mutableStateOf(initialTask?.profile ?: profiles.first())

    val isTitleValid by derivedStateOf { Title.isTitleValid(title) }
    val isTagsValid by derivedStateOf { Tags.isValidInput(tags) }
    val isSubmittable by derivedStateOf { isTitleValid && isTagsValid }
}
