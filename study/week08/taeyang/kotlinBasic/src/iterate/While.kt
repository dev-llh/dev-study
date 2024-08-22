package iterate

fun main() {

    val item = Array(5) { v -> v + 1}
    var index = 0

    while (index < item.size) {
        println(item[index])
        index++
    }
}
