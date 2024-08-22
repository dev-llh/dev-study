# JAVA 9 주요 변경점

## Module (Java Platform Module System - Jigsaw)
Jigsaw를 통하여 원하는 모듈만 모아 런타임 환경 이미지를 만들 수 있게 되었다. (소형 디바이스, 마이크로 서비스, 시작 시간 단축 등을 염두한 것으로 보인다.)

1. 모듈 (Module): 모듈은 패키지와 리소스의 집합이며, module-info.java 파일을 통해 정의된다. 모듈은 자신이 노출하고자 하는 패키지와 필요한 다른 모듈들을 명시적으로 선언한다.
2. `module-info.java`: 모듈의 메타데이터를 담고 있으며, 해당 모듈이 제공하는 패키지와 의존하는 모듈들을 정의한다. 모듈의 루트에 위치
3. Exports: 모듈이 제공하는 패키지들을 명시적으로 지정하며, 외부에서 사용할 수 있게 한다.
4. Requires: 모듈이 의존하는 모듈을 명시

```java
// mod1 module-info.java
// prv는 비공개
module kr.taeu.mod {
    exports kr.taeu.mod1;
}
```
```java
// app module-info.java
module kr.taeu.hello {
    requires java.base;

    requires kr.taeu.mod;
}
```
```java
public class Main {
    public static void main(String[] args) {
        boolean hasText = StringUtil.hasText("hello");
        System.out.println("hasText = " + hasText);

        int len = StringUtil.length("hihi");
        System.out.println("len = " + len);

//        PrivateClass privateClass = new PrivateClass(); //         cannot find symbol
//        privateClass.privateMethod();
    }
}
```

## UTF-8 Property Files
Properties file에 대한 기본 인코딩이 utf-8로 변경되었다 (기존 ISO-8859-1)

## Convenience Factory Methods for Collections
불변 Collection 팩토리 메서드가 생김

```java
List<Integer> old = Collections.unmodifiableList(Arrays.asList(1, 2, 3, 4, 5));

List<Integer> newList = List.of(1, 2, 3, 4, 5);
```

## Private Interface Method
interface에 private method, private static method를 제공한다.

```java
public class PrivateInterfaceMethodTest {

    @Test
    public void test () {
        TestClass testClass = new TestClass();

        testClass.testMethod();

        /*
        public method
        hello private method
        hello static method
         */
    }

    public interface TestInterface {
        private void testMethod1() {
            System.out.println("hello private method");
        }

        private static void testStaticMethod1() {
            System.out.println("hello static method");
        }

        default void testMethod() {
            System.out.println("public method");

            testMethod1();
            testStaticMethod1();
        }
    }

    public static class TestClass implements TestInterface {

    }
}
```

## Improve Diamond Operator
java 7에 추가된 Diamond Operartor를 익명클래스에도 사용 가능

```java
    @Test
    public void test() {
        TestInterface<String> test = new TestInterface<>() {
            @Override
            public void print(String str) {
                System.out.println("str = " + str);
            }
        };

        test.print("hello diamond");
    }

    public interface TestInterface<T> {
        void print(T str);
    }
```