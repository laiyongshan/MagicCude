package com.rflash.magiccube.ui.billconfirm;

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

public class DealFragment extends MVPBaseFragment<BillConfirmContract.View, BillConfirmPresenter> implements BillConfirmContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.bill_deal_rv)
    RecyclerView bill_deal_rv;

    private View notDataView;

    BillConfirmAdapter billConfirmAdapter;
    List<BillConfirmBean.ResultBean> billConfirmBeanList=new ArrayList<>();
    List<BillConfirmBean.ResultBean> DEAL_List=new ArrayList<>();
    int pageNum=1;
    private int TOTAL_COUNTER; //所有的数据总数

    static DealFragment dealFragment;

    public static DealFragment getInstance() {
        if (dealFragment == null) {
            dealFragment = new DealFragment();
            return dealFragment;
        }

        return dealFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_deal;
    }

    @Override
    protected void initView() {
        mPresenter.getBillConfirmList(pageNum+"");

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) bill_deal_rv.getParent(), false);


        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        bill_deal_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        billConfirmAdapter=new BillConfirmAdapter(DEAL_List,mPresenter);
        billConfirmAdapter.setOnLoadMoreListener(this,bill_deal_rv);
        billConfirmAdapter.disableLoadMoreIfNotFullPage();
        bill_deal_rv.setAdapter(billConfirmAdapter);
    }


    @Override
    public void onRefresh() {
        pageNum=1;
        mPresenter.getBillConfirmList(pageNum+"");
    }

    @Override
    public void onLoadMoreRequested() {
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (billConfirmAdapter.getData().size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    billConfirmAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    mPresenter.getBillConfirmList(pageNum+"");
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
    public void getDataSuccess(BillConfirmBean response) {
        if (response != null) {
            TOTAL_COUNTER = response.getTotalNum();
            Log.i("lys", "总条数：" + TOTAL_COUNTER);
            if (pageNum == 1) {
                billConfirmBeanList.clear();
                DEAL_List.clear();
                for(BillConfirmBean.ResultBean bean:response.getResult())
                    if(bean.getState().equals("DEAL"))
                        DEAL_List.add(bean);
                billConfirmAdapter.setNewData(DEAL_List);
                if(DEAL_List.isEmpty())
                    billConfirmAdapter.setEmptyView(notDataView);
            } else {
                for(BillConfirmBean.ResultBean bean:response.getResult())
                    if(bean.getState().equals("DEAL"))
                        DEAL_List.add(bean);
                billConfirmAdapter.addData(DEAL_List);
                billConfirmAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void updateCardBillSuccess() {

    }

}
