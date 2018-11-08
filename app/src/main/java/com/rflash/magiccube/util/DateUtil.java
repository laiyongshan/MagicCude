package com.rflash.magiccube.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * @author lys
 * @time 2018/11/8 16:37
 * @desc:
 */

public class DateUtil {

    //YYYYMMDD 转换成 YYYY-MM-DD
    public static String formatDate1(String str) {
        Date date = null;
        String dateStr = "";
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(str);
            dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    //YYYYMM 转换成 YYYY-MM
    public static String formatDate2(String str) {
        Date date = null;
        String dateStr = "";
        try {
            date = new SimpleDateFormat("yyyyMM").parse(str);
            dateStr = new SimpleDateFormat("yyyy-MM").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }


    //YYYYMMDDHHmmss 转换成 YYYY-MM-DD HH:mm:ss
    public static String formatDate3(String str) {
        Date date = null;
        String dateStr = "";
        try {
            date = new SimpleDateFormat("yyyyMMddHHmmss").parse(str);
            dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    //System.currentTimeMillis()与日期之间转换
    public static String mills2Date(long timeMillis){
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateformat.format(timeMillis);
        return dateStr;
    }

    //System.currentTimeMillis()与日期之间转换
    public static long Date2Mills(String date){
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = 0;
        try {
            time= dateformat.parse(date).getTime();
            System.out.println(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }


}
