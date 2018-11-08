package com.rflash.magiccube.ui.queryPlanning;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/11/6 16:36
 * @desc:
 */

public class QueryPlanningActvity extends MVPBaseActivity<QueryPlanningContract.View, QueryPlanningPresenter> implements QueryPlanningContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.planning_rv)
    RecyclerView planning_rv;

    @BindView(R.id.filtrate_img)
    ImageView filtrate_img;

    @BindView(R.id.planning_drawerlayout)
    DrawerLayout planning_drawerlayout;

    @BindView(R.id.clear_filter_tv)
    TextView clear_filter_tv;

    @BindView(R.id.sure_filter_tv)
    TextView sure_filter_tv;

    PlanningAdapter planningAdapter;
    List<PlaningBean.ResultBean> planingList = new ArrayList<>();

    private int pageNum = 1;
    private int TOTAL_COUNTER; //所有的数据总数
    String startDate = "", endDate = "", cardSeqno = "", customerName = "", cardNo = "", state = "", tranType = "", accountType = "", syncState = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_planning);
        initView();

        queryPlanning();
    }

    private void initView() {

        refreshLayout.setColorSchemeColors(ToolUtils.Colors);
        refreshLayout.setOnRefreshListener(this);

        planning_rv.setLayoutManager(new LinearLayoutManager(this));
        planningAdapter = new PlanningAdapter(planingList);
        planningAdapter.setOnLoadMoreListener(this, planning_rv);
        planningAdapter.disableLoadMoreIfNotFullPage();
        planning_rv.setAdapter(planningAdapter);
    }

    @OnClick({R.id.filtrate_img, R.id.clear_filter_tv, R.id.sure_filter_tv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.filtrate_img:
                planning_drawerlayout.openDrawer(Gravity.RIGHT);
                break;

            case R.id.clear_filter_tv:
                startDate = "";
                endDate = "";
                cardSeqno = "";
                customerName = "";
                cardNo = "";
                state = "";
                tranType = "";
                accountType = "";
                syncState = "";
                break;

            case R.id.sure_filter_tv:
                queryPlanning();
                planning_drawerlayout.closeDrawer(Gravity.RIGHT);
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    private void queryPlanning() {
        mPresenter.queryPlan(startDate, endDate, cardSeqno, customerName, cardNo, state, tranType, accountType, syncState, pageNum + "");
    }

    @Override
    public void onRefresh() {
        pageNum = 1;
        queryPlanning();
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
    public void getDataFail(Object msg) {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void getDataSuccess(PlaningBean response) {
        if (response != null) {
            TOTAL_COUNTER = response.getTotalNum();
            if (pageNum == 1) {
                planingList.clear();
                planingList = response.getResult();
                planningAdapter.setNewData(planingList);
            } else {
                planingList.addAll(response.getResult());
                planningAdapter.notifyDataSetChanged();
                planningAdapter.loadMoreComplete();
            }
        }
    }


    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (planningAdapter.getData().size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    planningAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    queryPlanning();
                }
                if (refreshLayout != null)
                    refreshLayout.setEnabled(true);
            }
        }, 1500);
    }

    private void getCount(List<PlaningBean.ResultBean> list){
        for(PlaningBean.ResultBean resultBean:list){
        }
    }
}
