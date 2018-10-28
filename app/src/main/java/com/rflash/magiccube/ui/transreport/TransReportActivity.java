package com.rflash.magiccube.ui.transreport;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.basemodule.view.LoadRecyclerView;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.creditpersonalmessage.CreditPersonalMessageActivity;
import com.rflash.magiccube.ui.creditqueryitem.CreditQueryItemActivity;
import com.rflash.magiccube.ui.creditrecharge.CreditreChargeActivity;
import com.rflash.magiccube.ui.creditreportmessage.CreditReportMessageActivity;
import com.rflash.magiccube.ui.userportrait.CreditShareItem;
import com.rflash.magiccube.view.DefaultLoadCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *  银联用户交易报告
 *  Created by Guobaihui on 2018/03/12.
 */

public class TransReportActivity extends MVPBaseActivity<TransReportContract.View, TransReportPresenter> implements TransReportContract.View, SwipeRefreshLayout.OnRefreshListener, LoadRecyclerView.OnLoadMoreListener, TransReportAdapter.QueryReportListener, TransReportAdapter.PersonalMessageListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    LoadRecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.iv_nodata)
    ImageView ivNoData;

    @BindView(R.id.tv_over_times)
    TextView tv_over_times;

    //当前页码
    private int pageNum = 1;
    //总页数
    private int totalPage;
    //是否是  下拉刷新
    private boolean refresh = false;

    private TransReportAdapter transReportAdapter;
    private ArrayList<CreditShareItem.ResultBean> list = new ArrayList<>();

    private String count = "0";
    private String unitPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_query_share);
        initView();
    }

    private void initView() {
        toolbar.setTitle("银联用户交易报告");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransReportActivity.this.finish();
            }
        });

        Bundle bundle = this.getIntent().getExtras();
        unitPrice = (String) bundle.get("unitPrice");

        //下拉时圆圈颜色
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#474E7A"));
        //下拉监听
        swipeRefreshLayout.setOnRefreshListener(this);
        //创建transReportAdapter
        transReportAdapter = new TransReportAdapter(this, list, R.layout.layout_credit_query_share);
        //设置LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加默认的上拉
        recyclerView.addLoadViewCreator(new DefaultLoadCreator());
        //添加上拉监听
        recyclerView.setOnLoadMoreListener(this);
        //设置空白界面
        recyclerView.addEmptyView(ivNoData);
        //设置transReportAdapter
        recyclerView.setAdapter(transReportAdapter);
        //设置transReportAdapter 的点击监听
        transReportAdapter.setListener(this);
        transReportAdapter.setReportListener(this);
//        mPresenter.queryTransReport(Config.TRANS_REPORT, "1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh = true;
        pageNum = 1;
        mPresenter.queryTransReport(Config.TRANS_REPORT, "1");
    }

    /**
     * SwipeRefreshLayout 下拉
     */
    @Override
    public void onRefresh() {
        refresh = true;
        pageNum = 1;
        mPresenter.queryTransReport(Config.TRANS_REPORT, "1");
    }

    /**
     * recyclerView 上拉
     */
    @Override
    public void onLoad() {
        if (totalPage >= pageNum + 1) {
            mPresenter.queryTransReport(Config.TRANS_REPORT, ++pageNum + "");
        } else {
            recyclerView.onStopLoad();
            Toast.makeText(this, "没有更多数据啦", Toast.LENGTH_SHORT).show();
        }
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

    @OnClick({R.id.btn_recharge, R.id.btn_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_recharge://充值次数
                Intent intentCharge = new Intent(this,CreditreChargeActivity.class);
                Bundle bundleCharge = new Bundle();
                bundleCharge.putString("total","银联用户交易报告");
                bundleCharge.putString("creditType", Config.TRANS_REPORT);
                bundleCharge.putString("unitPrice",unitPrice);
                intentCharge.putExtras(bundleCharge);
                startActivityForResult(intentCharge, Config.INTENTOK);
                break;
            case R.id.btn_query://查询
                if("0".equals(count)){
                    Toast.makeText(this,"当前查询次数为0，请充值次数。",Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent intent = new Intent(this,CreditQueryItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("count", count);
                bundle.putString("total", "银联用户交易报告");
                bundle.putString("creditType", Config.TRANS_REPORT);
                intent.putExtras(bundle);
                startActivityForResult(intent, Config.INTENTOK);
                break;

        }
    }

    /**
     * 银联用户交易报告列表
     *
     * @param transReport
     */
    @Override
    public void getTransReport(CreditShareItem transReport) {
        totalPage = transReport.getTotalPage();
        List<CreditShareItem.ResultBean> result = transReport.getResult();
        if(StringUtil.isEmpty(transReport.getTimes())){
            tv_over_times.setText("剩余次数：0");
            count = "0";
        }else{
            count = transReport.getTimes();
            tv_over_times.setText("剩余次数："+count);
        }
        if (result != null) {
            if (refresh) {
                list.clear();
            }
            list.addAll(result);
        }
        transReportAdapter.notifyDataSetChanged();
        refresh = false;
    }

    // 跳转个人信息页面
    @Override
    public void goPersonalMessage(int position, CreditShareItem.ResultBean userPortraitBean) {
        Bundle bundle = new Bundle();
        bundle.putString("name",userPortraitBean.getName());
        bundle.putString("identityCard",userPortraitBean.getIdentityCard());
        bundle.putString("cardNo",userPortraitBean.getCardNo());
        bundle.putString("mobile",userPortraitBean.getMobile());
        bundle.putString("date",userPortraitBean.getDate());
        bundle.putString("orderNo",userPortraitBean.getOrderNo());
        ActivityIntent.readyGo(this, CreditPersonalMessageActivity.class,bundle);
    }

    // 跳转到报告查看页面
    @Override
    public void goQueryReport(int position, CreditShareItem.ResultBean userPortraitBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("creditShareItem",userPortraitBean);
        bundle.putString("creditType",Config.TRANS_REPORT);
        ActivityIntent.readyGo(this,CreditReportMessageActivity.class,bundle);
    }

    // 从充值或查询界面获取剩余次数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch ( resultCode ) {
            case Config.INTENTOK :
                tv_over_times.setText("剩余次数："+data.getExtras().getString( "count" ));
                break;
            default :
                break;
        }
    }
}
