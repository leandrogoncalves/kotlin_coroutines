package classes

import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun main() {
    println("Init")

    val scopeJob = SupervisorJob()
    val coroutineScope = CoroutineScope(scopeJob)

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        println("Caught exception: $e")
    }

    val badJob = coroutineScope.launch(coroutineExceptionHandler) {
        delay(100)
        throw Exception()
    }

    val goodJob = coroutineScope.launch(coroutineExceptionHandler) {
        delay(200)
    }

    joinAll(goodJob, badJob)

    println("scopeJob: $scopeJob")
    println("goodJob: $goodJob")
    println("badJob: $badJob")
}

suspend fun old() {
    val parentJob = Job()
    val scope = CoroutineScope(parentJob + Dispatchers.Default)

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
