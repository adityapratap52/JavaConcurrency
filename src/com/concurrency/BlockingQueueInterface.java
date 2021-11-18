package com.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueInterface {
    public static void main(String[] args) throws InterruptedException{

        // define capacity of ArrayBlockingQueue
        int capacity = 5;

        // create object of ArrayBlockingQueue
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(capacity);

        queue.put("StarWars");
        queue.put("SuperMan");
        queue.put("Flash");
        queue.put("BatMan");
        queue.put("Avengers");

        System.out.println("Queue contains "+queue);

        queue.remove();
        queue.remove();

        queue.put("CaptainAmerica");
        queue.put("Thor");  //---------Create diagram----------ok
        System.out.println("Queue contains "+queue);
    }
}
