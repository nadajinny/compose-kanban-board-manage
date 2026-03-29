package woowacourse.kanban.board.model.taskcard

import kotlinx.collections.immutable.ImmutableList
import woowacourse.kanban.board.util.ErrorMessage

data class Tags(val value: ImmutableList<Tag>) {

    init {
        require(value.size <= MAX_TAGS) { ErrorMessage.tagCountExceeded(MAX_TAGS) }
    }

    companion object {
        private const val MAX_TAGS = 5

        fun isTagsValid(value: List<Tag>): Boolean {
            return value.size <= MAX_TAGS
        }
    }
}
