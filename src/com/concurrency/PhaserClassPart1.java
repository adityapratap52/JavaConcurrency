package com.concurrency;

import java.util.concurrent.Phaser;

class MyThread14 implements Runnable {
    Phaser phaser;
    String name;

    MyThread14 (Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
        phaser.register();
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " Beginning Phase One");
        phaser.arriveAndAwaitAdvance();     // Single arrival

        // Pause a bit to prevent jumbled output. This is for illustration only.
        // It is not required for the proper operation of the phaser.
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Thread " + name + " Beginning Phase Two");
        phaser.arriveAndAwaitAdvance();     // Single arrival

        // Pause a bit to prevent jumbled output. This is for illustration only.
        // It is not required for the proper operation of the phaser.
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Thread " + name + " Beginning Phase Three");
        phaser.arriveAndDeregister();   // Signal arrival and deregister
    }
}


public class PhaserClassPart1 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        int curPhase;

        System.out.println("Starting");

        new Thread(new MyThread14(phaser, "A")).start();
        new Thread(new MyThread14(phaser, "B")).start();
        new Thread(new MyThread14(phaser, "C")).start();

        // Wait for all thread to complete phase one.
        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete");

        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete");

        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete");

        // Deregister the main thread
        phaser.arriveAndDeregister();

        if (phaser.isTerminated()) System.out.println("The Phaser is terminated");
    }
}
