package com.concurrency;

// A Simple example of the basic divide-and-conquer strategy.
// In this case, RecursiveAction is used.

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

// A ForkJoinTask (via RecursiveAction) that transforms
// the elements in an array of doubles into their square roots.
class SqrtTransform extends RecursiveAction {
    // The threshold value is arbitrarily set at 1000 in this example.
    // In real-world code,its optimal value can be determined by
    // profiling and experimentation.
    final int seqThreshold = 1000;

    // Array to be accessed
    double []data;

    // Determines what part of data to process.
    int start, end;

    SqrtTransform(double []val, int s, int e) {
        data = val;
        start = s;
        end = e;
    }

    // This is the method in which parallel computation will occur.
    protected void compute() {

        // If number of elements is below the sequential threshold, then process sequentially.
        if ((end - start) < seqThreshold) {
            // Transform each element into its square root.
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        }
        else {
            // Otherwise, continue ot break the data into smaller pieces.

            // Find the midpoint.
            int middle = (start + end) / 2;

            // Invoke new tasks, using the subdivided data.
            invokeAll(new SqrtTransform(data, start, middle), new SqrtTransform(data, middle, end));
        }
    }
}

// Demonstrate parallel execution

public class RecursiveActionClass {
    public static void main(String[] args) {
        // Create a task pool.
        ForkJoinPool joinPool = new ForkJoinPool();

        double []num = new double[100000];

        // Give num some values.
        for (int i = 0; i < num.length; i++) {
            num[i] = i;
        }
        System.out.println("A portion of the original sequence: ");

        for (int i = 0; i < 10; i++){
            System.out.print(num[i] + " ");
        }
        System.out.println("\n");

        SqrtTransform task = new SqrtTransform(num, 0, num.length);

        // Start the main ForkJoinTask.
        joinPool.invoke(task);

        System.out.println("A portion of the transformed sequence (to four decimal places): ");
        for (int i = 0; i < 10; i++){
            System.out.printf("%.4f ", num[i]);
        }
        System.out.println();
    }
}
