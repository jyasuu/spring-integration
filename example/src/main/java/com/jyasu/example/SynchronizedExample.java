package com.jyasu.example;

public class SynchronizedExample {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public void nonSynchronizedIncrement() {
        synchronized (this) {
            counter++;
        }
    }

    public void bugIncrement() {
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedExample example = new SynchronizedExample();

        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                example.bugIncrement();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Counter: " + example.counter);
    }
}
