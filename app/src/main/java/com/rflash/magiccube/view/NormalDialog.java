package com.rflash.magiccube.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by yangfan on 2017/11/17.
 */

public class NormalDialog extends Dialog {


    private int resId;

    private int gravity = Gravity.CENTER;



    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    protected NormalDialog(Context context) {
        super(context);
    }

    protected NormalDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public NormalDialog(Context context, int themeResId, int resLayout) {
        super(context, themeResId);
        this.resId = resLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(resId);
        Window window = this.getWindow();
        //设置BottomDialog的宽高属性
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(getGravity());
        window.setAttributes(lp);
    }



}
