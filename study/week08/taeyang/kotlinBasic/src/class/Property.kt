package `class`

class Property(name: String, address: String, age: Int) {
    var name: String
        private set
    var address: String
    var age: Int

    init {
        this.name = name
        this.address = address
        this.age = age
    }
}

fun main(){
    val property = Property("Kotlin", "Korea", 41)
    println(property.name)
    println(property.address)
    println(property.age)
    // property.name = "Java"
    property.address = "USA"
    println(property.address)
}
