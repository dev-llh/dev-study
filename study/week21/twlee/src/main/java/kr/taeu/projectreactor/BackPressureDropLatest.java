package kr.taeu.projectreactor;

import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class BackPressureDropLatest {
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(300L))
                .doOnNext(d -> System.out.println("emmited by original: " + d))
                .onBackpressureBuffer(2,
                        d -> System.out.println("overflow and dropped: " + d),
                        BufferOverflowStrategy.DROP_LATEST)
                .doOnNext(d -> System.out.println("emmited by buffer: " + d))
                .publishOn(Schedulers.parallel(), false, 1)
                .subscribe(d -> {
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                            }
                            System.out.println("onNext: " + d);
                        },
                        e -> System.out.println("onError: " + e));

        Thread.sleep(3000L);
    }
}
