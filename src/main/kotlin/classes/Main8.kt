package classes

import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun main() {
    println("Init")

    val supervisorJob = SupervisorJob()
    val scope = CoroutineScope(supervisorJob + Dispatchers.Default)

    val goodJob = scope.launch {
        delay(200)
        println("Result: " + Random.nextInt(0, 100) * Random.nextInt(0, 100))
    }

    val badJob = scope.launch {
        delay(100)
        throw Exception()
    }

    joinAll(goodJob, badJob)

    println("supervisorJob: $supervisorJob")
    println("goodJob: $goodJob")
    println("badJob: $badJob")
}
