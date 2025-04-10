package fibonacci

suspend fun main(){
    val fibonacciSeq = sequence {
        var a = 0
        var b = 1

        yield(1)

        while (true) {
            yield(a + b)

            val tmp = a + b
            a = b
            b = tmp
        }
    }

    val res = fibonacciSeq
        .take(5)
        .toList()

    println(if(res.equals(listOf(1, 1, 2, 3, 5))) "Ok" else "Fail")
}