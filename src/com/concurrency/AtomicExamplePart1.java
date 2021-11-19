package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExamplePart1 {
    private static AtomicInteger ai = new AtomicInteger(0); // means -> start from 0

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i=0; i<10; i++){
//            executorService.submit(() -> System.out.println(ai.incrementAndGet()));   // ++0,++1,++2..
//            executorService.submit(() -> System.out.println(ai.getAndIncrement()));     // 0++,1++,2++..
//            executorService.submit(() -> System.out.println(ai.getAndSet(2)));     // get 0 and then 2,2,2,..
            executorService.submit(() -> System.out.println(ai.decrementAndGet()));     // -1,-2,..
        }
        executorService.shutdown();
    }
}
