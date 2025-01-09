package kr.taeu.projectreactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinksManyMulticastReplay {
    public static void main(String[] args) {

        Sinks.Many<Integer> sink = Sinks.many().replay().limit(2);
        Flux<Integer> flux = sink.asFlux();

        sink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        sink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        sink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        flux.subscribe(d -> System.out.println("sub1: " + d));

        sink.emitNext(4, Sinks.EmitFailureHandler.FAIL_FAST);

        flux.subscribe(d -> System.out.println("sub2: " + d));
    }
}
