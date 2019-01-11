package com.simon.interviewdemo.file;

import java.io.*;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/10/22 19:55
 **/
public class FileDemo {

    public void test() throws Exception {
        File f = new File("a.txt");
        FileInputStream fileInputStream = new FileInputStream(f);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String world = "";
        StringBuffer result = new StringBuffer();
        while ((world=reader.readLine()) != null){
            result.append(world).append("\n");
            File f2 = new File("a.txt");
            FileInputStream fileInputStream2 = new FileInputStream(f);
            InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream2);
            BufferedReader reader2 = new BufferedReader(inputStreamReader2);
            String world2 = "";
            if((world = reader2.readLine()) != null){
                result.append(world2).append("\n");
            }
        }
    }
}
