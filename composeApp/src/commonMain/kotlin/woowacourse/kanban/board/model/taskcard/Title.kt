package woowacourse.kanban.board.model.taskcard

import woowacourse.kanban.board.util.ErrorMessage

@JvmInline
value class Title(val value: String) {

    init {
        require(value.isNotBlank()) { ErrorMessage.TITLE_EMPTY }
    }

    companion object {
        fun isTitleValid(value: String): Boolean = value.isNotBlank()
    }
}
