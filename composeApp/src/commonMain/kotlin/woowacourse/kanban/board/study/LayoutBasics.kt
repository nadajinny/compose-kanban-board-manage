package woowacourse.kanban.board.study

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextExample() {
    Text(
        text = "Jetpack Compose Text 실습",
        color = Color.Blue,
        fontSize = 22.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
@Preview
fun ImageExample() {
    Image(
        Icons.Default.Favorite,
        contentDescription = "설명 텍스트 (접근성을 위해 중요!)",
    )
}

@Composable
fun ButtonExample() {
    Button(
        onClick = {
            println("버튼 클릭!")
        },
    ) {
        Text(text = "저장")
    }
}

@Composable
@Preview
fun ButtonExample2() {
    Button(
        onClick = {
            println("버튼 클릭!")
        },
    ) {
        Image(
            Icons.Default.Favorite,
            contentDescription = "버튼 예시",
        )
    }
}

@Composable
@Preview
fun ColumnExample() {
    Column {
        Text("첫 번째 아이템")
        Text("두 번째 아이템")
        Text("세 번째 아이템")
    }
}

@Composable
@Preview
fun RowExample() {
    Row {
        Text("첫 번째 아이템")
        Text("두 번째 아이템")
        Text("세 번째 아이템")
    }
}

@Composable
@Preview
fun HeartIconExample() {
    Button(
        onClick = {
            print("click")
        },
    ) {
        Row {
            Image(
                Icons.Default.Favorite,
                contentDescription = "버튼 & 이미지 & 텍스트",
            )
            Text("좋아요")
        }
    }
}

@Composable
@Preview
fun BoxExample() {
    Box(
        modifier = Modifier.size(120.dp),
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Blue)
                .align(Alignment.TopStart),
        )

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .align(Alignment.BottomEnd),
        )
    }
}

@Composable
@Preview
fun ColorBoxExample() {
    Box(
        modifier = Modifier.size(200.dp),
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Red)
                .align(Alignment.TopStart),
        )

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Yellow)
                .align(Alignment.Center),
        )

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Green)
                .align(Alignment.BottomEnd),
        )
    }
}

@Composable
fun CheckerScreen() {
    var checked by remember { mutableStateOf(true) }

    CheckerView(checked = checked) {
        checked = !checked
    }
}

@Composable
fun CheckerView(checked: Boolean, check: () -> Unit) {
    Column {
        Checkbox(
            checked = checked,
            onCheckedChange = { check() },
        )
        if (checked) Text(text = "체크됨!")
    }
}
