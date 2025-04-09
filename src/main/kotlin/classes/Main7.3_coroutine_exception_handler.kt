package classes

import kotlinx.coroutines.*
import utils.printHierarchy

suspend fun main() {
    println("Init")

    val scopeJob = SupervisorJob()
//    val scopeJob = Job()
    val coroutineScope = CoroutineScope(scopeJob)

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        println("Caught exception: $e")
    }

    val job = coroutineScope.launch(coroutineExceptionHandler) {
        delay(100)
        throw Exception()
    }

//    val job = CoroutineScope(coroutineExceptionHandler).launch {
//        delay(100)
//        throw Exception()
//    }

    job.join()
    scopeJob.printHierarchy()
    println("Finish")
}
