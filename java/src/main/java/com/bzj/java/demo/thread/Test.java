package com.bzj.java.demo.thread;

/**
 * @author aaronbai
 * @create 2018-03-15 15:22
 **/
public class Test implements Runnable {

    public volatile int race = 0;
    @Override
    public void run() {
        increase();
    }
    private void increase() {
        race ++;

    }
    public static void main(String[] args) {
        Test t = new Test();
        Thread [] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(t);
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        // 保证打印的时候1000个线程都已经执行完毕
        System.out.println(t.race);
    }


//    public static void main(String[] args) throws Exception{
//        int cpuNums = Runtime.getRuntime().availableProcessors();
//        System.out.println(cpuNums);
//    }
}