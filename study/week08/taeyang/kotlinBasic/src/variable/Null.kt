package variable

fun main() {

    var name: String? = null

//    var age: Int = null

    val name2: String? = null
    val nameLength = name2?.length

    println(name2)
    println(nameLength)


//    val nameLength2 = name2!!.length

    val age: Int? = name2 as? Int?

    println(age)

}
