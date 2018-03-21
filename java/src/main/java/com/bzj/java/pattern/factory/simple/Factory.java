package com.bzj.java.pattern.factory.simple;

/**
 * 简单工厂
 *
 * 解决具体类的new的过程
 * 通过传入的参数获取具体的对象
 *
 * @author aaronbai
 * @create 2018-03-20 17:58
 **/
public class Factory {

    public static Product getProduct(String arg) {
        Product product = null;
        if (arg.equalsIgnoreCase("A")) {
            product = new ConcreteProduct();
            //初始化设置product
        }
        else if (arg.equalsIgnoreCase("B")) {
            product = new ConcreteProduct();
            //初始化设置product
        }
        return product;
    }
}
