package com.bzj.java.pattern.factory.simple;

/**
 * @author aaronbai
 * @create 2018-03-20 19:04
 **/
public class Test {

    public static void main(String[] args) {
        Product product;
        product = Factory.getProduct("A"); //通过工厂类创建产品对象
        product.methodSame();
        product.methodDiff();
    }
}
