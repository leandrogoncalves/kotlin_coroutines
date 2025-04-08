package classes

import kotlinx.coroutines.*
import utils.printHierarchy
import utils.printInfo

suspend fun main() {

    val scopeJob = Job()
    val scope = CoroutineScope(scopeJob + Dispatchers.IO + CoroutineName("external scope"))
    scope.printInfo()
    val job = scope.launch(Dispatchers.Default + CoroutineName("internal coroutine")) {
        this.printInfo()
        delay(150)
        withContext(Dispatchers.IO + CoroutineName("with context")) {
            this.printInfo()
            delay(150)
            scopeJob.printHierarchy()
        }
    }
    scope.launch(CoroutineName("other coroutine")) {
        delay(750)
    }
    job.join()
}
