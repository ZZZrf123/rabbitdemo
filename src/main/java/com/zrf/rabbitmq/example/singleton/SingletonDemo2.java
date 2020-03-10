package com.zrf.rabbitmq.example.singleton;


import com.zrf.rabbitmq.annoations.NotThreadSafe;
import com.zrf.rabbitmq.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 饿汉模式
 * 单例的实例在类装载的时候创建
 */
@Slf4j
@ThreadSafe
public class SingletonDemo2 {

    //单例对象
    private static SingletonDemo2 singletonDemo1 = new SingletonDemo2();

    //静态工厂方法
    public static SingletonDemo2 init(){

        return singletonDemo1;
    }

    private SingletonDemo2(){
        //如果构造方法过多会慢
        //可能造成资源的浪费
    }

}
