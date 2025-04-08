package classes

import kotlinx.coroutines.*

suspend fun main() {

    val taskDurationSeconds = 5

    val taskResult = backgroundTask(taskDurationSeconds)
    println("$taskResult")

    printTaskTime(taskDurationSeconds)
}

private fun backgroundTask(taskDurationSeconds: Int): Long {
    println("background task started")

    val stopTimeNano = System.nanoTime() + taskDurationSeconds * 1_000_000_000L

    var iterationsCount: Long = 0
    while (System.nanoTime() < stopTimeNano) {
        iterationsCount++
    }

    println("background task completed")

    return iterationsCount
}

private suspend fun printTaskTime(remainingTimeSeconds: Int) {
    println("$remainingTimeSeconds seconds to finish the task")

    if (remainingTimeSeconds > 0) {
        delay(1000)
        printTaskTime(remainingTimeSeconds - 1)
    } else {
        println("task finished!")
    }
}
