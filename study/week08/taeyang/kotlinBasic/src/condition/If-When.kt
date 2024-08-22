package condition

fun getMsg(msgType: Int) = if (msgType == 1) "안녕" else "잘가"

fun main() {
    val type = 1

    val message = if (type == 1) "안녕" else "잘가"
    println(message)

    println(getMsg(2))

    val inputType = 2
    val msgType = "2"
    fun checkType(type: Int) = if (type == 1) 1 else -1

    when (inputType) {
        1 -> println("1")
        2, 3 -> println("2 or 3")
        else -> println("not")
    }

    when (inputType) {
        checkType(inputType) -> println("OK")
        else -> println("NOT OK")
    }

    val result = when (inputType) {
        in 1..100 -> "1..100 OK"
        else -> "NOT OK"
    }

    println(result)
    val inputString = "test"

    when {
        inputString == "4" -> println("4 OK")
        msgType == "2" -> println("msgType = 2")
    }

}
