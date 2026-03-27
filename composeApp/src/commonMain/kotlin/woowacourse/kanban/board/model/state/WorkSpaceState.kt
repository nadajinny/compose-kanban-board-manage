package woowacourse.kanban.board.model.state

import kotlinx.collections.immutable.ImmutableList
import woowacourse.kanban.board.model.project.Project

data class WorkSpaceState(
    val projects: ImmutableList<Project>
)
