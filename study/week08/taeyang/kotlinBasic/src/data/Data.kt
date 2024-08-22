package data

//data 클래스는 아래 메소드를 기본제공
//
//equals()
//hashCode()
//copy()
//toString()
//componentsN()
data class Entity(val id: Long, val name: String)


fun main() {
    val entity = Entity(1, "test")
    val entity2 = Entity(1, "test")
    println(entity)
    println(entity == entity2)
    println(entity.hashCode())
    println(entity2.hashCode())
    println(entity.copy())
    println(entity.copy(name = "test2"))
    println(entity.component1())
    println(entity.component2())
}
