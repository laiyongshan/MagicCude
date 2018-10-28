package com.rflash.basemodule.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yangfan on 2017/7/31.
 * Adapter简单封装
 */

public abstract class BaseAdp<T> extends RecyclerView.Adapter<BaseHolder> {
    public Context context;
    List<T> data;
    int layoutId;

    public BaseAdp(Context context, List<T> data, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseHolder.getHolder(context,parent,layoutId);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {

        onBind(holder,data.get(position),position);
    }

    public abstract void onBind(BaseHolder holder, T t, int position);
}

