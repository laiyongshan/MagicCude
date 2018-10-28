package com.rflash.magiccube.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rflash.basemodule.view.LoadRecyclerView;
import com.rflash.basemodule.view.LoadViewCreator;
import com.rflash.magiccube.R;

/**
 * Created by yangfan on 2017/11/23.
 */

public class DefaultLoadCreator extends LoadViewCreator {

    private TextView textView;
    private ProgressBar progressBar;
    private View inflate;

    @Override
    public View getLoadView(Context context, ViewGroup parent) {
        inflate = LayoutInflater.from(context).inflate(R.layout.layout_footview, parent, false);
        textView = inflate.findViewById(R.id.tv_load);
        progressBar = inflate.findViewById(R.id.progressbar);
        return inflate;
    }

    @Override
    public void onPull(int currentDragHeight, int loadViewHeight, int currentLoadStatus) {
        inflate.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        if (currentLoadStatus == LoadRecyclerView.LOAD_STATUS_NORMAL) {
            textView.setText("LOAD_STATUS_NORMAL");
        } else if (currentLoadStatus == LoadRecyclerView.LOAD_STATUS_PULL_DOWN_REFRESH) {
            textView.setText("上拉加载更多");
        } else if (currentLoadStatus == LoadRecyclerView.LOAD_STATUS_LOOSEN_LOADING) {
            textView.setText("松开后加载更多");
        }


    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("正在加载中...");
    }

    @Override
    public void onStopLoad() {
        inflate.setVisibility(View.GONE);
//        progressBar.setVisibility(View.GONE);
//        textView.setText("上拉加载更多");
    }
}
