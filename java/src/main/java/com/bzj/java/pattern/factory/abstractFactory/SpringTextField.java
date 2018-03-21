package com.bzj.java.pattern.factory.abstractFactory;

/**
 * Spring文本框类：具体产品
 *
 * @author aaronbai
 * @create 2018-03-21 10:32
 **/
public class SpringTextField implements TextField{
    public void display() {
        System.out.println("显示绿色边框文本框。");
    }
}
