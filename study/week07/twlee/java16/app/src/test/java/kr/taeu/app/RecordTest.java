package kr.taeu.app;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class RecordTest {

    @Test
    void test() {
        NewClass n = new NewClass(5, 10);
        int x = n.x();
    }

    @Test
    void localRecord() {
        record TotalSum(int a) {}

        NewClass n = new NewClass(5, 10);
        TotalSum totalSum = new TotalSum(n.x() + n.y());
        System.out.println("totalSum = " + totalSum);
    }

    static class oldClass {
        int a;
        int b;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            oldClass oldClass = (oldClass) o;
            return a == oldClass.a && b == oldClass.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
}
