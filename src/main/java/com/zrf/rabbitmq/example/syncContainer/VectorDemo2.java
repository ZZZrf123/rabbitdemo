package com.zrf.rabbitmq.example.syncContainer;

import com.zrf.rabbitmq.annoations.NotThreadSafe;
import com.zrf.rabbitmq.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class VectorDemo2 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static List<Integer> list = new Vector<>();

    public static void main(String[] args) throws Exception {
        while (true){
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Thread thread = new Thread() {
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    list.remove(i);
                }
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i);
                }

            }
        };

        thread.start();
        thread2.start();
    }
    }

    private static void update(int i) {
        list.add(i);
    }
}
