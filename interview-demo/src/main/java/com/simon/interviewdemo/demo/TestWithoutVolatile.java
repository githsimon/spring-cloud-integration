package com.simon.interviewdemo.demo;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/10/10 19:15
 **/
public class TestWithoutVolatile {
    private static volatile boolean bChanged;
//    private static  boolean bChanged;

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    System.err.println(bChanged + " == " + !bChanged);
                    if (bChanged == !bChanged) {
                        System.out.println("!=");
                        System.exit(0);
                    }
                }
            }
        }.start();
        Thread.sleep(1);
        new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    bChanged = !bChanged;
                }
            }
        }.start();
    }
}
