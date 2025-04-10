package timeout

import kotlinx.coroutines.*

fun main() {
    runBlocking<Unit> {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("Some expensive computation $i ...")
                delay(500L)
            }
        }
    }
}