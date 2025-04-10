package expensiveComputation

import kotlinx.coroutines.*

suspend fun expensiveComputation(res: MutableList<String>) {
    delay(1000L)
    res.add("word!")
}

fun main() {
    val res = mutableListOf<String>()

    //when
    runBlocking {
        launch { expensiveComputation(res) }
        res.add("Hello,")
    }
    println(if(res.equals(listOf("Hello,", "word!"))) "ok" else "fail")
}