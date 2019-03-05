package com.rflash.magiccube.ui.refund;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class OverTimeFragment extends MVPBaseFragment<RefundContract.View, RefundPresenter> implements RefundContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.overtime_refund_rv)
    RecyclerView overtime_refund_rv;

    private View notDataView;

    RefundAdapter refundAdapter;
    List<RefundBean.ResultBean> refunList = new ArrayList<>();
    List<RefundBean.ResultBean> OVERDUE_List = new ArrayList<>();
    RefundBean refundBean;
    int pageNum = 1;
    int pageSize = 50;
    private int TOTAL_COUNTER; //所有的数据总数

    static OverTimeFragment overTimeFragment;

    public static OverTimeFragment getInstance() {
        if (overTimeFragment == null) {
            overTimeFragment = new OverTimeFragment();
            return overTimeFragment;
        }
        return overTimeFragment;
    }

    @SuppressLint("ValidFragment")
    public OverTimeFragment() {

    }

    @Override
    protected void initView() {
        mPresenter.getRefundList(pageNum + "", pageSize + "");

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) overtime_refund_rv.getParent(), false);

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        overtime_refund_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        refundAdapter = new RefundAdapter(OVERDUE_List);
        refundAdapter.setOnLoadMoreListener(this, overtime_refund_rv);
        refundAdapter.disableLoadMoreIfNotFullPage();
        overtime_refund_rv.setAdapter(refundAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_overtime;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRefresh() {
        pageNum = 1;
        mPresenter.getRefundList(pageNum + "", pageSize + "");
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
                    mPresenter.getRefundList(pageNum + "", pageSize + "");
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

            if (pageNum == 1) {
                OVERDUE_List.clear();
                refunList.clear();
                refunList.addAll(refundBean.getResult());
                for (RefundBean.ResultBean resultBean : refundBean.getResult())
                    if (resultBean.getRepayState().equals("OVERDUE"))
                        OVERDUE_List.add(resultBean);
                refundAdapter.setNewData(OVERDUE_List);
                if (OVERDUE_List.isEmpty())
                    refundAdapter.setEmptyView(notDataView);
            } else {
                refunList.addAll(refundBean.getResult());
                for (RefundBean.ResultBean resultBean : refundBean.getResult()) {
                    if (resultBean.getRepayState().equals("OVERDUE"))
                        refundAdapter.addData(resultBean);
                }
                refundAdapter.loadMoreComplete();
            }

            if (refunList.size() < TOTAL_COUNTER) {
                pageNum++;
                mPresenter.getRefundList(pageNum + "", pageSize + "");
            }
        }
    }


}
