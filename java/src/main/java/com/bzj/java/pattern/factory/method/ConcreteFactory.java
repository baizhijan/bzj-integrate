package com.bzj.java.pattern.factory.method;

/**
 * 具体工厂实现
 * 双重抽象 工厂抽象
 * 每个工厂对应每个具体的创建类
 * 产生的对象也是单一的
 *
 * @author aaronbai
 * @create 2018-03-20 19:51
 **/
public class ConcreteFactory extends Factory{

    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}
