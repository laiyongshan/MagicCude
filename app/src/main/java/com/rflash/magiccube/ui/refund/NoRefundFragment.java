package com.rflash.magiccube.ui.refund;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.finance.FinanceBean;
import com.rflash.magiccube.ui.finance.FinanceManagerContract;
import com.rflash.magiccube.ui.finance.FinanceManagerPresenter;
import com.rflash.magiccube.ui.finance.financedetail.FinanceDetailAdapter;
import com.rflash.magiccube.ui.finance.financedetail.FinanceDetailBean;
import com.rflash.magiccube.ui.finance.financedetail.RepayFragment;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/10/31.
 */

public class NoRefundFragment extends MVPBaseFragment<RefundContract.View, RefundPresenter> implements RefundContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener  {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.no_refund_rv)
    RecyclerView no_refund_rv;

    RefundAdapter refundAdapter;
    List<RefundBean.ResultBean> refunList=new ArrayList<>();
    List<RefundBean.ResultBean> NO_REPAID_List=new ArrayList<>();
    RefundBean refundBean;
    int pageNum;
    private int TOTAL_COUNTER; //所有的数据总数

    static NoRefundFragment noRefundFragment;

    public static NoRefundFragment getInstance() {
        if (noRefundFragment == null) {
            noRefundFragment = new NoRefundFragment();
            return noRefundFragment;
        }

        return noRefundFragment;
    }

    @SuppressLint("ValidFragment")
    public NoRefundFragment() {
    }

    @Override
    protected void initView() {

        mPresenter.getRefundList(pageNum+"");


        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        no_refund_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        refundAdapter=new RefundAdapter(refunList);
        refundAdapter.setOnLoadMoreListener(this,no_refund_rv);
        refundAdapter.disableLoadMoreIfNotFullPage();
        no_refund_rv.setAdapter(refundAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_norefund;
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        mPresenter.getRefundList(pageNum+"");
    }

    @Override
    public void onLoadMoreRequested() {
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refundAdapter.getData().size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    refundAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    mPresenter.getRefundList(pageNum+"");
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
            Log.i("lys", "总条数：" + TOTAL_COUNTER);
            if (pageNum == 1) {
                refunList.clear();
                NO_REPAID_List.clear();
                for(RefundBean.ResultBean resultBean:refundBean.getResult())
                    if(resultBean.getRepayState().equals("NO_REPAID"))
                        NO_REPAID_List.add(resultBean);
                refundAdapter.setNewData(NO_REPAID_List);
            } else {
                for(RefundBean.ResultBean resultBean:refundBean.getResult())
                    if(resultBean.getRepayState().equals("NO_REPAID"))
                        NO_REPAID_List.add(resultBean);
                refundAdapter.addData(NO_REPAID_List);
                refundAdapter.loadMoreComplete();
            }
        }
    }

}
