package com.simon.interviewdemo.demo;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/12/26 17:53
 **/
public class DateTemp {

    /**
     * 获取连续日期/获取连续天数
     * @param date
     * @param current
     * @return
     */
    public List<String> getDays(Date date, int current) {

        if(current < 1 || current > 7 || date == null){
            throw new IllegalArgumentException("参数异常");
        }
        List<String> days = new ArrayList<>();

        Calendar now = Calendar.getInstance();
        Calendar calendarNow = new GregorianCalendar();
        calendarNow.setTime(date);
        int index = 1;
        if(current > 1){
            index = index - current;
        }
        calendarNow.add(Calendar.DAY_OF_MONTH, index);
        now.set(calendarNow.get(Calendar.YEAR), calendarNow.get(Calendar.MONTH), calendarNow.get(Calendar.DATE));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(calendarNow.get(Calendar.YEAR), calendarNow.get(Calendar.MONTH), calendarNow.get(Calendar.DATE));

        for (int i = 0; i < 13; i++)
        {
            days.add(format.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);//增加一天
        }
        return days;
    }

    @Test
    public void test(){
        List<String> days = getDays(new Date(),3);
        System.out.printf("");
    }
}