package classes

import kotlinx.coroutines.*
import utils.printInfo

suspend fun main() {

    val scopeJob = Job()
    val scope = CoroutineScope(scopeJob + Dispatchers.IO + CoroutineName("external scope"))
    scope.printInfo()
    val job = scope.launch(Dispatchers.Default + CoroutineName("internal coroutine")) {
        this.printInfo()
        delay(150)
    }
    job.join()
}
