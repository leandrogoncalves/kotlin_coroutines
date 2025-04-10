package hugeAmountOfCoroutines

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    runBlocking<Unit> {
        //given
        val counter = AtomicInteger(0)
        val numberOfCoroutines = 100_000

        //when
        val jobs = List(numberOfCoroutines) {
            launch {
                delay(1L)
                counter.incrementAndGet()
            }
        }
        jobs.forEach { it.join() }

        //then
        println(if (counter.get().equals(numberOfCoroutines)) "ok" else "fail")
    }
}