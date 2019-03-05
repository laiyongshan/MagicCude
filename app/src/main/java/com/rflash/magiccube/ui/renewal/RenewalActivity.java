package com.rflash.magiccube.ui.renewal;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.flyco.roundview.RoundTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.cardmanager.cardbase.BaseInfoBean;
import com.rflash.magiccube.ui.newmain.DirtData;
import com.rflash.magiccube.ui.renewalmind.RenewalMindBean;
import com.rflash.magiccube.util.DateUtil;
import com.rflash.magiccube.util.TimerPikerTools;
import com.rflash.magiccube.util.ToolUtils;
import com.rflash.magiccube.view.SuccessProgressDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by lenovo on 2018/10/14.
 * 续费
 */

public class RenewalActivity extends MVPBaseActivity<RenewalContract.View, RenewalPresenter> implements RenewalContract.View {

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.bankAndnum_tv)
    TextView bankAndnum_tv;

    @BindView(R.id.card_state_tv)
    TextView card_state_tv;//卡片状态

    @BindView(R.id.fixedLimit_et)
    EditText fixedLimit_et;//固定额度

    @BindView(R.id.availableAmt_et)
    EditText availableAmt_et;//可用额度

    @BindView(R.id.currentRepayAmt_et)
    EditText currentRepayAmt_et;//待还款金额

    @BindView(R.id.initAmt_et)
    EditText initAmt_et;//初始金额

    @BindView(R.id.serviceType_sp)
    MaterialSpinner serviceType_sp;//费用基数类型

    @BindView(R.id.paidAmt_et)
    EditText paidAmt_et;//实收费用

    @BindView(R.id.baseAmt_cb)
    CardView baseAmt_cb;

    @BindView(R.id.baseAmt_et)
    EditText baseAmt_et;//费用基数

    @BindView(R.id.serviceAmt_et)
    EditText serviceAmt_et;//自定义金额

    @BindView(R.id.serviceAmt_cd)
    CardView serviceAmt_cd;

    @BindView(R.id.serviceRate_et)
    EditText serviceRate_et;

    @BindView(R.id.serviceStartDate_tv)
    TextView serviceStartDate_tv;//开始时间

    @BindView(R.id.serviceEndDate_tv)
    TextView serviceEndDate_tv;//到期时间

    @BindView(R.id.zhuijiaEndDate_cb)
    CardView zhuijiaEndDate_cb;//追加日期

    @BindView(R.id.zhuijiaEndDate_tv)
    TextView zhuijiaEndDate_tv;

    @BindView(R.id.limit_ll)
    LinearLayout limit_ll;

    @BindView(R.id.date_ll)
    LinearLayout date_ll;

    @BindView(R.id.sure_rtv)
    RoundTextView sure_rtv;

    SuccessProgressDialog successProgressDialog;

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    RenewalMindBean.ResultBean renewalMindBean;

    CardBean.ResultBean cardDetailBean;
    String cardNo = "";
    String serviceEndDate;
    String serviceType;
    String serviceAmt;
    String serviceRate;
    String paidAmt;
    String fixedLimit;
    String currentRepayAmt;
    String initAmt;
    String serviceStartDate;
    String availableAmt;
    String state;

    String cardState;
    String refunAmt;

    String baseAmt;

    public static final int RENEWAL_RESULT_CODE = 110;

    DirtData dirtData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renewal);
        dirtData = new DirtData(this);
        initView();
    }

    private void initView() {
        successProgressDialog = new SuccessProgressDialog(this);

        serviceAmt_cd.setVisibility(View.GONE);
        renewalMindBean = (RenewalMindBean.ResultBean) getIntent().getSerializableExtra("renewalMindBean");
        cardDetailBean = (CardBean.ResultBean) getIntent().getSerializableExtra("cardDetail");
        if (cardDetailBean != null) {
            cardNo = cardDetailBean.getCardNo() + "";
            mPresenter.queryCardDetail(cardNo);
            state = cardDetailBean.getState() + "";
            bankAndnum_tv.setText("(" + cardDetailBean.getCardBankName() + cardDetailBean.getCardNo().substring(cardNo.length() - 4) + ")");
            baseAmt=cardDetailBean.getFixedLimit()+"";
            baseAmt_et.setText(StringUtil.getTwoPointString(baseAmt) + "");
        } else if (renewalMindBean != null) {
            cardNo = renewalMindBean.getCardNo() + "";
            mPresenter.queryCardDetail(cardNo);
        }


        serviceType_sp.setItems(dirtData.serviceTypeArr);
        serviceType_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if (i == 2) {//自定义金额
                    serviceAmt_cd.setVisibility(View.VISIBLE);
                    baseAmt_cb.setVisibility(View.GONE);
                } else if (i == 1) {//还款额
                    serviceAmt_cd.setVisibility(View.GONE);
                    baseAmt_cb.setVisibility(View.VISIBLE);
                    baseAmt_et.setText(refunAmt + "");
                } else if (i == 0) {//固定额度
                    serviceAmt_cd.setVisibility(View.GONE);
                    baseAmt_cb.setVisibility(View.VISIBLE);
                    baseAmt_et.setText(StringUtil.getTwoPointString(baseAmt) + "");
                }
            }
        });

        fixedLimit_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (serviceType_sp.getSelectedIndex() == 0) {//固定额度
                    baseAmt_et.setText(editable.toString());
                }
            }
        });

        serviceStartDate = TimerPikerTools.getTodayDate();
        serviceStartDate_tv.setText(serviceStartDate + "");

    }

    @OnClick({R.id.title_back_tv, R.id.zhuijiaEndDate_tv, R.id.serviceStartDate_tv, R.id.serviceEndDate_tv, R.id.sure_rtv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_back_tv:
                cancel();
                break;

            case R.id.serviceStartDate_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView(RenewalActivity.this, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        serviceStartDate = simpleDateFormat.format(date);
                        serviceStartDate_tv.setText(serviceStartDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.serviceEndDate_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView(RenewalActivity.this, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
//                        serviceEndDate = simpleDateFormat.format(date);
                        serviceEndDate = TimerPikerTools.getLastDayOfMonth(date);
                        serviceEndDate_tv.setText(serviceEndDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.zhuijiaEndDate_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView(RenewalActivity.this, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
//                        serviceEndDate = simpleDateFormat.format(date);
                        serviceEndDate = TimerPikerTools.getLastDayOfMonth(date);
                        zhuijiaEndDate_tv.setText(serviceEndDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.sure_rtv:
                if (state.equals("EXPIRE")) {
                    renewalCard();
                } else if (state.equals("VALID")) {
                    renewalCardNoOver();
                }
                break;
        }
    }

    //卡片续期   已過期
    private void renewalCard() {
//        if(cardDetailBean.getState().equals("VALID")){//正常
//        }else if(cardDetailBean.getState().equals("EXPIRE")){//过期
//        }
        if (fixedLimit_et.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请输入固定额度", Toast.LENGTH_SHORT).show();
        } else if (availableAmt_et.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请输入可用额度", Toast.LENGTH_SHORT).show();
        } else if (currentRepayAmt_et.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请输入待还款金额", Toast.LENGTH_SHORT).show();
        } else if (initAmt_et.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请输入初始金额", Toast.LENGTH_SHORT).show();
        } else if (paidAmt_et.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请输入实收费用金额", Toast.LENGTH_SHORT).show();
        } else if (serviceRate_et.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请输入收费比例", Toast.LENGTH_SHORT).show();
        } else if (availableAmt_et.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请输入可用额度", Toast.LENGTH_SHORT).show();
        } else if (serviceStartDate_tv.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请选择续期开始时间", Toast.LENGTH_SHORT).show();
        } else if (serviceEndDate_tv.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请选择续期结束日期", Toast.LENGTH_SHORT).show();
        } else {
            serviceEndDate = serviceEndDate_tv.getText().toString().trim();
            serviceType = dirtData.serviceTypeOptions[serviceType_sp.getSelectedIndex()];

//            serviceAmt=serviceAmt_et.getText().toString().trim();
            serviceRate = ToolUtils.getDouble(Double.valueOf(serviceRate_et.getText().toString().trim()) * 100) + "";
            paidAmt = StringUtil.getFen(paidAmt_et.getText().toString().trim());
            if (serviceType_sp.getSelectedIndex() == 2) {//自定义金额
                serviceAmt = StringUtil.getFen(serviceAmt_et.getText().toString().trim() + "");
            } else {
                serviceAmt = "";
            }
            fixedLimit = fixedLimit_et.getText().toString().trim();
            currentRepayAmt = currentRepayAmt_et.getText().toString().trim();
            initAmt = initAmt_et.getText().toString().trim();
            serviceStartDate = serviceStartDate_tv.getText().toString().trim();
            availableAmt = availableAmt_et.getText().toString().trim();

            mPresenter.overdueRenewal(cardNo, serviceEndDate.replace("-", ""), serviceType, serviceAmt,
                    serviceRate, paidAmt,
                    fixedLimit, currentRepayAmt, initAmt, serviceStartDate.replace("-", ""),
                    availableAmt, cardState);
        }
    }


    //卡片续期   未過期
    private void renewalCardNoOver() {

        if (serviceRate_et.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请输入收费比例", Toast.LENGTH_SHORT).show();
        } else if (paidAmt_et.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请输入实收费用金额", Toast.LENGTH_SHORT).show();
        } else if (zhuijiaEndDate_tv.getText().toString().trim().equals("")) {
            Toast.makeText(RenewalActivity.this, "请选择追加日期", Toast.LENGTH_SHORT).show();
        } else {
            serviceEndDate = zhuijiaEndDate_tv.getText().toString().trim();
            serviceType = dirtData.serviceTypeOptions[serviceType_sp.getSelectedIndex()];
            serviceRate = Float.valueOf(serviceRate_et.getText().toString().trim()) / 100 + "";
            paidAmt = StringUtil.getFen(paidAmt_et.getText().toString().trim());
            if (serviceType_sp.getSelectedIndex() == 2) {//自定义金额
                serviceAmt = StringUtil.getFen(serviceAmt_et.getText().toString().trim() + "");
            } else {
                serviceAmt = "";
            }
            mPresenter.noOverdueRewal(cardNo, serviceEndDate.replace("-", ""), serviceType, serviceAmt,
                    serviceRate, paidAmt, cardState);
        }

    }


    private void cancel() {
        final MaterialDialog mMaterialDialog = new MaterialDialog(RenewalActivity.this);
        mMaterialDialog.setTitle("提示");
        mMaterialDialog.setMessage("确定退出该页面？");
        mMaterialDialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mMaterialDialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();
            }
        });
        mMaterialDialog.show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                cancel();
                return false;//拦截事件
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void showRefresh() {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void renewalResult() {
        successProgressDialog.showDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                successProgressDialog.dismiss();
                setResult(RENEWAL_RESULT_CODE);
                finish();
            }
        }, 2000);
    }


    @Override
    public void getDataSuccess(BaseInfoBean response) {
        if (response != null) {
            refunAmt = StringUtil.getTwoPointString(response.getResult().getCurrentRepayAmt() + "");
            serviceRate = ToolUtils.getDouble(Double.valueOf(response.getResult().getServiceRate() + "") * 100) + "";
            state = response.getResult().getState() + "";
            baseAmt=response.getResult().getFixedLimit()+"";
            baseAmt_et.setText(StringUtil.getTwoPointString(baseAmt) + "");

            if (state.equals("VALID")) {//卡片未过期时自动填写
                serviceRate_et.setText(serviceRate + "");
            }
            if (state.equals("FREEZE")) {//卡片已冻结，不可续期
                RenewalActivity.this.finish();
                Toast.makeText(RenewalActivity.this, "卡片已冻结，不可续期", Toast.LENGTH_SHORT).show();
            }
            if (cardNo.length() > 4)
                bankAndnum_tv.setText("(" + response.getResult().getCardBankName() + "" + cardNo.substring(cardNo.length() - 4) + ")");
            if (state.equals("VALID")){//正常
                cardState = "N";
                card_state_tv.setText("正在服务中" + "(结束日期" + DateUtil.formatDate1(response.getResult().getServiceEndDate()) + ")");
                card_state_tv.setTextColor(Color.parseColor("#3F51B5"));
                zhuijiaEndDate_cb.setVisibility(View.VISIBLE);
                limit_ll.setVisibility(View.GONE);
                date_ll.setVisibility(View.GONE);
            } else if (state.equals("EXPIRE")) {
                cardState = "Y";
                card_state_tv.setText("没有相关服务");
                card_state_tv.setTextColor(Color.RED);
                zhuijiaEndDate_cb.setVisibility(View.GONE);
                limit_ll.setVisibility(View.VISIBLE);
                date_ll.setVisibility(View.VISIBLE);
            }
        }
    }
}
