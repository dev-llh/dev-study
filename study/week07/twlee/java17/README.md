# JAVA 17 주요 변경점
## Sealed Class
JAVA 15에서 제안되었던 Sealed Class가 정식 편입되었다. 상속 또는 구현할 클래스를 정해두고, 해당 클래스만 상속/구현 할 수 있도록 제한 하는 기능

```java
    static sealed class Animal permits Dog, Cat, Bear { }
```
위 Animal 클래스는 Dog, Cat 외에는 확장할 수 없다. 또한 Sealed 클래스의 하위 클래스는 반드시 아래 키워드 중 하나를 명시 해야한다.
* sealed: 동일하게 특정 클래스만 확장을 허용
* non-sealed: 열린 확장
* final: 닫힌 확장

```java
    static final class Dog extends Animal { }

    static non-sealed class Cat extends Animal { }

    static sealed class Bear extends Animal permits HalfMoonBear{ }

    static final class HalfMoonBear extends Bear { }
```

동일 파일내에서는 permits를 생략할 수 있다.
```java
    static sealed class Animal 
//            permits Dog, Cat, Bear 생략가능 
    { }

    static final class Dog extends Animal { }

    static non-sealed class Cat extends Animal { }

    static sealed class Bear extends Animal
//            permits HalfMoonBear 생략가능 
    { }

    static final class HalfMoonBear extends Bear { }
```
