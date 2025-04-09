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

    val badJob = coroutineScope.async(coroutineExceptionHandler) {
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
    badJob.await()
}