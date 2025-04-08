package classes

import kotlinx.coroutines.*
import utils.threadLogInfo
import kotlin.random.Random

suspend fun main() {
    threadLogInfo("Init")

    val parentJob = Job()
    val scope = CoroutineScope(parentJob + Dispatchers.Default)

    val goodJob = scope.launch {
        delay(200)
        println("Result: " + Random.nextInt(0, 100) * Random.nextInt(0, 100))
    }

    val badJob = scope.async {
        delay(100)
        throw Exception()
    }

    joinAll(goodJob, badJob)

    println("parentJob: $parentJob")
    println("goodJob: $goodJob")
    println("badJob: $badJob")

    try {
        badJob.await()
    } catch (e: Exception) {
        println("Async exception: $e")
    }
}
