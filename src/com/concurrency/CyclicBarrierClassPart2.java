package com.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class MyThread12 implements Runnable{
    CyclicBarrier cyclicBarrier;
    String name;

    MyThread12(CyclicBarrier cyclicBarrier, String name){
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }

    public void run(){
        System.out.println(name);

            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
    }
}

// An object of this class is called when the cyclic barrier end
class BarAction implements Runnable{
    public void run(){
        System.out.println("Barrier Reached");
    }
}

public class CyclicBarrierClassPart2 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4,new BarAction());

        System.out.println("Starting.....");

        new Thread(new MyThread12(cyclicBarrier,"A")).start();
        new Thread(new MyThread12(cyclicBarrier,"B")).start();
        new Thread(new MyThread12(cyclicBarrier,"C")).start();

        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("Main method close");
    }
}
