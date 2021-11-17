package com.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

// many threads in one thread pool
class Command implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello");
    }
}

public class ThreadFactoryInterfacePart2 {
    public static void main(String[] args) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        for (int i=1; i<10; i++){

            // Creating new threads with the default ThreadFactory
            Thread thread = threadFactory.newThread(new Command());

            // print the thread names
            System.out.println("Name given by threadFactory = "+thread.getName());

            thread.start();
        }
    }
}
