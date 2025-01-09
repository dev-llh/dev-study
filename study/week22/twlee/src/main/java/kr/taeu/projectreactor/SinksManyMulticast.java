package kr.taeu.projectreactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinksManyMulticast {
    public static void main(String[] args) {

        Sinks.Many<Integer> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> flux = sink.asFlux();

        sink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        sink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        // sinks -> hot Publisher, onBackPressureBuffer -> Warm up의 의미를 가지는 Hot Sequence
        flux.subscribe(d -> System.out.println("sub1: " + d));

        flux.subscribe(d -> System.out.println("sub2: " + d));

        sink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
