package enums


enum class Enum(val number: Int, val desc: String) {
    ONE(1, "one"),
    TWO(2, "two"),
    THREE(3, "three"),
    FOUR(4, "four");

    fun getNumberStatus() = "($number, $desc)"
}

fun selectStatus(status: Enum) = when (status) {
    Enum.ONE -> "One"
    Enum.TWO, Enum.THREE -> "Two or Three"
    Enum.FOUR -> "Four"
//    else -> "NOT"
}

fun selectStatus1(status1: Enum, status2: Enum) = when (setOf(status1, status2)) {
    setOf(Enum.ONE) -> "One"
    setOf(Enum.TWO, Enum.THREE) -> "Two or Three"
    else -> "NOT"
}

fun main() {
    println(Enum.ONE.getNumberStatus())
    println(Enum.TWO.getNumberStatus())
    println(Enum.THREE.getNumberStatus())

    println(selectStatus(Enum.ONE))
    println(selectStatus1(Enum.ONE, Enum.ONE))
    println(selectStatus1(Enum.THREE, Enum.TWO))
    println(selectStatus1(Enum.ONE, Enum.TWO))
}
