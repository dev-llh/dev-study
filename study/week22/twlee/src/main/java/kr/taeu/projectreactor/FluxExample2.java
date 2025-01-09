package kr.taeu.projectreactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxExample2 {
    public static void main(String[] args) {
        Flux<String> flux = Mono.just("Hello")
                .concatWith(Flux.just("World", "ConcatWith"));
        flux.subscribe(System.out::println);
    }
}
