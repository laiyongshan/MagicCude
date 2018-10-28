package com.rflash.basemodule;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yangfan on 2017/10/30.
 */

public abstract class BaseFragment extends Fragment {

    protected Activity context;
    protected View mRootView;
    protected Unbinder mUnbinder;

    //用户是否可见
    protected boolean mIsVisible;

    //是否加载完成，当执行完oncreateview,view的初始化后会赋值位true
    protected boolean mIsPrepare;

    //是否加载数据
    protected boolean isLoad;

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据，
     */
    protected  void initData(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
    }

    protected abstract int getLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView =  inflater.inflate(getLayout(), container, false);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView();
        if (mIsVisible) {
            initData();
            isLoad = true;
        }
        mIsPrepare = true;
    }


    /**
     * 一定要手动调用
     * FragmentPagerAdapter中源码中才有调用这个方法
     *
     * @param isVisibleToUser true ：表示对用户可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisible = getUserVisibleHint();
        if (!mIsVisible || isLoad || !mIsPrepare) {
            return;
        }
        initData();
    }
}
