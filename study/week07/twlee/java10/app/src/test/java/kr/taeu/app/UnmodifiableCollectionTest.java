package kr.taeu.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UnmodifiableCollectionTest {

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
}
