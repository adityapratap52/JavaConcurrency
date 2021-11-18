package com.concurrency;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentSkipListClass {
    public static void main(String[] args) {
        Set<String> set = new ConcurrentSkipListSet<>();
        set.add("Tim");
        set.add("Pascal");
        set.add("Elias");

        for (String s : set){
            System.out.println(s);     // print in alphabetical order
        }
    }
}
