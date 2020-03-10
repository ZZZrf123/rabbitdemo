package com.zrf.rabbitmq.example.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class MyTest {



    public static int clientCount = 5000;

    public static int threadCount = 1;

    public static int count = 0;


    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadCount);
        final CountDownLatch countDownLatch = new CountDownLatch(clientCount);

        for(int i = 0;i<clientCount;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add();
                        semaphore.release();
                    } catch (InterruptedException e) {
                        System.out.println("错误" +e);
                    }

                    countDownLatch.countDown();
                }
            });

        }

        countDownLatch.await();
        executorService.shutdown();

        log.info("count:{}", count);
    }

    private static void add() {
        count++;
    }
}
