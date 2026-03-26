package woowacourse.kanban.board.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.Blue80
import woowacourse.kanban.board.Gray10
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.Gray40
import woowacourse.kanban.board.Purple50
import woowacourse.kanban.board.component.board.Board
import woowacourse.kanban.board.component.sample.ProfilePreviewData
import woowacourse.kanban.board.component.sample.ProjectPreviewData
import woowacourse.kanban.board.model.state.WorkSpaceState
import woowacourse.kanban.board.model.taskcard.Profile
@Composable
fun WorkSpace(
    workSpaceState: WorkSpaceState,
    profiles: ImmutableList<Profile>,
    modifier: Modifier = Modifier
) {
    var selectedProject by remember { mutableStateOf(workSpaceState.projects[0]) }
    Row(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(255.dp)
                .background(Color.White),
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "프로젝트",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Gray10
                    ),
                )
                Text(
                    text = "4주차 미션 보드",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Gray40
                    ),
                )
            }
            HorizontalDivider()
            workSpaceState.projects.forEach { project ->
                val backgroundColor =
                    if (project == selectedProject) Blue80
                    else Color.Transparent
                val textColor =
                    if (project == selectedProject) Purple50
                    else Gray20
                Button(
                    onClick = { selectedProject = project },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = backgroundColor,
                        contentColor = textColor
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        hoveredElevation = 0.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = project.title,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                            ),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
            }
        }
        Board(
            project = selectedProject,
            profiles = profiles
        )
    }
}

@Preview(showBackground = true, widthDp = 1500)
@Composable
private fun WorkSpacePreview() {
    val workSpace = WorkSpaceState(
        ProjectPreviewData().values.toImmutableList()
    )
    val profiles = ProfilePreviewData().values.toImmutableList()
    MaterialTheme {
        WorkSpace(workSpace, profiles)
    }
}
