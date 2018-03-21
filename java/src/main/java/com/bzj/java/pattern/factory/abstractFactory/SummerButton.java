package com.bzj.java.pattern.factory.abstractFactory;

/**
 * Summer按钮类：具体产品
 *
 * @author aaronbai
 * @create 2018-03-21 10:28
 **/
public class SummerButton implements Button{
    public void display() {
        System.out.println("显示浅蓝色按钮。");
    }
}
