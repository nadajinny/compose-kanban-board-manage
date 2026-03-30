package woowacourse.kanban.board.component.modal.input

class TextInputState(
    val value: String,
    val onChange: (String) -> Unit,
    val isError: Boolean = false,
)
