package com.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Computation1 implements Runnable{
    public static int product = 0;

    @Override
    public void run(){
        product = 2 * 3;
        try {
            CyclicBarrierClassPart1.barrier.await();
        }catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
    }
}

class Computation2 implements Runnable{
    public static int sum = 0;

    @Override
    public void run(){
        // check if barrier is broken or not
        System.out.println("Is the barrier broken? - "+CyclicBarrierClassPart1.barrier.isBroken());
        sum = 10 + 20;
        try {
            CyclicBarrierClassPart1.barrier.await(3000, TimeUnit.MILLISECONDS);

            // number of parties waiting at the barrier
            System.out.println("Number of parties waiting at the barrier at this point = "+CyclicBarrierClassPart1.barrier.getNumberWaiting());
        }catch (InterruptedException | BrokenBarrierException | TimeoutException e){
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierClassPart1 implements Runnable{
    public static CyclicBarrier barrier = new CyclicBarrier(3);
    public static void main(String[] args) {
        // parent thread
        CyclicBarrierClassPart1 parentClass = new CyclicBarrierClassPart1();
        Thread myThread1 = new Thread(parentClass);
        myThread1.start();
    }

    @Override
    public void run() {
        System.out.println("Number of parties required to trip the barrier = "+barrier.getParties());
        System.out.println("Sum of product and sum = "+(Computation1.product+Computation2.sum));

        // objects on which the child thread has to run
        Computation1 comp1 = new Computation1();
        Computation2 comp2 = new Computation2();

        // creation of child thread
        Thread myThr1 = new Thread(comp1);
        Thread myThr2 = new Thread(comp2);

        // moving child thread to runnable state
        myThr1.start();
        myThr2.start();

        try{
            CyclicBarrierClassPart1.barrier.await();
        }catch (InterruptedException | BrokenBarrierException e){
            System.out.println(e.getMessage());
        }
        // barrier break as the number of thread waiting for the barrier at the point = 3
        System.out.println("Sum of product and sum = "+(Computation1.product+Computation2.sum));

        // Resetting the barrier
        barrier.reset();
        System.out.println("Barrier reset successful");
    }
}
