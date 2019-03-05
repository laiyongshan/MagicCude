package com.rflash.magiccube.util;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.rflash.magiccube.R;

import java.lang.reflect.Field;
import java.text.NumberFormat;

/**
 * @author lys
 * @time 2018/10/8 16:43
 * @desc:
 */

public class ToolUtils {

    public static int[] Colors = new int[]{
            Color.rgb(64,203,4),
            Color.rgb(228,217,8),
            Color.rgb(255,177,9),
            Color.rgb(255,73,9),
            Color.rgb(255,9,188),
            Color.rgb(134,7,40),
            Color.rgb(136,136,136)
    };

    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }


    }

    public static int getBankIcon(String bankName){
        if(bankName.contains("工商"))
            return R.mipmap.icon_gongshan;
        else if(bankName.contains("光大"))
            return  R.mipmap.icon_guangda;
        else if(bankName.contains("广发"))
            return R.mipmap.icon_guangfa;
        else if(bankName.contains("广州银行"))
            return  R.mipmap.icon_guangzhou;
        else if(bankName.contains("华夏"))
            return  R.mipmap.icon_huaxia;
        else if(bankName.contains("建设"))
            return  R.mipmap.icon_jianshe;
        else if(bankName.contains("交通"))
            return  R.mipmap.icon_jiaotong;
        else if(bankName.contains("民生"))
            return  R.mipmap.icon_minsheng;
        else if(bankName.contains("农业"))
            return  R.mipmap.icon_nongye;
        else if(bankName.contains("平安"))
            return  R.mipmap.icon_pingan;
        else if(bankName.contains("浦发"))
            return  R.mipmap.icon_pufa;
        else if(bankName.contains("兴业"))
            return  R.mipmap.icon_xingye;
        else if(bankName.contains("邮政"))
            return  R.mipmap.icon_youzheng;
        else if(bankName.contains("招商"))
            return  R.mipmap.icon_zhaoshang;
        else if(bankName.contains("中国银行"))
            return  R.mipmap.icon_zhongguo;
        else if(bankName.contains("中信"))
            return  R.mipmap.icon_zhongxin;

        return R.mipmap.icon_bank_defual_logo;
    }

    public static int getBankCardBg(String bankName){
        if(bankName.contains("工商"))
            return R.mipmap.bg_bank_card_red;
        else if(bankName.contains("光大"))
            return  R.mipmap.bg_bank_card_p;
        else if(bankName.contains("广发"))
            return R.mipmap.bg_bank_card_red;
        else if(bankName.contains("广州银行"))
            return  R.mipmap.bg_bank_card_red;
        else if(bankName.contains("华夏"))
            return  R.mipmap.bg_bank_card_red;
        else if(bankName.contains("建设"))
            return  R.mipmap.bg_bank_card_blue;
        else if(bankName.contains("交通"))
            return  R.mipmap.bg_bank_card_green;
        else if(bankName.contains("民生"))
            return  R.mipmap.bg_bank_card_blue;
        else if(bankName.contains("农业"))
            return  R.mipmap.bg_bank_card_green;
        else if(bankName.contains("平安"))
            return  R.mipmap.bg_bank_card_red;
        else if(bankName.contains("浦发"))
            return  R.mipmap.bg_bank_card_blue;
        else if(bankName.contains("兴业"))
            return  R.mipmap.bg_bank_card_blue;
        else if(bankName.contains("邮政"))
            return  R.mipmap.bg_bank_card_green;
        else if(bankName.contains("招商"))
            return  R.mipmap.bg_bank_card_red;
        else if(bankName.contains("中国银行"))
            return  R.mipmap.bg_bank_card_red;
        else if(bankName.contains("中信"))
            return  R.mipmap.bg_bank_card_red;

        return R.mipmap.bg_bank_card_blue;
    }

    public static int getFinanceBankCardBg(String bankName){
        if(bankName.contains("工商"))
            return R.mipmap.bg_finance_card_red;
        else if(bankName.contains("光大"))
            return  R.mipmap.bg_finance_card_p;
        else if(bankName.contains("广发"))
            return R.mipmap.bg_finance_card_red;
        else if(bankName.contains("广州银行"))
            return  R.mipmap.bg_finance_card_red;
        else if(bankName.contains("华夏"))
            return  R.mipmap.bg_finance_card_red;
        else if(bankName.contains("建设"))
            return  R.mipmap.bg_finance_card_blue;
        else if(bankName.contains("交通"))
            return  R.mipmap.bg_finance_card_green;
        else if(bankName.contains("民生"))
            return  R.mipmap.bg_finance_card_blue;
        else if(bankName.contains("农业"))
            return  R.mipmap.bg_finance_card_green;
        else if(bankName.contains("平安"))
            return  R.mipmap.bg_finance_card_red;
        else if(bankName.contains("浦发"))
            return  R.mipmap.bg_finance_card_blue;
        else if(bankName.contains("兴业"))
            return  R.mipmap.bg_finance_card_blue;
        else if(bankName.contains("邮政"))
            return  R.mipmap.bg_finance_card_green;
        else if(bankName.contains("招商"))
            return  R.mipmap.bg_finance_card_red;
        else if(bankName.contains("中国银行"))
            return  R.mipmap.bg_finance_card_red;
        else if(bankName.contains("中信"))
            return  R.mipmap.bg_finance_card_red;

        return R.mipmap.bg_finance_card_blue;
    }

    public static int getPlanItemCardBg(String bankName){
        if(bankName.contains("工商"))
            return R.mipmap.bg_planning_red;
        else if(bankName.contains("光大"))
            return  R.mipmap.bg_planning_p;
        else if(bankName.contains("广发"))
            return R.mipmap.bg_planning_red;
        else if(bankName.contains("广州银行"))
            return  R.mipmap.bg_planning_red;
        else if(bankName.contains("华夏"))
            return  R.mipmap.bg_planning_red;
        else if(bankName.contains("建设"))
            return  R.mipmap.bg_planning_blue;
        else if(bankName.contains("交通"))
            return  R.mipmap.bg_planning_green;
        else if(bankName.contains("民生"))
            return  R.mipmap.bg_planning_blue;
        else if(bankName.contains("农业"))
            return  R.mipmap.bg_planning_green;
        else if(bankName.contains("平安"))
            return  R.mipmap.bg_planning_red;
        else if(bankName.contains("浦发"))
            return  R.mipmap.bg_planning_blue;
        else if(bankName.contains("兴业"))
            return  R.mipmap.bg_planning_blue;
        else if(bankName.contains("邮政"))
            return  R.mipmap.bg_planning_green;
        else if(bankName.contains("招商"))
            return  R.mipmap.bg_planning_red;
        else if(bankName.contains("中国银行"))
            return  R.mipmap.bg_planning_red;
        else if(bankName.contains("中信"))
            return  R.mipmap.bg_planning_red;

        return R.mipmap.bg_planning_blue;
    }

    public static String getDouble(double d){
        NumberFormat nf = NumberFormat.getNumberInstance();
        /**
         * setMaximumFractionDigits(int newValue)
         设置数的小数部分所允许的最大位数。
         */
        nf.setMaximumFractionDigits(3);
        return nf.format(d);
    }

}
