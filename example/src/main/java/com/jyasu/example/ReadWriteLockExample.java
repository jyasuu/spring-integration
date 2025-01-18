package com.jyasu.example;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int value = 0;

    public void write(int newValue) {
        lock.writeLock().lock();
        try {
            value = newValue;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int read() {
        lock.readLock().lock();
        try {
            return value;
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample example = new ReadWriteLockExample();

        Runnable writer = () -> {
            for (int i = 0; i < 100; i++) {
                example.write(i);
            }
        };

        Runnable reader = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Read Value: " + example.read());
            }
        };

        Thread t1 = new Thread(writer);
        Thread t2 = new Thread(reader);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
