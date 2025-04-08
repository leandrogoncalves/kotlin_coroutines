package classes

import kotlinx.coroutines.*
import utils.threadLogInfo

suspend fun main() {

    var counter = 0
    withContext(Dispatchers.Default) {
        repeat(1000) {
            launch {
                delay(100)
                counter++
            }
        }
    }

    threadLogInfo("Counter value: $counter")
}
