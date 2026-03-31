package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.component.WorkSpace
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.Workspace.WorkSpace as WorkSpaceModel
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.TaskCardData

@Composable
fun App() {
    val workSpace = remember {
        WorkSpaceModel(
            listOf(
                Project("Compose1", listOf<TaskCardData>().toImmutableList()),
                Project("Compose2", listOf<TaskCardData>().toImmutableList()),
                Project("Compose3너무너무긴문장은말줄임표로표시합니다", listOf<TaskCardData>().toImmutableList()),
            ).toImmutableList()
        )
    }

    val profiles = remember {
        listOf(
            Profile("다이노"),
            Profile("페임스")
        ).toImmutableList()
    }

    WorkSpace(
        workSpace = workSpace,
        profiles = profiles,
    )
}
