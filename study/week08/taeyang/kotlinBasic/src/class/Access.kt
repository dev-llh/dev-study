package `class`


class TestClass(name: String) {
    var name: String
    private var age: Int = 0
    init {
        this.name = name
    }
}

class OverLoadingConstructor(name: String) {
    // init 블럭 그리고 constructor에 초기화 구문이 둘다 들어가 있으므로 초기화 필요 없다.
    var name: String
    var address: String = "주소"
    // primitive type 프로퍼티는 사용 불가능 하기 때문에 0값으로 라도 초기화 해야한다.
    private var age: Int = 0

    init {
        this.name = name
    }

    constructor(name: String, address: String, age: Int) : this(name) {
        this.name = name
        this.address = address
        this.age = age
    }
    override fun toString(): String {
        return "OverLoadingConstructor(name='$name', address='$address', age=$age)"
    }
}

class OverLoadingConstructorWithoutInit {
    var name: String
    var address: String = ""
    var age: Int = 0

    constructor(name: String) {
        this.name = name
    }

    constructor(name: String, address: String, age: Int) {
        this.name = name
        this.address = address
        this.age = age
    }

    override fun toString(): String {
        return "OverLoadingConstructorWithoutInit(name='$name', address='$address', age=$age)"
    }
}

class PrivateDefaultConstructor private constructor() {
    lateinit var name: String

    // object 키워드로 인하여 of 메소드는 static 으로 생성된다.
    companion object {
        // 선언된 프로퍼티도 static으로 선언된다
        fun of(name: String): PrivateDefaultConstructor {
            val instance = PrivateDefaultConstructor()
            instance.name = name
            return instance
        }
    }

    override fun toString(): String {
        return "PrivateDefaultConstructor(name='$name')"
    }
}


fun main (){
    val testClass = TestClass("Kotlin")
    println(testClass.name)
    testClass.name = "Java"
    println(testClass.name)
//    testClass.age = 41
    // println(testClass.age)

    // 다음과 같이 생성자가 제공된다.
    val overLoadingConstructor = OverLoadingConstructor("kotlin")
    val overLoadingConstructor2 = OverLoadingConstructor("kotlin", "서울", 20)

    println(overLoadingConstructor.toString())
    println(overLoadingConstructor2.toString())

    // 다음과 같이 생성자가 제공된다.
    val overLoadingConstructor3 = OverLoadingConstructorWithoutInit("김찬정")
    val overLoadingConstructor4 = OverLoadingConstructorWithoutInit("김찬정", "서울", 20)
    println(overLoadingConstructor3.toString())
    println(overLoadingConstructor4.toString())
    // 정적팩토리 메소드로 객체 생성이 가능하다.
    val of = PrivateDefaultConstructor.of("kotlin")
    println(of.toString())

}
