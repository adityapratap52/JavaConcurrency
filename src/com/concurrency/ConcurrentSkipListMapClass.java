package com.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapClass {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentSkipListMap<>();
        map.put("Guiter","Jesse");
        map.put("Bass","Job");
        map.put("Piano","Sikha");

        for (String s : map.keySet()){
            System.out.println(s+" : "+map.get(s));
        }
    }
}
