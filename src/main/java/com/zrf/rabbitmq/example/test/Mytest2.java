package com.zrf.rabbitmq.example.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Mytest2 {

    public static int clientCount = 5000;

    public static int threadCount = 1;

    public static int count = 0;

    public static void add(){
        count++;
    }

    public static void main(String[] args) throws  Exception{
        ExecutorService executorService  = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadCount);

        CountDownLatch countDownLatch = new CountDownLatch(clientCount);

        for(int i = 0;i<clientCount;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add();
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();

                }
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:" + count);

    }
}
