package kr.taeu.reactivepractice.reactivestreams;

public interface Subscription {
    void request(long n);
    void cancel();
}
