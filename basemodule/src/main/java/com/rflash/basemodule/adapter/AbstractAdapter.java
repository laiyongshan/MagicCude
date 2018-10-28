package com.rflash.basemodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Author: yo_hack
 * Date: 2015/12/14 15:05
 * Describe: 封装了的adapter
 */
public abstract class AbstractAdapter<T> extends BaseAdapter {
    protected Context context;
    protected List<T> listData;
    protected LayoutInflater inflater;
    // 资源文件ID
    private int resId;

    public AbstractAdapter(Context context, List<T> listData, int resId) {
        super();
        this.context = context;
        this.listData = listData;
        this.inflater = LayoutInflater.from(context);
        this.resId = resId;
    }

    @Override
    public int getCount() {
        return listData == null ? 0 : listData.size();
    }

    @Override
    public T getItem(int position) {
        return listData == null ? null : listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 初始化ViewHolder,使用通用的ViewHolder，一样代码就搞定ViewHolder的初始化咯
        ViewHolder holder = ViewHolder.get(context, convertView, parent,
                this.resId, position);
        convert(holder, listData.get(position));
        return holder.getConvertView();
    }



    //需要重写的方法
    public abstract void convert(ViewHolder holder, T bean);



}