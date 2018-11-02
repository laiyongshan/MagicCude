package com.rflash.magiccube.util;

import android.content.Context;
import android.graphics.Color;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.OnDismissListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Admin on 2018/3/20 18:18.
 */

public class TimerPikerTools {

    static TimePickerView mtimePickerView;
    /**
     *
     * @param isMonth   是否是只显示到月
//     * @param selectedDate  当前控件中该显示时间
     */
    public static TimePickerView creatTimePickerView(Context context, String TileText, boolean isYear, boolean isMonth, boolean isDay, TimePickerView.OnTimeSelectListener onTimeSelectListener){

        Calendar startDate = Calendar.getInstance();
        startDate.set(2009, 0, 1);
        Calendar  endDate= Calendar.getInstance() ;//系统当前时间
        endDate.add(Calendar.DATE,0); //取到昨天的日期

        //时间选择器 ，自定义布局
        mtimePickerView = new TimePickerView.Builder(context,onTimeSelectListener)
                .setCancelText("")//取消按钮文字
                .setSubmitText("确定 ")//确认按钮文字
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText(TileText+"")
                .setTitleColor(Color.parseColor("#5EB9F2"))
                .setLineSpacingMultiplier(2f)//设置两横线之间的间隔倍数
                .setSubmitColor(Color.parseColor("#5EB9F2"))
                .setCancelColor(Color.parseColor("#5EB9F2"))
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .setDate(endDate)
                .isCyclic(false)//是否循环滚动
                .setType(new boolean[]{isYear,isMonth,isDay, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .isDialog(true)
                .build();

        mtimePickerView.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
//                mtimePickerView.show(true);
            }
        });
            return mtimePickerView;
    }


    /**
     *
     * @param isMonth   是否是只显示到月
    //     * @param selectedDate  当前控件中该显示时间
     */
    public static TimePickerView creatTimePickerView2(Context context, String TileText, boolean isYear, boolean isMonth, boolean isDay, TimePickerView.OnTimeSelectListener onTimeSelectListener){

        Calendar startDate = Calendar.getInstance();
        startDate.set(2009, 0, 1);
        Calendar  endDate= Calendar.getInstance() ;//系统当前时间
        endDate.add(Calendar.DATE,-1); //取到昨天的日期

        //时间选择器 ，自定义布局
        mtimePickerView = new TimePickerView.Builder(context,onTimeSelectListener)
                .setCancelText("")//取消按钮文字
                .setSubmitText("确定 ")//确认按钮文字
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText(TileText+"")
                .setTitleColor(Color.parseColor("#5EB9F2"))
                .setLineSpacingMultiplier(2f)//设置两横线之间的间隔倍数
                .setSubmitColor(Color.parseColor("#5EB9F2"))
                .setCancelColor(Color.parseColor("#5EB9F2"))
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .setDate(endDate)
                .setRangDate(startDate, endDate)
                .isCyclic(false)//是否循环滚动
                .setType(new boolean[]{isYear,isMonth,isDay, true, true, true})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .isDialog(true)
                .build();

        mtimePickerView.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
//                mtimePickerView.show(true);
            }
        });
        return mtimePickerView;
    }


    public static String getTodayDate(){
        Calendar   cal   =   Calendar.getInstance();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return format;
    }

    /*
    * 获取前多少天的日期 yyyy-mm-dd
    * */
    public static String getLastDate(int days){
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   days);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return format;
    }

    /*
    * 获取上一个月
    * */
    public static String getLastMonthDate(){
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.MONTH, -1); //月份减1
        String format = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return format;
    }

    /**
     * 获取上一年
     * */
    public static String getLastYearDate(){
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.YEAR, -1); //年份减1
        String format = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return format;
    }

}
