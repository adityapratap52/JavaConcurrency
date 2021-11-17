package com.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierClass implements Runnable{

        private CyclicBarrier barrier;

        public CyclicBarrierClass(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

    @Override
    public void run() {
        try {
            LOG.info(Thread.currentThread().getName() +
                    " is waiting");
            barrier.await();
            LOG.info(Thread.currentThread().getName() +
                    " is released");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
