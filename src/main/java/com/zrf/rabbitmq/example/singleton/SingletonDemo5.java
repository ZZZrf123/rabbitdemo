package com.zrf.rabbitmq.example.singleton;


import com.zrf.rabbitmq.annoations.NotThreadSafe;
import com.zrf.rabbitmq.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式
 * 双重检测机制
 * 会因为指令重排导致线程不安全
 * 
 */
@Slf4j
@ThreadSafe
public class SingletonDemo5 {

    //volatile 加上双重检测机制不允许指令重排
    private volatile static SingletonDemo5 singletonDemo1 = null;

    //静态工厂方法
    public  static SingletonDemo5 init(){
        if(singletonDemo1 == null){
            synchronized (SingletonDemo5.class){//双重检测机制 //同步锁
                if(singletonDemo1 == null){
                    return new SingletonDemo5();
                }
            }
        }
        return singletonDemo1;
    }

    private SingletonDemo5(){

    }

}
