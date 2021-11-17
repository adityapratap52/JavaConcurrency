package com.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyThread implements Runnable{

    String name;
    CountDownLatch latch;

    MyThread(CountDownLatch latch, String name){
        this.latch = latch;
        this.name = name;
        new Thread(this);
    }

    public void run(){
        for (int i=0; i<4; i++){
            System.out.println(name+" "+i);
            latch.countDown();
        }
    }
}

public class ExecutorServiceInterface {
    public static void main(String[] args) {
        CountDownLatch cd1 = new CountDownLatch(4);       // 4 is the number of thread
        CountDownLatch cd2 = new CountDownLatch(4);
        CountDownLatch cd3 = new CountDownLatch(4);
        CountDownLatch cd4 = new CountDownLatch(4);

        ExecutorService service = Executors.newFixedThreadPool(6);    // 2 is number of threads in thread pool
        System.out.println("Starting");

        service.execute(new MyThread(cd1,"A"));
        service.execute(new MyThread(cd2,"B"));
        service.execute(new MyThread(cd3,"C"));
        service.execute(new MyThread(cd4,"D"));

//        service.shutdownNow();// If you use shutdownNow on this place then remaining task waiting(deadlock occur)

        try {                   // If you do not use await then main thread is running any time
            cd1.await();        // means A->B->"Done"->true->C->D [OR] D->B->"Done"->true->C->A
            cd2.await();
            cd3.await();
            cd4.await();
        }catch (InterruptedException e){
            System.out.println("InterruptedException Occur");
        }

        service.shutdown();             // It is shutdown all the task if all threads have terminated
//        service.shutdownNow();             // It is shutdown all the task immediately
        System.out.println("Done");
        System.out.println(service.isShutdown());   // check that all task is shutdown properly
    }
}
