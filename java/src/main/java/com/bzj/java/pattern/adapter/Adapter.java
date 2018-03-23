package com.bzj.java.pattern.adapter;

/**
 * 适配器
 *
 * @author aaronbai
 * @create 2018-03-22 11:16
 **/
public class Adapter implements Target {

    @Override
    public int[] sort(int[] array) {
        System.out.println("对参数转换");
        return Adaptee.quickSort(array);
    }

    @Override
    public int search(int[] array, int key) {
        System.out.println("对参数转换");
        return Adaptee.binarySearch(array, key);
    }
}
