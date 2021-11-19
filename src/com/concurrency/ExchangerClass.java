package com.concurrency;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class MakeString implements Runnable {
    Exchanger<String> exchanger;
    String str;

    MakeString(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
        str = new String();

        new Thread(this).start();
    }

    public void run() {
        char ch = 'A';
        try {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++){
                    str += ch++;
                }
//                if (i == 2) {
//                    // Exchange the buffer and only wait for 250 milliseconds
//                    str = exchanger.exchange(str, 250, TimeUnit.MILLISECONDS);
//                    continue;
//                }

                // Exchange a full buffer for an empty one
                str = exchanger.exchange(str);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class UseString implements Runnable {
    Exchanger<String> exchanger;
    String str;

    UseString(Exchanger<String> exchanger){
        this.exchanger = exchanger;
        new Thread(this).start();
    }

    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
//                if (i == 2) {
//                    // Thread sleeps for 500 milliseconds cause timeout
//                    Thread.sleep(500);
//                    continue;
//                }

                // Exchange an empty buffer for a full one
                str = exchanger.exchange(new String());
                System.out.println("Got: " + str);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ExchangerClass {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new UseString(exchanger);
        new MakeString(exchanger);
    }
}
