package lambda


fun main (){
    // 기본 함수 선언 방식
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

// 기본 함수 선언 방식을 람다식으로 표현
    val sumLambda: (Int, Int) -> Int = { a, b -> a + b }

    // 코틀린 함수 선언 방식
    fun sum2(a: Int, b: Int) = a + b

// 코틀린 함수 선언 방식을 람다식으로 표현
    val sum2Lambda = { a: Int, b: Int -> a + b }


    println(sum(1,2))
    println(sumLambda(1,2))
    println(sum2(1,2))
    println(sum2Lambda(1,2))

    val listOf = List(10) { v -> v + 1 }

    val newList = listOf.filter { it > 2 }
        .map { it + 10 }

    println(listOf)
    println(newList)
}
