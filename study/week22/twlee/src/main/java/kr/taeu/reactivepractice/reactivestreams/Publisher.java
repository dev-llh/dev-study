package kr.taeu.reactivepractice.reactivestreams;

public interface Publisher<T> {
    void subscribe(Subscriber<? super T> subscriber);
}
