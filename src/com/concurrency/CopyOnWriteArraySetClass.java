package com.concurrency;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetClass {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();
        set.add("Tim");
        set.add("Pascal");
        set.add("Elias");

        for (String s: set){
            System.out.println(s);
            set.add(s);
        }

        System.out.println(set);
    }
}
