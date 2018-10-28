package com.rflash.magiccube.ui.refund;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2018/10/11.
 * 还款账单
 */

public class RefundActivity extends MVPBaseActivity<RefundContract.View,RefundPresenter> implements RefundContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.refund_rv)
    RecyclerView refund_rv;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    int pageNum=1;

    private final String[] mTitles = {"未还清","已还清","已逾期"};

    RefundAdapter refundAdapter;
    List<RefundBean> refunList=new ArrayList<>();

    int refundType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        initView();
    }

    private void initView(){

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(ToolUtils.Colors);

        for(int i=0;i<mTitles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(mTitles[i]));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){//已还清
                    refundAdapter=new RefundAdapter(0,refunList);
                    refund_rv.setAdapter(refundAdapter);
                }else if(tab.getPosition()==1){//未还清
                    refundAdapter=new RefundAdapter(1,refunList);
                    refund_rv.setAdapter(refundAdapter);
                }else if(tab.getPosition()==2){//已逾期
                    refundAdapter=new RefundAdapter(2,refunList);
                    refund_rv.setAdapter(refundAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
 
        refund_rv.setLayoutManager(new LinearLayoutManager(this));
        refundAdapter=new RefundAdapter(refundType,refunList);
        refund_rv.setAdapter(refundAdapter);

    }


    @OnClick({R.id.title_back_tv})
    public void click(View view){
        switch (view.getId()) {
            case R.id.title_back_tv:
                finish();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getRefundList(pageNum+"");
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        mPresenter.getRefundList(pageNum+"");
    }

    @Override
    public void showRefresh() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void finishRefresh() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void getDataFail(String msg) {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void getDataSuccess(RefundBean response) {

    }
}
