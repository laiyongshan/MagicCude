package com.rflash.basemodule.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yangfan on 2017/11/23.
 * 上拉加载更多
 */

public class LoadRecyclerView extends WrapRecyclerView {

    //上拉加载更多的辅助类
    private LoadViewCreator mLoadCreator;

    //上拉加载更多头部的高度
    private int mLoadViewHeight = 0;

    //上拉加载更多头部的view
    private View mLoadView;

    //手指按下Y的位置
    private int mFingerDownY;

    //是否正在拖动
    private boolean mCurrentDrag = false;

    //当前的状态
    private int mCurrentLoadStatus;

    // 手指拖拽的阻力指数
    private float mDragIndex = 0.35f;

    //默认状态
    public static final int LOAD_STATUS_NORMAL = 0x0011;

    //上拉加载更多状态
    public static final int LOAD_STATUS_PULL_DOWN_REFRESH = 0x0022;

    //松开加载更多状态
    public static final int LOAD_STATUS_LOOSEN_LOADING = 0x0033;

    //正在加载更多状态
    public static final int LOAD_STATUS_LOADING = 0x0044;

    public LoadRecyclerView(Context context) {
        super(context);
    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 添加LoadViewCreator
     *
     * @param loadCreator
     */
    public void addLoadViewCreator(LoadViewCreator loadCreator) {
        if (this.mLoadCreator == null) {
            this.mLoadCreator = loadCreator;
            addRefreshView();
        }
    }


    /**
     * 添加上拉的view
     */
    private void addRefreshView() {

        Adapter adapter = getAdapter();
        if (adapter != null && mLoadCreator != null) {
            View loadView = mLoadCreator.getLoadView(getContext(), this);
            if (loadView != null) {
                addFooterView(loadView);
                this.mLoadView = loadView;
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        addRefreshView();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFingerDownY = (int) ev.getRawY();
                Log.i("---dispatchDOWN==", mFingerDownY + "");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("---dispatchACTION_UP==", mFingerDownY + "");
                if (mCurrentDrag) {
                    restoreLoadView();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);

    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {

            case MotionEvent.ACTION_MOVE:
                // 如果recycleview在滚动或者加载中
                if (canScrollDown() || mCurrentLoadStatus == LOAD_STATUS_LOADING) {
                    Log.i("--onTouchEvent--", "到底部");
                    return super.onTouchEvent(e);
                }
                //显示的高度小于view的高度
                if (computeVerticalScrollExtent()<getHeight()){
                    return super.onTouchEvent(e);
                }
                //如果没有数据时
                if (getAdapter()!= null&& getAdapter().getItemCount()<=1){
                    return super.onTouchEvent(e);
                }


                if (mLoadCreator != null && mLoadView != null) {
                    mLoadViewHeight = mLoadView.getMeasuredHeight();
                    Log.i("---mLoadViewHeight-", mLoadViewHeight + "");
                }

                // 解决上拉加载更多自动滚动问题
                if (mCurrentDrag) {
                    scrollToPosition(getAdapter().getItemCount() - 1);
                }

                // 获取手指触摸拖拽的距离
                int distanceY = (int) ((e.getRawY() - mFingerDownY) * mDragIndex);
                Log.i("---distanceY-", "" + distanceY);

                if (distanceY < 0 && mLoadView != null) {
                    setLoadViewMarginBottom(-distanceY);
                    updateLoadStatus(-distanceY);
                    mCurrentDrag = true;
                    return true;
                }
                break;

        }
        return super.onTouchEvent(e);
    }

    /**
     * 重置当前加载更多状态
     */
    private void restoreLoadView() {
        int currentBottomMargin = ((MarginLayoutParams) mLoadView.getLayoutParams()).bottomMargin;
        int finalBottomMargin = 0;
        if (mCurrentLoadStatus == LOAD_STATUS_LOOSEN_LOADING) {
            mCurrentLoadStatus = LOAD_STATUS_LOADING;
            if (mLoadCreator != null) {
                mLoadCreator.onLoading();
            }
            if (mListener != null) {
                mListener.onLoad();
            }
        }

        int distance = currentBottomMargin - finalBottomMargin;
        Log.i("---distance-", distance + "");

        // 回弹到指定位置
        ValueAnimator animator = ObjectAnimator.ofFloat(currentBottomMargin, finalBottomMargin).setDuration(distance);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentTopMargin = (float) animation.getAnimatedValue();
                setLoadViewMarginBottom((int) currentTopMargin);
            }
        });
        animator.start();
        mCurrentDrag = false;
    }

    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll up. Override this if the child view is a custom view.
     * 这个是从SwipeRefreshLayout里面copy过来的源代码
     */
    public boolean canScrollDown() {
        return ViewCompat.canScrollVertically(this, 1);
    }


    /**
     * 更新加载的状态
     */
    private void updateLoadStatus(int distanceY) {
        if (distanceY <= 0) {
            mCurrentLoadStatus = LOAD_STATUS_NORMAL;
        } else if (distanceY < mLoadViewHeight * 1.5) {
            mCurrentLoadStatus = LOAD_STATUS_PULL_DOWN_REFRESH;
        } else {
            mCurrentLoadStatus = LOAD_STATUS_LOOSEN_LOADING;
        }

        if (mLoadCreator != null) {
            mLoadCreator.onPull(distanceY, mLoadViewHeight, mCurrentLoadStatus);
        }
    }

    /**
     * 设置加载View的marginBottom
     */
    public void setLoadViewMarginBottom(int marginBottom) {
        MarginLayoutParams params = (MarginLayoutParams) mLoadView.getLayoutParams();
        if (marginBottom < 0) {
            marginBottom = 0;
        }
        params.bottomMargin = marginBottom;
        mLoadView.setLayoutParams(params);
    }

    /**
     * 停止加载更多(需要主动去调，应用场景：数据加载完成，recycleView.onStopLoad())
     */
    public void onStopLoad() {
        mCurrentLoadStatus = LOAD_STATUS_NORMAL;
        if (mLoadCreator != null) {
            mLoadCreator.onStopLoad();
        }
    }

    // 处理加载更多回调监听
    private OnLoadMoreListener mListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mListener = listener;
    }

    public interface OnLoadMoreListener {
        void onLoad();
    }




}
