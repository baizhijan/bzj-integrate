package com.bzj.java.pattern.factory.abstractFactory;


/**
 * @author aaronbai
 * @create 2018-03-20 20:34
 **/
public class Test {

    public static void main(String[] args) throws ClassNotFoundException {
        SkinFactory factory;
        Button bt;
        TextField tf;
        ComboBox cb;

        factory = (SkinFactory)XMLUtil.getBean();
        bt = factory.createButton();
        tf = factory.createTextField();
        cb = factory.createComboBox();
        bt.display();
        tf.display();
        cb.display();
    }
}


