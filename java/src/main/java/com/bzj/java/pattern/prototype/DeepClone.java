package com.bzj.java.pattern.prototype;

import java.io.*;

/**
 * 深克隆原型
 * <p>
 * 通过Serializable 序列化流实现深克隆
 * 创建新的对象,同时复制基本数据类型,引用类型也重新创建和复制
 *
 * @author aaronbai
 * @create 2018-03-21 16:18
 **/
public class DeepClone implements Serializable {
    private static final long serialVersionUID = -964678137560964608L;

    private Attachment attachment;
    private String name;
    private String date;
    private String content;

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

    public DeepClone deepClone() throws IOException, ClassNotFoundException, OptionalDataException {
        //将对象写入流中
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);

        //将对象从流中取出
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (DeepClone) ois.readObject();
    }
}
