package `interface`

interface Interface {
    val number: Int
    val name: String
    fun getNameInEnglish(): String
    fun isMe(age: Int): Boolean
}

class InterfaceImpl(private var age: Int) : Interface{
    override val number: Int
        get() = age
    override val name: String = "test"

    override fun getNameInEnglish(): String {
        return "test"
    }
    override  fun isMe(age: Int): Boolean {
        return this.age == age
    }
}

fun main () {
    val interfaceImpl = InterfaceImpl(10)

    println(interfaceImpl.number)
    println(interfaceImpl.name)
    println(interfaceImpl.getNameInEnglish())
    println(interfaceImpl.isMe(10))
}

