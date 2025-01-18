package com.jyasu.example;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int numberOfThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, () -> 
            System.out.println("All threads reached the barrier!")
        );

        Runnable worker = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier...");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " crossed the barrier!");
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(worker).start();
        }
    }
}
