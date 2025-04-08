package classes

import kotlinx.coroutines.*
import utils.threadLogInfo
import java.lang.Exception
import kotlin.coroutines.coroutineContext

suspend fun main() {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    val taskDurationSeconds = 5

    val taskJob = coroutineScope.launch {
        try {
            val taskResult = backgroundTask(taskDurationSeconds)
            threadLogInfo("$taskResult")
        } catch (e: Exception) {
            threadLogInfo("Task canceled: $e")
        }
    }

    val timerJob = coroutineScope.launch(NonCancellable) {
        try {
            printTaskTime(taskDurationSeconds)
        } catch (e: Exception) {
            threadLogInfo("Timer canceled: $e")
        }
    }

    /*
    * NonCancellable
    * */

    delay(3000)
    coroutineScope.coroutineContext.cancelChildren()
    joinAll(taskJob, timerJob)
}

private suspend fun backgroundTask(taskDurationSeconds: Int): Long {
    threadLogInfo("background task started")

    val stopTimeNano = System.nanoTime() + taskDurationSeconds * 1_000_000_000L

    var iterationsCount: Long = 0
    while (System.nanoTime() < stopTimeNano) {
        coroutineContext.ensureActive()
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
