package com.simon.interviewdemo.thread;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/10/11 19:56
 **/
public class PCData {
    private final int intData;
    public PCData(int d){
        intData = d;
    }
    public PCData(String d){
        intData = Integer.valueOf(d);
    }
    public int getData(){
        return intData;
    }
    @Override
    public String toString(){
        return "data:"+intData;
    }
}
