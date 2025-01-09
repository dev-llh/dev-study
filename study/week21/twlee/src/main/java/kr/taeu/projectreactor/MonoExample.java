package kr.taeu.projectreactor;

import reactor.core.publisher.Mono;

public class MonoExample {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello Reactor");
        mono.map(String::toUpperCase)
                .subscribe(System.out::println);
    }
}
