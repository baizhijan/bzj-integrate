package com.bzj.java.pattern.adapter;

/**
 * @author aaronbai
 * @create 2018-03-22 11:21
 **/
public class Test {

    public static void main(String[] args) {
        Adapter adapter = new Adapter();

        int scores[] = {84, 76, 50, 69, 90, 91, 88, 96};

        int[] sortArr = adapter.sort(scores);
        for (int i : sortArr) {
            System.out.print(i + " ");
        }

        int score = adapter.search(sortArr, 90);
        System.out.println(score);
    }
}
