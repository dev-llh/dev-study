package kr.taeu.app;

import java.util.Objects;

/**
 * 안녕
 *
 * @param x 이건 X야
 * @param y 이건 Y다
 */
public record NewClass(
        int x,
        int y
) {

    public NewClass {
        Objects.requireNonNull(x);
    }

    static String s = "hello static";

    static {
        System.out.println("hello static initializer");
    }

    public static String getS() {
        return s;
    }
}
