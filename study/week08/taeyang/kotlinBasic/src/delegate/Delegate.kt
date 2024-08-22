package delegate

//*
//* by 키워드를 이용한 위임은 interface의 구현체만 위임 활 수 있다.
//* 일반 클래스를 위임하는 것은 안된다.

interface UserService {
    fun findByName(name: String): String
    fun findAll(): List<String>
}

class UserServiceImpl(var name: String) : UserService {
    override fun findByName(name: String) = name;
    override fun findAll() = listOf(name, "test")
}

/**
 * 실제적으로는 UserService를 구현하는 것이 아니라 UserServiceImpl를 상속 없이 상속 하는
 * 이라고 보면된다 open 키워드가 없어 상속 할 수 없는 UserServiceImpl 위임하여
 * 상속의 효과를 누린다.
 * 또 하나의 장점은 모든 메소드를 구현 할 필요가 없고 필요한 메소드만 오버라이드 하면된다
 */
//class ImplByUserService(private val us : UserService) : UserService by us {
//    override fun findAll() = listOf(name, "test", "test2")
//}
