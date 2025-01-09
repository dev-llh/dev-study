package kr.taeu.reactivepractice.reactivestreams;

public interface Subscriber<T> {
    void onSubscribe(Subscription subscription);
    void onNext(T t);
    void onError(Throwable throwable);
    void onComplete();
}
