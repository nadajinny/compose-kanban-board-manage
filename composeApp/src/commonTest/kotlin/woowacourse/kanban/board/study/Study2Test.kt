package woowacourse.kanban.board.study

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Study2Test {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    fun sum(
        lazyFilterNumbers : () -> List<Int>
    ): Int {
        var total = 0
        lazyFilterNumbers.invoke().forEach {
            total += it
        }
        return total
    }

    fun sumAll(numbers: List<Int>): Int {
        return sum { numbers }
    }

    fun sumAllEven(numbers: List<Int>): Int {
        return sum { numbers.asSequence().filter { it % 2 == 0 }.toList() }
    }

    fun sumAllOverThree(numbers: List<Int>): Int {
        return sum { numbers.asSequence().filter { it > 3 }.toList() }
    }

    @Test
    fun test1() {
        assertThat(sumAll(numbers)).isEqualTo(21)
    }
}