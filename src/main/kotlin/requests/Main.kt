package requests

import java.util.Locale
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.*

suspend fun generate(): String {
    val currentLocale: Locale = Locale.getDefault()
    val countryCode: String = currentLocale.country
    val order = generateSequence(1) { it + 1 }
        .take(1)
        .distinct()
        .sorted()
        .toSet()

    val number = order.stream().findFirst().get()
    val orderNumber = countryCode + number
    println("Order number: $orderNumber")
    return orderNumber
}



fun main() {
    runBlocking {
        println("Start of execution ")
        val orderNumber: String = generate()
        println("Coroutine completed with order number: $orderNumber")
    }

    println("Main program continues while the coroutine is running.")
}