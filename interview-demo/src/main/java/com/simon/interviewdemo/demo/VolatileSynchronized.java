package com.simon.interviewdemo.demo;

import org.junit.Test;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/10/10 13:36
 **/
public class VolatileSynchronized {

    boolean isOpen = true;

    @Test
    public void run1() {
        Thread thread = new Thread(() -> {
            System.err.println("1 isOpen = " + isOpen);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isOpen = false;
        });
        Thread thread2 = new Thread(() -> {
            run2();
        });
        thread.start();
        thread2.start();
        try {
            Thread.currentThread().join();
            Thread.activeCount();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run2() {
        while (true) {
            System.err.println("2 isOpen = " + isOpen);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    final private void test3(){

    }
}
