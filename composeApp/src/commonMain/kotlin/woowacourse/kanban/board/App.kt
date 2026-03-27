package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.component.WorkSpace
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.state.WorkSpaceState
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.TaskCardData

@Composable
fun App() {
    val workSpace = WorkSpaceState(
        listOf(
            Project("Compose1", listOf<TaskCardData>().toImmutableList()),
            Project("Compose2", listOf<TaskCardData>().toImmutableList()),
            Project("Compose3너무너무긴문장은말줄임표로표시합니다", listOf<TaskCardData>().toImmutableList()),
        ).toImmutableList()
    )

    val profiles = listOf(
        Profile("다이노", Res.drawable.profile),
        Profile("페임스", Res.drawable.profile)
    ).toImmutableList()

    WorkSpace(
        workSpaceState = workSpace,
        profiles = profiles,
    )
}
