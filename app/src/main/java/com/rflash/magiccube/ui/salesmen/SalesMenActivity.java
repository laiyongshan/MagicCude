package com.rflash.magiccube.ui.salesmen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.rflash.basemodule.BaseActivity;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/11 09:15
 * @desc:
 */

public class SalesMenActivity extends MVPBaseActivity<SalesMenContract.View,SalesMenPresenter> implements SalesMenContract.View, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.salesmen_rv)
    RecyclerView salesmen_rv;

    @BindView(R.id.back_iv)
    ImageView back_iv;

    @BindView(R.id.add_salesmen_iv)
    ImageView add_salesmen_iv;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    String name="";//业务员
    String profitRatio="";//提成费率

    AddSalesDialog addSalesDialog;

    SalesMenAdapter salesMenAdapter;
    SalesmenBean salesmenBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_men);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    //获取业务员信息列表
    private void getData(){
        refresh_layout.setRefreshing(true);
        mPresenter.querySalesMan(name,profitRatio);
    }

    //新增、修改业务员
    protected void operaSalesMan(String flag,String id,String name,String profitRatio){
        refresh_layout.setRefreshing(true);
        mPresenter.operaSalesMan(id,flag,name,profitRatio);

    }

    //删除业务员
    protected  void deleteSalesMan(String id,String name){
        mPresenter.deleteSalesMan(id,name);
    }

    private void initView(){

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setColorSchemeColors(ToolUtils.Colors);

        salesmen_rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick({R.id.back_iv,R.id.add_salesmen_iv})
    public void click(View view){
        switch (view.getId()){
            case R.id.back_iv:
                finish();
                break;

            case R.id.add_salesmen_iv:
                addSalesDialog=new AddSalesDialog(SalesMenActivity.this, R.style.Dialog, new AddSalesDialog.AddListener() {
                    @Override
                    public void SureListener(String name, String profitRatio) {
                        operaSalesMan("ADD","",name,profitRatio);
                        addSalesDialog.dismiss();
                    }

                    @Override
                    public void CancleListener() {

                    }
                });
                if(!addSalesDialog.isShowing())
                     addSalesDialog.show();
                break;
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.querySalesMan(name,profitRatio);
    }

    @Override
    public void getDataFail(String msg) {
        refresh_layout.setRefreshing(false);
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
    public void getDataSuccess(Object response) {
        Log.i("lys",response.getClass().getName());
        if(response!=null){
            salesmenBean= (SalesmenBean) response;
            salesMenAdapter=new SalesMenAdapter(SalesMenActivity.this,salesmenBean.getResult());
            salesmen_rv.setAdapter(salesMenAdapter);
        }
    }

    @Override
    public void deleteSalesManSuccess(Object response) {
        getData();//删除成功后重新查询
        Log.i("lys",response.getClass().getName());
    }

    @Override
    public void operaSalesManSuccess(Object response) {
        getData();//新增、修改之后重新查询
        Log.i("lys",response.getClass().getName());
    }
}
