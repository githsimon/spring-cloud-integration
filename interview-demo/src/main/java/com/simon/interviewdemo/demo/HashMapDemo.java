package com.simon.interviewdemo.demo;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/10/8 22:28
 **/
public class HashMapDemo {

    private Integer flag = 0;

    /**
     * print HashMap
     * @throws Exception
     */
    @Test
    public void test1 () throws Exception{
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("Key","Value");
        Set<Map.Entry<String,String>> entrySet =  hashMap.entrySet();
        int size = entrySet.size();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            System.err.println("1: " + "" + entry.getKey() + "=" +entry.getValue());
        }
        hashMap.keySet().forEach((e)->{
            String value = hashMap.get(e);
            System.err.println("2: " + "" + e +" = " +value);
        });
        hashMap.forEach((k,y)-> System.err.println("3: " + "" + k + " = " + y));


        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.err.println("4:" + entry.getKey() + " = " + entry.getValue());
        }
    }
}
