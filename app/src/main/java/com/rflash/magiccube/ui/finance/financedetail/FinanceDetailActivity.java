package com.rflash.magiccube.ui.finance.financedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.finance.FinanceManagerContract;
import com.rflash.magiccube.ui.finance.FinanceManagerPresenter;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/8 15:57
 * @desc:
 */

public class FinanceDetailActivity extends MVPBaseActivity<FinanceManagerContract.View,FinanceManagerPresenter> implements FinanceManagerContract.View,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.finance_detail_drawerlayout)
    DrawerLayout finance_detail_drawerlayout;

    @BindView(R.id.channelName_sp)
    MaterialSpinner channelName_sp;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.finance_detail_rv)
    RecyclerView finance_detail_rv;

    FinanceDetailAdapter financeDetailAdapter;
    List<FinanceDetailBean> financeDetailBeanList=new ArrayList<>();

    private final String[] mTitles = {"消费","还款"};

    String cardNo="4392250810275866";
    String tranType="";
    String channelId="";
    int pageNum=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_detail);
        initView();

        queryPlan();
    }

    private void initView(){

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setColorSchemeColors(ToolUtils.Colors);

        channelName_sp.setItems("请选择渠道名");
        channelName_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {

            }
        });

        for(int i=0;i<mTitles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(mTitles[i]));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){//消费
                    financeDetailAdapter=new FinanceDetailAdapter(0,financeDetailBeanList);
                    finance_detail_rv.setAdapter(financeDetailAdapter);
                }else if(tab.getPosition()==1){//还款
                    financeDetailAdapter=new FinanceDetailAdapter(1,financeDetailBeanList);
                    finance_detail_rv.setAdapter(financeDetailAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for(int i=0;i<20;i++){
            financeDetailBeanList.add(new FinanceDetailBean());
        }

        financeDetailAdapter=new FinanceDetailAdapter(0,financeDetailBeanList);
        finance_detail_rv.setLayoutManager(new LinearLayoutManager(this));
        finance_detail_rv.setAdapter(financeDetailAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ToolUtils.setIndicator(tabLayout,40,40);
    }

    @OnClick({R.id.title_back_tv,R.id.filtrate_img,R.id.clear_filter_tv,R.id.sure_filter_tv})
    public void OnClick(View view){
        switch (view.getId()) {
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.filtrate_img:
                finance_detail_drawerlayout.openDrawer(Gravity.RIGHT);
                break;

            case R.id.clear_filter_tv:

                break;

            case R.id.sure_filter_tv:
                queryPlan();
                finance_detail_drawerlayout.closeDrawer(Gravity.RIGHT);
                break;
        }
    }

    private void queryPlan(){
        mPresenter.queryPlan(cardNo,tranType,channelId,pageNum+"");
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        queryPlan();
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void getDataFail(Object msg) {

    }

    @Override
    public void getDataSuccess(Object response) {

    }

}
