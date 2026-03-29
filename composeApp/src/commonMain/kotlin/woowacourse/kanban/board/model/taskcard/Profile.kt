package woowacourse.kanban.board.model.taskcard

import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.DrawableResource

data class Profile(
    val nickname: String,
    val icon: DrawableResource = Res.drawable.profile,
)
