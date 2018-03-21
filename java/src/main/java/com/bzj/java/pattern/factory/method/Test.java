package com.bzj.java.pattern.factory.method;

/**
 * @author aaronbai
 * @create 2018-03-20 19:58
 **/
public class Test {

    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        Product product = factory.factoryMethod();
        product.methodDiff();
    }
}
