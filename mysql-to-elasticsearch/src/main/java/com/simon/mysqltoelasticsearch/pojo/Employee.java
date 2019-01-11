package com.simon.mysqltoelasticsearch.pojo;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description employee info
 * @Author yangsong3
 * @Date 2019/1/10 21:17
 **/
public class Employee {
    public Integer id;
    private String name;
    private Integer age;
    private Integer salary;
    private String school;
    private LocalDateTime birthday;
    private LocalDateTime inDate;//入司时间
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getInDate() {
        return inDate;
    }

    public void setInDate(LocalDateTime inDate) {
        this.inDate = inDate;
    }
}
