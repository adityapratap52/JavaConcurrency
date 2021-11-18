package com.concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class Worker1 implements Runnable{
    String name;
    ReentrantLock reentrantLock;
    public Worker1(ReentrantLock r1,String n){
        reentrantLock = r1;
        name = n;
    }

    public void run(){
        boolean done = false;
        while (!done){
            // Getting outer Lock
            boolean ans = reentrantLock.tryLock();

            // return true if lock is free
            if (ans){
                try {
                    Date date = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    System.out.println("task name - "+name+" outer lock acquired at "+ft.format(date)+" Doing outer work");
                    Thread.sleep(1500);

                    // Getting Inner Lock
                    reentrantLock.lock();
                    try {
                        date = new Date();
                        ft = new SimpleDateFormat("hh:mm:ss");
                        System.out.println("task name - "+name+" inner lock acquired at "+ft.format(date)+" Doing inner work");
                        System.out.println("Lock Hold Count - "+reentrantLock.getHoldCount());
                        Thread.sleep(1500);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }finally {
                        // Inner lock release
                        System.out.println("task name - "+name+" releasing inner lock");
                        reentrantLock.unlock();
                    }
                    System.out.println("Lock Hold Count - "+reentrantLock.getHoldCount());
                    System.out.println("task name - "+name+" work done");

                    done = true;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    // Outer Lock release
                    System.out.println("task name - "+name+" releasing outer lock");
                    reentrantLock.unlock();
                    System.out.println("Lock Hold Count - "+reentrantLock.getHoldCount());
                }
            }else {
                System.out.println("task name - "+name+" waiting for lock");
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

public class LockInterfacePart2 {
    static final int MAX_T = 2;
    public static void main(String[] args) {
        ReentrantLock rel = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        Runnable w1 = new Worker1(rel,"Job1");
        Runnable w2 = new Worker1(rel,"Job2");
        Runnable w3 = new Worker1(rel,"Job3");
        Runnable w4 = new Worker1(rel,"Job4");
        pool.execute(w1);
        pool.execute(w2);
        pool.execute(w3);
        pool.execute(w4);

        pool.shutdown();
    }
}
