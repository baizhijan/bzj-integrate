package com.bzj.java.pattern.singleton;

/**
 * 懒汉式单例
 * 保证线程安全采用双重验证
 * 缺点:锁机制会导致效率不高
 *
 * @author aaronbai
 * @create 2018-03-21 14:52
 **/
public class SingletonLazybones {

    private volatile static SingletonLazybones singleton;

    private SingletonLazybones() {
    }

    public static SingletonLazybones getSingleton() {
        if (singleton == null) {
            synchronized (SingletonLazybones.class) {
                if (singleton == null) {
                    singleton = new SingletonLazybones();
                }
            }
        }
        return singleton;
    }
}
