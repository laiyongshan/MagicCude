package com.rflash.magiccube.ui.creditpersonalmessage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *  信用查询中各项的个人信息
 *  Created by Guobaihui on 2018/03/12.
 */

public class CreditPersonalMessageActivity extends MVPBaseActivity<CreditPersonalMessageContract.View, CreditPersonalMessagePresenter> implements CreditPersonalMessageContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_identityCard)
    TextView tv_identityCard;
    @BindView(R.id.tv_cardNo)
    TextView tv_cardNo;
    @BindView(R.id.tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.tv_date)
    TextView tv_date;

    private  String orderNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_personal_share);
        initView();
    }

    private void initView() {
        toolbar.setTitle("个人信息");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditPersonalMessageActivity.this.finish();
            }
        });

        Bundle bundle = this.getIntent().getExtras();
        tv_name.setText(bundle.getString("name"));
        tv_identityCard.setText(bundle.getString("identityCard"));
        tv_cardNo.setText(StringUtil.getBankNo(bundle.getString("cardNo")));
        tv_mobile.setText(bundle.getString("mobile"));
        tv_date.setText(bundle.getString("date"));
        orderNo = bundle.getString("orderNo");
    }

    @OnClick({R.id.tv_delete_message})
    public void onViewClicked(View view) {
        if(orderNo != null)
            mPresenter.deletePersonalMessage(orderNo);
    }

    @Override
    public void retuenUpActivity() {
        CreditPersonalMessageActivity.this.finish();
    }
}
