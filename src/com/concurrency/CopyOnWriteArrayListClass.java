package com.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListClass {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();  // throws ConcurrentModificationException
//        List<String> list = new CopyOnWriteArrayList<>();
        list.add("Squirrel");
        list.add("Mouse");
        list.add("Hamster");

        for (String s : list){
            System.out.println(s);
            list.add(s);
        }
        System.out.println(list);
    }
}
