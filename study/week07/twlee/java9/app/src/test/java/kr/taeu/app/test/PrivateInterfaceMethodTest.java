package kr.taeu.app.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PrivateInterfaceMethodTest {

    @Test
    public void test () {
        TestClass testClass = new TestClass();

        testClass.testMethod();

        TestInterface.testStaticMethod1();
        /*
        public method
        hello private method
        hello static method
         */

        HashMap<String, String> hashMap = new HashMap<>() {{
            put("tet", "t");
        }};
    }

    public interface TestInterface {
        private void testMethod1() {
            System.out.println("hello private method");
        }

        private static void testStaticMethod1() {
            System.out.println("hello static method");
        }

        default void testMethod() {
            System.out.println("public method");

            testMethod1();
            testStaticMethod1();
        }
    }

    public static class TestClass implements TestInterface {
        @Override
        public void testMethod() {
            TestInterface.testStaticMethod1();
        }
    }
}
