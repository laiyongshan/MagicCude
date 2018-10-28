package com.rflash.magiccube.ui.creditreportmessage;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.creditpersonalmessage.CreditPersonalMessageActivity;
import com.rflash.magiccube.ui.userportrait.CreditShareItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.BindView;


/**
 * 信用查询中各项的报告信息
 * Created by Guobaihui on 2018/03/12.
 */

public class CreditReportMessageActivity extends MVPBaseActivity<CreditReportMessageContract.View, CreditReportMessagePresenter> implements CreditReportMessageContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ll_user_protrait)
    LinearLayout ll_user_protrait;
    @BindView(R.id.ll_trans_report)
    LinearLayout ll_trans_report;
    @BindView(R.id.ll_suspect_cash)
    LinearLayout ll_suspect_cash;
    @BindView(R.id.ll_bill_check)
    LinearLayout ll_bill_check;
    @BindView(R.id.ll_credit_application)
    LinearLayout ll_credit_application;
    @BindView(R.id.ll_credit_internet)
    LinearLayout ll_credit_internet;

    // 银联用户画像查询报告控件
    // 基本信息
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_user_identityCard)
    TextView tv_user_identityCard;
    @BindView(R.id.tv_user_cardNo)
    TextView tv_user_cardNo;
    @BindView(R.id.tv_user_mobile)
    TextView tv_user_mobile;

    // 银行卡属性
    @BindView(R.id.tv_user_s0660)
    TextView tv_user_s0660;
    @BindView(R.id.tv_user_s0467)
    TextView tv_user_s0467;
    @BindView(R.id.tv_user_s0661)
    TextView tv_user_s0661;
    @BindView(R.id.tv_user_s0506)
    TextView tv_user_s0506;

    // 持卡人身份特征
    @BindView(R.id.tv_user_s0503)
    TextView tv_user_s0503;
    @BindView(R.id.tv_user_s0504)
    TextView tv_user_s0504;
    @BindView(R.id.tv_user_s0659)
    TextView tv_user_s0659;
    @BindView(R.id.tv_user_s0476)
    TextView tv_user_s0476;
    @BindView(R.id.tv_user_s0474)
    TextView tv_user_s0474;
    @BindView(R.id.tv_user_s0477)
    TextView tv_user_s0477;

    // 持卡人资产状况
    @BindView(R.id.tv_user_s0470)
    TextView tv_user_s0470;
    @BindView(R.id.tv_user_s0663)
    TextView tv_user_s0663;
    @BindView(R.id.tv_user_s0472)
    TextView tv_user_s0472;
    @BindView(R.id.tv_user_s0664)
    TextView tv_user_s0664;

    // 近12月银行卡交易统计（金额）
    @BindView(R.id.tv_user_amount_data_1)
    TextView tv_user_amount_data_1;
    @BindView(R.id.tv_user_amount_data_2)
    TextView tv_user_amount_data_2;
    @BindView(R.id.tv_user_amount_data_3)
    TextView tv_user_amount_data_3;
    @BindView(R.id.tv_user_amount_data_4)
    TextView tv_user_amount_data_4;
    @BindView(R.id.tv_user_amount_data_5)
    TextView tv_user_amount_data_5;
    @BindView(R.id.tv_user_amount_data_6)
    TextView tv_user_amount_data_6;
    @BindView(R.id.tv_user_amount_data_7)
    TextView tv_user_amount_data_7;
    @BindView(R.id.tv_user_amount_data_8)
    TextView tv_user_amount_data_8;
    @BindView(R.id.tv_user_amount_data_9)
    TextView tv_user_amount_data_9;
    @BindView(R.id.tv_user_amount_data_10)
    TextView tv_user_amount_data_10;
    @BindView(R.id.tv_user_amount_data_11)
    TextView tv_user_amount_data_11;
    @BindView(R.id.tv_user_amount_data_12)
    TextView tv_user_amount_data_12;

    @BindView(R.id.tv_user_amount_consume_1)
    TextView tv_user_amount_consume_1;
    @BindView(R.id.tv_user_amount_consume_2)
    TextView tv_user_amount_consume_2;
    @BindView(R.id.tv_user_amount_consume_3)
    TextView tv_user_amount_consume_3;
    @BindView(R.id.tv_user_amount_consume_4)
    TextView tv_user_amount_consume_4;
    @BindView(R.id.tv_user_amount_consume_5)
    TextView tv_user_amount_consume_5;
    @BindView(R.id.tv_user_amount_consume_6)
    TextView tv_user_amount_consume_6;
    @BindView(R.id.tv_user_amount_consume_7)
    TextView tv_user_amount_consume_7;
    @BindView(R.id.tv_user_amount_consume_8)
    TextView tv_user_amount_consume_8;
    @BindView(R.id.tv_user_amount_consume_9)
    TextView tv_user_amount_consume_9;
    @BindView(R.id.tv_user_amount_consume_10)
    TextView tv_user_amount_consume_10;
    @BindView(R.id.tv_user_amount_consume_11)
    TextView tv_user_amount_consume_11;
    @BindView(R.id.tv_user_amount_consume_12)
    TextView tv_user_amount_consume_12;

    @BindView(R.id.tv_user_amount_bank_1)
    TextView tv_user_amount_bank_1;
    @BindView(R.id.tv_user_amount_bank_2)
    TextView tv_user_amount_bank_2;
    @BindView(R.id.tv_user_amount_bank_3)
    TextView tv_user_amount_bank_3;
    @BindView(R.id.tv_user_amount_bank_4)
    TextView tv_user_amount_bank_4;
    @BindView(R.id.tv_user_amount_bank_5)
    TextView tv_user_amount_bank_5;
    @BindView(R.id.tv_user_amount_bank_6)
    TextView tv_user_amount_bank_6;
    @BindView(R.id.tv_user_amount_bank_7)
    TextView tv_user_amount_bank_7;
    @BindView(R.id.tv_user_amount_bank_8)
    TextView tv_user_amount_bank_8;
    @BindView(R.id.tv_user_amount_bank_9)
    TextView tv_user_amount_bank_9;
    @BindView(R.id.tv_user_amount_bank_10)
    TextView tv_user_amount_bank_10;
    @BindView(R.id.tv_user_amount_bank_11)
    TextView tv_user_amount_bank_11;
    @BindView(R.id.tv_user_amount_bank_12)
    TextView tv_user_amount_bank_12;

    // 近12月银行卡交易统计（笔数）
    @BindView(R.id.tv_user_count_data_1)
    TextView tv_user_count_data_1;
    @BindView(R.id.tv_user_count_data_2)
    TextView tv_user_count_data_2;
    @BindView(R.id.tv_user_count_data_3)
    TextView tv_user_count_data_3;
    @BindView(R.id.tv_user_count_data_4)
    TextView tv_user_count_data_4;
    @BindView(R.id.tv_user_count_data_5)
    TextView tv_user_count_data_5;
    @BindView(R.id.tv_user_count_data_6)
    TextView tv_user_count_data_6;
    @BindView(R.id.tv_user_count_data_7)
    TextView tv_user_count_data_7;
    @BindView(R.id.tv_user_count_data_8)
    TextView tv_user_count_data_8;
    @BindView(R.id.tv_user_count_data_9)
    TextView tv_user_count_data_9;
    @BindView(R.id.tv_user_count_data_10)
    TextView tv_user_count_data_10;
    @BindView(R.id.tv_user_count_data_11)
    TextView tv_user_count_data_11;
    @BindView(R.id.tv_user_count_data_12)
    TextView tv_user_count_data_12;

    @BindView(R.id.tv_user_count_consume_1)
    TextView tv_user_count_consume_1;
    @BindView(R.id.tv_user_count_consume_2)
    TextView tv_user_count_consume_2;
    @BindView(R.id.tv_user_count_consume_3)
    TextView tv_user_count_consume_3;
    @BindView(R.id.tv_user_count_consume_4)
    TextView tv_user_count_consume_4;
    @BindView(R.id.tv_user_count_consume_5)
    TextView tv_user_count_consume_5;
    @BindView(R.id.tv_user_count_consume_6)
    TextView tv_user_count_consume_6;
    @BindView(R.id.tv_user_count_consume_7)
    TextView tv_user_count_consume_7;
    @BindView(R.id.tv_user_count_consume_8)
    TextView tv_user_count_consume_8;
    @BindView(R.id.tv_user_count_consume_9)
    TextView tv_user_count_consume_9;
    @BindView(R.id.tv_user_count_consume_10)
    TextView tv_user_count_consume_10;
    @BindView(R.id.tv_user_count_consume_11)
    TextView tv_user_count_consume_11;
    @BindView(R.id.tv_user_count_consume_12)
    TextView tv_user_count_consume_12;

    @BindView(R.id.tv_user_count_bank_1)
    TextView tv_user_count_bank_1;
    @BindView(R.id.tv_user_count_bank_2)
    TextView tv_user_count_bank_2;
    @BindView(R.id.tv_user_count_bank_3)
    TextView tv_user_count_bank_3;
    @BindView(R.id.tv_user_count_bank_4)
    TextView tv_user_count_bank_4;
    @BindView(R.id.tv_user_count_bank_5)
    TextView tv_user_count_bank_5;
    @BindView(R.id.tv_user_count_bank_6)
    TextView tv_user_count_bank_6;
    @BindView(R.id.tv_user_count_bank_7)
    TextView tv_user_count_bank_7;
    @BindView(R.id.tv_user_count_bank_8)
    TextView tv_user_count_bank_8;
    @BindView(R.id.tv_user_count_bank_9)
    TextView tv_user_count_bank_9;
    @BindView(R.id.tv_user_count_bank_10)
    TextView tv_user_count_bank_10;
    @BindView(R.id.tv_user_count_bank_11)
    TextView tv_user_count_bank_11;
    @BindView(R.id.tv_user_count_bank_12)
    TextView tv_user_count_bank_12;

    @BindView(R.id.tv_user_s0677_0)
    TextView tv_user_s0677_0;
    @BindView(R.id.tv_user_s0677_1)
    TextView tv_user_s0677_1;
    @BindView(R.id.tv_user_s0677_2)
    TextView tv_user_s0677_2;
    @BindView(R.id.tv_user_s0677_3)
    TextView tv_user_s0677_3;
    @BindView(R.id.tv_user_s0670)
    TextView tv_user_s0670;
    @BindView(R.id.tv_user_s0671)
    TextView tv_user_s0671;
    @BindView(R.id.tv_user_s0188)
    TextView tv_user_s0188;
    @BindView(R.id.tv_user_s0185)
    TextView tv_user_s0185;
    @BindView(R.id.tv_user_s0194)
    TextView tv_user_s0194;
    @BindView(R.id.tv_user_s0200)
    TextView tv_user_s0200;


    //银联用户交易查询报告控件
    // 基本信息
    @BindView(R.id.tv_trans_name)
    TextView tv_trans_name;
    @BindView(R.id.tv_trans_identityCard)
    TextView tv_trans_identityCard;
    @BindView(R.id.tv_trans_cardNo)
    TextView tv_trans_cardNo;
    @BindView(R.id.tv_trans_mobile)
    TextView tv_trans_mobile;
    @BindView(R.id.tv_trans_cardbrand)
    TextView tv_trans_cardbrand;
    @BindView(R.id.tv_trans_bankname)
    TextView tv_trans_bankname;
    @BindView(R.id.tv_trans_cardlevel)
    TextView tv_trans_cardlevel;
    @BindView(R.id.tv_trans_cardtype)
    TextView tv_trans_cardtype;

    //核心消费指标
    @BindView(R.id.tv_trans_cardProperty)
    TextView tv_trans_cardProperty;
    @BindView(R.id.tv_trans_firstTransDate)
    TextView tv_trans_firstTransDate;
    @BindView(R.id.tv_trans_transTotalAmt)
    TextView tv_trans_transTotalAmt;
    @BindView(R.id.tv_trans_transTotalCnt)
    TextView tv_trans_transTotalCnt;
    @BindView(R.id.tv_trans_cashTotalAmt)
    TextView tv_trans_cashTotalAmt;
    @BindView(R.id.tv_trans_cashTotalCnt)
    TextView tv_trans_cashTotalCnt;
    @BindView(R.id.tv_trans_refundTotalAmt)
    TextView tv_trans_refundTotalAmt;
    @BindView(R.id.tv_trans_refundTotalCnt)
    TextView tv_trans_refundTotalCnt;
    @BindView(R.id.tv_trans_rigidTransAmtPre)
    TextView tv_trans_rigidTransAmtPre;
    @BindView(R.id.tv_trans_fromCity)
    TextView tv_trans_fromCity;
    @BindView(R.id.tv_trans_noTransWeekPre)
    TextView tv_trans_noTransWeekPre;
    @BindView(R.id.tv_trans_monthCardLargeAmt)
    TextView tv_trans_monthCardLargeAmt;

    //消费行为特征
    @BindView(R.id.tv_trans_ifHasBusinessTrip)
    TextView tv_trans_ifHasBusinessTrip;
    @BindView(R.id.tv_trans_ifHasWeddingTrans)
    TextView tv_trans_ifHasWeddingTrans;
    @BindView(R.id.tv_trans_ifHasFamily)
    TextView tv_trans_ifHasFamily;
    @BindView(R.id.tv_trans_ifHasOverseasTrans)
    TextView tv_trans_ifHasOverseasTrans;
    @BindView(R.id.tv_trans_ifHasNightTrans)
    TextView tv_trans_ifHasNightTrans;
    @BindView(R.id.tv_trans_ifHasLotteryTrans)
    TextView tv_trans_ifHasLotteryTrans;
    @BindView(R.id.tv_trans_ifHasUnemployed)
    TextView tv_trans_ifHasUnemployed;

    //每月消费情况
    @BindView(R.id.tl_amt)
    TableLayout tl_amt;
    @BindView(R.id.tl_cnt)
    TableLayout tl_cnt;
    @BindView(R.id.tl_cash)
    TableLayout tl_cash;

    //消费地点分布
    @BindView(R.id.tl_places)
    TableLayout tl_places;

    //消费大类分布
    @BindView(R.id.tl_mcc)
    TableLayout tl_mcc;

    //特殊交易统计
    @BindView(R.id.tv_trans_publicPayAmt)
    TextView tv_trans_publicPayAmt;
    @BindView(R.id.tv_trans_publicPayCnt)
    TextView tv_trans_publicPayCnt;
    @BindView(R.id.tv_trans_taxAmt)
    TextView tv_trans_taxAmt;
    @BindView(R.id.tv_trans_taxCnt)
    TextView tv_trans_taxCnt;
    @BindView(R.id.tv_trans_nightTransAmt)
    TextView tv_trans_nightTransAmt;
    @BindView(R.id.tv_trans_nightTransCnt)
    TextView tv_trans_nightTransCnt;
    @BindView(R.id.tv_trans_lotteryTransAmt)
    TextView tv_trans_lotteryTransAmt;
    @BindView(R.id.tv_trans_lotteryTransCnt)
    TextView tv_trans_lotteryTransCnt;
    @BindView(R.id.tv_trans_onlineTransAmt)
    TextView tv_trans_onlineTransAmt;
    @BindView(R.id.tv_trans_onlineTransCnt)
    TextView tv_trans_onlineTransCnt;

    //疑似套现甄别
    @BindView(R.id.tv_trans_yearMisCreditCardCashAmt)
    TextView tv_trans_yearMisCreditCardCashAmt;
    @BindView(R.id.tv_trans_yearMisCreditCardCashCnt)
    TextView tv_trans_yearMisCreditCardCashCnt;
    @BindView(R.id.tv_trans_yearMisHighRiskCashAmtPre)
    TextView tv_trans_yearMisHighRiskCashAmtPre;
    @BindView(R.id.tv_trans_yearMisHighRiskCashCntPre)
    TextView tv_trans_yearMisHighRiskCashCntPre;


    //用户疑似套现查询报告控件
    // 基本信息
    @BindView(R.id.tv_cash_name)
    TextView tv_cash_name;
    @BindView(R.id.tv_cash_identityCard)
    TextView tv_cash_identityCard;
    @BindView(R.id.tv_cash_cardNo)
    TextView tv_cash_cardNo;
    @BindView(R.id.tv_cash_mobile)
    TextView tv_cash_mobile;

    //查询情况
    @BindView(R.id.tv_cash_cashOutAmount)
    TextView tv_cash_cashOutAmount;
    @BindView(R.id.tv_cash_cashOutAmountRate)
    TextView tv_cash_cashOutAmountRate;
    @BindView(R.id.tv_cash_cashOutCount)
    TextView tv_cash_cashOutCount;
    @BindView(R.id.tv_cash_cashOutCountRate)
    TextView tv_cash_cashOutCountRate;
    @BindView(R.id.tv_cash_cashOutIndustryAmountRate)
    TextView tv_cash_cashOutIndustryAmountRate;
    @BindView(R.id.tv_cash_cashOutIndustryCountRate)
    TextView tv_cash_cashOutIndustryCountRate;


    //用户账单真伪核验查询报告控件
    // 基本信息
    @BindView(R.id.tv_bill_name)
    TextView tv_bill_name;
    @BindView(R.id.tv_bill_identityCard)
    TextView tv_bill_identityCard;
    @BindView(R.id.tv_bill_cardNo)
    TextView tv_bill_cardNo;
    @BindView(R.id.tv_bill_mobile)
    TextView tv_bill_mobile;
    @BindView(R.id.tv_bill_beginDate)
    TextView tv_bill_beginDate;
    @BindView(R.id.tv_bill_endDate)
    TextView tv_bill_endDate;

    //查询情况
    @BindView(R.id.tv_transTime_1)
    TextView tv_transTime_1;
    @BindView(R.id.tv_transTime_2)
    TextView tv_transTime_2;
    @BindView(R.id.tv_transTime_3)
    TextView tv_transTime_3;
    @BindView(R.id.tv_transTime_4)
    TextView tv_transTime_4;
    @BindView(R.id.tv_transTime_5)
    TextView tv_transTime_5;
    @BindView(R.id.tv_transAmount_1)
    TextView tv_transAmount_1;
    @BindView(R.id.tv_transAmount_2)
    TextView tv_transAmount_2;
    @BindView(R.id.tv_transAmount_3)
    TextView tv_transAmount_3;
    @BindView(R.id.tv_transAmount_4)
    TextView tv_transAmount_4;
    @BindView(R.id.tv_transAmount_5)
    TextView tv_transAmount_5;

    //用户账单真伪核验查询报告控件
    // 基本信息
    @BindView(R.id.tv_application_name)
    TextView tv_application_name;
    @BindView(R.id.tv_application_mobile)
    TextView tv_application_mobile;
    @BindView(R.id.tv_application_identityCard)
    TextView tv_application_identityCard;

    // 信贷情况
    @BindView(R.id.tv_applicationTime)
    TextView tv_applicationTime;
    @BindView(R.id.tv_applicationAmount)
    TextView tv_applicationAmount;
    @BindView(R.id.tv_application_money)
    TextView tv_application_money;
    @BindView(R.id.tv_loanlendersTime)
    TextView tv_loanlendersTime;
    @BindView(R.id.tv_loanlendersAmount)
    TextView tv_loanlendersAmount;
    @BindView(R.id.tv_registerTime)
    TextView tv_registerTime;
    @BindView(R.id.tv_rejectionTime)
    TextView tv_rejectionTime;
    @BindView(R.id.tv_application_yq_counts)
    TextView tv_application_yq_counts;
    @BindView(R.id.tv_application_yq_money)
    TextView tv_application_yq_money;
    @BindView(R.id.tv_application_yq_lastTime)
    TextView tv_application_yq_lastTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_report_detail);
        initView();
    }

    private void initView() {
        Bundle bundle = this.getIntent().getExtras();
        String creditType = (String) bundle.get("creditType");
        CreditShareItem.ResultBean creditShareItem = (CreditShareItem.ResultBean) bundle.getSerializable("creditShareItem");
        switch (creditType) {
            case Config.USER_PORTRAIT:
                toolbar.setTitle("银联用户画像查询报告");
                handleUserReportView(creditShareItem);
                break;
            case Config.TRANS_REPORT:
                toolbar.setTitle("银联用户交易查询报告");
                handleTransReportView(creditShareItem);
                break;
            case Config.SUSPECT_CASH:
                toolbar.setTitle("用户疑似套现查询报告");
                handleCashReportView(creditShareItem);
                break;
            case Config.BILL_CHECK:
                toolbar.setTitle("用户账单真伪核验查询报告");
                handleBillReportView(creditShareItem);
                break;
            case Config.CLOUD_CREDIT_APPLICATION:
                toolbar.setTitle("云信贷申请反欺诈报告");
                handleApplicationReportView(creditShareItem);
                break;
            case Config.CLOUD_CREDIT_INTERNET:
                toolbar.setTitle("云信贷互联反欺诈报告");
                handleInternetReportView(creditShareItem);
                break;
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditReportMessageActivity.this.finish();
            }
        });
    }

    // 处理银联用户画像查询报告View
    private void handleUserReportView(CreditShareItem.ResultBean creditShareItem) {
        ll_user_protrait.setVisibility(View.VISIBLE);
        ll_trans_report.setVisibility(View.GONE);
        ll_suspect_cash.setVisibility(View.GONE);
        ll_bill_check.setVisibility(View.GONE);
        ll_credit_application.setVisibility(View.GONE);
        ll_credit_internet.setVisibility(View.GONE);

        try {
            tv_user_name.setText(StringUtil.changeToMinusSign(creditShareItem.getName()));
            tv_user_identityCard.setText(StringUtil.changeToMinusSign(creditShareItem.getIdentityCard()));
            tv_user_cardNo.setText(StringUtil.changeToMinusSign(creditShareItem.getCardNo()));
            tv_user_mobile.setText(StringUtil.changeToMinusSign(creditShareItem.getMobile()));
            JSONObject jsonContent = new JSONObject(creditShareItem.getContent());
            JSONObject jsonData = jsonContent.getJSONObject("data");
            JSONArray arrayTran = jsonData.getJSONArray("tran");
            if(arrayTran.length() > 0){
                for (int i = 0; i < arrayTran.length(); i++) {
                    if (i == 0) {
                        JSONObject tran1 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_1.setText(StringUtil.changeToMinusSign((String) tran1.optString("yearMonth")));
                        tv_user_amount_consume_1.setText(StringUtil.changeToMinusSign((String) tran1.optString("S0534")));
                        tv_user_amount_bank_1.setText(StringUtil.changeToMinusSign((String) tran1.optString("S0535")));
                        tv_user_count_data_1.setText(StringUtil.changeToMinusSign((String) tran1.optString("yearMonth")));
                        tv_user_count_consume_1.setText(StringUtil.changeToMinusSign((String) tran1.optString("S0537")));
                        tv_user_count_bank_1.setText(StringUtil.changeToMinusSign((String) tran1.optString("S0538")));
                    } else if (i == 1) {
                        JSONObject tran2 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_2.setText(StringUtil.changeToMinusSign((String) tran2.optString("yearMonth")));
                        tv_user_amount_consume_2.setText(StringUtil.changeToMinusSign((String) tran2.optString("S0534")));
                        tv_user_amount_bank_2.setText(StringUtil.changeToMinusSign((String) tran2.optString("S0535")));
                        tv_user_count_data_2.setText(StringUtil.changeToMinusSign((String) tran2.optString("yearMonth")));
                        tv_user_count_consume_2.setText(StringUtil.changeToMinusSign((String) tran2.optString("S0537")));
                        tv_user_count_bank_2.setText(StringUtil.changeToMinusSign((String) tran2.optString("S0538")));
                    } else if (i == 2) {
                        JSONObject tran3 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_3.setText(StringUtil.changeToMinusSign((String) tran3.optString("yearMonth")));
                        tv_user_amount_consume_3.setText(StringUtil.changeToMinusSign((String) tran3.optString("S0534")));
                        tv_user_amount_bank_3.setText(StringUtil.changeToMinusSign((String) tran3.optString("S0535")));
                        tv_user_count_data_3.setText(StringUtil.changeToMinusSign((String) tran3.optString("yearMonth")));
                        tv_user_count_consume_3.setText(StringUtil.changeToMinusSign((String) tran3.optString("S0537")));
                        tv_user_count_bank_3.setText(StringUtil.changeToMinusSign((String) tran3.optString("S0538")));
                    } else if (i == 3) {
                        JSONObject tran4 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_4.setText(StringUtil.changeToMinusSign((String) tran4.optString("yearMonth")));
                        tv_user_amount_consume_4.setText(StringUtil.changeToMinusSign((String) tran4.optString("S0534")));
                        tv_user_amount_bank_4.setText(StringUtil.changeToMinusSign((String) tran4.optString("S0535")));
                        tv_user_count_data_4.setText(StringUtil.changeToMinusSign((String) tran4.optString("yearMonth")));
                        tv_user_count_consume_4.setText(StringUtil.changeToMinusSign((String) tran4.optString("S0537")));
                        tv_user_count_bank_4.setText(StringUtil.changeToMinusSign((String) tran4.optString("S0538")));
                    } else if (i == 4) {
                        JSONObject tran5 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_5.setText(StringUtil.changeToMinusSign((String) tran5.optString("yearMonth")));
                        tv_user_amount_consume_5.setText(StringUtil.changeToMinusSign((String) tran5.optString("S0534")));
                        tv_user_amount_bank_5.setText(StringUtil.changeToMinusSign((String) tran5.optString("S0535")));
                        tv_user_count_data_5.setText(StringUtil.changeToMinusSign((String) tran5.optString("yearMonth")));
                        tv_user_count_consume_5.setText(StringUtil.changeToMinusSign((String) tran5.optString("S0537")));
                        tv_user_count_bank_5.setText(StringUtil.changeToMinusSign((String) tran5.optString("S0538")));
                    } else if (i == 5) {
                        JSONObject tran6 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_6.setText(StringUtil.changeToMinusSign((String) tran6.optString("yearMonth")));
                        tv_user_amount_consume_6.setText(StringUtil.changeToMinusSign((String) tran6.optString("S0534")));
                        tv_user_amount_bank_6.setText(StringUtil.changeToMinusSign((String) tran6.optString("S0535")));
                        tv_user_count_data_6.setText(StringUtil.changeToMinusSign((String) tran6.optString("yearMonth")));
                        tv_user_count_consume_6.setText(StringUtil.changeToMinusSign((String) tran6.optString("S0537")));
                        tv_user_count_bank_6.setText(StringUtil.changeToMinusSign((String) tran6.optString("S0538")));
                    } else if (i == 6) {
                        JSONObject tran7 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_7.setText(StringUtil.changeToMinusSign((String) tran7.optString("yearMonth")));
                        tv_user_amount_consume_7.setText(StringUtil.changeToMinusSign((String) tran7.optString("S0534")));
                        tv_user_amount_bank_7.setText(StringUtil.changeToMinusSign((String) tran7.optString("S0535")));
                        tv_user_count_data_7.setText(StringUtil.changeToMinusSign((String) tran7.optString("yearMonth")));
                        tv_user_count_consume_7.setText(StringUtil.changeToMinusSign((String) tran7.optString("S0537")));
                        tv_user_count_bank_7.setText(StringUtil.changeToMinusSign((String) tran7.optString("S0538")));
                    } else if (i == 7) {
                        JSONObject tran8 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_8.setText(StringUtil.changeToMinusSign((String) tran8.optString("yearMonth")));
                        tv_user_amount_consume_8.setText(StringUtil.changeToMinusSign((String) tran8.optString("S0534")));
                        tv_user_amount_bank_8.setText(StringUtil.changeToMinusSign((String) tran8.optString("S0535")));
                        tv_user_count_data_8.setText(StringUtil.changeToMinusSign((String) tran8.optString("yearMonth")));
                        tv_user_count_consume_8.setText(StringUtil.changeToMinusSign((String) tran8.optString("S0537")));
                        tv_user_count_bank_8.setText(StringUtil.changeToMinusSign((String) tran8.optString("S0538")));
                    } else if (i == 8) {
                        JSONObject tran9 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_9.setText(StringUtil.changeToMinusSign((String) tran9.optString("yearMonth")));
                        tv_user_amount_consume_9.setText(StringUtil.changeToMinusSign((String) tran9.optString("S0534")));
                        tv_user_amount_bank_9.setText(StringUtil.changeToMinusSign((String) tran9.optString("S0535")));
                        tv_user_count_data_9.setText(StringUtil.changeToMinusSign((String) tran9.optString("yearMonth")));
                        tv_user_count_consume_9.setText(StringUtil.changeToMinusSign((String) tran9.optString("S0537")));
                        tv_user_count_bank_9.setText(StringUtil.changeToMinusSign((String) tran9.optString("S0538")));
                    } else if (i == 9) {
                        JSONObject tran10 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_10.setText(StringUtil.changeToMinusSign((String) tran10.optString("yearMonth")));
                        tv_user_amount_consume_10.setText(StringUtil.changeToMinusSign((String) tran10.optString("S0534")));
                        tv_user_amount_bank_10.setText(StringUtil.changeToMinusSign((String) tran10.optString("S0535")));
                        tv_user_count_data_10.setText(StringUtil.changeToMinusSign((String) tran10.optString("yearMonth")));
                        tv_user_count_consume_10.setText(StringUtil.changeToMinusSign((String) tran10.optString("S0537")));
                        tv_user_count_bank_10.setText(StringUtil.changeToMinusSign((String) tran10.optString("S0538")));
                    } else if (i == 10) {
                        JSONObject tran11 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_11.setText(StringUtil.changeToMinusSign((String) tran11.optString("yearMonth")));
                        tv_user_amount_consume_11.setText(StringUtil.changeToMinusSign((String) tran11.optString("S0534")));
                        tv_user_amount_bank_11.setText(StringUtil.changeToMinusSign((String) tran11.optString("S0535")));
                        tv_user_count_data_11.setText(StringUtil.changeToMinusSign((String) tran11.optString("yearMonth")));
                        tv_user_count_consume_11.setText(StringUtil.changeToMinusSign((String) tran11.optString("S0537")));
                        tv_user_count_bank_11.setText(StringUtil.changeToMinusSign((String) tran11.optString("S0538")));
                    } else if (i == 11) {
                        JSONObject tran12 = (JSONObject) arrayTran.get(i);
                        tv_user_amount_data_12.setText(StringUtil.changeToMinusSign((String) tran12.optString("yearMonth")));
                        tv_user_amount_consume_12.setText(StringUtil.changeToMinusSign((String) tran12.optString("S0534")));
                        tv_user_amount_bank_12.setText(StringUtil.changeToMinusSign((String) tran12.optString("S0535")));
                        tv_user_count_data_12.setText(StringUtil.changeToMinusSign((String) tran12.optString("yearMonth")));
                        tv_user_count_consume_12.setText(StringUtil.changeToMinusSign((String) tran12.optString("S0537")));
                        tv_user_count_bank_12.setText(StringUtil.changeToMinusSign((String) tran12.optString("S0538")));
                    }
                }
            }

            tv_user_s0660.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0660")));
            tv_user_s0467.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0467")));
            tv_user_s0661.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0661")));
            tv_user_s0506.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0506")));
            tv_user_s0503.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0503")));
            tv_user_s0504.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0504")));
            tv_user_s0659.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0659")));
            tv_user_s0476.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0476")));
            tv_user_s0474.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0474")));
            tv_user_s0477.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0477")));

            tv_user_s0470.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0470")));
            tv_user_s0663.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0663")));
            tv_user_s0472.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0472")));
            tv_user_s0664.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0664")));

            tv_user_s0677_0.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0677_0")));
            tv_user_s0677_1.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0677_1")));
            tv_user_s0677_2.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0677_2")));
            tv_user_s0677_3.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0677_3")));
            tv_user_s0670.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0670")));
            tv_user_s0671.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0671")));
            tv_user_s0188.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0188")));
            tv_user_s0185.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0185")));
            tv_user_s0194.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0194")));
            tv_user_s0200.setText(StringUtil.changeToMinusSign((String) jsonData.optString("S0200")));
        } catch (Exception e) {
            Toast.makeText(this,"数据获取异常",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }

    // 处理银联用户交易查询报告View
    @SuppressLint("ResourceAsColor")
    private void handleTransReportView(CreditShareItem.ResultBean creditShareItem) {
        ll_user_protrait.setVisibility(View.GONE);
        ll_trans_report.setVisibility(View.VISIBLE);
        ll_suspect_cash.setVisibility(View.GONE);
        ll_bill_check.setVisibility(View.GONE);
        ll_credit_application.setVisibility(View.GONE);
        ll_credit_internet.setVisibility(View.GONE);

        try {
            tv_trans_name.setText(StringUtil.changeToMinusSign(creditShareItem.getName()));
            tv_trans_identityCard.setText(StringUtil.changeToMinusSign(creditShareItem.getIdentityCard()));
            tv_trans_cardNo.setText(StringUtil.changeToMinusSign(creditShareItem.getCardNo()));
            tv_trans_mobile.setText(StringUtil.changeToMinusSign(creditShareItem.getMobile()));

            JSONObject jsonContent = new JSONObject(creditShareItem.getContent());
            JSONObject jsonData = jsonContent.getJSONObject("data");
            JSONArray monthConsumesRspList = jsonData.getJSONArray("MonthConsumesRspList");
            JSONArray consumeCitesRspList = jsonData.getJSONArray("ConsumeCitesRspList");
            JSONObject transBehaviorRspList = jsonData.getJSONObject("TransBehaviorRspList");
            JSONObject transCreditsRspList = jsonData.getJSONObject("TransCreditsRspList");
            JSONObject cashoutRspList = jsonData.getJSONObject("CashoutRspList");
            JSONArray categoriesRspList = jsonData.getJSONArray("CategoriesRspList");
            JSONObject bankInf = jsonData.getJSONObject("bankInf");
            JSONObject propertyRspList = jsonData.getJSONObject("PropertyRspList");

            if(monthConsumesRspList.length() > 0){
                for (int i = 0; i < monthConsumesRspList.length(); i++) {
                    JSONObject json = (JSONObject) monthConsumesRspList.get(i);
                    View view = getPartView();
                    TextView tv_first = view.findViewById(R.id.tv_first);
                    TextView tv_second = view.findViewById(R.id.tv_second);
                    TextView tv_third = view.findViewById(R.id.tv_third);
                    // 每月消费金额情况
                    tv_first.setText(StringUtil.changeToMinusSign((String) json.optString("yearMonth")));
                    tv_second.setText(StringUtil.changeToMinusSign((String) json.optString("transAmt")));
                    tv_third.setText(StringUtil.changeToMinusSign((String) json.optString("amtRank")));
                    tl_amt.addView(view);
                }
                for (int i = 0; i < monthConsumesRspList.length(); i++) {
                    JSONObject json = (JSONObject) monthConsumesRspList.get(i);
                    View view = getPartView();
                    TextView tv_first = view.findViewById(R.id.tv_first);
                    TextView tv_second = view.findViewById(R.id.tv_second);
                    TextView tv_third = view.findViewById(R.id.tv_third);
                    // 每月消费笔数情况
                    tv_first.setText(StringUtil.changeToMinusSign((String) json.optString("yearMonth")));
                    tv_second.setText(StringUtil.changeToMinusSign((String) json.optString("transCnt")));
                    tv_third.setText(StringUtil.changeToMinusSign((String) json.optString("cntRank")));
                    tl_cnt.addView(view);
                }
                for (int i = 0; i < monthConsumesRspList.length(); i++) {
                    JSONObject json = (JSONObject) monthConsumesRspList.get(i);
                    View view = getPartView();
                    TextView tv_first = view.findViewById(R.id.tv_first);
                    TextView tv_second = view.findViewById(R.id.tv_second);
                    TextView tv_third = view.findViewById(R.id.tv_third);
                    // 每月取现情况
                    tv_first.setText(StringUtil.changeToMinusSign((String) json.optString("yearMonth")));
                    tv_second.setText(StringUtil.changeToMinusSign((String) json.optString("cashAmt")));
                    tv_third.setText(StringUtil.changeToMinusSign((String) json.optString("cashCnt")));
                    tl_cash.addView(view);
                }
            }

            if(consumeCitesRspList.length() > 0){
                for (int i = 0; i < consumeCitesRspList.length(); i++) {
                    JSONObject json = (JSONObject) consumeCitesRspList.get(i);
                    View view = getPartView();
                    TextView tv_first = view.findViewById(R.id.tv_first);
                    TextView tv_second = view.findViewById(R.id.tv_second);
                    TextView tv_third = view.findViewById(R.id.tv_third);
                    tv_first.setText(StringUtil.changeToMinusSign((String) json.optString("zone")));
                    tv_second.setText(StringUtil.changeToMinusSign((String) json.optString("placesTransAmtPre")));
                    tv_third.setText(StringUtil.changeToMinusSign((String) json.optString("placesTransCntPre")));
                    tl_places.addView(view);
                }
            }

            if(categoriesRspList.length() > 0){
                for (int i = 0; i < categoriesRspList.length(); i++) {
                    JSONObject json = (JSONObject) categoriesRspList.get(i);
                    View view = getPartView();
                    TextView tv_first = view.findViewById(R.id.tv_first);
                    TextView tv_second = view.findViewById(R.id.tv_second);
                    TextView tv_third = view.findViewById(R.id.tv_third);
                    tv_first.setText(StringUtil.changeToMinusSign((String) json.optString("mtc")));
                    tv_second.setText(StringUtil.changeToMinusSign((String) json.optString("transAmtPre")));
                    tv_third.setText(StringUtil.changeToMinusSign((String) json.optString("transCntPre")));
                    tl_mcc.addView(view);
                }
            }

            tv_trans_cardbrand.setText(StringUtil.changeToMinusSign((String) bankInf.optString("o_cardbrand")));
            tv_trans_bankname.setText(StringUtil.changeToMinusSign((String) bankInf.optString("o_bankname")));
            tv_trans_cardlevel.setText(StringUtil.changeToMinusSign((String) bankInf.optString("o_cardlevel")));
            tv_trans_cardtype.setText(StringUtil.changeToMinusSign((String) bankInf.optString("o_cardtype")));

            tv_trans_cardProperty.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("cardProperty")));
            tv_trans_firstTransDate.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("firstTransDate")));
            tv_trans_transTotalAmt.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("transTotalAmt")));
            tv_trans_transTotalCnt.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("transTotalCnt")));
            tv_trans_cashTotalAmt.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("cashTotalAmt")));
            tv_trans_cashTotalCnt.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("cashTotalCnt")));
            tv_trans_refundTotalAmt.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("refundTotalAmt")));
            tv_trans_refundTotalCnt.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("refundTotalCnt")));
            tv_trans_rigidTransAmtPre.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("rigidTransAmtPre")));
            tv_trans_fromCity.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("fromCity")));
            tv_trans_noTransWeekPre.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("noTransWeekPre")));
            tv_trans_monthCardLargeAmt.setText(StringUtil.changeToMinusSign((String) propertyRspList.optString("monthCardLargeAmt")));

            tv_trans_ifHasBusinessTrip.setText(StringUtil.changeToMinusSign((String) transBehaviorRspList.optString("ifHasBusinessTrip")));
            tv_trans_ifHasWeddingTrans.setText(StringUtil.changeToMinusSign((String) transBehaviorRspList.optString("ifHasWeddingTrans")));
            tv_trans_ifHasFamily.setText(StringUtil.changeToMinusSign((String) transBehaviorRspList.optString("ifHasFamily")));
            tv_trans_ifHasOverseasTrans.setText(StringUtil.changeToMinusSign((String) transBehaviorRspList.optString("ifHasOverseasTrans")));
            tv_trans_ifHasNightTrans.setText(StringUtil.changeToMinusSign((String) transBehaviorRspList.optString("ifHasNightTrans")));
            tv_trans_ifHasLotteryTrans.setText(StringUtil.changeToMinusSign((String) transBehaviorRspList.optString("ifHasLotteryTrans")));
            tv_trans_ifHasUnemployed.setText(StringUtil.changeToMinusSign((String) transBehaviorRspList.optString("ifHasUnemployed")));

            tv_trans_publicPayAmt.setText(StringUtil.changeToMinusSign((String) transCreditsRspList.optString("publicPayAmt")));
            tv_trans_publicPayCnt.setText(StringUtil.changeToMinusSign((String) transCreditsRspList.optString("publicPayCnt")));
            tv_trans_taxAmt.setText(StringUtil.changeToMinusSign((String) transCreditsRspList.optString("taxAmt")));
            tv_trans_taxCnt.setText(StringUtil.changeToMinusSign((String) transCreditsRspList.optString("taxCnt")));
            tv_trans_nightTransAmt.setText(StringUtil.changeToMinusSign((String) transCreditsRspList.optString("nightTransAmt")));
            tv_trans_nightTransCnt.setText(StringUtil.changeToMinusSign((String) transCreditsRspList.optString("nightTransCnt")));
            tv_trans_lotteryTransAmt.setText(StringUtil.changeToMinusSign((String) transCreditsRspList.optString("lotteryTransAmt")));
            tv_trans_lotteryTransCnt.setText(StringUtil.changeToMinusSign((String) transCreditsRspList.optString("lotteryTransCnt")));
            tv_trans_onlineTransAmt.setText(StringUtil.changeToMinusSign((String) transCreditsRspList.optString("onlineTransAmt")));
            tv_trans_onlineTransCnt.setText(StringUtil.changeToMinusSign((String) transCreditsRspList.optString("onlineTransCnt")));

            tv_trans_yearMisCreditCardCashAmt.setText(StringUtil.changeToMinusSign((String) cashoutRspList.optString("yearMisCreditCardCashAmt")));
            tv_trans_yearMisCreditCardCashCnt.setText(StringUtil.changeToMinusSign((String) cashoutRspList.optString("yearMisCreditCardCashCnt")));
            tv_trans_yearMisHighRiskCashAmtPre.setText(StringUtil.changeToMinusSign((String) cashoutRspList.optString("yearMisHighRiskCashAmtPre")));
            tv_trans_yearMisHighRiskCashCntPre.setText(StringUtil.changeToMinusSign((String) cashoutRspList.optString("yearMisHighRiskCashCntPre")));


        } catch (Exception e) {
            Toast.makeText(this,"数据获取异常",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // 处理用户疑似套现查询报告View
    private void handleCashReportView(CreditShareItem.ResultBean creditShareItem) {
        ll_user_protrait.setVisibility(View.GONE);
        ll_trans_report.setVisibility(View.GONE);
        ll_suspect_cash.setVisibility(View.VISIBLE);
        ll_bill_check.setVisibility(View.GONE);
        ll_credit_application.setVisibility(View.GONE);
        ll_credit_internet.setVisibility(View.GONE);

        try {
            tv_cash_name.setText(StringUtil.changeToMinusSign(creditShareItem.getName()));
            tv_cash_identityCard.setText(StringUtil.changeToMinusSign(creditShareItem.getIdentityCard()));
            tv_cash_cardNo.setText(StringUtil.changeToMinusSign(creditShareItem.getCardNo()));
            tv_cash_mobile.setText(StringUtil.changeToMinusSign(creditShareItem.getMobile()));
            JSONObject jsonContent = new JSONObject(creditShareItem.getContent());
            JSONObject jsonData = jsonContent.getJSONObject("data");

            tv_cash_cashOutAmount.setText(StringUtil.changeToMinusSign((String) jsonData.optString("cashOutAmount")));
            tv_cash_cashOutAmountRate.setText(StringUtil.changeToMinusSign((String) jsonData.optString("cashOutAmountRate")));
            tv_cash_cashOutCount.setText(StringUtil.changeToMinusSign((String) jsonData.optString("cashOutCount")));
            tv_cash_cashOutCountRate.setText(StringUtil.changeToMinusSign((String) jsonData.optString("cashOutCountRate")));
            tv_cash_cashOutIndustryAmountRate.setText(StringUtil.changeToMinusSign((String) jsonData.optString("cashOutIndustryAmountRate")));
            tv_cash_cashOutIndustryCountRate.setText(StringUtil.changeToMinusSign((String) jsonData.optString("cashOutIndustryCountRate")));

        } catch (Exception e) {
            Toast.makeText(this,"数据获取异常",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // 处理用户账单真伪核验查询报告View
    private void handleBillReportView(CreditShareItem.ResultBean creditShareItem) {
        ll_user_protrait.setVisibility(View.GONE);
        ll_trans_report.setVisibility(View.GONE);
        ll_suspect_cash.setVisibility(View.GONE);
        ll_bill_check.setVisibility(View.VISIBLE);
        ll_credit_application.setVisibility(View.GONE);
        ll_credit_internet.setVisibility(View.GONE);

        try {
            tv_bill_name.setText(StringUtil.changeToMinusSign(creditShareItem.getName()));
            tv_bill_identityCard.setText(StringUtil.changeToMinusSign(creditShareItem.getIdentityCard()));
            tv_bill_cardNo.setText(StringUtil.changeToMinusSign(creditShareItem.getCardNo()));
            tv_bill_mobile.setText(StringUtil.changeToMinusSign(creditShareItem.getMobile()));
            tv_bill_beginDate.setText(StringUtil.changeToMinusSign(creditShareItem.getBeginDate()));
            tv_bill_endDate.setText(StringUtil.changeToMinusSign(creditShareItem.getEndDate()));

            JSONObject jsonContent = new JSONObject(creditShareItem.getContent());
            JSONArray transactions = jsonContent.getJSONArray("transactions");

            if(transactions.length() > 0){
                for (int i = 0; i < transactions.length(); i++) {
                    if (i == 0) {
                        JSONObject tran1 = (JSONObject) transactions.get(i);
                        tv_transTime_1.setText(StringUtil.changeToMinusSign((String) tran1.optString("transTime")));
                        tv_transAmount_1.setText(StringUtil.changeToMinusSign(StringUtil.getTwoPointString((String) tran1.optString("transAmount"))));
                    } else if (i == 1) {
                        JSONObject tran2 = (JSONObject) transactions.get(i);
                        tv_transTime_2.setText(StringUtil.changeToMinusSign((String) tran2.optString("transTime")));
                        tv_transAmount_2.setText(StringUtil.changeToMinusSign(StringUtil.getTwoPointString((String) tran2.optString("transAmount"))));
                    } else if (i == 2) {
                        JSONObject tran3 = (JSONObject) transactions.get(i);
                        tv_transTime_3.setText(StringUtil.changeToMinusSign((String) tran3.optString("transTime")));
                        tv_transAmount_3.setText(StringUtil.changeToMinusSign(StringUtil.getTwoPointString((String) tran3.optString("transAmount"))));
                    } else if (i == 3) {
                        JSONObject tran4 = (JSONObject) transactions.get(i);
                        tv_transTime_4.setText(StringUtil.changeToMinusSign((String) tran4.optString("transTime")));
                        tv_transAmount_4.setText(StringUtil.changeToMinusSign(StringUtil.getTwoPointString((String) tran4.optString("transAmount"))));
                    } else if (i == 4) {
                        JSONObject tran5 = (JSONObject) transactions.get(i);
                        tv_transTime_5.setText(StringUtil.changeToMinusSign((String) tran5.optString("transTime")));
                        tv_transAmount_5.setText(StringUtil.changeToMinusSign(StringUtil.getTwoPointString((String) tran5.optString("transAmount"))));
                    }
                }
            }

        } catch (Exception e) {
            Toast.makeText(this,"数据获取异常",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // 处理云信贷申请反欺诈报告View
    private void handleApplicationReportView(CreditShareItem.ResultBean creditShareItem) {
        ll_user_protrait.setVisibility(View.GONE);
        ll_trans_report.setVisibility(View.GONE);
        ll_suspect_cash.setVisibility(View.GONE);
        ll_bill_check.setVisibility(View.GONE);
        ll_credit_application.setVisibility(View.VISIBLE);
        ll_credit_internet.setVisibility(View.GONE);

        try {
            tv_application_name.setText(StringUtil.changeToMinusSign(creditShareItem.getName()));
            tv_application_identityCard.setText(StringUtil.changeToMinusSign(creditShareItem.getIdentityCard()));
            tv_application_mobile.setText(StringUtil.changeToMinusSign(creditShareItem.getMobile()));

            JSONObject jsonContent = new JSONObject(creditShareItem.getContent());
            JSONObject jsonData = jsonContent.getJSONObject("data");

            JSONObject apply = jsonData.getJSONObject("apply");
            JSONArray dksq = apply.getJSONArray("dksq");
            if(dksq.length() > 0){
                JSONObject dksqInfo = (JSONObject) dksq.get(0);
                tv_applicationTime.setText(StringUtil.changeToMinusSign((String) dksqInfo.optString("applicationTime")));
                tv_applicationAmount.setText(StringUtil.changeToMinusSign((String) dksqInfo.optString("applicationAmount")));
            }
            JSONObject debt = jsonData.getJSONObject("debt");
            JSONArray dksqArray = debt.getJSONArray("debt");
            if(dksqArray.length() > 0){
                JSONObject debtInfo = (JSONObject) dksqArray.get(0);
                tv_application_money.setText(StringUtil.changeToMinusSign((String) debtInfo.optString("money")));
            }
            JSONObject lender = jsonData.getJSONObject("lender");
            JSONArray lenderArray = lender.getJSONArray("lender");
            if(lenderArray.length() > 0 ){
                JSONObject lenderInfo = (JSONObject) lenderArray.get(0);
                tv_loanlendersTime.setText(StringUtil.changeToMinusSign((String) lenderInfo.optString("loanlendersTime")));
                tv_loanlendersAmount.setText(StringUtil.changeToMinusSign((String) lenderInfo.optString("loanlendersAmount")));
            }
            JSONObject register = jsonData.getJSONObject("register");
            JSONArray registerArray = register.getJSONArray("ptzc");
            if(registerArray.length() > 0 ){
                JSONObject registerInfo = (JSONObject) registerArray.get(0);
                tv_registerTime.setText(StringUtil.changeToMinusSign((String) registerInfo.optString("registerTime")));
            }
            JSONObject rejection = jsonData.getJSONObject("rejection");
            JSONArray rejectionArray = rejection.getJSONArray("rejection");
            if(rejectionArray.length() > 0 ){
                JSONObject rejectionInfo = (JSONObject) rejectionArray.get(0);
                tv_rejectionTime.setText(StringUtil.changeToMinusSign((String) rejectionInfo.optString("rejectionTime")));
            }
            JSONObject yq = jsonData.getJSONObject("yq");
            JSONArray yqArray = yq.getJSONArray("yq");
            if(yqArray.length() > 0 ){
                JSONObject yqInfo = (JSONObject) yqArray.get(0);
                tv_application_yq_counts.setText(StringUtil.changeToMinusSign((String) yqInfo.optString("counts")));
                tv_application_yq_money.setText(StringUtil.changeToMinusSign((String) yqInfo.optString("money")));
                tv_application_yq_lastTime.setText(StringUtil.changeToMinusSign((String) yqInfo.optString("lastTime")));
            }

        } catch (Exception e) {
            Toast.makeText(this,"数据获取异常",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // 处理云信贷互联反欺诈报告View
    private void handleInternetReportView(CreditShareItem.ResultBean creditShareItem) {
        ll_user_protrait.setVisibility(View.GONE);
        ll_trans_report.setVisibility(View.GONE);
        ll_suspect_cash.setVisibility(View.GONE);
        ll_bill_check.setVisibility(View.GONE);
        ll_credit_application.setVisibility(View.GONE);
        ll_credit_internet.setVisibility(View.VISIBLE);
    }

    //通过加载xml文件将view添加到布局中
    public View getPartView() {
        LayoutInflater inflater =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //将view对象挂载到那个父元素上，这里没有就为null
        return inflater.inflate(R.layout.layout_table_row_item, null);
    }
}
