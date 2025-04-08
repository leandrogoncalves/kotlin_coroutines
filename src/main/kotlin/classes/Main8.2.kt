package classes

import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun main() {
    println("Init")

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        println("Caught exception: $e")
    }

    val parentJob = Job()
    val scope = CoroutineScope(parentJob + coroutineExceptionHandler + Dispatchers.Default)

    val goodJob = scope.launch {
        delay(200)
        println("Result: " + Random.nextInt(0, 100) * Random.nextInt(0, 100))
    }

    val badJob = scope.launch {
        delay(100)
        throw Exception()
    }

    joinAll(goodJob, badJob)

    println("parentJob: $parentJob")
    println("goodJob: $goodJob")
    println("badJob: $badJob")
}
