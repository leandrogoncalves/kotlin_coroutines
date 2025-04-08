package classes

import kotlinx.coroutines.*

suspend fun main() {
    println("Init")

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        println("Caught exception: $e")
    }

    val job = CoroutineScope(coroutineExceptionHandler).launch {
        delay(100)
        throw Exception()
    }

    job.join()
}
