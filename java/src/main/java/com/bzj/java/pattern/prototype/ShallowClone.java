package com.bzj.java.pattern.prototype;

/**
 * 浅克隆原型
 *
 * 通过实现Cloneable接口实现浅克隆
 * 只创建了新对象,对象的属性值没有发生改变直接复制(基本数据类型&&引用数据类型直接复制引用)
 *
 * @author aaronbai
 * @create 2018-03-21 16:17
 **/
public class ShallowClone implements Cloneable{

    private Attachment attachment;
    private String name;
    private  String date;
    private  String content;

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected ShallowClone clone() throws CloneNotSupportedException {
        return (ShallowClone)super.clone();
    }
}
