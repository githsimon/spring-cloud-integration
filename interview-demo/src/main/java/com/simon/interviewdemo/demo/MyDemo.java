package com.simon.interviewdemo.demo;

import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/10/8 9:45
 **/
public class MyDemo {
    @Test
    public void char2(){
//        char char2 = '我';//占用2个字节
//        HashMap<String,String> map = new HashMap<>();
//        Hashtable<String,String> map2 = new Hashtable<>();
        ConcurrentHashMap<String,String> map3 = new ConcurrentHashMap<>();
//        map.put(null,"3");
//        map2.put(null,"3");
        map3.put(null,"3");
        System.err.println(map3.get(null) + "");
//        System.err.println(map2.get(null));
//        System.err.println(map3.get(null));
    }
    @Test
    public void test4(){
        int x =0;
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("11");
        list.forEach(System.out::print);
    }

    @Test
    public void demo1() {
        String str="";
        str.substring(1,2);
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("B");
            }
        });
        A.start();
        B.start();
    }

    private void printNumber(String threadName) {
        int i = 0;
        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "print:" + i);
        }
    }
}
