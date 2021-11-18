package com.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// The DelayObject for DelayQueue
// It must implement Delayed and
// its getDelay() and compareTo() method
class DelayObject implements Delayed{

    private String name;
    private  long time;

    public DelayObject(String name, long delayTime){
        this.name = name;
        this.time = System.currentTimeMillis()+delayTime;
    }

    // Implementing getDelay()method of Delayed

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = time - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    // Implementing compareTo() method of Delayed

    @Override
    public int compareTo(Delayed obj) {
        if (this.time < ((DelayObject)obj).time) return -1;
        if (this.time > ((DelayObject)obj).time) return 1;

        return 0;
    }

    // Implementing toString() method of Delayed

    @Override
    public String toString() {
        return "\n{" +
                "name='" + name +
                ", time=" + time +
                '}';
    }
}

// Driver class
public class DelayDequeInterface {
    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<DelayObject> dq = new DelayQueue<>();

        // Add numbers to end of DelayQueue
        dq.add(new DelayObject("A",1));
        dq.add(new DelayObject("B",2));
        dq.add(new DelayObject("C",3));
        dq.add(new DelayObject("D",4));

        System.out.println("Delay Queue: "+dq);

        BlockingQueue<DelayObject> dq2 = new DelayQueue<>(dq);
        System.out.println("DelayQueue: "+dq2);
    }
}
