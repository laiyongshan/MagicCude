package com.rflash.magiccube.ui.notify;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.util.ToolUtils;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/11/7 10:11
 * @desc:通知页面
 */

public class NotifyActivity extends MVPBaseActivity<NotifyContract.View,NotifyPresenter> implements NotifyContract.View,  BaseQuickAdapter.RequestLoadMoreListener,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.back_iv)
    ImageView back_iv;

    @BindView(R.id.notify_rv)
    RecyclerView notify_rv;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    private View notDataView;

    NotifyAdapter mNotifyAdapter;

    int pageNum=1;
    int pageSize=50;
    int TOTAL_COUNTER;

    List<NotifyBean.ResultBean> notifyBeanList=new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        RichText.initCacheDir(this);
        initView();

        getNotifyData(pageNum+"");
    }

    //初始化控件
    private void initView(){
        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) notify_rv.getParent(), false);

        notify_rv.setLayoutManager(new LinearLayoutManager(this));
        mNotifyAdapter=new NotifyAdapter(notifyBeanList);
        mNotifyAdapter.setOnLoadMoreListener(this, notify_rv);
        notify_rv.setAdapter(mNotifyAdapter);
    }

    @OnClick({R.id.back_iv})
    public void click(View view){
        switch (view.getId()){
            case R.id.back_iv:
                finish();
                break;
        }
    }

    private void getNotifyData(String pageNum){
        mPresenter.getNotifyData(pageNum,pageSize+"");
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        getNotifyData(pageNum+"");
    }

    @Override
    public void showRefresh() {
        refresh_layout.setRefreshing(true);
    }

    @Override
    public void finishRefresh() {
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void getDataFail(String msg) {
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void getDataSuccess(NotifyBean response){
        if (response != null) {
            TOTAL_COUNTER = response.getTotalNum();
            notifyBeanList.clear();
            notifyBeanList=response.getResult();
            if (pageNum == 1) {
                mNotifyAdapter.setNewData(notifyBeanList);
                if(notifyBeanList.isEmpty())
                    mNotifyAdapter.setEmptyView(notDataView);
            } else {
                mNotifyAdapter.addData(notifyBeanList);
                mNotifyAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void onLoadMoreRequested(){
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (mNotifyAdapter.getData().size() >= TOTAL_COUNTER){
                    pageNum=1;
                    //数据全部加载完毕
                    mNotifyAdapter.loadMoreEnd();
                } else{
                    //获取更多数据
                    pageNum++;
                    getNotifyData(pageNum+"");
                }
                if(refresh_layout!=null)
                    refresh_layout.setEnabled(true);
            }
        },1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束时清空内容
        RichText.clear(this);
    }
}
