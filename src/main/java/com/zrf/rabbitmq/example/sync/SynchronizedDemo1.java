package com.zrf.rabbitmq.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedDemo1 {

    //修饰代码块
    public void test1(){
        synchronized (this){
            for(int i = 0;i<10;i++){
                System.out.println("test1:" + i);
            }
        }
    }

    public synchronized void test2(int j){
        for(int i = 0;i<10;i++){
            System.out.println("test2:" + i + "and j=" +j);
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo1 synchronizedDemo1 = new SynchronizedDemo1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronizedDemo1.test1();

            }
        });
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
