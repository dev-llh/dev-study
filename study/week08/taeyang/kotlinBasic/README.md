# 목적
> Kotlin을 사용해보자.

```markdown
# Kotlin 기초 강의 자료

## 1. Kotlin 소개
Kotlin은 JetBrains에서 개발한 정적 타입 지정 프로그래밍 언어로, 자바와 100% 호환됩니다. Kotlin은 간결하고 실용적이며, 객체 지향 프로그래밍과 함수형 프로그래밍의 요소를 모두 포함하고 있습니다.

## 2. Kotlin 설치
Kotlin을 설치하려면 IntelliJ IDEA와 같은 IDE를 사용하는 것이 좋습니다. IntelliJ IDEA는 Kotlin을 기본적으로 지원합니다.

### IntelliJ IDEA 설치 및 설정
1. [IntelliJ IDEA 다운로드 페이지](https://www.jetbrains.com/idea/download/)에서 설치 파일을 다운로드합니다.
2. 설치 파일을 실행하여 IntelliJ IDEA를 설치합니다.
3. IntelliJ IDEA를 실행하고, 새로운 프로젝트를 생성합니다.
4. 프로젝트 생성 시, Kotlin을 선택하고, 필요한 설정을 완료합니다.

## 3. Kotlin 기본 문법

### 3.1 변수 선언
Kotlin에서는 `val`과 `var` 키워드를 사용하여 변수를 선언합니다.
- `val`: 변경 불가능한 변수 (immutable)
- `var`: 변경 가능한 변수 (mutable)

```kotlin
val immutableVariable: String = "Hello, Kotlin"
var mutableVariable: Int = 42
```

### 3.2 함수 선언
Kotlin에서 함수를 선언하는 방법은 다음과 같습니다.

```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}
```

### 3.3 조건문
Kotlin의 조건문은 자바와 유사합니다.

```kotlin
fun maxOf(a: Int, b: Int): Int {
    return if (a > b) a else b
}
```

### 3.4 null 안전성
Kotlin은 null 안전성을 지원합니다. 변수 타입 뒤에 `?`를 붙여 nullable 타입을 선언할 수 있습니다.

```kotlin
var nullableVariable: String? = null
```

### 3.5 클래스와 객체
Kotlin에서 클래스를 선언하고 객체를 생성하는 방법은 다음과 같습니다.

```kotlin
class Person(val name: String, var age: Int)

val person = Person("John", 30)
```

### 3.6 상속
Kotlin에서 클래스를 상속하는 방법은 다음과 같습니다.

```kotlin
open class Animal(val name: String)

class Dog(name: String) : Animal(name)
```

### 3.7 인터페이스
Kotlin에서 인터페이스를 선언하고 구현하는 방법은 다음과 같습니다.

```kotlin
interface Drivable {
    fun drive()
}

class Car : Drivable {
    override fun drive() {
        println("Driving a car")
    }
}
```

### 3.8 람다식
Kotlin은 람다식을 지원합니다.

```kotlin
val sum = { a: Int, b: Int -> a + b }
println(sum(1, 2)) // 3
```

## 4. Kotlin의 주요 특징
- **간결성**: 불필요한 코드를 줄이고 가독성을 높입니다.
- **안전성**: null 안전성을 제공하여 null 포인터 예외를 방지합니다.
- **상호 운용성**: 자바와 100% 호환되며, 자바 코드와 혼용하여 사용할 수 있습니다.
- **함수형 프로그래밍**: 람다식, 고차 함수, 불변성 등을 지원합니다.

# Kotlin 학습 자료

## 1. Kotlin이란?

Kotlin은 JetBrains에서 개발한 현대적인 프로그래밍 언어로, JVM(자바 가상 머신)에서 실행되며 Java와 100% 호환성을 가지고 있습니다. 간결한 문법과 강력한 기능을 통해 개발자 생산성을 극대화하고, Android 개발을 포함한 다양한 플랫폼에서 널리 사용됩니다.

## 2. Kotlin의 주요 특징

### 2.1 간결한 문법
Kotlin은 Java에 비해 코드가 훨씬 간결하며, 불필요한 코드 작성이 최소화됩니다. 예를 들어, 세미콜론(;)을 사용하지 않아도 되며, 데이터 클래스, 람다 함수 등 다양한 문법적 축약을 제공합니다.

### 2.2 Null 안전성
Kotlin은 NullPointerException(NPE)을 방지하기 위해 null 안전성을 기본으로 지원합니다. Kotlin에서는 변수에 null을 할당할 수 있는지 명시적으로 선언해야 합니다.

```kotlin
var name: String = "Kotlin"  // Null을 허용하지 않음
var nullableName: String? = null  // Null을 허용함
```

### 2.3 스마트 캐스트
Kotlin은 타입 검사를 통해 불필요한 타입 캐스팅을 줄여줍니다. 조건문 내에서 타입이 확인되면 자동으로 캐스팅됩니다.

```kotlin
fun printLength(obj: Any) {
    if (obj is String) {
        println(obj.length)  // obj가 String으로 스마트 캐스트 됨
    }
}
```

### 2.4 데이터 클래스
Kotlin은 데이터 클래스를 통해 간단한 데이터 객체를 쉽게 정의할 수 있습니다. 데이터 클래스는 자동으로 `equals()`, `hashCode()`, `toString()` 메서드를 생성합니다.

```kotlin
data class User(val name: String, val age: Int)
```

### 2.5 확장 함수
Kotlin은 기존 클래스에 새로운 메서드를 추가할 수 있는 확장 함수를 지원합니다. 이 기능을 사용하면 기존 코드를 변경하지 않고도 기능을 확장할 수 있습니다.

```kotlin
fun String.addExclamation() = this + "!"
println("Hello".addExclamation())  // "Hello!"
```

## 3. Kotlin과 Java의 차이점

### 3.1 코드 간결성
Kotlin은 Java에 비해 코드가 더 간결하고 읽기 쉽습니다. 예를 들어, 게터와 세터, 기본 생성자 등을 코드에서 직접 작성할 필요 없이 자동으로 생성해 줍니다.

```kotlin
// Kotlin
data class Person(val name: String, val age: Int)

// Java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

### 3.2 Null 안전성
Java에서는 null을 안전하게 처리하기 위해 별도의 검사를 해야 하지만, Kotlin에서는 언어 차원에서 null을 안전하게 다룰 수 있도록 지원합니다.

### 3.3 함수형 프로그래밍 지원
Kotlin은 람다 표현식, 고차 함수 등 함수형 프로그래밍 패러다임을 지원합니다. 이를 통해 코드의 가독성과 유지보수성을 향상시킬 수 있습니다.

## 4. Kotlin의 장점

### 4.1 생산성 향상
간결한 문법과 강력한 기능 덕분에 코드를 더 빠르고 효율적으로 작성할 수 있습니다. 이는 생산성 향상으로 이어집니다.

### 4.2 유지보수 용이성
Kotlin의 코드베이스는 가독성이 높고 에러를 줄일 수 있는 다양한 기능을 제공하기 때문에, 유지보수가 용이합니다.

### 4.3 안정성
Kotlin은 NullPointerException과 같은 일반적인 오류를 방지하는 기능을 제공하여 더 안정적인 코드를 작성할 수 있습니다.

### 4.4 상호운용성
Kotlin은 Java와 100% 호환되므로, 기존의 Java 프로젝트에 Kotlin을 점진적으로 도입할 수 있습니다.

## 5. Kotlin 시작하기

### 5.1 Kotlin 설치 및 설정
Kotlin은 IntelliJ IDEA와 Android Studio에서 기본적으로 지원됩니다. 다음은 Kotlin을 시작하기 위한 간단한 설정 방법입니다.

1. **IntelliJ IDEA에서 Kotlin 프로젝트 생성**
    - `File > New > Project`로 이동
    - `Kotlin`을 선택하고 프로젝트를 생성

2. **Gradle 또는 Maven 프로젝트에 Kotlin 추가**

```groovy
plugins {
    id 'org.jetbrains.kotlin.jvm' version '1.8.10'
}

dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
}
```

3. **Hello, World! 예제**

```kotlin
fun main() {
    println("Hello, World!")
}
```

### 5.2 Kotlin 코딩 연습
Kotlin을 연습할 수 있는 다양한 온라인 플랫폼이 존재합니다:
- [Kotlin Playground](https://play.kotlinlang.org/)
- [JetBrains Academy](https://www.jetbrains.com/academy/)

## 6. Kotlin 심화 주제

### 6.1 코루틴(Coroutine)
코루틴은 Kotlin에서 비동기 프로그래밍을 위한 강력한 도구입니다. 코루틴을 사용하면 비동기 코드가 동기 코드처럼 보이게 작성할 수 있습니다.

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}
```

### 6.2 DSL(Domain-Specific Language)
Kotlin은 DSL을 쉽게 만들 수 있는 언어적 특성을 가지고 있습니다. DSL을 사용하면 특정 도메인에 특화된 간결하고 직관적인 언어를 정의할 수 있습니다.

### 6.3 멀티플랫폼 프로젝트
Kotlin은 하나의 코드베이스로 Android, iOS, 웹, 데스크톱 애플리케이션을 개발할 수 있는 멀티플랫폼 기능을 제공합니다.

## 7. 추가 자료

- [Kotlin 공식 문서](https://kotlinlang.org/docs/home.html)
- [Kotlin Koans - 실습 예제](https://kotlinlang.org/docs/koans.html)
- [JetBrains Academy Kotlin Track](https://www.jetbrains.com/academy/)
- [읽어보면 좋은 블로그](https://incheol-jung.gitbook.io/docs/study/kotlin-in-action/3)
