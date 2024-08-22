package collections


fun main (){
    val arr = arrayOfNulls<Int>(10)
    val initArr = arrayOf(1, 2, 3)
    val initDiffTypeArr = arrayOf(1, "2", 3L)
    val initIntArr = intArrayOf(1, 2, 3)
    val initConstructorArr = Array(10) { 1 }
    val initLambdaArr = Array(10) { v -> v + 1 }
    val initWithIncrement = (1..10).toList().toTypedArray()
    val initWithIncrementByStep = (1..10).step(2).toList().toTypedArray()
    val initWithIncrementByStep2 = IntRange(1, 10).step(2).toList().toTypedArray()

    println("arr:")
    for (i in arr.indices) {
        print("${arr[i]} ")
    }
    println("\n")

    println("initArr:")
    for (i in initArr.indices) {
        print("${initArr[i]} ")
    }
    println("\n")

    println("initDiffTypeArr:")
    for (i in initDiffTypeArr.indices) {
        print("${initDiffTypeArr[i]} ")
    }
    println("\n")

    println("initIntArr:")
    for (i in initIntArr.indices) {
        print("${initIntArr[i]} ")
    }
    println("\n")

    println("initConstructorArr:")
    for (i in initConstructorArr.indices) {
        print("${initConstructorArr[i]} ")
    }
    println("\n")

    println("initLambdaArr:")
    for (i in initLambdaArr.indices) {
        print("${initLambdaArr[i]} ")
    }
    println("\n")

    println("initWithIncrement:")
    for (i in initWithIncrement.indices) {
        print("${initWithIncrement[i]} ")
    }
    println("\n")

    println("initWithIncrementByStep:")
    for (i in initWithIncrementByStep.indices) {
        print("${initWithIncrementByStep[i]} ")
    }
    println("\n")

    println("initWithIncrementByStep2:")
    for (i in initWithIncrementByStep2.indices) {
        print("${initWithIncrementByStep2[i]} ")
    }
    println()

}
