# Ktorm 개념 및 사용법

## Ktorm이란?
Ktorm은 **Kotlin 기반의 경량 ORM(Object-Relational Mapping) 라이브러리**로, SQL을 보다 Kotlin스럽게 작성할 수 있도록 도와준다.

- **특징**
    - 간결한 DSL (Domain Specific Language) 제공
    - 간단한 API로 CRUD 기능 수행 가능
    - 트랜잭션 및 Connection Pool 지원

---

## Ktorm 설정하기

### 🔹 1) Gradle 의존성 추가
```kotlin
dependencies {
    implementation("org.ktorm:ktorm-core:3.5.0") // 최신 버전 확인 후 적용
    implementation("com.h2database:h2:1.4.200")  // H2 데이터베이스 사용
}
```

---

## Database 연결 설정
```kotlin
import org.ktorm.database.Database

val database = Database.connect(
    url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
    driver = "org.h2.Driver"
)
```

---

## 테이블 및 Entity 클래스 정의
```kotlin
import org.ktorm.schema.*
import kotlinx.serialization.Serializable

@Serializable
data class User(val id: Int, val name: String)

object Users : Table<Nothing>("users") {
    val id = int("id").primaryKey()
    val name = varchar("name")
}
```

---

## CRUD 예제

- 데이터 삽입 : database.insert()
- 데이터 조회 : database.from().select().where()
- 데이터 수정 : database.update()
- 데이터 삭제 : database.delete()
```kotlin
import org.ktorm.dsl.*

fun insertUser(name: String): Int {
    return database.insert(Users) {
        set(Users.name, name)
    }
}

fun getUserById(userId: Int): User? {
    return database.from(Users)
        .select()
        .where { Users.id eq userId }
        .map { row ->
            User(
                id = row[Users.id]!!,
                name = row[Users.name]!!
            )
        }.firstOrNull()
}

fun updateUser(id: Int, newName: String): Int {
    return database.update(Users) {
        set(Users.name, newName)
        where { Users.id eq id }
    }
}

fun deleteUser(id: Int): Int {
    return database.delete(Users) {
        it.id eq id
    }
}

```
