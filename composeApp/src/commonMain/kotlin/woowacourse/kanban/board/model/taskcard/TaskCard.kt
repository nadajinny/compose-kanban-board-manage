package woowacourse.kanban.board.model.taskcard

import java.util.UUID

class TaskCard(
    val id: String = UUID.randomUUID().toString(),
    val title: Title,
    val description: Description,
    val tags: Tags,
    val status: Status,
    val profile: Profile,
) {
    fun changeStatus(afterStatus: Status): TaskCard {
        return TaskCard(
            id = id,
            title = title,
            description = description,
            tags = tags,
            status = afterStatus,
            profile = profile
        )
    }
}
