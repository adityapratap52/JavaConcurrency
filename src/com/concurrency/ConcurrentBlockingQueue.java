package com.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConcurrentBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("Ram");
        queue.offer("Aditya");
        queue.offer("Arun");

        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.size());
        System.out.println(queue.contains("Aditya"));

        System.out.println(queue);

        try {
            queue.offer("Jim",200, TimeUnit.MILLISECONDS);
            queue.poll(300,TimeUnit.MILLISECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(queue);
    }
}
