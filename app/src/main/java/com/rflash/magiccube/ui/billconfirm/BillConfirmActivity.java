package com.rflash.magiccube.ui.billconfirm;

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
 * Created by lenovo on 2018/10/10.
 */

public class BillConfirmActivity extends MVPBaseActivity<BillConfirmContract.View,BillConfirmPresenter> implements BillConfirmContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.bill_confirm_rv)
    RecyclerView bill_confirm_rv;

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    int pageNum=1;

    private final String[] mTitles = {"待确认","已确认","已忽略"};

    BillConfirmAdapter billConfirmAdapter;
    List<BillConfirmBean> billConfirmBeanList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_confirm);
        initView();
    }

    private void initView(){

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        for(int i=0;i<mTitles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(mTitles[i]));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){//待确认
                    billConfirmAdapter=new BillConfirmAdapter(0,billConfirmBeanList);
                    bill_confirm_rv.setAdapter(billConfirmAdapter);
                }else if(tab.getPosition()==1){//已确认
                    billConfirmAdapter=new BillConfirmAdapter(1,billConfirmBeanList);
                    bill_confirm_rv.setAdapter(billConfirmAdapter);
                }else if(tab.getPosition()==2){//已忽略
                    billConfirmAdapter=new BillConfirmAdapter(2,billConfirmBeanList);
                    bill_confirm_rv.setAdapter(billConfirmAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bill_confirm_rv.setLayoutManager(new LinearLayoutManager(this));
        billConfirmAdapter=new BillConfirmAdapter(0,billConfirmBeanList);
        bill_confirm_rv.setAdapter(billConfirmAdapter);
    }

    @OnClick({R.id.title_back_tv})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getBillConfirmList(pageNum+"");
    }


    @Override
    public void onRefresh() {
        pageNum=1;
        mPresenter.getBillConfirmList(pageNum+"");
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
    public void getDataSuccess(BillConfirmBean response) {
            if(response!=null){
                if(pageNum==1){

                }else{
                    pageNum++;
//                    billConfirmBeanList.add(response.get)
                }
            }
    }
}
