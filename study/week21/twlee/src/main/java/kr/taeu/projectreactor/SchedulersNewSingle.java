package kr.taeu.projectreactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulersNewSingle {
    public static void main(String[] args) throws InterruptedException {
        doTask("task1")
                .subscribe(d -> System.out.println(Thread.currentThread().getName() + " onNext: " + d));

        doTask("task2")
                .subscribe(d -> System.out.println(Thread.currentThread().getName() + " onNext: " + d));

        Thread.sleep(1000);
    }

    private static Flux<Integer> doTask(String name) {
        return Flux.just(1, 2, 3, 4)
                .publishOn(Schedulers.newSingle("new-single", true)) // 데몬스레드 여부, 주 스레드가 종료되면 자동 종료
                .filter(d -> d > 3)
                .doOnNext(d -> System.out.println(Thread.currentThread().getName() + ", " + name + ", filter: " + d))
                .map(d -> d * 10)
                .doOnNext(d -> System.out.println(Thread.currentThread().getName() + ", " + name + " map: " + d));
    }
}
