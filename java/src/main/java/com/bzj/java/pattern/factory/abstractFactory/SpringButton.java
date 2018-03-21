package com.bzj.java.pattern.factory.abstractFactory;

/**
 * Spring按钮类：具体产品
 *
 * @author aaronbai
 * @create 2018-03-21 10:27
 **/
public class SpringButton implements Button{
    public void display() {
        System.out.println("显示浅绿色按钮");
    }
}
