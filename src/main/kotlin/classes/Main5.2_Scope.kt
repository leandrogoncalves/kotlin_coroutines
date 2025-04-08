package classes

import kotlinx.coroutines.*
import utils.printInfo

suspend fun main() {

    val scopeJob = Job()
    val scope = CoroutineScope(scopeJob)
    scope.printInfo()
    val job = scope.launch {
        this.printInfo()
        delay(150)
    }
    job.join()
}
