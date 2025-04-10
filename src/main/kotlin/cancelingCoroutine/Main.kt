package cancelingCoroutine

import kotlinx.coroutines.*

fun main() {
    runBlocking<Unit> {
        //given
        val job = launch(Dispatchers.Default) {
            while (isActive) {
                println("is working")
            }
        }

        delay(1300L)

        //when
        job.cancel()

        //then cancel successfully
    }
}