package kr.taeu.reactivepractice.imperative_declarative;

import java.util.Arrays;
import java.util.List;

public class Declarative {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 21, 10, 8, 11);
        int sum = numbers.stream()
                .filter(number -> number > 6 && (number % 2 != 0))
                .mapToInt(n -> n)
                .sum();

        System.out.println("sum = " + sum);
    }
}
