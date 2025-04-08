package utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

fun threadLogInfo(message: String) {
    println("Thread: {${Thread.currentThread().name}; ${Thread.currentThread().id}} Message: $message;")
}

fun CoroutineScope.printInfo() {
    println()
    println("CoroutineScope: $this")
    println("CoroutineContext: ${this.coroutineContext}")
    println("Job: ${this.coroutineContext[Job]}")
    println()
}

fun Job.printHierarchy(level: Int = 0) {
    val indentation = "....".repeat(level)
    println("$indentation $this")
    for (child in this.children) {
        child.printHierarchy(level + 1)
    }
    if (level == 0) {
        println()
    }
}
