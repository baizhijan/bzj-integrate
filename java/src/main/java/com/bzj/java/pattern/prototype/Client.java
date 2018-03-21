package com.bzj.java.pattern.prototype;

import java.io.IOException;

/**
 * 原型模式
 *
 * @author aaronbai
 * @create 2018-03-21 16:34
 **/
public class Client {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        //创建对象
        Attachment attachment = new Attachment();

        //浅克隆
        ShallowClone shallowClone = new ShallowClone();
        shallowClone.setName("shallowClone");
        shallowClone.setAttachment(attachment);
        shallowClone.setContent("1234");
        shallowClone.setDate("2018-03-21");

        ShallowClone shallow = shallowClone.clone();
        System.out.println(shallow == shallowClone);
        System.out.println(shallow.getName() == shallowClone.getName());
        System.out.println(shallow.getAttachment() == shallowClone.getAttachment());
        System.out.println(shallow.getContent() == shallowClone.getContent());
        System.out.println(shallow.getDate() == shallowClone.getDate());

        System.out.println("------------------------------------------------------");

        //深克隆
        DeepClone deepClone = new DeepClone();
        deepClone.setName("deepClone");
        deepClone.setAttachment(attachment);
        deepClone.setContent("1234");
        deepClone.setDate("2018-03-21");
        DeepClone deep = deepClone.deepClone();
        System.out.println(deep == deepClone);
        System.out.println(deep.getName() == deepClone.getName());
        System.out.println(deep.getAttachment() == deepClone.getAttachment());
        System.out.println(deep.getContent() == deepClone.getContent());
        System.out.println(deep.getDate() == deepClone.getDate());

    }
}
