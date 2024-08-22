package function

fun test(name: String): String {
    return "Hello $name"
}

fun simpleTest(name: String) = "Hello $name"


fun defaultTest(name: String = "test"): String {
    return "Hello $name"
}

fun nameTest(name: String, age: Int = 1) = name

// newList(vararg ts: T) => java에서 newList(String... name)와 같다
fun <T> newList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts)
        result.add(t)
    return result
}

// Array 클래스의 length 메소드를 override 한다.
fun <T> Array<T>.length(): Int {
    return 1
}

// Array 크래스에 새로운 메소드를 추가한다.
fun <T> Array<T>.addMethod(index1: Int, index2: Int): Int {
    return 1
}

// 중의 함수 선언은 infix 키워드를 붙여 선언한다.
infix fun Int.multiply(value: Int): Int {
    return this * value
}

fun main(){

    println(test("test"))
    println(simpleTest("test"))
    println(defaultTest("test"))


    val newList = newList(1, 2, 3)

    println(newList)
    val item = Array(5) { v -> v + 1 }
    println(item)
    // '*' 스프레드 연산자이며  item배열의 요소를 풀어서 넘긴다.
    val newList2 = newList(*item)

    println(newList2)

    val arrayOf = arrayOf(1, 2, 3)

    val addMethod = arrayOf.addMethod(1, 3) // 1이 리턴된다.
    val length = arrayOf.length() // 1이 리턴된다.

    println(addMethod)
    println(length)

    println(9 multiply 1) // 9 출력
    println(9.multiply(1)) // 9 출력
    println(3 + 2 multiply 2) // 10 출력, + 연산자 우선순위가 더 높다
}

