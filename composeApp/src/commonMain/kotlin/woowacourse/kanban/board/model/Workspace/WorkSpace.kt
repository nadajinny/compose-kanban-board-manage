package woowacourse.kanban.board.model.Workspace

import kotlinx.collections.immutable.ImmutableList
import woowacourse.kanban.board.model.project.Project

data class WorkSpace(
    val projects: ImmutableList<Project>
)
