package kr.taeu.projectreactor;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextExample2 {
    public static void main(String[] args) throws InterruptedException {
        String key1 = "company";
        String key2 = "name";

        Mono.deferContextual(ctx -> Mono.just(ctx.get(key1))) // Context에는 apple, bill이 존재, apple을 반환
                .publishOn(Schedulers.parallel())
                .contextWrite(ctx -> ctx.put(key2, "bill"))
                .transformDeferredContextual((mono, ctx) -> mono.map(d -> d + ", " + ctx.getOrDefault(key2, "steve"))) // 이 시점에 stream의 context에는 apple만 존재 그러므로 apple, steve가 반환
                .contextWrite(ctx -> ctx.put(key1, "apple"))
                .subscribe(d -> System.out.println("onNext: " + d));

        Thread.sleep(1000);
    }
}
