package com.rflash.magiccube.ui.finance.financedetail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.finance.FinanceBean;
import com.rflash.magiccube.ui.finance.FinanceManagerContract;
import com.rflash.magiccube.ui.finance.FinanceManagerPresenter;
import com.rflash.magiccube.ui.salesmen.SalesmenBean;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/10/30.
 */

@SuppressLint("ValidFragment")
public class SaleFragment extends MVPBaseFragment<FinanceManagerContract.View, FinanceManagerPresenter> implements FinanceManagerContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.finance_sale_rv)
    RecyclerView finance_sale_rv;

    FinanceDetailAdapter financeDetailAdapter;
    List<FinanceDetailBean.ResultBean> REPAY_List = new ArrayList<>();//收款

    String cardNo = "";
    String tranType = "SALE";
    String channelId = "";
    private int pageNum = 1;
    private int TOTAL_COUNTER; //所有的数据总数
    FinanceBean.ResultBean financeBean;
    FinanceDetailBean financeDetailBean;


    static SaleFragment saleFragment;

    public static SaleFragment getInstance(FinanceBean.ResultBean financeBean){
        if(saleFragment==null){
            saleFragment=new SaleFragment(financeBean);
            return  saleFragment;
        }
        return  saleFragment;
    }

    public SaleFragment(FinanceBean.ResultBean financeBean){
        this.financeBean = financeBean;
        cardNo = financeBean.getCardNo() + "";
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_sale;
    }

    @Override
    protected void initView() {

        queryPlan();

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        finance_sale_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        financeDetailAdapter=new FinanceDetailAdapter(REPAY_List);
        financeDetailAdapter.setOnLoadMoreListener(this,finance_sale_rv);
        financeDetailAdapter.disableLoadMoreIfNotFullPage();
        finance_sale_rv.setAdapter(financeDetailAdapter);
    }


    private void queryPlan() {
        mPresenter.queryPlan(cardNo, tranType, channelId, pageNum + "");
    }

    @Override
    public void onRefresh() {
        pageNum = 1;
        queryPlan();
    }

    @Override
    public void onLoadMoreRequested() {
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (financeDetailAdapter.getData().size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    financeDetailAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    queryPlan();
                }
                refresh_layout.setEnabled(true);
            }
        }, 1500);
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
    public void getDataFail(Object msg) {
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void getDataSuccess(Object response) {
        if (response != null) {
            financeDetailBean = (FinanceDetailBean) response;
            TOTAL_COUNTER = financeDetailBean.getTotalNum();
            Log.i("lys", "总条数：" + TOTAL_COUNTER);
            if (pageNum == 1) {
                REPAY_List.clear();
                REPAY_List = financeDetailBean.getResult();
                financeDetailAdapter.setNewData(REPAY_List);
            } else {
                REPAY_List.addAll(financeDetailBean.getResult());
                financeDetailAdapter.addData(REPAY_List);
                financeDetailAdapter.loadMoreComplete();
            }
        }
    }

}