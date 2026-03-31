package woowacourse.kanban.board.study

import androidx.compose.foundation.lazy.LazyListScope
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

data class Car(val name: String, val position: Int) {
    fun move(
        lazyIsMovable: () -> Boolean
    ): Car {
        if (lazyIsMovable.invoke()) {
            return copy(position = position + 1)
        }
        return this
    }
}


class CarTest {
    @Test
    fun 이동() {
        val car = Car("jason", 0)
        val actual: Car = car.move {
            true
        }
        assertThat(actual).isEqualTo(Car("jason", 1))
    }

    @Test
    fun 정지() {
        val car = Car("jason", 0)
        val actual: Car = car.move { false }
        assertThat(actual).isEqualTo(Car("jason", 0))
    }
}
