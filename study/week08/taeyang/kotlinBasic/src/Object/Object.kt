package Object

object Object {
    var name = "test"
    var age = 10

    fun getNames() = "($name)"
    fun getAges() = age
}

fun main() {
    println(Object.name)
    println(Object.age)
    Object.name = "test"
    Object.age = 12
    println(Object.getNames())
    println(Object.getAges())
}
