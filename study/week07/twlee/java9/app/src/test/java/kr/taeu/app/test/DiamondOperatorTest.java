package kr.taeu.app.test;

import org.junit.jupiter.api.Test;

public class DiamondOperatorTest {

    @Test
    public void test() {
        TestInterface<String> test = new TestInterface<>() {
            @Override
            public void print(String str) {
                System.out.println("str = " + str);
            }
        };

        test.print("hello diamond");

        new HashMap();
    }

    public interface TestInterface<T> {
        void print(T str);
    }
}
