package com.rflash.magiccube.ui.creditqueryitem;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.utils.StringUtil;
import com.rflash.basemodule.view.DecimalEditText;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.util.CheckUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *  信用查询中各项的查询页面
 *  Created by Guobaihui on 2018/03/12.
 */

public class CreditQueryItemActivity extends MVPBaseActivity<CreditQueryItemContract.View, CreditQueryItemPresenter> implements CreditQueryItemContract.View, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_times)
    TextView tv_times;

    @BindView(R.id.et_name)
    DecimalEditText et_name;

    @BindView(R.id.et_cardNo)
    DecimalEditText et_cardNo;

    @BindView(R.id.et_identityCard)
    DecimalEditText et_identityCard;

    @BindView(R.id.et_mobile)
    DecimalEditText et_mobile;

    private String count;
    private String total;
    private String creditType;

    //日期选择器dialog
    private DatePickerDialog datePickerDialog;
    //是否选中起始时间按钮
    private boolean startDateClick = false;
    //开始时间
    @BindView(R.id.tv_start_date)
    TextView tvStartDate;
    //结束时间
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;

    @BindView(R.id.rl_date)
    RelativeLayout rl_date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_query_item);
        initView();
        tvEndDate.setOnClickListener(this);
        tvStartDate.setOnClickListener(this);
    }

    private void initView() {
        Bundle bundle = this.getIntent().getExtras();
        count = (String) bundle.get("count");
        total = (String) bundle.get("total");
        creditType = (String) bundle.get("creditType");
        toolbar.setTitle(total);
        tv_times.setText("剩余："+count+"次");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditQueryItemActivity.this.finish();
            }
        });

        switch (creditType){
            case Config.BILL_CHECK:
                rl_date.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.tv_query})
    public void onViewClicked(View view) {
        switch (creditType){
            case Config.USER_PORTRAIT:
                if(StringUtil.isEmpty(et_name.getText().toString()) ||
                        StringUtil.isEmpty(et_identityCard.getText().toString()) ||
                        StringUtil.isEmpty(et_cardNo.getText().toString()) ||
                        StringUtil.isEmpty(et_mobile.getText().toString())){
                    Toast.makeText(this, "姓名、身份证、银行卡号及预留电话都不可为空！", Toast.LENGTH_SHORT).show();
                    break;
                }
                boolean checkUser = checkQueryElement(et_identityCard.getText().toString(), et_cardNo.getText().toString(), et_mobile.getText().toString());
                if(!checkUser){
                    break;
                }
                mPresenter.queryProduct(et_name.getText().toString(),
                        et_identityCard.getText().toString(),
                        et_cardNo.getText().toString(),
                        et_mobile.getText().toString(),
                        "", "", Config.USER_PORTRAIT);
                break;
            case Config.TRANS_REPORT:
                if(StringUtil.isEmpty(et_name.getText().toString()) ||
                        StringUtil.isEmpty(et_identityCard.getText().toString()) ||
                        StringUtil.isEmpty(et_cardNo.getText().toString()) ||
                        StringUtil.isEmpty(et_mobile.getText().toString())){
                    Toast.makeText(this, "姓名、身份证、银行卡号及预留电话都不可为空！", Toast.LENGTH_SHORT).show();
                    break;
                }
                boolean checkTrans = checkQueryElement(et_identityCard.getText().toString(), et_cardNo.getText().toString(), et_mobile.getText().toString());
                if(!checkTrans){
                    break;
                }
                mPresenter.queryProduct(et_name.getText().toString(),
                        et_identityCard.getText().toString(),
                        et_cardNo.getText().toString(),
                        et_mobile.getText().toString(),
                        "", "", Config.TRANS_REPORT);
                break;
            case Config.SUSPECT_CASH:
                if(StringUtil.isEmpty(et_cardNo.getText().toString())){
                    Toast.makeText(this, "银行卡号不可为空！", Toast.LENGTH_SHORT).show();
                    break;
                }
                boolean checkCash = checkQueryElement(null, et_cardNo.getText().toString(), null);
                if(!checkCash){
                    break;
                }
                mPresenter.queryProduct(et_name.getText().toString(),
                        et_identityCard.getText().toString(),
                        et_cardNo.getText().toString(),
                        et_mobile.getText().toString(),
                        "", "", Config.SUSPECT_CASH);
                break;
            case Config.BILL_CHECK:
                if("起始时间".equals(tvStartDate.getText().toString()) || "终止时间".equals(tvEndDate.getText().toString())){
                    Toast.makeText(this, "姓名、银行卡号、时间段不可为空！", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(StringUtil.isEmpty(et_name.getText().toString()) || StringUtil.isEmpty(et_cardNo.getText().toString()) ||
                        StringUtil.isEmpty(tvStartDate.getText().toString()) || StringUtil.isEmpty(tvEndDate.getText().toString())){
                    Toast.makeText(this, "姓名、银行卡号、时间段不可为空！", Toast.LENGTH_SHORT).show();
                    break;
                }
                boolean checkBill = checkQueryElement(null, et_cardNo.getText().toString(), null);
                if(!checkBill){
                    break;
                }
                String startDate = tvStartDate.getText().toString();
                String endDate = tvEndDate.getText().toString();
                SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat to = new SimpleDateFormat("yyyyMMdd");
                try {
                    Date startTemp = from.parse(startDate);
                    Date ndTemp = from.parse(endDate);
                    startDate = to.format(startTemp);
                    endDate = to.format(ndTemp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mPresenter.queryProduct(et_name.getText().toString(),
                        et_identityCard.getText().toString(),
                        et_cardNo.getText().toString(),
                        et_mobile.getText().toString(),
                        startDate, endDate, Config.BILL_CHECK);
                break;
            case Config.CLOUD_CREDIT_APPLICATION:
                if(StringUtil.isEmpty(et_name.getText().toString()) ||
                        StringUtil.isEmpty(et_identityCard.getText().toString()) ||
                        StringUtil.isEmpty(et_mobile.getText().toString())){
                    Toast.makeText(this, "姓名、身份证、预留电话都不可为空！", Toast.LENGTH_SHORT).show();
                    break;
                }
                boolean checkApplication = checkQueryElement(et_identityCard.getText().toString(), null, et_mobile.getText().toString());
                if(!checkApplication){
                    break;
                }
                mPresenter.queryProduct(et_name.getText().toString(),
                        et_identityCard.getText().toString(),
                        et_cardNo.getText().toString(),
                        et_mobile.getText().toString(),
                        "", "", Config.CLOUD_CREDIT_APPLICATION);
                break;
            case Config.CLOUD_CREDIT_INTERNET:
                if(StringUtil.isEmpty(et_identityCard.getText().toString()) ||
                        StringUtil.isEmpty(et_cardNo.getText().toString()) ||
                        StringUtil.isEmpty(et_mobile.getText().toString())){
                    Toast.makeText(this, "身份证、银行卡号及预留电话都不可为空！", Toast.LENGTH_SHORT).show();
                    break;
                }
                boolean checkInternet = checkQueryElement(et_identityCard.getText().toString(), et_cardNo.getText().toString(), et_mobile.getText().toString());
                if(!checkInternet){
                    break;
                }
                mPresenter.queryProduct(et_name.getText().toString(),
                        et_identityCard.getText().toString(),
                        et_cardNo.getText().toString(),
                        et_mobile.getText().toString(),
                        "", "", Config.CLOUD_CREDIT_INTERNET);
                break;
        }
    }

    @Override
    public void finishActivity() {
        Intent intent = new Intent();
        intent.putExtra("count",String.valueOf(Integer.valueOf(count)-1));
        setResult(Config.INTENTOK,intent);
        CreditQueryItemActivity.this.finish();
    }


    /**
     * 日期选择监听
     *
     * @param view
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     */
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String month = (monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1) + "";
        String day = dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth + "";
        String date = year + "-" + month + "-" + day;
        if (startDateClick) {
            tvStartDate.setText(date);
            startDateClick = false;
        } else {
            tvEndDate.setText(date);
        }
    }


    /**
     * 选择起始时间
     */
    public void selectorStartDate() {
        startDateClick = true;
        if (datePickerDialog == null) {
            setDatePickerDialog();
        }
        datePickerDialog.show(getFragmentManager(), "IntelligentPlanningActivity");
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                startDateClick = false;
            }
        });
    }


    /**
     * 终止时间
     */
//    @OnClick(R.id.tv_end_date)
    public void selectorEndDate() {
        if (datePickerDialog == null) {
            setDatePickerDialog();
        }
        datePickerDialog.show(getFragmentManager(), "IntelligentPlanningActivity");

    }


    /**
     * 设置日期选择器
     */
    private void setDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);// 日期减1
        datePickerDialog = DatePickerDialog.newInstance(
                this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);
        //设置选择器的颜色
        datePickerDialog.setAccentColor("#474E7A");
        //设置标题
        datePickerDialog.setTitle("日期选择");
        //设置选择器的选择范围
        datePickerDialog.setMaxDate(calendar);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_start_date) {
            selectorStartDate();
        } else if (v.getId() == R.id.tv_end_date) {
            selectorEndDate();
        }
    }

    /**
     *
     * @param identityCard  身份证
     * @param cardNo   银行卡号
     * @param mobile    银行预留电话
     * @return
     */
    private boolean checkQueryElement(String identityCard, String cardNo, String mobile){
        try {
            if(!StringUtil.isEmpty(identityCard)){
                String idCardValidate = CheckUtil.IDCardValidate(identityCard);
                if(!StringUtil.isEmpty(idCardValidate)){
                    Toast.makeText(this, idCardValidate, Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            if(!StringUtil.isEmpty(cardNo)){
                boolean checkBankCard = CheckUtil.checkBankCard(cardNo);
                if(!checkBankCard){
                    Toast.makeText(this, "银行卡信息格式有误", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            if(!StringUtil.isEmpty(mobile)){
                boolean mobiolePhoneNumber = CheckUtil.isMobiolePhoneNumber(mobile);
                if(!mobiolePhoneNumber){
                    Toast.makeText(this, "银行预留电话信息格式有误", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}
