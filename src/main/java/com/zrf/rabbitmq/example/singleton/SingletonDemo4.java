package com.zrf.rabbitmq.example.singleton;


import com.zrf.rabbitmq.annoations.NotRecommend;
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
@NotThreadSafe
public class SingletonDemo4 {

    //单例对象 懒汉模式
    private static SingletonDemo4 singletonDemo1 = null;

    //静态工厂方法
    public  static  SingletonDemo4 init(){
        if(singletonDemo1 == null){
            synchronized (SingletonDemo4.class){//双重检测机制 //同步锁
                if(singletonDemo1 == null){
                    return new SingletonDemo4();
                }
            }
        }
        return singletonDemo1;
    }

    private SingletonDemo4(){

    }

}
