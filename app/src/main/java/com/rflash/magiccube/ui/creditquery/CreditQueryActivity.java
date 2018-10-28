package com.rflash.magiccube.ui.creditquery;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.bill.Bill;
import com.rflash.magiccube.ui.billcheck.BillCheckActivity;
import com.rflash.magiccube.ui.creditapplication.CreditApplicationActivity;
import com.rflash.magiccube.ui.creditinternet.CreditInternetActivity;
import com.rflash.magiccube.ui.suspectcash.SuspectCashActivity;
import com.rflash.magiccube.ui.transreport.TransReportActivity;
import com.rflash.magiccube.ui.userportrait.UserPortraitActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *  信用查询界面
 *  Created by Guobaihui on 2018/03/12.
 */

public class CreditQueryActivity extends MVPBaseActivity<CreditQueryContract.View, CreditQueryPresenter> implements CreditQueryContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //银联用户画像
    @BindView(R.id.tv_user_portrait_count)
    TextView tv_user_portrait_count;
    //银联用户交易报告
    @BindView(R.id.tv_trans_report_count)
    TextView tv_trans_report_count;
    //用户疑似套现
    @BindView(R.id.tv_suspect_cash_count)
    TextView tv_suspect_cash_count;
    //用户账单真伪核验
    @BindView(R.id.tv_bill_check_count)
    TextView tv_bill_check_count;
    //云信贷申请反欺诈
    @BindView(R.id.tv_cloud_credit_application_count)
    TextView tv_cloud_credit_application_count;
    //云信贷互联反欺诈
    @BindView(R.id.tv_cloud_credit_internet_count)
    TextView tv_cloud_credit_internet_count;

    private String userPrice;
    private String transPrice;
    private String suspectPrice;
    private String billPrice;
    private String applicationPrice;
    private String internetPrice;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_query);
        initView();
    }

    @OnClick({R.id.user_portrait_layout, R.id.trans_report_layout,
            R.id.suspect_cash_layout, R.id.bill_check_layout,
            R.id.cloud_credit_application_layout, R.id.cloud_credit_internet_layout})
    public void onViewClicked(View view) {
        Bundle bundle  = null;
        switch (view.getId()) {
            case R.id.user_portrait_layout://银联用户画像
                bundle = new Bundle();
                bundle.putString("unitPrice",userPrice);// 传递单价
                ActivityIntent.readyGo(this, UserPortraitActivity.class, bundle);
                break;
            case R.id.trans_report_layout://银联用户交易报告
                bundle = new Bundle();
                bundle.putString("unitPrice",transPrice);
                ActivityIntent.readyGo(this, TransReportActivity.class, bundle);
                break;
            case R.id.suspect_cash_layout://用户疑似套现
                bundle = new Bundle();
                bundle.putString("unitPrice",suspectPrice);
                ActivityIntent.readyGo(this, SuspectCashActivity.class, bundle);
                break;
            case R.id.bill_check_layout://用户账单真伪核验
                bundle = new Bundle();
                bundle.putString("unitPrice",billPrice);
                ActivityIntent.readyGo(this, BillCheckActivity.class, bundle);
                break;
            case R.id.cloud_credit_application_layout://云信贷申请反欺诈
                bundle = new Bundle();
                bundle.putString("unitPrice",applicationPrice);
                ActivityIntent.readyGo(this, CreditApplicationActivity.class, bundle);
                break;
            case R.id.cloud_credit_internet_layout://云信贷互联反欺诈
                bundle = new Bundle();
                bundle.putString("unitPrice",internetPrice);
                ActivityIntent.readyGo(this, CreditInternetActivity.class, bundle);
                break;

        }
    }

    private void initView() {
        toolbar.setTitle("信用查询");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditQueryActivity.this.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryCredit();
    }

    /**
     * 获取征信产品列表
     *
     * @param creditQuery
     */
    @Override
    public void getCreditList(CreditQuery creditQuery) {
        List<CreditQuery.ResultBean> result = creditQuery.getResult();
        if(result != null){
            for(CreditQuery.ResultBean resultBean:result){
                switch (resultBean.getCreditType()){
                    case Config.USER_PORTRAIT:
//                        userCount = resultBean.getTimes();
                        userPrice = StringUtil.getTwoPointString(resultBean.getUnitPrice());
                        tv_user_portrait_count.setText("剩余次数："+resultBean.getTimes());
                    break;
                    case Config.TRANS_REPORT:
//                        transCount = resultBean.getTimes();
                        transPrice = StringUtil.getTwoPointString(resultBean.getUnitPrice());
                        tv_trans_report_count.setText("剩余次数："+resultBean.getTimes());
                        break;
                    case Config.SUSPECT_CASH:
//                        suspectCount = resultBean.getTimes();
                        suspectPrice = StringUtil.getTwoPointString(resultBean.getUnitPrice());
                        tv_suspect_cash_count.setText("剩余次数："+resultBean.getTimes());
                        break;
                    case Config.BILL_CHECK:
//                        billCount = resultBean.getTimes();
                        billPrice = StringUtil.getTwoPointString(resultBean.getUnitPrice());
                        tv_bill_check_count.setText("剩余次数："+resultBean.getTimes());
                        break;
                    case Config.CLOUD_CREDIT_APPLICATION:
//                        applicationCount = resultBean.getTimes();
                        applicationPrice = StringUtil.getTwoPointString(resultBean.getUnitPrice());
                        tv_cloud_credit_application_count.setText("剩余次数："+resultBean.getTimes());
                        break;
                    case Config.CLOUD_CREDIT_INTERNET:
//                        internetCount = resultBean.getTimes();
                        internetPrice = StringUtil.getTwoPointString(resultBean.getUnitPrice());
                        tv_cloud_credit_internet_count.setText("剩余次数："+resultBean.getTimes());
                        break;
                }
            }
        }
    }
}
