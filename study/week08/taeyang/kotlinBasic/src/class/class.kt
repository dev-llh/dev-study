package `class`

// 멤버 프로퍼티(변수)와 생성자를 동시에 선언
class SimpleClass(val name: String, var address: String, var age: Int = 41)


// name: String <= var/val 를 붙이지 않으면 생성자 매개변수만 선언 한 것 이다.
// 그래서 멤버 프로퍼티(변수)를 꼭 선언 해야한다.
class BasicClass(name: String, address: String) {
    var name: String
    var address: String = ""
        get() = "$field 나라"
        set(value) {
            field = "$value"
        }

    init {
        this.name = name
        this.address = address
    }
}

fun main (){
    val simpleClass = SimpleClass("Kotlin", "Korea", 41)
    println(simpleClass.name)
    println(simpleClass.address)
    println(simpleClass.age)
//    simpleClass.name = "java"
    simpleClass.address = "USA"
    println(simpleClass.address)
    val basicClass = BasicClass("Kotlin", "Korea")
    println(basicClass.address)
    basicClass.address = "USA"
    println(basicClass.address)
}

