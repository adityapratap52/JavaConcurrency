package com.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

// Different Thread in different threadPool
class Command1 implements Runnable{
    @Override
    public void run(){

    }       // Aa56DD46EE36
}

public class ThreadFactoryInterfacePart3 {
    public static void main(String[] args) {
        for (int i=1; i<10; i++) {
            ThreadFactory threadFactory = Executors.defaultThreadFactory();
            Thread thread = threadFactory.newThread(new Command1());

            System.out.println("Name given by threadFactory = "+thread.getName());

            thread.start();
        }
    }
}
