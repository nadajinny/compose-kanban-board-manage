package woowacourse.kanban.board.util

object ErrorMessage {
    const val TITLE_EMPTY = "[ERROR] 제목은 빈 값일 수 없습니다."
    const val TAG_EMPTY = "[ERROR] 제목은 빈 값일 수 없습니다."
    fun tagTooLong(maxLength: Int): String {
        return "[ERROR] 태그 텍스트는 ${maxLength}글자 이하여야 합니다."
    }

    fun tagCountExceeded(maxCount: Int): String {
        return "[ERROR] 태그 개수는 ${maxCount}개 이하여야합니다."
    }
}
