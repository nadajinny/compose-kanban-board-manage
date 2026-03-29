package woowacourse.kanban.board.model.taskcard

import woowacourse.kanban.board.util.ErrorMessage

data class Tag(
    val value: String
) {

    init {
        require(value.isNotBlank()) { ErrorMessage.TAG_EMPTY }
        require(value.length <= TAG_MAX_TEXT_LENGTH) { ErrorMessage.tagTooLong(TAG_MAX_TEXT_LENGTH) }
    }

    companion object {
        private const val TAG_MAX_TEXT_LENGTH = 5

        fun isTagValid(value: String): Boolean {
            if (value.isEmpty()) return true
            val splitTags = value.split(",").map { it.trim() }
            return splitTags.all { it.isNotEmpty() && it.length <= TAG_MAX_TEXT_LENGTH }
        }

        fun extractedTags(value: String): List<Tag> =
            value.split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .map { Tag(it) }
    }
}
