package kr.taeu.app;

import org.junit.jupiter.api.Test;

import java.util.List;

public class VarTest {

    @Test
    void test() {
        List<String> list = List.of("a", "b", "c", "d", "e", "f", "g", "h");

        List<String> collect = list.stream()
                .map((var s) -> s + "-")
                .toList();

        System.out.println("collect = " + collect);
    }
}
