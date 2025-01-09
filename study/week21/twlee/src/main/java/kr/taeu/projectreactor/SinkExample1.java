package kr.taeu.projectreactor;

import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class SinkExample1 {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;

        Sinks.Many<String> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        IntStream.range(1, tasks)
                .forEach(n -> {
                    try {
                        new Thread(() -> {
                            unicastSink.emitNext(doTasks(n), Sinks.EmitFailureHandler.FAIL_FAST);
                            System.out.println(Thread.currentThread().getName() + " emited: " + n);
                        }).start();
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        System.out.println("e.getMessage() = " + e.getMessage());
                    }
                });

        unicastSink.asFlux()
                .publishOn(Schedulers.parallel())
                .map(res -> res + " success!")
                .doOnNext(n -> System.out.println(Thread.currentThread().getName() + " map: " + n))
                .publishOn(Schedulers.parallel())
                .subscribe(n -> System.out.println(Thread.currentThread().getName() + " subscribe: " + n));

        Thread.sleep(1000L);
    }

    private static String doTasks(int taskNum) {
        return "task " + taskNum + " result";
    }
}
