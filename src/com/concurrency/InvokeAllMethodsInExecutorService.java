package com.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvokeAllMethodsInExecutorService {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callables = new ArrayList<>();
        callables.add(() -> 1);
        callables.add(() -> 2);

        try {
            Thread.sleep(100);
            System.out.println(executorService.invokeAll(callables));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}

