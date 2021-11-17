package com.concurrency;

import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

// ThreadFactory class
class CustomThreadFactory implements ThreadFactory{

    // stores the thread count
    private int count = 0;

    public int getCount(){ return count;}

    // Factory method
    @Override
    public Thread newThread(Runnable command){
        count++;
        return new Thread(command);
    }
}

public class ThreadFactoryInterfacePart1 {
    public static void main(String[] args) {
        CustomThreadFactory threadFactory = new CustomThreadFactory();

        // Creating Runnable objects using the lambda expression
        Runnable command1 = () -> System.out.println("Command 1 executed");
        Runnable command2 = () -> System.out.println("Command 2 executed");
        Runnable command3 = () -> System.out.println("Command 3 executed");
        Runnable command4 = () -> System.out.println("Command 4 executed");
        Runnable command5 = () -> System.out.println("Command 5 executed");

        // Putting the commands in an ArrayList
        ArrayList<Runnable> array = new ArrayList<>(5);
        array.add(command1);
        array.add(command2);
        array.add(command3);
        array.add(command4);
        array.add(command5);

        // creating threads and running them
        for (Runnable command : array){
            threadFactory.newThread(command).start();
        }

        // print the thread count
        System.out.println("Total number of threads created using Custom ThreadFactory = "+threadFactory.getCount());
    }
}