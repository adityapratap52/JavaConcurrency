package com.concurrency;

public class DeadLock {

    public static void run(){
        final  String resource1 = "stuck";
        final  String resource2 = "forever";

        // first get a lok on resource1 and then try to obtain one on resource2
        Thread thread1 = new Thread(() ->{
           synchronized (resource1){
               System.out.println("Thread1 has a lock on resource1");

               // make sure it's not too fast and avoids deadlock
               try {
                   Thread.sleep(500);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }

               synchronized (resource2){
                   System.out.println("Thread1 has a lock on resource2");
               }
           }
        });

        // first get a lock on resource2 and then try to obtain one on resource1
        Thread thread2 = new Thread(() -> {
            synchronized (resource2){       // solve deadlock problem -> change in resource1
                System.out.println("Thread2 has a lock on resource2");

                // make sure it's not too fast and avoids deadlock
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                synchronized (resource1){       // solve deadlock problem -> change in resource1
                    System.out.println("Thread2 has a lock on resource1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        run();
    }
}
