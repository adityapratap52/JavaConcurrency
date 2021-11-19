package com.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SearchWork extends RecursiveTask<Integer>{
    int []arr;
    int start, end;
    int searchEle;

    // Constructor for initialising the member variables
    public SearchWork(int []arr, int start, int end, int searchEle){
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchEle = searchEle;
    }

    protected Integer compute(){            // It is work as call method by it is not as a call
        // Returns the frequency computed by countFreq
        return countFreq();
    }

    // A method to return the count of number of times the searched element has occurred
    private Integer countFreq(){
        // At the beginning the c is set to 0
        int c = 0;

        for(int i = start; i <= end; i++){
            // if element is present in array
            if (arr[i] == searchEle){
                // Increment the c by 1
                c = c + 1;
            }
        }
        //return the number of times the searched element has occurred
        return c;
    }
}

public class RecursiveTaskClass {
    public static void main(String[] args) {
        // Custom input array elements
        int []arr = {50,12,45,67,124,50,89,9,56,88,99,50,44,11,66,77};

        // the element that is to be searched in the arr
        int searchEle = 50;

        // assigning values to the starting and ending indices
        int start = 0;
        int end = arr.length - 1;

        // Creating an object of the ForkJoinPool Class
//        ForkJoinPool joinPool = ForkJoinPool.commonPool();
        ForkJoinPool joinPool = new ForkJoinPool();

        // Now creating an object of the SearchWork class
        SearchWork searchWork = new SearchWork(arr,start,end,searchEle);

        // submitting the task to the ForkJoinPool
        int freq = joinPool.invoke(searchWork);

        System.out.println("The number "+searchEle+" is found "+freq+" times.");
    }
}
