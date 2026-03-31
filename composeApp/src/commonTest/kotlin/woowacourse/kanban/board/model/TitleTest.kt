package woowacourse.kanban.board.model

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import woowacourse.kanban.board.model.taskcard.Title

class TitleTest {

    @Test
    fun `Title의 value가 문자가 포함된 값이 입력되면 value를 가진 Title이 생성된다`() {
        val title = Title(value = "제목이에요")
        assertThat(title.value).isEqualTo("제목이에요")
    }

    @Test
    fun `Title의 value에 빈 값이 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            Title(value = "")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Title의 value에 공백으로만 이루어진 값이 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            Title(value = "     ")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `isValidTitle에 빈 값이 입력되면 false를 반환한다`() {
        assertFalse { Title.isTitleValid("") }
    }

    @Test
    fun `isValidTitle에 공백으로만 이루어진 값이 입력되면 false를 반환한다`() {
        assertFalse { Title.isTitleValid("         ") }
    }

    @Test
    fun `isValidTitle에 문자가 포함된 값이 입력되면 true를 반환한다`() {
        assertTrue { Title.isTitleValid(" 제목 ") }
    }
}
