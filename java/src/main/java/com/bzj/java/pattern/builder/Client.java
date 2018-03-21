package com.bzj.java.pattern.builder;

/**
 * @author aaronbai
 * @create 2018-03-21 17:44
 **/
public class Client {
    public static void main(String[] args) {
        //创建具体构建者
        ConcreteBuilder builder =  new ConcreteBuilder();
        //创建指挥者
        Director  director = new Director();
        Product product = director.construct(builder);
        System.out.println(product);
    }
}
