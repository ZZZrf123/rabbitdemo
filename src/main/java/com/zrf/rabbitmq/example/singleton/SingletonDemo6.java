package com.zrf.rabbitmq.example.singleton;


import com.zrf.rabbitmq.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 饿汉模式
 * 单例的实例在类装载的时候创建
 */
@Slf4j
@ThreadSafe
public class SingletonDemo6 {

    private SingletonDemo6(){
        //如果构造方法过多会慢
        //可能造成资源的浪费
    }
    //单例对象
    private static SingletonDemo6 singletonDemo1 = null;

    static{
        singletonDemo1 = new SingletonDemo6();
    }
    //静态工厂方法
    public static SingletonDemo6 getInstance(){

        return singletonDemo1;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());

        System.out.println(getInstance().hashCode());
    }

}
