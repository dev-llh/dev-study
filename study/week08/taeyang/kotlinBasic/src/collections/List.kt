package collections

fun main(){

    // 불변 List 초기화 이후 수정 삭제 불가
    val listOf = listOf(1, 2, 3)
    val list = List(3) { v -> v + 1 }

// 수정 삭제 추가 가능
    val emptyMutableList = mutableListOf<Int>()
    val mutableListOf = mutableListOf(1, 2, 3)
    val mutableList = MutableList(2) { v -> v + 1 }

    listOf.get(0)
    listOf[0]
    mutableListOf.add(3, 4)
    mutableListOf.remove(1)
// even [2], odd [1,3] 각각 리스트를 구분 해서 반환함
    val (even, odd) = listOf.partition { it % 2 == 0 }

    println(listOf)
    println(list)
    println(emptyMutableList)
    println(mutableListOf)
    println(mutableList)
    println(even)
    println(odd)
}
