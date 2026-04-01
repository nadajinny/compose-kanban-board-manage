package woowacourse.kanban.board.model.taskcard

import java.util.UUID

class TaskCard(
    val id: String = UUID.randomUUID().toString(),
    val title: Title,
    val description: Description,
    val tags: Tags,
    val status: Status,
    val profile: Profile,
)
