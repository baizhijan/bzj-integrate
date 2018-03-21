package com.bzj.java.pattern.factory.abstractFactory;

/**
 * 界面皮肤工厂接口：抽象工厂
 *
 * @author aaronbai
 * @create 2018-03-21 10:55
 **/
public interface SkinFactory {

    public Button createButton();
    public TextField createTextField();
    public ComboBox createComboBox();
}
