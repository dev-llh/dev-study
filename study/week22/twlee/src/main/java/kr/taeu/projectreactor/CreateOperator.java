package kr.taeu.projectreactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class CreateOperator {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;
        Flux.create((FluxSink<String> sink) -> {
                    IntStream.range(1, tasks)
                            .forEach(n -> sink.next(doTasks(n)));
                })
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(n -> System.out.println(Thread.currentThread().getName() + " create: " + n))
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
