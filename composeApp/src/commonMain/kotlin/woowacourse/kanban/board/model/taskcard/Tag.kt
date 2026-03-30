package woowacourse.kanban.board.model.taskcard

import woowacourse.kanban.board.util.ErrorMessage

data class Tag(
    val value: String
) {

    init {
        require(isValidValue(value)) {
            if (value.isBlank()) ErrorMessage.TAG_EMPTY
            else ErrorMessage.tagTooLong(TAG_MAX_TEXT_LENGTH)
        }
    }

    companion object {
        private const val TAG_MAX_TEXT_LENGTH = 5

        fun isValidValue(value: String): Boolean =
            value.isNotBlank() && value.length <= TAG_MAX_TEXT_LENGTH

        fun isValidInput(value: String): Boolean =
            value.split(",")
                .map { it.trim() }
                .all(::isValidValue)

        fun parseAll(value: String): List<Tag> =
            value.split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .map { Tag(it) }
    }
}
