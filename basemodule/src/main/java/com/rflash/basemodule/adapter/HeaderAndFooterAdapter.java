package com.rflash.basemodule.adapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangfan on 2017/11/23.
 * 本类是一个RecyclerView.Adapter的包装类，主要是给RecyclerView添加头部（可以多个）和尾部（可以多个）
 * 如果不需要添加头部和尾部，使用{@link ( BaseAdp)}
 * 原理：通过不同的viewType 去添加头部和尾部的view
 */

public class HeaderAndFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 10000;
    private static final int BASE_ITEM_TYPE_FOOTER = 20000;


    private SparseArrayCompat<View> mHeaderViews;
    private SparseArrayCompat<View> mFootViews;


    private RecyclerView.Adapter mInnerAdapter;


    public HeaderAndFooterAdapter(RecyclerView.Adapter mInnerAdapter) {
        this.mInnerAdapter = mInnerAdapter;
        mHeaderViews = new SparseArrayCompat<>();
        mFootViews = new SparseArrayCompat<>();
    }

    /**
     * 添加头部view
     *
     * @param headerView
     */
    public void addHeaderView(View headerView) {
        mHeaderViews.put(getHeaderCount() + BASE_ITEM_TYPE_HEADER, headerView);
        notifyDataSetChanged();

    }




    public int getHeaderCount() {
        return mHeaderViews.size();
    }

    /**
     * 添加尾部
     *
     * @param footerView
     */
    public void addFooterView(View footerView) {
        mFootViews.put(getFooterCount() + BASE_ITEM_TYPE_FOOTER, footerView);
        notifyDataSetChanged();
    }



    /**
     * 尾部的数量
     *
     * @return
     */
    public int getFooterCount() {
        return mFootViews.size();
    }


    /**
     * 获取mInnerAdapter 列表条数
     *
     * @return
     */
    private int getInnerAdapterItemCount() {
        return mInnerAdapter.getItemCount();
    }


    /**
     * 是否是头部view
     *
     * @param position 当前列表的下标
     * @return 比如 HeaderView 有两个，那么下标为0，1的是头部
     */
    private boolean isHeaderView(int position) {
        return position < getHeaderCount();
    }


    /**
     * 是否是尾部view
     *
     * @param position 当前列表的下标
     * @return 比如 FooterView 有两个，HeaderView 有两个，中间的列表有4个，一共8条数据，下标为6，7的是尾部。
     */
    private boolean isFooterView(int position) {
        return position >= getInnerAdapterItemCount() + getHeaderCount();
    }


    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseHolder holder = null;
        if (mHeaderViews.get(viewType) != null) {
            holder = new BaseHolder(mHeaderViews.get(viewType));
            return holder;
        } else if (mFootViews.get(viewType) != null) {
            holder = new BaseHolder(mFootViews.get(viewType));
            return holder;
        } else {
            return mInnerAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (isHeaderView(position)) {
            return;
        }

        if (isFooterView(position)) {
            return;
        }

        mInnerAdapter.onBindViewHolder(holder, position - getHeaderCount());

    }


    /**
     * 获取列表的类型
     *
     * @param position 当前列表的下标
     *                 比如：头部2个，中间4个，尾部2个。
     *                 那么mFootViews就是2个长度，下标分别是0，1对应position下标应该是6，7
     *                 mInnerAdapter长度是4  现在的下标是2,3,4,5对应原来mInnerAdapter.getItemViewType(position)的下标0,1,2,3
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterView(position)) {
            return mFootViews.keyAt(position - getHeaderCount() - getInnerAdapterItemCount());
        } else {
            return mInnerAdapter.getItemViewType(position - getHeaderCount());
        }
    }

    /**
     * 列表条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return getInnerAdapterItemCount() + getHeaderCount() + getFooterCount();
    }


    /**
     * 解决多个header或者footer不在一行的问题
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || (isFooterView(position))) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }


}
