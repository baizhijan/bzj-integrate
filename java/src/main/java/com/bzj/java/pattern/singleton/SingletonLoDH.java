package com.bzj.java.pattern.singleton;

/**
 * 线程安全兼顾效率单例
 * <p>
 * 为例保证线程安全同时兼顾资源单例
 *
 * @author aaronbai
 * @create 2018-03-21 15:09
 **/
public class SingletonLoDH {

    private static class HolderClass {
        private final static SingletonLoDH instance = new SingletonLoDH();
    }

    private SingletonLoDH() {
    }

    public static SingletonLoDH getSingleton() {
        return HolderClass.instance;
    }
}
