package classes

import kotlinx.coroutines.*

fun main() = runBlocking {
    var contador = 0

    // Lançando 1000 coroutines que incrementam o contador
    val jobs = List(1000) {
        launch(Dispatchers.Default) {
            // Simulando algum trabalho
            delay(1)
            contador++ // Operação não atômica - problema de memória compartilhada
        }
    }

    // Aguardando todas as coroutines terminarem
    jobs.forEach { it.join() }

    // O valor esperado seria 1000, mas provavelmente será menor devido a race conditions
    println("Valor final do contador: $contador")

    // Solução usando AtomicInteger
    val contadorSeguro = java.util.concurrent.atomic.AtomicInteger(0)

    val jobsSeguro = List(1000) {
        launch(Dispatchers.Default) {
            delay(1)
            contadorSeguro.incrementAndGet() // Operação atômica - segura para concorrência
        }
    }

    jobsSeguro.forEach { it.join() }

    println("Valor final do contador seguro: ${contadorSeguro.get()}")
}