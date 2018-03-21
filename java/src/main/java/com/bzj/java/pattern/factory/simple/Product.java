package com.bzj.java.pattern.factory.simple;

/**
 * 抽象类
 *
 * @author aaronbai
 * @create 2018-03-20 18:05
 **/
public abstract class Product {

    //所有产品类的公共业务方法
    public void methodSame() {
    }

    //声明抽象业务方法
    public abstract void methodDiff();
}
