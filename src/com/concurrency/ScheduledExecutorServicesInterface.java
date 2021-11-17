package com.concurrency;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Task implements Runnable{

    int num;

    Task(int num){this.num = num;}

    public void run(){
        System.out.println("Number: "+num+" Current Time: "+Calendar.getInstance().get(Calendar.SECOND));
    }
}

public class ScheduledExecutorServicesInterface {
    public static void main(String[] args) {
        System.out.println("A count-down-clock program that count 10 to 0");

        // creating a ScheduledExecutorService object
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(11);

        System.out.println("Current time: "+ Calendar.getInstance().get(Calendar.SECOND));
        for (int i=10; i>=0; i--){
            scheduler.schedule(new Task(i),10 - i, TimeUnit.SECONDS);
        }

        // remember to shut down the scheduler so that it no longer accepts any new tasks
        scheduler.shutdown();
    }
}
