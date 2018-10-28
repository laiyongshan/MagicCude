package com.rflash.basemodule.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yangfan on 2017/7/31.
 * ViewHolder简单封装
 */

public class BaseHolder extends RecyclerView.ViewHolder {
    public View itemView;
    //存放item
    SparseArray<View> views;
    public BaseHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }






    /**
     * 供adapter调用
     * @param context
     * @param parent
     * @param layoutId
     * @param <T>
     * @return holder
     */
    public static <T extends BaseHolder> T getHolder(Context context, ViewGroup parent,int layoutId){
        return (T) new BaseHolder(LayoutInflater.from(context).inflate(layoutId,parent,false));

    }


    /**
     * 根据item控件id获取控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
        View childrenView = views.get(viewId);
        if (childrenView == null){
            childrenView = itemView.findViewById(viewId);
            views.put(viewId,childrenView);
        }
        return (T) childrenView;
    }

    public BaseHolder setOnClickListener(int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
    public BaseHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener){
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public BaseHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseHolder setText(int viewId, SpannableString text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseHolder setText(int viewId, int resString)
    {
        TextView tv = getView(viewId);
        tv.setText(resString);
        return this;
    }

}
