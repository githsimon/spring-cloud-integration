package com.simon.interviewdemo.demo;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/10/9 14:59
 **/
public class Student {
    private String name;
    private Integer age;

    public void stu1(String name) {
        this.name = name;
    }
    public void stu1(String name,Integer age) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
