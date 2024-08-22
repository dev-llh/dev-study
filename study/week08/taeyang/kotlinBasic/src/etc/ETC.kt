package etc

import kotlin.properties.Delegates.observable
import kotlin.properties.Delegates.vetoable

operator fun Int.plus(b: String) = "$this$b"

class Position(var x: Int, var b: Int) {
    operator fun plus(other: Position) = Position(x + other.x, b + other.b)
    override fun toString(): String {
        return "Postion(x=$x, b=$b)"
    }
}

// 일반 클래스는 componentN 메소드를 구현 해야 한다.
class Person(val name: String, val age: Int) {
    operator fun component1(): String = name
    operator fun component2(): Int = age
}


fun main() {
    println(1 + "1")

    val location = Position(1,2) + Position(3,4)
    println(location)

    var name: String by observable("test") { property, oldValue, newValue -> println("newValue") }
    name = "test1"
    println(name)
    name = "test2"
    println(name)

    var age: Int by vetoable(0) { property, oldValue, newValue -> newValue > oldValue }

    age = 10
    println(age)
    age = 5
    println(age)
    age = 20
    println(age)

    val person = Person("Adam", 100)
    val (names, ages) = person

    println(names)
    println(ages)

}
