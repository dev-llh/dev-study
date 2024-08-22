# JAVA 14 주요 변경점
## JAVA 12 ~ 13의 switch 문법 정식 도입
### 이전
* C, C++ 형태의 Switch 문법
* 불필요한 반복 코드
* 다수의 case와 break 존재
* 개발자의 휴먼에러로 break 빼먹으면 다음 분기 실행
### 신규
* Switch 내에서 `case AA, BB, CC -> System...`와 같은 문법이 가능
* 단일 수행 또는 블록 수행 가능
* Switch 블록 내에서 값을 반환 가능
* 여러 조건을 쉼표로 구분하여 한 라인에서 표현 가능

```java
@Test
void test() {
    // old
    String t = "A";
    switch (t) {
        case "A":
        case "B":
        case "C":
            System.out.println("ABC");
            break;
        case "X":
            System.out.println("X");
            break;
    }

    // new
    switch (t) {
        case "A", "B", "C" -> System.out.println("ABC");
        case "X" -> System.out.println("X");
    }
}
```

```java
@Test
void test2() {
    String t = "A";

    int sum = switch (t) {
        case "A", "B", "C" -> 0;
        case "X" -> {
            int result = t.length() * 300;
            yield result;
        }
        default -> 100;
    };

    System.out.println("sum = " + sum);
}
```

## 향상된 NPE
기존에는 NPE 메시지를 보고 라인을 따라가서 문제를 찾음
```
// 5번째 라인에 가서 문제를 직접 찾음
Exception in thread "main" java.lang.NullPointerException
	at kr.taeu.app.main(Main.java:10)
```

향상된 NPE에서는 좀더 자세한 메시지를 보여줌
```
Cannot invoke "kr.taeu.app.NPETest$B.hello()" because "a.b" is null
java.lang.NullPointerException: Cannot invoke "kr.taeu.app.NPETest$B.hello()" because "a.b" is null
```