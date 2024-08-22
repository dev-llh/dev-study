package kr.taeu.app;

import org.junit.jupiter.api.Test;

public class InstanceofTest {

    @Test
    void test() {
        Object t = "Asdf";

        // old
        if (t instanceof String) {
            String s = (String) t;
            System.out.println("s = " + s);
        }

        // new
        if (t instanceof String s) {
            System.out.println("s = " + s);
        }

        // and 조건에서는 후행조건으로 사용가능
        if (t instanceof String s && s.contains("A")) {
            System.out.println("s = " + s);
        }

        // instanceof 조건이 만족하지 않으면 후행에서 사용할 수 없으므로 or는 안됌 (flow scoping 유효)
//        if (t instanceof String s || s.length() > 5) {
//            System.out.println("s = " + s);
//        }
    }

    static class TestClass {
        int a;
        int b;

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof TestClass)) {
                return false;
            }
            TestClass t = (TestClass) o;
            return a == t.a && b == t.b;
        }
    }

    static class NewClass {
        int a;
        int b;

        @Override
        public boolean equals(Object o) {
            return (o instanceof NewClass n)
                    && a == n.a
                    && b == n.b;
        }
    }
}
