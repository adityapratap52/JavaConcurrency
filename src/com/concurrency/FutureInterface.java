package com.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//-----------------With Callable Interface----------------------------------
class CallableExp implements Callable{

    @Override
    public Object call() throws InterruptedException{     // you can use Integer,Long instead of Object
        Random generator = new Random();
        Integer randomNumber = generator.nextInt(5);

        Thread.sleep(randomNumber*1000);

        return randomNumber;
    }
}

public class FutureInterface {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // FutureTask is a concrete class that
        // implements both Runnable and Future
        FutureTask []randomNumberTask = new FutureTask[5];

        for (int i=0; i<5; i++) {
            Callable callable = new CallableExp();

            // Create the FutureTask with Callable
            randomNumberTask[i] = new FutureTask(callable);

            // As it implements Runnable, create Thread with FutureTask
            Thread myThread = new Thread(randomNumberTask[i]);
            myThread.start();
        }

        for (int i=0; i<5; i++){
            // As it implements Future, we can call get()
            System.out.println(randomNumberTask[i].get());

            // This method blocks till the result is obtained
            // The get method can throw checked exceptions
            // like when it is interrupted. This is the reason
            // for adding the throws clause to main
        }
    }
}


//-----------------With Runnable Interface----------------------------------
/*
class CallableExp implements Runnable{
    Object result = null;
    @Override
    public void run(){
        Random generator = new Random();
        Integer randomNumber = generator.nextInt(5);

        try {
            Thread.sleep(randomNumber*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = randomNumber;

        synchronized (this){
            notifyAll();
        }
    }

    synchronized public Object get() throws InterruptedException{
        while (result == null){
            wait();
        }
        return result;
    }
}

public class FutureInterface {

    public static void main(String[] args) throws InterruptedException {

        CallableExp []randomNumber = new CallableExp[5];

        for (int i=0; i<5; i++) {
            randomNumber[i] = new CallableExp();
            Thread myThread = new Thread(randomNumber[i]);
            myThread.start();
        }

        for (int i=0; i<5; i++){
            System.out.println(randomNumber[i].get());
        }
    }
}
 */