package woowacourse.kanban.board.model.state

import kotlinx.collections.immutable.ImmutableList
import woowacourse.kanban.board.model.project.Project

data class WorkSpace(
    val projects: ImmutableList<Project>
)
