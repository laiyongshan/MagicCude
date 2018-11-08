package com.rflash.magiccube.ui.billconfirm;

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

public class IgnoreFragment extends MVPBaseFragment<BillConfirmContract.View, BillConfirmPresenter> implements BillConfirmContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.bill_ignore_rv)
    RecyclerView bill_ignore_rv;

    private View notDataView;

    BillConfirmAdapter billConfirmAdapter;
    List<BillConfirmBean.ResultBean> billConfirmBeanList=new ArrayList<>();
    List<BillConfirmBean.ResultBean> IGNORE_List=new ArrayList<>();
    int pageNum=1;
    private int TOTAL_COUNTER; //所有的数据总数

    static IgnoreFragment ignoreFragment;

    public static IgnoreFragment getInstance() {
        if (ignoreFragment == null) {
            ignoreFragment = new IgnoreFragment();
            return ignoreFragment;
        }

        return ignoreFragment;
    }

    @SuppressLint("ValidFragment")
    public IgnoreFragment() {
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_ignore;
    }

    @Override
    protected void initView() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) bill_ignore_rv.getParent(), false);

        mPresenter.getBillConfirmList(pageNum+"");

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        bill_ignore_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        billConfirmAdapter=new BillConfirmAdapter(IGNORE_List,mPresenter);
        billConfirmAdapter.setOnLoadMoreListener(this,bill_ignore_rv);
        billConfirmAdapter.disableLoadMoreIfNotFullPage();
        bill_ignore_rv.setAdapter(billConfirmAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        pageNum=1;
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
                if (billConfirmBeanList.size() >= TOTAL_COUNTER) {
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
                IGNORE_List.clear();
                billConfirmBeanList.addAll(response.getResult());
                for(BillConfirmBean.ResultBean bean:response.getResult())
                    if(bean.getState().equals("IGNORE"))
                        IGNORE_List.add(bean);
                billConfirmAdapter.setNewData(IGNORE_List);
                if(IGNORE_List.isEmpty())
                    billConfirmAdapter.setEmptyView(notDataView);
            } else {
                billConfirmBeanList.addAll(response.getResult());
                for(BillConfirmBean.ResultBean bean:response.getResult())
                    if(bean.getState().equals("IGNORE"))
                        billConfirmAdapter.addData(bean);

                billConfirmAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void updateCardBillSuccess() {

    }

}
