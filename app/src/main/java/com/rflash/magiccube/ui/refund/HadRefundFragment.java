package com.rflash.magiccube.ui.refund;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/10/31.
 */

public class HadRefundFragment extends MVPBaseFragment<RefundContract.View, RefundPresenter> implements RefundContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.had_refund_rv)
    RecyclerView had_refund_rv;

    private View notDataView;

    RefundAdapter refundAdapter;
    List<RefundBean.ResultBean> refunList=new ArrayList<>();
    List<RefundBean.ResultBean> REPAID_List=new ArrayList<>();
    RefundBean refundBean;
    int pageNum=1;
    int pageSize=50;
    private int TOTAL_COUNTER; //所有的数据总数

    static HadRefundFragment hadRefundFragment;

    public static HadRefundFragment getInstance() {
        if (hadRefundFragment == null) {
            hadRefundFragment = new HadRefundFragment();
            return hadRefundFragment;
        }

        return hadRefundFragment;
    }

    @SuppressLint("ValidFragment")
    public HadRefundFragment() {
    }


    @Override
    protected void initView() {
        mPresenter.getRefundList(pageNum+"",pageSize+"");

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) had_refund_rv.getParent(), false);

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        had_refund_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        refundAdapter=new RefundAdapter(REPAID_List);
        refundAdapter.setOnLoadMoreListener(this,had_refund_rv);
        refundAdapter.disableLoadMoreIfNotFullPage();
        had_refund_rv.setAdapter(refundAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_had_refund;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        mPresenter.getRefundList(pageNum+"",pageSize+"");
    }

    @Override
    public void onLoadMoreRequested() {
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refunList.size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    refundAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    mPresenter.getRefundList(pageNum+"",pageSize+"");
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
    public void getDataFail(String msg) {
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void getDataSuccess(RefundBean response) {
        if (response != null) {
            refundBean = (RefundBean) response;

            TOTAL_COUNTER = refundBean.getTotalNum();
            Log.i("lys", "数据总条数："+TOTAL_COUNTER);
            if (pageNum == 1) {
                REPAID_List.clear();
                refunList.clear();
                refunList.addAll(response.getResult());
                for(RefundBean.ResultBean resultBean:refundBean.getResult()) {
                    if (resultBean.getRepayState().equals("REPAID")) {
                        REPAID_List.add(resultBean);
                    }
                }
                refundAdapter.setNewData(REPAID_List);
                if(REPAID_List.isEmpty())
                    refundAdapter.setEmptyView(notDataView);
            } else {
                refunList.addAll(response.getResult());
                for(RefundBean.ResultBean resultBean:refundBean.getResult())
                    if(resultBean.getRepayState().equals("REPAID"))
                        refundAdapter.addData(resultBean);
                refundAdapter.loadMoreComplete();
            }

            if(refunList.size()<TOTAL_COUNTER) {
                pageNum++;
                mPresenter.getRefundList(pageNum+"",pageSize+"");
            }
        }
    }

}
