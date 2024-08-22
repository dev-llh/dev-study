package kr.taeu.app;

import org.junit.jupiter.api.Test;

public class SwitchTest {

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
}
