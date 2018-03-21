package com.bzj.java.pattern.factory.abstractFactory;

/**
 * Summer皮肤工厂：具体工厂
 *
 * @author aaronbai
 * @create 2018-03-21 10:57
 **/
public class SummerSkinFactory implements SkinFactory{
    public Button createButton() {
        return new SummerButton();
    }

    public TextField createTextField() {
        return new SummerTextField();
    }

    public ComboBox createComboBox() {
        return new SummerComboBox();
    }
}
