package kr.taeu.projectreactor;

import reactor.core.publisher.Flux;

public class FluxExample1 {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("Hello", "Reactor", "World");
        flux.map(String::toLowerCase)
                .subscribe(System.out::println);
    }
}
