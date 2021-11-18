package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceBasic {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> System.out.println("1: "+Math.random()+" Thread id: "+Thread.currentThread().getId()));
        executorService.execute(() -> System.out.println("2: "+Math.random()+" Thread id: "+Thread.currentThread().getId()));
        executorService.execute(() -> System.out.println("3: "+Math.random()+" Thread id: "+Thread.currentThread().getId()));
        executorService.execute(() -> System.out.println("4: "+Math.random()+" Thread id: "+Thread.currentThread().getId()));

        executorService.shutdown();
    }
}
