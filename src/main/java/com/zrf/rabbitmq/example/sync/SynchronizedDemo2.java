package com.zrf.rabbitmq.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedDemo2 {

    //修饰代码块
    public static void test1(int j){
        synchronized (SynchronizedDemo2.class){
            for(int i = 0;i<10;i++){
                System.out.println("test1:" + i + "and j=" +j);
            }
        }
    }

    public static synchronized void test2(int j){
        for(int i = 0;i<10;i++){
            System.out.println("test2:" + i + "and j=" +j);
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo2 synchronizedDemo1 = new SynchronizedDemo2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronizedDemo1.test2(1);

            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronizedDemo1.test2(2);

            }
        });
    }
}
