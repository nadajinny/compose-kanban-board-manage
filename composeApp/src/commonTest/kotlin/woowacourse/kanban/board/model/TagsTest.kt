package woowacourse.kanban.board.model

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import woowacourse.kanban.board.model.taskcard.Tag
import woowacourse.kanban.board.model.taskcard.Tags

class TagsTest {

    @Test
    fun `isValidTags에 최대 태그수 이하의 태그가 입력되면 true를 반환한다`() {
        val tags = listOf(Tag("태그1"), Tag("태그2"), Tag("태그3"))
        assertTrue { Tags.isTagsValid(tags) }
    }

    @Test
    fun `isValidTags에 최대 태그수가 1개 이상일 때, 1개 태그만 입력되면 true를 반환한다`() {
        assertTrue { Tags.isTagsValid(listOf(Tag(value = "태그1"))) }
    }

    @Test
    fun `isValidTags에 최대 태그 개수를 초과하는 태그가 입력되면 false를 반환한다`() {
        assertFalse { Tags.isTagsValid(listOf(Tag("태그1"), Tag("태그1"), Tag("태그1"), Tag("태그1"), Tag("태그1"), Tag("태그1"))) }
    }

    @Test
    fun `isValidTags에 태그가 입력되지 않으면 true를 반환한다`() {
        assertTrue { Tags.isTagsValid(emptyList()) }
    }

    @Test
    fun `빈 태그 입력값이 isValidInput에 입력되면 true를 반환한다`() {
        assertTrue { Tags.isValidInput("") }
    }
}
