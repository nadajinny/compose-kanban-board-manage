package woowacourse.kanban.board.component.modal.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.Blue50
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.component.ComponentText

@Composable
fun Footer(
    onClickClose: () -> Unit,
    onClickTaskCreate: () -> Unit,
    isButtonEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
    ) {
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            FooterButton(
                containerColor = Color.Transparent,
                contentColor = Gray20,
                text = ComponentText.CANCEL_BUTTON,
                onClick = onClickClose,
            )
            Spacer(modifier = Modifier.width(12.dp))
            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = ComponentText.CREATE_BUTTON,
                onClick = onClickTaskCreate,
            )
        }
    }
}

@Composable
private fun FooterButton(
    containerColor: Color,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    contentColor: Color = Color.Unspecified,
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FooterPreview() {
    Footer(
        onClickClose = {},
        onClickTaskCreate = {},
        isButtonEnabled = true,
    )
}
