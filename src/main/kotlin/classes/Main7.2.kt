package classes

import kotlinx.coroutines.*

suspend fun main() {
    println("Init")

    val job = CoroutineScope(Dispatchers.Default).launch {
        delay(100)
        throw Exception()
    }

    job.join()
}
