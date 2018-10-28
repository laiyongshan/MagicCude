package com.rflash.magiccube.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.rflash.magiccube.R;

import java.text.DecimalFormat;

/**
 * Created by yangfan on 2017/11/18.
 */

public class BarChartView extends View {

    //是否绘制文字
    private boolean isDrawText = false;


    //前天的金额
    private float money1 = 1.0f;

    //昨天的金额
    private float money2 = 2.0f;

    //今天的金额
    private float money3 = 3.0f;

    //三个金额中的最大值
    private float max;


    //前天日期
    private String data1 = "xx-xx";

    //昨天日期
    private String data2 = "xx-xx";

    //今天日期
    private String data3 = "xx-xx";


    private Paint paint = new Paint();

    //格式化
    DecimalFormat df = new DecimalFormat("0.00");



    public String getData1() {
        return data1;
    }

    public String getData2() {
        return data2;
    }

    public String getData3() {
        return data3;
    }

    public BarChartView(Context context) {
        super(context);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BarChartView);
        isDrawText = ta.getBoolean(R.styleable.BarChartView_isDrawText, false);
        ta.recycle();
        max = Math.max(Math.max(money1, money2), money3);

        ObjectAnimator money1Animator = ObjectAnimator.ofFloat(this, "money1", 0f, money1);
        ObjectAnimator money2Animator = ObjectAnimator.ofFloat(this, "money2", 0f, money2);
        ObjectAnimator money3Animator = ObjectAnimator.ofFloat(this, "money3", 0f, money3);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(money1Animator, money2Animator, money3Animator);
        set.setDuration(1000);
        set.start();


    }

    public BarChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BarChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setMoney1(float money1) {
        this.money1 = money1;
        invalidate();
    }

    public void setMoney2(float money2) {
        this.money2 = money2;
        invalidate();
    }

    public void setMoney3(float money3) {
        this.money3 = money3;
        invalidate();
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMoney1() {
        return money1;
    }

    public float getMoney2() {
        return money2;
    }

    public float getMoney3() {
        return money3;
    }

    public void setData1(String data1,String data2,String data3) {
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        if (TextUtils.isEmpty(data1)){
            this.data1 = "xx-xx";
        }
        if (TextUtils.isEmpty(data2)){
            this.data2 = "xx-xx";
        }
        if (TextUtils.isEmpty(data3)){
            this.data3 = "xx-xx";
        }

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int width = getWidth();
        int height = getHeight();

        int strokeWidth = width / 9;
        //定义文字与图形的距离

        int margin = 0;
        //文字的高度
        int textHeight = 0;
        Paint textPaint = new Paint();


        if (isDrawText) {

            //定义文字与图形的距离
            margin = strokeWidth / 4 * 3;

            //绘制文字的paint
            textPaint.setAntiAlias(true);
            textPaint.setColor(Color.parseColor("#000000"));
            textPaint.setStrokeWidth(2);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setTextSize(28);
            Rect bounds = new Rect();
            textPaint.getTextBounds(data1, 0, data1.length(), bounds);
            //获取文字的高度
            textHeight = bounds.bottom - bounds.top;

            // 每个距形的高度 (height-2*(margin+textHeight))*money/max
            // 每个距形起始的高度 height-margin-textHeight
            float height1 = 0;
            float height2 = 0;
            float height3 = 0;
            if (money1 > 0) {
                height1 = (height - 2 * (margin + textHeight)) * money1 / max;
            }
            if (money2 > 0) {
                height2 = (height - 2 * (margin + textHeight)) * money2 / max;
            }
            if (money3 > 0) {
                height3 = (height - 2 * (margin + textHeight)) * money3 / max;
            }

            //画前天的日期
            canvas.drawText(data1, strokeWidth / 2 * 3, height, textPaint);
            //画前天的金额
            if (money1 > 0)
                canvas.drawText("¥" + df.format(money1), strokeWidth / 2 * 3, height - (height1 + margin * 2 + textHeight), textPaint);

            //画昨天的日期
            canvas.drawText(data2, strokeWidth / 2 * 9, height, textPaint);
            //画昨天的金额
            if (money2 > 0)
                canvas.drawText("¥" + df.format(money2), strokeWidth / 2 * 9, height - (height2 + margin * 2 + textHeight), textPaint);


            //画今天的日期
            canvas.drawText(data3, strokeWidth / 2 * 15, height, textPaint);
            //画今天的金额
            if (money3 > 0)
                canvas.drawText("¥" + df.format(money3), strokeWidth / 2 * 15, height - (height3 + margin * 2 + textHeight), textPaint);

        }
        //先画底部的线条
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#C7C7C7"));
        paint.setStrokeWidth(2);
        canvas.drawLine(0, height - margin - textHeight, width, height - margin - textHeight, paint);


        //画距形
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.parseColor("#34C0E3"));


        // 每个距形的高度 (height-2*(margin+textHeight))*money/max
        // 每个距形起始的高度 height-margin-textHeight
        float height1 = 0;
        float height2 = 0;
        float height3 = 0;
        if (money1 > 0) {
            height1 = (height - 2 * (margin + textHeight)) * money1 / max;
        }
        if (money2 > 0) {
            height2 = (height - 2 * (margin + textHeight)) * money2 / max;
        }
        if (money3 > 0) {
            height3 = (height - 2 * (margin + textHeight)) * money3 / max;
        }
        //画前天的距形
        canvas.drawLine(strokeWidth / 2 * 3, height - margin - textHeight, strokeWidth / 2 * 3, height - (height1 + margin + textHeight), paint);


        //画昨天的距形
        canvas.drawLine(strokeWidth / 2 * 9, height - margin - textHeight, strokeWidth / 2 * 9, height - (height2 + margin + textHeight), paint);


        //画今天的距形
        canvas.drawLine(strokeWidth / 2 * 15, height - margin - textHeight, strokeWidth / 2 * 15, height - (height3 + margin + textHeight), paint);


    }


}


