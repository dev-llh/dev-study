package kr.taeu.projectreactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinksManyUnicast {
    public static void main(String[] args) {

        Sinks.Many<Integer> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> flux = sink.asFlux();

        sink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        sink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        flux.subscribe(System.out::println);

        sink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        // Sinks.many().unicast() sinks only allow a single Subscriber
        // flux.subscribe(System.out::println);
    }
}
