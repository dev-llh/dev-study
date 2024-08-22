package kr.taeu.app.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionTest {

    @Test
    public void unmodifiableList () {
        List<Integer> old = Collections.unmodifiableList(Arrays.asList(1, 2, 3, 4, 5));

        List<Integer> newList = List.of(1, 2, 3, 4, 5);
    }
}
