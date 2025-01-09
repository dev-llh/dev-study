package kr.taeu.projectreactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class HotSequence {
    public static void main(String[] args) throws InterruptedException {
        List<String> input = Arrays.asList("Apple", "Banana", "Kiwi", "Pear", "Mango", "Orange");
        Flux<String> hot = Flux.fromIterable(input)
                .delayElements(Duration.ofSeconds(1))
                .share();

        hot.subscribe(fruit -> System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm:ss")) + " (1) Fruit call: " + fruit));

        Thread.sleep(2500);

        hot.subscribe(fruit -> System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm:ss")) + " (2) Fruit call: " + fruit));

        Thread.sleep(4000);

    }
}
