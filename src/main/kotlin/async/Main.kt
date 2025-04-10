package async

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking<Unit> {
        val delay = 1000L
        val time = measureTimeMillis {
            //given
            val one = async(Dispatchers.Default) { someExpensiveComputation(delay) }
            val two = async(Dispatchers.Default) { someExpensiveComputation(delay) }

            //when
            runBlocking {
                one.await()
                two.await()
            }
        }

        //then
        println(if(time < delay * 2) "ok" else "fail")
    }
}

suspend fun someExpensiveComputation(delay: Long) {
    delay(delay)
    println("Some expensive computation ...")
}