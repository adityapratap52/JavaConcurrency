package com.concurrency;

import java.util.concurrent.Semaphore;

class Shared{
    static int count = 0;
}

class MyThread1 extends Thread{
    Semaphore sem;
    String threadName;
    public MyThread1(Semaphore sem, String threadName){
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
    }

    @Override
    public void run() {

        // run by thread A
        if (this.getName().equals("A")){
            System.out.println("Starting "+threadName);
            try {
                // First, get a permit.
                System.out.println(threadName+" is waiting for a permit");

                // acquiring the lock
                sem.acquire();

                System.out.println(threadName+" gets a permit");

                // Now, accessing the shared resource.
                // other waiting threads will wait, until this
                // thread release the lock
                for (int i=0; i<5; i++){
                    Shared.count++;
                    System.out.println(threadName+" : "+Shared.count);

                    // Now, allowing a context switch -- if possible.
                    // for thread B to execute
                    Thread.sleep(10);
                }
            }catch (InterruptedException e){
                System.out.println(e);
            }

            // Release the permit.
            System.out.println(threadName+" release the permit");
            sem.release();
        }

        // run by thread B
        else {
            System.out.println("Starting "+threadName);
            try {
                // First, get a permit
                System.out.println(threadName+" is waiting for a permit");

                // acquiring the lock
                sem.acquire();

                System.out.println(threadName+" gets a permit.");

                // Now, accessing the shared resource
                // other waiting threads will wait, until this
                // thread release the lock
                for (int i=0; i<5; i++){
                    Shared.count--;
                    System.out.println(threadName+" : "+Shared.count);

                    // Now allowing a context switch -- if possible.
                    // for thread A to execute
                    Thread.sleep(10);
                }
            }catch (InterruptedException e){
                System.out.println(e);
            }
            // Release the permit.
            System.out.println(threadName+" release the permit.");
            sem.release();
        }
    }
}

// Driver class
public class SemaphoreClass {
    public static void main(String[] args) throws InterruptedException {
        // creating a Semaphore object
        // with number of permit 1
        Semaphore sem = new Semaphore(1);

        MyThread1 mt1 = new MyThread1(sem,"A");
        MyThread1 mt2 = new MyThread1(sem,"B");

        mt1.start();
        mt2.start();

        mt1.join();
        mt2.join();

        // count will always remain 0 after
        // both threads will complete their execution
        System.out.println("count: "+Shared.count);
    }
}
