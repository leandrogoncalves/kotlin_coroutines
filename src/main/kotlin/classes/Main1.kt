package classes

import kotlinx.coroutines.*
import utils.threadLogInfo

suspend fun main() {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    val taskDurationSeconds = 5

    val taskJob = coroutineScope.launch {
        val taskResult = backgroundTask(taskDurationSeconds)
        threadLogInfo("$taskResult")
    }

    val timerJob = coroutineScope.launch {
        printTaskTime(taskDurationSeconds)
    }

    taskJob.join()
    timerJob.join()
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
