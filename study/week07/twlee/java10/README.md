# JAVA 10 주요 변경점
## Local Variable Type Inference
지역 변수에 대해 `var`를 사용하여 타입을 추론한다.
```java
    @Test
    void test() {
        List<Integer> old = new ArrayList<>();

        var list = new ArrayList<Integer>();
    }
```

## Creating Unmodifiable Collections
불변 컬렉션 API가 몇가지 추가되었다 아래 메서드는 기존 인스턴스에서 새 Unmodifiable Collection 인스턴스를 만든다.
* List.copyOf
* Set.copyOf
* Map.copyOf

```java
    @Test
    void test() {
        List<String> test = new ArrayList<>();
        test.add("a");
        test.add("b");
        test.add("c");

        List<String> other = List.copyOf(test);

        System.out.println(test.getClass());
        System.out.println(other.getClass());


        Assertions.assertIterableEquals(test, other);
        Assertions.assertNotEquals(test.getClass(), other.getClass());
    }
```

Stream 패키지의 Collectors 클래스에 다음과 같은 메서드가 추가되었다.
* Collector toUnmodifiableList();
* Collector toUnmodifiableSet();
* Collector toUnmodifiableMap(Function, Function);
* Collector toUnmodifiableMap(Function, Function, BinaryOperator);

