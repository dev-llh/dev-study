package kr.taeu.projectreactor;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class ColdSequence {
    public static void main(String[] args) throws InterruptedException {
        List<String> input = Arrays.asList("Apple", "Banana", "Kiwi");
        Flux<String> cold = Flux.fromIterable(input)
                .map(String::toUpperCase);
        cold.subscribe(System.out::println);
        System.out.println("=============================================");
        Thread.sleep(3000);
        cold.subscribe(System.out::println);
    }
}
