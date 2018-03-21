package com.bzj.java.pattern.singleton;

/**
 * 饿汉式单例
 *
 * 缺陷:容易造成资源浪费,影响初始化加载耗时
 *
 *
 * @author aaronbai
 * @create 2018-03-21 14:38
 **/
public class SingletonHunger {

    private static SingletonHunger singleton =  new SingletonHunger();

    private SingletonHunger(){

    }

    public static SingletonHunger getSingleton(){
        return singleton;
    }
}
