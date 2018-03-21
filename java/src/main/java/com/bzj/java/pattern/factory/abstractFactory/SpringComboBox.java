package com.bzj.java.pattern.factory.abstractFactory;

/**
 * Spring组合框类：具体产品
 *
 * @author aaronbai
 * @create 2018-03-21 10:54
 **/
public class SpringComboBox implements ComboBox{
    public void display() {
        System.out.println("显示绿色边框组合框。");
    }
}
