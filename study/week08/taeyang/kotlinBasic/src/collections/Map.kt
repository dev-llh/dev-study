package collections

fun main(){
    // 불변
    val mapOf = mapOf("a" to 1, "b" to 2)
// 변경 가능
    val emptyMutableMap = mutableMapOf<String, Int>()

    mapOf.get("a")
    mapOf["a"]
    emptyMutableMap.put("c", 3)

    println(mapOf)
    println(emptyMutableMap)
}
