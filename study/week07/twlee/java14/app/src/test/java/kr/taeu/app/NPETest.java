package kr.taeu.app;

import org.junit.jupiter.api.Test;

public class NPETest {

    @Test
    void test() {
        A a = new A();
        a.b.hello();
    }

    class A {
        B b = null;
    }

    class B {
        public void hello() {
            System.out.println("hello");
        }
    }
}
