package classes

import kotlinx.coroutines.*
import utils.printHierarchy

suspend fun main() {
    println("Init")
    val scopeJob = Job()
    val corrotineScope = CoroutineScope(scopeJob)

    val job = corrotineScope.launch {
        delay(100)
        throw Exception("Uma exception foi lançada")
    }

//    val job = CoroutineScope(Dispatchers.Default).launch {
//        delay(100)
//        throw Exception()
//    }

    job.join()
    scopeJob.printHierarchy()

}
