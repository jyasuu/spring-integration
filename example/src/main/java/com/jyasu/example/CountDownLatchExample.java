package com.jyasu.example;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 3;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        Runnable worker = () -> {
            System.out.println(Thread.currentThread().getName() + " is working...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            latch.countDown();
        };

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(worker).start();
        }

        latch.await(); // Wait until all threads finish
        System.out.println("All threads are done!");
    }
}
