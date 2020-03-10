package com.zrf.rabbitmq.example.immutable;

import com.google.common.collect.Maps;
import com.zrf.rabbitmq.annoations.NotThreadSafe;

import java.util.Map;

@NotThreadSafe
public class ImmuTableDemo1 {

    private final static Integer a = 1;
    private final static String  b = "2";
    private final static Map<Integer,Integer> c = Maps.newHashMap();

    static{
        c.put(1,2);
        c.put(3,4);
        c.put(5,6);
    }

    public static void main(String[] args) {

    }

}
