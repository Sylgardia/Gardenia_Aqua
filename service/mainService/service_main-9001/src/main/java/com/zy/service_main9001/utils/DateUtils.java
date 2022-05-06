package com.zy.service_main9001.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zy
 * @date 2021/10/16 23:23
 * @description
 */
public class DateUtils {
    public static Date getNow() {
        return new Date();
    }

    public static long calculateTimeDifference(Date startTime, Date endTime) throws ParseException {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.setTime(startTime);
        end.setTime(endTime);

        //获取毫秒
        long now = start.getTimeInMillis();
        long last = end.getTimeInMillis();

        //对比时间
        return (now - last) / (1000L * 3600L * 24L);
    }
}
