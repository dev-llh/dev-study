package kr.taeu.app;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.SequencedSet;

public class SequencedCollectionsTest {

    @Test
    void test() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        System.out.println("set = " + set);
        SequencedSet<String> reversed = set.reversed();
        System.out.println("reversed = " + reversed);
    }
}
