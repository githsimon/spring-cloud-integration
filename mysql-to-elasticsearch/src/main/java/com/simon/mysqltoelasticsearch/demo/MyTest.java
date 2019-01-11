package com.simon.mysqltoelasticsearch.demo;

import com.simon.mysqltoelasticsearch.pojo.Employee;
import com.simon.mysqltoelasticsearch.utils.ESUtil;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2019/1/5 11:20
 **/
public class MyTest {
    public static void main(String[] args) {
//        DecimalFormat df = new DecimalFormat("#0.00");
//        double db = Double.parseDouble(df.format(Math.random() * 70000));
//        System.out.println(db);
        test2();
    }

    public static void test2() {
        try {
            String index = "employee";
            List<Employee> dataList = new ArrayList<>();
            for (int i = 0; i < 300; i++) {
                int hours = (int) (Math.random() * 50000);
                if (hours % 2 == 0) {
                    hours = 0 - hours;
                }
                LocalDateTime randomBirthday = LocalDateTime.now().plusYears(-27).plusHours(hours);
                LocalDateTime randomInDate = LocalDateTime.now().plusYears(-3).plusHours((int)(Math.random() * 10000));
                Employee entity = new Employee();
                entity.setId(i + 1);
                entity.setName("杨颂" + (int)(Math.random() * 12));
                entity.setAge((int)(Math.random() * 100));
                entity.setSalary((int)(Math.random() * 50000));
                entity.setSchool("北京大学-分校"+ String.format("%02d",(int)(Math.random() * 20)));
                entity.setBirthday(randomBirthday);
                entity.setInDate(randomInDate);
                dataList.add(entity);
            }
            ESUtil.createBulkDocument(index, "salary",dataList);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(){
        try {
            ESUtil.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
