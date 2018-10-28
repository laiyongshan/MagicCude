package com.rflash.magiccube.ui.creditrecharge;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rflash.basemodule.utils.StringUtil;
import com.rflash.basemodule.view.DecimalEditText;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.util.ImgHelper;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *  信用查询充值共用Activity
 *  Created by Guobaihui on 2018/03/13.
 */

public class CreditreChargeActivity extends MVPBaseActivity<CreditrechargeContract.View, CreditrechargePresenter> implements CreditrechargeContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_total)
    TextView tv_total;

    // 购买次数输入框
    @BindView(R.id.edt_buy_count)
    DecimalEditText edt_buy_count;
    // 更改按钮
    @BindView(R.id.bt_change)
    Button bt_change;
    // 总金额
    @BindView(R.id.tv_money)
    TextView tv_money;

    @BindView(R.id.rg_tran_type)
    RadioGroup rg_tran_type;

    @BindView(R.id.iv_qrcode)
    ImageView iv_qrcode;

    @BindView(R.id.tv_unitPrice)
    TextView tv_unitPrice;

    @BindView(R.id.tv_price)
    TextView tv_price;

    @BindView(R.id.ll_money)
    LinearLayout ll_money;

    private String creditType;
    private String unitPrice;
    private boolean clickedButtonChange = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_recharge_share);
        initView();
    }

    private void initView() {
        toolbar.setTitle("购买次数");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditreChargeActivity.this.finish();
            }
        });

        Bundle bundle = this.getIntent().getExtras();
        String total = (String) bundle.get("total");
        creditType = (String) bundle.get("creditType");
        unitPrice = (String) bundle.get("unitPrice");
        tv_total.setText(total);
        tv_unitPrice.setText("￥ "+unitPrice+"/次");
        disableRadioGroup(rg_tran_type);

        edt_buy_count.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!StringUtil.isEmpty(s.toString())){
                    BigDecimal b1=new BigDecimal(s.toString());
                    BigDecimal b2=new BigDecimal(unitPrice);
                    double money = b1.multiply(b2).doubleValue();
                    DecimalFormat df = new DecimalFormat("0.00");
                    tv_money.setText("￥"+df.format(money).toString());
                    enableRadioGroup(rg_tran_type);
                }else{
                    tv_money.setText("￥0");
                    disableRadioGroup(rg_tran_type);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rg_tran_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId > 0){
                    edt_buy_count.setFocusable(false);
                    edt_buy_count.setFocusableInTouchMode(false);
                    if(!clickedButtonChange){
                        hideSoftInput();
                        RadioButton radbtn = (RadioButton) findViewById(checkedId);
                        if(Config.STRINGALIPAY.equals(radbtn.getText().toString())){
                            mPresenter.rechargeTimes(creditType, edt_buy_count.getText().toString(), Config.ALIPAY);
                        }else if(Config.STRINGWXPAY.equals(radbtn.getText().toString())){
                            mPresenter.rechargeTimes(creditType, edt_buy_count.getText().toString(), Config.WXPAY);
                        }
                    }
                }
                clickedButtonChange = false;

            }
        });
    }

    @OnClick({R.id.bt_change})
    public void onViewClicked(View view) {
        clickedButtonChange = true;
        edt_buy_count.setText("");
        tv_money.setText("￥0");
        iv_qrcode.setImageDrawable(null);
        tv_price.setText("");
        ll_money.setVisibility(View.INVISIBLE);
        rg_tran_type.clearCheck();
        disableRadioGroup(rg_tran_type);
        edt_buy_count.setFocusableInTouchMode(true);
        edt_buy_count.setFocusable(true);
    }

    public void disableRadioGroup(RadioGroup radioGroup) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(false);
        }
    }

    public void enableRadioGroup(RadioGroup radioGroup) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(true);
        }
    }

    @Override
    public void showQrcode(CreditRechare data) {
        try {
            Bitmap bitmap = ImgHelper.bytes2Bimap( ImgHelper.decode(data.getQrcode())) ;
            iv_qrcode.setImageBitmap(bitmap);
            ll_money.setVisibility(View.VISIBLE);
            tv_price.setText("￥"+StringUtil.getTwoPointString(data.getPrice()));
        } catch (IOException e) {
            e.printStackTrace();
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
}
