package woowacourse.kanban.board.component

object ComponentText {
    const val HEADER_LABEL = "새 태스크 생성"
    const val TITLE_LABEL = "제목 *"
    const val TITLE_PLACEHOLDER = "태스크 제목을 입력하세요"
    const val TITLE_ERROR = "제목이 비어있으면 안됩니다."
    const val DESCRIPTION_LABEL = "설명"
    const val DESCRIPTION_PLACEHOLDER = "태스크에 대한 자세한 설명을 입력하세요"
    const val TAG_LABEL = "태그"
    const val TAG_PLACEHOLDER = "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)"
    const val TAG_SUPPORTING = "5자 이내에 태그를 최대 5개까지 등록할 수 있습니다."
    const val TAG_ERROR = "태그 형식이 올바르지 않습니다."

    const val STATE_BUTTON_LABEL = "상태"
    const val STATE_BUTTON_TODO = "To Do"
    const val STATE_BUTTON_PROGRESS = "In Progress"
    const val STATE_BUTTON_DONE = "Done"
    const val PROFILE_BUTTON_LABEL = "담당자"
    const val CANCEL_BUTTON = "취소"
    const val CREATE_BUTTON = "생성"

    const val BOARD_HEADER_TITLE = "Compose Desktop 칸반 보드"
    const val BOARD_HEADER_PROGRESS = "완료율:"
    const val BOARD_TASK_CREATE_BUTTON = "새 태스크 생성"
    const val BOARD_TASK_CREATE_SNACKBAR = "새로운 태스크가 추가되었습니다."
    const val BOARD_TASK_MOVE_SNACKBAR = "태스크가 이동되었습니다."
}
