package kr.taeu.projectreactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureRequest {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnRequest(d -> System.out.println("request cnt: " + d))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        try {
                            Thread.sleep(1000L);
                            System.out.println("hookOnNext: " + value);
                            request(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }
}
