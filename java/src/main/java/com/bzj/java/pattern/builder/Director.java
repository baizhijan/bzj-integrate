package com.bzj.java.pattern.builder;

/**
 * 指挥者
 *
 * 构建者的对外交互中心
 *
 * @author aaronbai
 * @create 2018-03-21 17:36
 **/
public class Director {

    public Product construct(Builder builder) {
        builder.buildType();
        builder.buildSex();
        builder.buildFace();
        builder.buildCostume();
        builder.buildHairstyle();
        return builder.createProduct();
    }
}
