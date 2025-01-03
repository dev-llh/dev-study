# Ktor Overview

## Ktor란?
Ktor는 Kotlin으로 개발된 비동기 서버 및 클라이언트 프레임워크로, JetBrains에서 개발.  
경량화된 설계와 유연성을 제공하여 REST API, 웹소켓, 클라이언트 애플리케이션 등을 구현하는 데 적합.

---

## 장점
1. **경량성과 유연성**: 불필요한 의존성을 배제하고 필요한 모듈만 추가할 수 있어 성능과 유지보수성에서 유리.
2. **코루틴 기반 비동기 처리**: 코루틴을 활용해 비동기 I/O 처리를 기본으로 설계되었기 때문에, 필요한 네트워크 및 I/O 작업의 성능을 극대화함
3. **Kotlin 기반**: Kotlin의 최신 기능과 코루틴을 활용해 비동기 프로그래밍을 간단히 구현할 수 있음.

---

## 단점
1. **생태계 부족**: Spring과 비교했을 때 관련 자료와 커뮤니티 지원이 부족함.
2. **러닝 커브**: Kotlin과 Ktor의 구조를 이해해야 하므로 초보자에게는 다소 어렵게 느껴질 수 있음.
3. **기능 부족**: Spring 같은 풀스택 프레임워크와 비교하면 내장된 고급 기능이 부족할 수 있음.

---

## 사용 사례
- **SK Planet**
- **KaKao Pay**
- **G-Bike** 
- **OPUS M**
- **QANDA**


---

## Application.kt
애플리케이션의 엔트리 포인트로서 서버의 전반적인 설정을 정의.

## Routing
Application.kt에서 라우팅을 정의하고, 각 엔드포인트에 대한 처리를 구현.
```kotlin
fun Application.module() {
    intercept(ApplicationCallPipeline.Setup) {
        val uri = call.request.uri
        println("Setup uri: $uri")

    }

    routing {
        commonRoutes()
        storeRoutes()
        userRoutes()
    }
}
```
```kotlin
fun Route.storeRoutes() {
    val storeService = StoreService(StoreDao())
    val objectMapper = ObjectMapper()

    route("/store") {
        intercept(ApplicationCallPipeline.Setup) {
            val uri = call.request.uri
            println("Setup store uri: $uri")

        }

        get("/all") {
            call.respondText(objectMapper.writeValueAsString(storeService.getAllStores()))
        }

        get("gara") {
            call.respondText(objectMapper.writeValueAsString(Store(999, "Gara", "Gara")))
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@get
            }
            val user = storeService.getStoreById(id)
            if (user == null) {
                call.respond(HttpStatusCode.NotFound, "User not found")
            } else {
                call.respondText(objectMapper.writeValueAsString(user))
            }
        }
    }
}

```


## Spring의 Bean 대응 구조
Ktor는 Spring과 달리 전통적인 DI(Dependency Injection) 컨테이너를 제공하지 않지만,  
Kotlin의 **코틀린 DSL**과 **모듈 시스템**을 활용하여 유사한 기능을 구현할 수 있음.

- **Ktor의 DI**: Koin, Dagger 같은 외부 DI 라이브러리와 통합하여 DI 기능을 구현.
- **Application Module**: Ktor의 Application 모듈은 Spring의 Bean과 유사하게 역할별 구성 요소를 정의하고 로드할 수 있는 구조를 제공.
```kotlin
fun Application.module() {
    install(Routing) {
        get("/") {
            call.respondText("Hello, Ktor!")
        }
    }
}
```
- **object**: Kotlin의 object 키워드를 이용해 객체가 생성자 없이 즉시 생성되어, Bean처럼 이용이 가능.
```kotlin
object UserService {
    private val userDao: UserDao = UserDao

    fun getAllUsers(): List<User> = userDao.getAllUsers()

    fun getUserById(id: Long): User? = userDao.getUserById(id)

    fun createUser(user: User): User {
        if (user.name.isBlank()) {
            throw IllegalArgumentException("Name and email must not be blank")
        }
        return userDao.addUser(user)
    }

    fun updateUser(id : Long, user: User): Boolean {
        if (user.name.isBlank()) {
            throw IllegalArgumentException("Name and email must not be blank")
        }
        return userDao.updateUser(id, user)
    }

    fun deleteUser(id: Long): Boolean = userDao.deleteUser(id)
}
```
- **전역변수**: Application.kt에 전역으로 변수를 선언하면, 소스코드 전체에서 사용 가능.
```kotlin
fun Application.module() {
    //...
}
val globalObjectMapper = ObjectMapper()
```


