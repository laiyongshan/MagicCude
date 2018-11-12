package com.rflash.magiccube.ui.renewalmind;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.renewal.RenewalActivity;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/11/7 09:23
 * @desc:
 */

public class RenewalMindActivity  extends MVPBaseActivity<RenewalMindContract.View,RenewalMindPresenter> implements RenewalMindContract.View,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.renewalmind_rv)
    RecyclerView renewalmind_rv;

    private View notDataView;

    RenewalMindAdapter renewalmindAdapter;
    List<RenewalMindBean.ResultBean> renewalMindList=new ArrayList<>();

    private int pageNum = 1;
    private int TOTAL_COUNTER; //所有的数据总数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renewalmind);
        initView();

        getRemindList();
    }

    //初始化控件
    private void initView(){

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) renewalmind_rv.getParent(), false);

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        renewalmind_rv.setLayoutManager(new LinearLayoutManager(this));
        renewalmindAdapter=new RenewalMindAdapter(renewalMindList);
        renewalmindAdapter.setOnLoadMoreListener(this,renewalmind_rv);
        renewalmindAdapter.disableLoadMoreIfNotFullPage();
        renewalmindAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(RenewalMindActivity.this,RenewalActivity.class);
                intent.putExtra("renewalMindBean",renewalMindList.get(position));
                RenewalMindActivity.this.startActivity(intent);
            }
        });
        renewalmind_rv.setAdapter(renewalmindAdapter);
    }

    @OnClick({R.id.title_back_tv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_back_tv:
                finish();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //获取续期提醒列表
    private  void getRemindList(){
        mPresenter.getRenewalList(pageNum+"");
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        getRemindList();
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
    public void getDataSuccess(RenewalMindBean renewalMindBean) {
        if (renewalMindBean != null) {
            TOTAL_COUNTER = renewalMindBean.getTotalNum();
            if (pageNum == 1) {
                renewalMindList.clear();
                renewalMindList = renewalMindBean.getResult();
                renewalmindAdapter.setNewData(renewalMindList);
                if(renewalMindList.isEmpty())
                    renewalmindAdapter.setEmptyView(notDataView);
            } else {
                renewalMindList.addAll(renewalMindBean.getResult());
                renewalmindAdapter.notifyDataSetChanged();
                renewalmindAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void onLoadMoreRequested() {
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (renewalmindAdapter.getData().size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    renewalmindAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    getRemindList();
                }
                if(refresh_layout!=null)
                    refresh_layout.setEnabled(true);
            }
        }, 1500);
    }
}
