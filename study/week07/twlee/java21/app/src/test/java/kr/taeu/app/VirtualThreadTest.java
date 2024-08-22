package kr.taeu.app;

import org.junit.jupiter.api.Test;

public class VirtualThreadTest {

    @Test
    void platformThread() {
        Task task1 = new Task("Thread 1");
        Task task2 = new Task("Thread 2");

        Thread thread1 = Thread.ofPlatform().name("Platform Thread1").unstarted(task1);
        Thread thread2 = Thread.ofPlatform().name("Platform Thread2").unstarted(task2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted while waiting.");
        }

        System.out.println("Main End");
    }

    @Test
    void virtualThread() {
        Task task1 = new Task("Thread 1");
        Task task2 = new Task("Thread 2");

        Thread thread1 = Thread.ofVirtual().name("Virtual Thread1").unstarted(task1);
        Thread thread2 = Thread.startVirtualThread(task2);

        thread1.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted while waiting.");
        }

        System.out.println("Main End");
    }

    static class Task implements Runnable {
        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                Thread current = Thread.currentThread();
                System.out.println(name + " is running: " + i + ", currentThread: " + current.getName() + ", isVirtual: " + current.isVirtual());
                try {
                    Thread.sleep(1000); // 1초 동안 대기
                } catch (InterruptedException e) {
                    System.out.println(name + " was interrupted.");
                }
            }
            System.out.println(name + " has finished.");
        }
    }
}
