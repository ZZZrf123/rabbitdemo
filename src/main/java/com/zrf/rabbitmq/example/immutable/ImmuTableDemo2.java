package com.zrf.rabbitmq.example.immutable;

import com.google.common.collect.Maps;
import com.zrf.rabbitmq.annoations.NotThreadSafe;

import java.util.Collections;
import java.util.Map;

@NotThreadSafe
public class ImmuTableDemo2 {

    private static Map<Integer,Integer> c = Maps.newHashMap();

    static{
        c.put(1,2);
        c.put(3,4);
        c.put(5,6);
        c = Collections.unmodifiableMap(c);
    }

    public static void main(String[] args) {
        c.put(1,3);
        System.out.println("{}"+c.get(1));
    }



}
