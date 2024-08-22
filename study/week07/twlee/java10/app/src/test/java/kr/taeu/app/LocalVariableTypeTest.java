package kr.taeu.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTypeTest {

    @Test
    void test() {
        List<Integer> old = new ArrayList<>();

        var list = new ArrayList<Integer>();
    }
}
