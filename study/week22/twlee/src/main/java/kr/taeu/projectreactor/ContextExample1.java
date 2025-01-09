package kr.taeu.projectreactor;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextExample1 {
    public static void main(String[] args) throws InterruptedException {
        Mono.deferContextual(ctx -> Mono.just("Hello " + " " + ctx.get("firstName"))
                        .doOnNext(d -> System.out.println(Thread.currentThread().getName() + ",just doOnNext: " + d)))
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .transformDeferredContextual((mono, ctx) -> mono.map(d -> d + " " + ctx.get("lastName")))
                .contextWrite(ctx -> ctx.put("lastName", "Jobs")) // put을 통해 context에 데이터를 쓰면, 불변 객체를 반환함
                .contextWrite(ctx -> ctx.put("firstName", "Steve"))
                .subscribe(d -> System.out.println(Thread.currentThread().getName() + ",onNext: " + d));

        Thread.sleep(1000);
    }
}
