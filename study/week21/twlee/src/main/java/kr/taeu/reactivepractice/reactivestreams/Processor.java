package kr.taeu.reactivepractice.reactivestreams;

public interface Processor<T, R> extends Subscriber<T>, Publisher<T>{
}
