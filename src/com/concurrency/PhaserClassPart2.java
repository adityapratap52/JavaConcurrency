package com.concurrency;

import java.util.concurrent.Phaser;

// Extend Phaser and override onAdvance() so that only a specific
// number of phases are executed.
class MyPhaser extends Phaser {
    int numPhases;

    MyPhaser(int parties, int phaseCount) {
        super(parties);
        numPhases = phaseCount - 1;
    }

    // Override onAdvance() to execute the specified number of phases

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        // This println() statement is for illustration only.
        // Normally, onAdvance() will not display output.
        System.out.println("Phase " + phase + " completed.\n");

        // If all phases have completed, return true
        if (phase == numPhases || registeredParties == 0)   return true;

        // Otherwise, return false.
        return false;
    }
}

class MyThread15 implements Runnable {
    Phaser phaser;
    String name;

    MyThread15(Phaser p, String n) {
        phaser = p;
        name = n;
        phaser.register();
    }

    @Override
    public void run() {
        while (!phaser.isTerminated()){
            System.out.println("Thread " + name + " Beginning Phase " + phaser.getPhase());

            phaser.arriveAndAwaitAdvance();

            // Pause a bit to prevent jumbled output. This is for illustration only.
            // It is not required for the proper operation of the phaser.
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

public class PhaserClassPart2 {
    public static void main(String[] args) {
        MyPhaser myPhaser = new MyPhaser(1,5);

        System.out.println("Starting..\n");

        new Thread(new MyThread15(myPhaser, "A")).start();
        new Thread(new MyThread15(myPhaser, "B")).start();
        new Thread(new MyThread15(myPhaser, "C")).start();

        // Wait for the specified number of phases to complete.
        while (!myPhaser.isTerminated()) {
            myPhaser.arriveAndAwaitAdvance();
        }

        System.out.println("The Phaser is terminated");
    }
}
