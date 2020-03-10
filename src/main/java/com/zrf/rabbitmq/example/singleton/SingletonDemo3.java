package com.zrf.rabbitmq.example.singleton;


import com.zrf.rabbitmq.annoations.NotRecommend;
import com.zrf.rabbitmq.annoations.NotThreadSafe;
import com.zrf.rabbitmq.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式
 * 单例的实例在第一次使用的时候创建
 */
@Slf4j
@ThreadSafe
@NotRecommend
public class SingletonDemo3 {

    //单例对象 懒汉模式
    private static SingletonDemo3 singletonDemo1 = null;

    //静态工厂方法
    public  static synchronized SingletonDemo3 init(){
        if(singletonDemo1 == null){
            return new SingletonDemo3();
        }
        return singletonDemo1;
    }

    private SingletonDemo3(){

    }

}
