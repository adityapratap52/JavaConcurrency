package com.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockClass {
    public static int counter = 0;
    public static Lock lock = new ReentrantLock();

    public static void incrementCounter(){
        lock.lock();
        int current = counter;
        System.out.println("Before: "+counter+" Current thread: "+Thread.currentThread().getId());
        counter = current +1;
        System.out.println("After: "+counter+" Current thread: "+Thread.currentThread().getId());

        lock.unlock();      // preferred to use in finally block
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++){
            Thread t = new Thread(() -> incrementCounter());
            t.start();
        }
    }
}
