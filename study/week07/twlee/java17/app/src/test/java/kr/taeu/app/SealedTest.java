package kr.taeu.app;

public class SealedTest {

    static sealed class Animal
            permits Dog, Cat, Bear
    { }

    static final class Dog extends Animal { }

    static non-sealed class Cat extends Animal { }

    static sealed class Bear extends Animal
//            permits HalfMoonBear 생략가능
    { }

    static final class HalfMoonBear extends Bear { }
}
