package kr.taeu.projectreactor;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinksOne {
    public static void main(String[] args) {
        Sinks.One<Object> one = Sinks.one();
        Mono<Object> mono = one.asMono();

        one.emitValue("Hello Reactor", Sinks.EmitFailureHandler.FAIL_FAST);
        one.emitValue("Hi Reactor", Sinks.EmitFailureHandler.FAIL_FAST);

        mono.subscribe(System.out::println);
        mono.subscribe(System.out::println);
    }
}
