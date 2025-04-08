
import kotlinx.coroutines.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Start of execution ")
        val orderNumber: String = generate()
        println("Coroutine completed with order number: $orderNumber")
    }

    println("Main program continues while the coroutine is running.")
}

suspend fun generate(): String {
    val currentLocale: java.util.Locale = java.util.Locale.getDefault()
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