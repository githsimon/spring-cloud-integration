package com.simon.interviewdemo.valotile;

/**
 * @Description 这个实例就很好的诠释了什么是volatile的可见性
 * @Author yangsong3
 * @Date 2018/10/30 19:26
 **/
public class ValotileTest {
//    private static int flag = 10;//程序会一直执行while，
    private static volatile int flag = 10;
    public static void main(String[] args) {

        Thread thread  = new Thread(()->{
            flag = 20;
        });
        thread.start();
        while (flag == 10){
            //
        }
        System.err.println("over");
    }
}
