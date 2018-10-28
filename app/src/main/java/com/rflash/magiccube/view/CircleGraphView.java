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
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.rflash.magiccube.R;

/**
 * Created by yangfan on 2017/11/18.
 */

public class CircleGraphView extends View {


    //是否绘制圆中间的文字
    private boolean isDrawMidText = false;

    //圆中间的文字  默认是   笔数
    private String midText = "笔数";


    //第一部分的数字
    private int part1 = 0;

    //第二部分的数字
    private int part2 = 0;

    //第一部分圆环的角度
    private int progress = 90;


    //绘制角度(动画角度)
    private int drawProgress;


    //圆环第一种颜色
    private int circleColor1 = Color.parseColor("#c7c7c7");

    //圆环第二种颜色
    private int circleColor2 = Color.parseColor("#6CB7F1");


    //pain对象
    private Paint paint = new Paint();


    public CircleGraphView(Context context) {
        super(context);
    }

    public CircleGraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleGraphView);
        isDrawMidText = ta.getBoolean(R.styleable.CircleGraphView_isDrawMidText, false);
        circleColor2 = ta.getColor(R.styleable.CircleGraphView_circleColor2, circleColor2);
        ta.recycle();  //注意回收

        //加动画
        ObjectAnimator moneyAnimator = ObjectAnimator.ofInt(this, "drawProgress", 0, 360);
        moneyAnimator.setDuration(1000).start();
        if (isDrawMidText) {
            ObjectAnimator text1Animator = ObjectAnimator.ofInt(this, "part1", 0, part1);
            ObjectAnimator text2Animator = ObjectAnimator.ofInt(this, "part2", 0, part2);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(text1Animator, text2Animator, moneyAnimator);
            set.setDuration(1000);
            set.start();
        }

    }

    public CircleGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    public int getPart1() {
        return part1;
    }

    public int getPart2() {
        return part2;
    }



    public void setPart1(int part1) {
        this.part1 = part1;
        invalidate();
    }

    public void setPart2(int part2) {
        this.part2 = part2;
        invalidate();

    }


    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getDrawProgress() {
        return drawProgress;
    }

    public void setDrawProgress(int drawProgress) {
        this.drawProgress = drawProgress;
        invalidate();
    }

    public void setDrawMidText(boolean drawMidText) {
        isDrawMidText = drawMidText;
    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int width = getWidth();
        int strokeWidth = 0;
        if (width >= 300) {
            strokeWidth = 20;
        } else {
            strokeWidth = 10;
        }
        //画出第一部分的圆环
        paint.setAntiAlias(true);
        paint.setColor(circleColor1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        //圆的半径就是view的宽／2
        //计算第一部分圆转过的角度
        int sweepAngle = progress;
        //right 设置为 宽度-线的宽度
        canvas.drawArc(strokeWidth, strokeWidth, width - strokeWidth, width - strokeWidth, 180, drawProgress, false, paint);

        //画第二部分的圆环
        if (drawProgress > progress) {
            paint.setColor(circleColor2);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);
            //从上个圆环结算处开始画
            canvas.drawArc(strokeWidth, strokeWidth, width - strokeWidth, width - strokeWidth, sweepAngle + 180, drawProgress - sweepAngle, false, paint);
        }
        if (isDrawMidText) {
            //画中间的文字
            paint.setColor(Color.parseColor("#000000"));
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(28);
            paint.setTextAlign(Paint.Align.CENTER);
            Rect bounds = new Rect();
            paint.getTextBounds(midText, 0, midText.length(), bounds);
            canvas.drawText(midText, width / 2, width / 2 - (bounds.bottom - bounds.top), paint);
            canvas.drawText(part1 + "/" + part2, width / 2, (bounds.bottom - bounds.top) + width / 2, paint);
        }


    }


}


/**
 * if (isDrawBottomText){//画底部的文字,不画中间文字
 * <p>
 * //先确定圆的半径：定为  view.width/4
 * //left : view.width/4   top: 定为view.width/6
 * // bottom:view.width/3*2    right:view.width/4*3
 * <p>
 * //先画圆的第一部分
 * paint.setColor(circleColor1);
 * paint.setStyle(Paint.Style.STROKE);
 * paint.setStrokeWidth(20);
 * int width = getWidth();
 * int sweepAngle = (part1*360) / (part1 + part2);
 * canvas.drawArc(width/4,width/6 ,width/4*3,width/3*2,-180,sweepAngle,false,paint);
 * <p>
 * <p>
 * //画圆的第二部分
 * paint.setColor(circleColor2);
 * paint.setStyle(Paint.Style.STROKE);
 * paint.setStrokeWidth(20);
 * canvas.drawArc(width/4,width/6 ,width/4*3,width/3*2,-180,sweepAngle-360,false,paint);
 * <p>
 * <p>
 * //画圆下面的文字
 * paint.setColor(bottomTextselectorColor);
 * paint.setStyle(Paint.Style.FILL);
 * paint.setTextSize(28);
 * paint.setTextAlign(Paint.Align.CENTER);
 * canvas.drawText(bottomText,width/2,width/6*5,paint);
 * <p>
 * <p>
 * <p>
 * }
 */

