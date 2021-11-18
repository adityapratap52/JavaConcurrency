package com.concurrency;

public class SynchronizedLock {
    public static int counter = 0;
    public static Object lock = new Object();

    public static void incrementCounter(){
        synchronized (lock){          // you can use concurrent lock in this place

            int current = counter;
            System.out.println("Before: "+counter+" Current thread: "+Thread.currentThread().getId());
            counter = current +1;
            System.out.println("After: "+counter+" Current thread: "+Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++){
            Thread t = new Thread(() -> incrementCounter());
            t.start();
        }
    }
}
