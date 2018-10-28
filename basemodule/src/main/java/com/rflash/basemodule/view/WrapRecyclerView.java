package com.rflash.basemodule.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.rflash.basemodule.adapter.HeaderAndFooterAdapter;

/**
 * Created by yangfan on 2017/11/23.
 * 可以添加头部和底部的RecyclerView
 */


public class WrapRecyclerView extends RecyclerView {

    public static final String TAG = "WrapRecyclerView";

    //包裹了一层的头部底部Adapter
    private HeaderAndFooterAdapter mHFAdapter;

    //列表数据的Adapter(可以理解为中间列表)
    private RecyclerView.Adapter mAdapter;
    // 空列表数据应该显示的空View
    // 正在加载数据页面，也就是正在获取后台接口页面
    private View mEmptyView, mLoadingView;


    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    // 添加头部
    public void addHeaderView(View view) {
        if (mHFAdapter != null) {
            mHFAdapter.addHeaderView(view);
        }
    }

    // 添加底部
    public void addFooterView(View view) {
        if (mHFAdapter != null) {
            mHFAdapter.addFooterView(view);
        }
    }




    @Override
    public void setAdapter(Adapter adapter) {

        // 为了防止多次设置Adapter
        if (mAdapter != null) {
            mAdapter.unregisterAdapterDataObserver(mDataObserver);
            mAdapter = null;
        }

        this.mAdapter = adapter;

        if (adapter instanceof HeaderAndFooterAdapter) {
            mHFAdapter = (HeaderAndFooterAdapter) adapter;
        } else {
            mHFAdapter = new HeaderAndFooterAdapter(adapter);
        }

        super.setAdapter(mHFAdapter);

        // 注册一个观察者
        mAdapter.registerAdapterDataObserver(mDataObserver);
    }

    private AdapterDataObserver mDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyDataSetChanged没效果
            if (mHFAdapter != mAdapter)
                mHFAdapter.notifyDataSetChanged();
            dataChanged();

        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyDataSetChanged没效果
            if (mHFAdapter != mAdapter)
                mHFAdapter.notifyItemRemoved(positionStart);
            dataChanged();

        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyItemMoved没效果
            if (mHFAdapter != mAdapter)
                mHFAdapter.notifyItemMoved(fromPosition, toPosition);
            dataChanged();

        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyItemChanged没效果
            if (mHFAdapter != mAdapter)
                mHFAdapter.notifyItemChanged(positionStart);
            dataChanged();

        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyItemChanged没效果
            if (mHFAdapter != mAdapter)
                mHFAdapter.notifyItemChanged(positionStart, payload);
            dataChanged();

        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            if (mAdapter == null) return;
            // 观察者  列表Adapter更新 包裹的也需要更新不然列表的notifyItemInserted没效果
            if (mHFAdapter != mAdapter)
                mHFAdapter.notifyItemInserted(positionStart);
            dataChanged();
        }
    };


    /**
     * Adapter数据改变的方法
     */
    private void dataChanged() {
        if (mAdapter.getItemCount() == 0) {
            // 没有数据
            if (mEmptyView != null) {
                mEmptyView.setVisibility(VISIBLE);
                Log.i(TAG, "mEmptyView VISIBLE");
            }
        } else {
            //有数据
            if (mEmptyView != null) {
                mEmptyView.setVisibility(GONE);
                Log.i(TAG, "mEmptyView GONE");
            }
        }
    }

    /**
     * 添加一个空列表数据页面
     */
    public void addEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }

    /**
     * 添加一个正在加载数据的页面
     */
    public void addLoadingView(View loadingView) {
        this.mLoadingView = loadingView;
    }

}
