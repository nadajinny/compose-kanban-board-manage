package woowacourse.kanban.board.model

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import woowacourse.kanban.board.model.taskcard.Tag

class TagTest {

    @Test
    fun `Tag에 빈 값이 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            Tag("")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Tag에 공백으로만 이루어진 값이 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            Tag("    ")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Tag에 5자를 초과하는 값이 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            Tag("일이삼사오육")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `5자 이하의 값이 isValidValue에 입력되면 true를 반환한다`() {
        assertThat(Tag.isValidValue("일이삼사오"))
            .isTrue
    }

    @Test
    fun `빈 문자열이 isValidInput에 입력되면 false를 반환한다`() {
        assertThat(Tag.isValidInput(""))
            .isFalse
    }
}
