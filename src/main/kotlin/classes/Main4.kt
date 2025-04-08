package classes

import kotlinx.coroutines.*
import utils.threadLogInfo

suspend fun main() {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    val taskDurationSeconds = 5

    threadLogInfo("Init")

    val taskDeferred = coroutineScope.async<Long> {
        val taskResult = backgroundTask(taskDurationSeconds)
        taskResult
    }

    val timerJob = coroutineScope.launch {
        printTaskTime(taskDurationSeconds)
    }

    val result = taskDeferred.await()
    timerJob.join()

    threadLogInfo("result: $result")
}

private fun backgroundTask(taskDurationSeconds: Int): Long {
    threadLogInfo("background task started")

    val stopTimeNano = System.nanoTime() + taskDurationSeconds * 1_000_000_000L

    var iterationsCount: Long = 0
    while (System.nanoTime() < stopTimeNano) {
        iterationsCount++
    }

    threadLogInfo("background task completed")

    return iterationsCount
}

private suspend fun printTaskTime(remainingTimeSeconds: Int) {
    threadLogInfo("$remainingTimeSeconds seconds to finish the task")

    if (remainingTimeSeconds > 0) {
        delay(1000)
        printTaskTime(remainingTimeSeconds - 1)
    } else {
        threadLogInfo("task finished!")
    }
}
