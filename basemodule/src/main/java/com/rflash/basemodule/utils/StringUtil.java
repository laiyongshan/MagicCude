package com.rflash.basemodule.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangfan on 2017/11/28.
 */

public class StringUtil {

    /**
     * 将银行卡号截取显示
     *
     * @param bankNo
     * @return
     */
    public static String getBankNo(String bankNo) {
        StringBuffer sb = new StringBuffer();
        if (TextUtils.isEmpty(bankNo)) {
            return "";
        }
        int length = bankNo.length();
        sb.append(bankNo.substring(0, 6))
                .append("****")
                .append(bankNo.substring(length - 4, length));
        return sb.toString();
    }


    /**
     * 将分转成元
     *
     * @return
     */
    public static String getTwoPointString(String resString) {

        if (TextUtils.isEmpty(resString)) {
            return "";
        }
        Float aFloat = Float.valueOf(resString);
        float i = aFloat / 100;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(i);
    }


    public static String getFen(String yuan) {
        if (TextUtils.isEmpty(yuan)) {
            return "";
        }

        Float floatYuan = Float.valueOf(yuan);
        DecimalFormat df = new DecimalFormat("0");
        return df.format(floatYuan * 100);
    }

    public static String getTwoPointString(float money) {

        float i = money / 100;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(i);
    }

    public static String getDate(int date) {
        String first = String.valueOf(date);
        StringBuffer sb = new StringBuffer(first);
        String result = sb.insert(6, "-").substring(4, sb.length());

        return result;
    }

    /**
     * 搜索关键字高亮
     * @param content  文字总内容
     * @param keyword   高亮文字
     * @param color    高亮颜色
     * @return
     */
    public static SpannableString setKeyWordColor(String content, String keyword, int color) {
        SpannableString sps = new SpannableString(content);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(sps);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            sps.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return sps;
    }

    /**
     * 对于字符串是否为空的校验
     * @param str
     * @return
     */
    public static  boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    /**
     * 对于字符串为空的转为 '-' 用于信用查询报告
     */
    public static  String changeToMinusSign(String str){
        return str=isEmpty(str)?"——":str;
    }

}
