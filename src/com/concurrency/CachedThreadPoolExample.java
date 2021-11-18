package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CachedThreadPoolExample {
    // In previous FixedPoolExample we created 100 threads and execute the task
    // but CachedThreadPool is not taken any argument it is dynamically create
    // threads means --->  if task is 100 then many threads are created automatically
    private static ExecutorService executorService = Executors.newCachedThreadPool(); // 1 is slower than 100

    public static Future<Double> getRandom(int i){
        return executorService.submit(() ->{
            Thread.sleep((int)(Math.random()*200));
            System.out.println(i+" Thread id: "+Thread.currentThread().getId());
            return Math.random();
        });
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++){
            getRandom(i);
        }

        executorService.shutdown();
    }
}
