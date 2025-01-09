package kr.taeu.projectreactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulersImmediate {
    public static void main(String[] args) throws InterruptedException {
        Flux.just(1, 2, 3, 4)
                .publishOn(Schedulers.parallel())
                .filter(d -> d % 2 == 0)
                .doOnNext(d -> System.out.println(Thread.currentThread().getName() + " filter: " + d))
                .publishOn(Schedulers.immediate())
                .map(d -> d * 2)
                .doOnNext(d -> System.out.println(Thread.currentThread().getName() + " map: " + d))
                .subscribe(d -> System.out.println(Thread.currentThread().getName() +  " onNext: " + d));

        Thread.sleep(1000);
    }
}
