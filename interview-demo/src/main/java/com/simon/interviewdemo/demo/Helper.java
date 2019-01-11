package com.simon.interviewdemo.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/10/8 22:09
 **/
public enum Helper {
    instance2;
    private static final ExecutorService tPool = Executors.newFixedThreadPool(2);

    public static String[] buildNoArr(int max) {
        String[] noArr = new String[max];
        for (int i = 0; i < max; i++) {
            noArr[i] = Integer.toString(i + 1);
        }
        return noArr;
    }
    public void run(Runnable r){
        tPool.submit(r);
    }
}
