# Ktor Overview

## Ktor란?
Ktor는 Kotlin으로 개발된 비동기 서버 및 클라이언트 프레임워크로, JetBrains에서 개발.  
경량화된 설계와 유연성을 제공하여 REST API, 웹소켓, 클라이언트 애플리케이션 등을 구현하는 데 적합.

---

## 장점
1. **경량성과 유연성**: 불필요한 의존성을 배제하고 필요한 모듈만 추가할 수 있어 성능과 유지보수성에서 유리.
2. **Kotlin 기반**: Kotlin의 최신 기능과 코루틴을 활용해 비동기 프로그래밍을 간단히 구현할 수 있음.

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

Spring의 @Component나 @Service에 대응되는 구성 요소는 명시적으로 코딩하거나 DI 라이브러리를 통해 설정할 수 있음.
