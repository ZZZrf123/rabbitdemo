package com.zrf.rabbitmq.example.singleton;


import com.zrf.rabbitmq.annoations.Recommend;
import com.zrf.rabbitmq.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 枚举模式
 * 最安全的
 *
 */
@Slf4j
@ThreadSafe
@Recommend
public class SingletonDemo7 {

    private SingletonDemo7(){

    }


    //静态工厂方法
    public static SingletonDemo7 getInstance(){

        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonDemo7 singletonDemo7;

        //jvm保证这个方法只被调用一次
        Singleton(){
            singletonDemo7 = new SingletonDemo7();
        }

        public SingletonDemo7 getInstance(){
            return singletonDemo7;
        }
    }



}
