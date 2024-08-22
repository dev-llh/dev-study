package `class`

open class Parent(name: String, age: Int) {
    var name: String
    var age: Int

    init {
        this.name = name
        this.age = age
    }

    fun isAdultParentMethod() = age > 20
    open fun getNameInEnglish(): String {
        return "kim"
    }

    open override fun toString(): String {
        return "Parent(name='$name', age=$age)"
    }
}

class Child(name: String, age: Int, address: String) : Parent(name, age) {
    var address: String = name

    override fun getNameInEnglish(): String {
        return "child kim"
    }

    override fun toString(): String {
        return "Child(name='$name', age=$age, address='$address')"
    }
}

class Child2 : Parent {

    var address: String

    constructor(name: String, age: Int, address: String) : super(name, age) {
        this.address = address
    }

    override fun getNameInEnglish(): String {
        return "child kim"
    }

    override fun toString(): String {
        return "Child(name='$name', age=$age, address='$address')"
    }
}

fun main (){
    val parent = Parent("kim", 41)
    println(parent.toString())
    val child = Child("kim2", 42, "Korea")
    println(child.toString())
    val child2 = Child2("kim3", 43, "USA")

    println(child2.toString())
}
