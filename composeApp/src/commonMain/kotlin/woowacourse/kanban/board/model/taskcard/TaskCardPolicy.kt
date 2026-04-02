package woowacourse.kanban.board.model.taskcard

object TaskCardPolicy {
    fun canDelete(status: Status) : Boolean {
        return when(status) {
            Status.REVIEW, Status.DONE -> false
            else -> true
        }
    }

    fun requireProfile(status: Status): Boolean {
        return when(status) {
            Status.TODO -> false
            else -> true
        }
    }


}