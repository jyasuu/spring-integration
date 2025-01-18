package com.jyasu.example;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    private final StampedLock lock = new StampedLock();
    private int value = 0;

    public void write(int newValue) {
        long stamp = lock.writeLock();
        try {
            value = newValue;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public int read() {
        long stamp = lock.tryOptimisticRead();
        int currentValue = value;

        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentValue = value;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return currentValue;
    }

    public static void main(String[] args) throws InterruptedException {
        StampedLockExample example = new StampedLockExample();

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
