package com.bzj.java.pattern.builder;

/**
 * 抽象建造者
 *
 * 定义构建的对象&构建对象的属性
 *
 * @author aaronbai
 * @create 2018-03-21 17:35
 **/
public abstract class Builder {
    protected Product product = new Product();

    public abstract void buildType();

    public abstract void buildSex();

    public abstract void buildFace();

    public abstract void buildCostume();

    public abstract void buildHairstyle();

    public Product createProduct() {
        return product;
    }
}
