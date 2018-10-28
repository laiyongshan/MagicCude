package com.rflash.magiccube.ui.bill;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.rflash.basemodule.view.LoadRecyclerView;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.view.DefaultLoadCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 账单确认界面
 */

public class BillActivity extends MVPBaseActivity<BillContract.View, BillPresenter> implements BillContract.View, SwipeRefreshLayout.OnRefreshListener, LoadRecyclerView.OnLoadMoreListener, BillAdapter.SureMoneyListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    LoadRecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.iv_nodata)
    ImageView ivNoData;

    private BillAdapter billAdapter;

    private ArrayList<Bill.ResultBean> list = new ArrayList<>();


    //当前页码
    private int pageNum = 1;
    //总页数
    private int totalPage;
    //是否是  下拉刷新
    private boolean refresh = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();

    }

    private void initView() {

        toolbar.setTitle("账单确认");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillActivity.this.finish();
            }
        });
        //下拉时圆圈颜色
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#474E7A"));
        //下拉监听
        swipeRefreshLayout.setOnRefreshListener(this);
        //创建BillAdapter
        billAdapter = new BillAdapter(this, list, R.layout.layout_bill);
        //设置LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加默认的上拉
        recyclerView.addLoadViewCreator(new DefaultLoadCreator());
        //添加上拉监听
        recyclerView.setOnLoadMoreListener(this);
        //设置空白界面
        recyclerView.addEmptyView(ivNoData);
        //设置billAdapter
        recyclerView.setAdapter(billAdapter);
        //设置billAdapter 的点击监听
        billAdapter.setListener(this);
        mPresenter.queryCardBill(Config.ISCONFIRM_N, Config.VALID, "1");
    }

    /**
     * SwipeRefreshLayout 下拉
     */
    @Override
    public void onRefresh() {
        refresh = true;
        pageNum = 1;
        mPresenter.queryCardBill(Config.ISCONFIRM_N, Config.VALID, "1");
    }

    /**
     * recyclerView 上拉
     */
    @Override
    public void onLoad() {
        if (totalPage >= pageNum + 1) {
            mPresenter.queryCardBill(Config.ISCONFIRM_N, Config.VALID, ++pageNum + "");
        } else {
            recyclerView.onStopLoad();
            Toast.makeText(this, "没有更多数据啦", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取账单列表
     *
     * @param bill
     */
    @Override
    public void getCardBill(Bill bill) {
        totalPage = bill.getTotalPage();
        List<Bill.ResultBean> result = bill.getResult();
        if (result != null) {
            if (refresh) {
                list.clear();
            }
            list.addAll(result);
        }
        billAdapter.notifyDataSetChanged();
        refresh = false;
    }

    /**
     * 修改账单金额 成功
     *
     * @param position
     */
    @Override
    public void updateSuccess(int position) {
        list.remove(position);
        billAdapter.notifyDataSetChanged();
    }

    @Override
    public void dismissProgress() {
        super.dismissProgress();
        recyclerView.onStopLoad();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    /**
     * 点击确认金额按钮
     *
     * @param position 下标
     * @param fenMoney
     */
    @Override
    public void onSureMoney(int position, String fenMoney) {
        hideSoftInput();
        Bill.ResultBean resultBean = list.get(position);
        mPresenter.updateCardBill(resultBean.getBillId() + "", resultBean.getCardNo(), resultBean.getBillMonth(),fenMoney, Config.CONFIRM, position);
    }
}
