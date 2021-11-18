package com.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ConcurrentHashMapClass {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("Ram","MCA");
        map.put("Arun","MBA");
        map.put("Tarun","Mtech");

        for (String s : map.keySet()){
            System.out.println(s+" in Hindustaan");
            map.remove("Arun");
        }
        System.out.println(map);
    }
}
