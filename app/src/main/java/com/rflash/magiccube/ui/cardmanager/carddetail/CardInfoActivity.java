package com.rflash.magiccube.ui.cardmanager.carddetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.magiccube.R;
import com.rflash.magiccube.ui.cardmanager.cardbase.CardBaseInforActivity;
import com.rflash.magiccube.ui.cardmanager.cardbill.CardBillActivity;
import com.rflash.magiccube.ui.cardmanager.cardcharge.CardChargeActivity;
import com.rflash.magiccube.ui.cardmanager.increase.CardIncreaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/11 15:24
 * @desc:
 */

public class CardInfoActivity extends BaseActivity {

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.bankAndnum_tv)
    TextView bankAndnum_tv;

    @BindView(R.id.card_base_info)
    CardView card_base_info;

    @BindView(R.id.cardinfo_bill_cv)
    CardView cardinfo_bill_cv;

    @BindView(R.id.cardinfo_increase_cv)
    CardView cardinfo_increase_cv;

    @BindView(R.id.cardinfo_charge_cv)
    CardView cardinfo_charge_cv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardinfo);
    }

    private void initView(){

    }

    @OnClick({R.id.title_back_tv,R.id.card_base_info,R.id.cardinfo_bill_cv,R.id.cardinfo_increase_cv,R.id.cardinfo_charge_cv})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.card_base_info://基本信息
                ActivityIntent.readyGo(CardInfoActivity.this,CardBaseInforActivity.class);
                break;

            case R.id.cardinfo_bill_cv://账单
                ActivityIntent.readyGo(CardInfoActivity.this,CardBillActivity.class);
                break;

            case R.id.cardinfo_increase_cv:
                ActivityIntent.readyGo(CardInfoActivity.this,CardIncreaseActivity.class);
                break;

            case R.id.cardinfo_charge_cv:
                ActivityIntent.readyGo(CardInfoActivity.this,CardChargeActivity.class);
                break;
        }
    }
}
