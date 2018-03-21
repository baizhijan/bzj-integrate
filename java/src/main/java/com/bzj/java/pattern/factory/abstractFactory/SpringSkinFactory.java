package com.bzj.java.pattern.factory.abstractFactory;

/**
 * Spring皮肤工厂：具体工厂
 *
 * @author aaronbai
 * @create 2018-03-21 10:56
 **/
public class SpringSkinFactory implements SkinFactory{

    public Button createButton() {
        return new SpringButton();
    }

    public TextField createTextField() {
        return new SpringTextField();
    }

    public ComboBox createComboBox() {
        return new SpringComboBox();
    }
}
