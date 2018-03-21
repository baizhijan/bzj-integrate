package com.bzj.java.pattern.factory.abstractFactory;

/**
 * Summer文本框类：具体产品
 *
 * @author aaronbai
 * @create 2018-03-21 10:33
 **/
public class SummerTextField implements TextField{
    public void display() {
        System.out.println("显示蓝色边框文本框。");
    }
}
