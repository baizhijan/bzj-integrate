package com.bzj.java.pattern.prototype;

import java.io.Serializable;

/**
 * @author aaronbai
 * @create 2018-03-21 16:40
 **/
public class Attachment implements Serializable{

    private static final long serialVersionUID = 8423160450671558175L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
