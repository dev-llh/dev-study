package iterate

fun main(){

    val item = arrayOf(1, 2, 3)
    val list = listOf(1, 2, 3)

    for (index in item.indices) {
        print(index)
    }

    println()

    for (index in list.indices) {
        print(index)
    }

    println()
    for (i in 1..10) {
        print("$i ")
    }
    println()

    for (i in 1 until 10) {
        print("$i ")
    }

    println()
    for (i in 2..10 step 2) {
        print("$i ")
    }

    println()
    for (i in 10 downTo 1) {
        print("$i ")
    }

}
