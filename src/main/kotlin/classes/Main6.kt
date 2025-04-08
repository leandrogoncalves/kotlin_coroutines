package classes

import kotlinx.coroutines.*
import utils.threadLogInfo

suspend fun main() {
    var counter = 0
    val deferredList = mutableListOf<Deferred<Int>>()
    withContext(Dispatchers.Default) {
        repeat(1000) {
            deferredList.add(
                async {
                    delay(100)
                    1
                }
            )
        }
        for (deferred in deferredList) {
            counter += deferred.await()
        }
    }

    threadLogInfo("Counter value: $counter")

    counter = 0
    withContext(Dispatchers.Default) {
        counter = (1..1000).toList().map {
            async {
                delay(100)
                1
            }
        }.awaitAll().sum()
    }

    threadLogInfo("Counter value: $counter")
}

