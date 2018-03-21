package com.bzj.java.pattern.builder;

/**
 * 具体创建者
 *
 * 对象的具体构建者,每个构建者可以构建一类型的对象
 *
 * @author aaronbai
 * @create 2018-03-21 17:35
 **/
public class ConcreteBuilder extends Builder{
    @Override
    public void buildType() {
        product.setType("类型");
    }

    @Override
    public void buildSex() {
        product.setSex("男");
    }

    @Override
    public void buildFace() {
        product.setFace("帅");
    }

    @Override
    public void buildCostume() {
        product.setCostume("盔甲");
    }

    @Override
    public void buildHairstyle() {
        product.setHairstyle("飘逸");
    }
}
