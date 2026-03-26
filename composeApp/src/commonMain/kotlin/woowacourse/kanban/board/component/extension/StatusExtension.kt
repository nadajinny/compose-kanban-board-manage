package woowacourse.kanban.board.component.extension

import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.ImmutableList
import woowacourse.kanban.board.Blue60
import woowacourse.kanban.board.Blue70
import woowacourse.kanban.board.Blue90
import woowacourse.kanban.board.Green60
import woowacourse.kanban.board.Green70
import woowacourse.kanban.board.Green90
import woowacourse.kanban.board.Yellow60
import woowacourse.kanban.board.Yellow70
import woowacourse.kanban.board.Yellow90
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskCardData

fun Status.toText(): String = when (this) {
    Status.TODO -> ComponentText.STATE_BUTTON_TODO
    Status.PROGRESS -> ComponentText.STATE_BUTTON_PROGRESS
    Status.DONE -> ComponentText.STATE_BUTTON_DONE
}

fun Status.toBackgroundColor(): Color = when (this) {
    Status.TODO -> Blue90
    Status.PROGRESS -> Yellow90
    Status.DONE -> Green90
}

fun Status.toBorderColor(): Color = when (this) {
    Status.TODO -> Blue70
    Status.PROGRESS -> Yellow70
    Status.DONE -> Green70
}

fun Status.toHeaderColor(): Color = when (this) {
    Status.TODO -> Blue60
    Status.PROGRESS -> Yellow60
    Status.DONE -> Green60
}

fun Status.toFilterTask(status: Status, project: Project): ImmutableList<TaskCardData> = when (this) {
    Status.TODO -> project.todoTasks
    Status.PROGRESS -> project.progressTasks
    Status.DONE -> project.doneTasks
}
