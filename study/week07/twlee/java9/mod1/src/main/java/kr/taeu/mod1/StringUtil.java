package kr.taeu.mod1;

import kr.taeu.prv.PrivateClass;

public class StringUtil {
    public static boolean hasText(String test) {
        return test != null && !test.isEmpty();
    }

    public static int length(String test) {
        PrivateClass privateClass = new PrivateClass();
        privateClass.privateMethod();
        return test.length();
    }
}
