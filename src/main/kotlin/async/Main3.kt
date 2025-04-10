package async

import kotlinx.coroutines.*

suspend fun main() {
    val deferred = CoroutineScope(Dispatchers.IO).async {
        println("Starting")
        delay(1000)
        println("Done")
        1
    }
    println("Waiting")
    println(deferred.await())
}