package com.bzj.java.pattern.adapter;

/**
 * 目标对象
 *
 * @author aaronbai
 * @create 2018-03-22 11:10
 **/
public interface Target {
    public int[] sort(int array[]); //成绩排序
    public int search(int array[],int key); //成绩查找
}
