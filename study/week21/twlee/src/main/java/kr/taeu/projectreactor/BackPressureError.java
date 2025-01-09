package kr.taeu.projectreactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class BackPressureError {
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(1)) // 0.001초 emit
                .onBackpressureError()
                .doOnNext(d -> System.out.println("doOnNext: " + d)) // Publisher가 emit한 데이터를 확인하거나 추가 동작 정의, 디버깅용도로 사용
                .publishOn(Schedulers.parallel())
                .subscribe(d -> {
                            try {
                                Thread.sleep(5L); // 0.005초 처리
                            } catch (InterruptedException e) {}
                            System.out.println("onNext: " + d);
                        },
                        e -> System.out.println("onError: " + e));

        Thread.sleep(2000);
    }
}
