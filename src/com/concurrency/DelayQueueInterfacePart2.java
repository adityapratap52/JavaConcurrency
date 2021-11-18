package com.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayObject1 implements Delayed {

    private String name;
    private long time;

    // Constructor of DelayObject
    public DelayObject1(String name, long delayTime)
    {
        this.name = name;
        this.time = System.currentTimeMillis()
                + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit)
    {
        long diff = time - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed obj)
    {
        if (this.time < ((DelayObject1)obj).time) {
            return -1;
        }
        if (this.time > ((DelayObject1)obj).time) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString()
    {
        return "\n{"
                + "name=" + name
                + ", time=" + time
                + "}";
    }
}

public class DelayQueueInterfacePart2 {
    public static void main(String[] args) {
        BlockingQueue<DelayObject1> DQ
                = new DelayQueue<>();

        // Add numbers to end of DelayQueue
        // using add() method
        DQ.add(new DelayObject1("A", 1));
        DQ.add(new DelayObject1("B", 2));
        DQ.add(new DelayObject1("C", 3));
        DQ.add(new DelayObject1("D", 4));

        // print queue
        System.out.println("DelayQueue: "
                + DQ);

        // print the head using peek() method
        System.out.println("Head of DelayQueue: "
                + DQ.peek());

        // print the size using size() method
        System.out.println("Size of DelayQueue: "
                + DQ.size());

        // remove the head using poll() method
        System.out.println("Head of DelayQueue: "
                + DQ.poll());

        // print the size using size() method
        System.out.println("Size of DelayQueue: "
                + DQ.size());

        // clear the DelayQueue using clear() method
        DQ.clear();
        System.out.println("Size of DelayQueue"
                + " after clear: "
                + DQ.size());
    }
}
