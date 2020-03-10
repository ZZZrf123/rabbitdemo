package com.zrf.rabbitmq.example.singleton;


import com.zrf.rabbitmq.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式
 * 单例的实例在第一次使用的时候创建
 */
@Slf4j
@NotThreadSafe
public class SingletonDemo1 {

    //单例对象 懒汉模式
    private static SingletonDemo1 singletonDemo1 = null;

    //静态工厂方法
    public static  SingletonDemo1 init(){
        if(singletonDemo1 == null){
            return new SingletonDemo1();
        }
        return singletonDemo1;
    }

    private SingletonDemo1(){

    }

}
