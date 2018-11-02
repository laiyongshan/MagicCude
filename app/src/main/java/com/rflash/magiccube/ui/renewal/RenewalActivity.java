package com.rflash.magiccube.ui.renewal;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.cardmanager.addplan.AddPlanActivity;
import com.rflash.magiccube.view.SuccessProgressDialog;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by lenovo on 2018/10/14.
 * 续费
 */

public class RenewalActivity extends MVPBaseActivity<RenewalContract.View,RenewalPresenter> implements RenewalContract.View {

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
    MaterialSpinner paidAmt_et;//费用基数

    @BindView(R.id.serviceAmt_et)
    EditText serviceAmt_et;

    @BindView(R.id.serviceEndDate)
    TextView serviceEndDate;//到期时间

    @BindView(R.id.sure_rtv)
    RoundTextView sure_rtv;


    SuccessProgressDialog successProgressDialog;

    CardBean.ResultBean cardDetailBean;
    String cardNo = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renewal);
        initView();
    }

    private void initView() {
        cardDetailBean = (CardBean.ResultBean) getIntent().getSerializableExtra("cardDetail");
        cardNo = cardDetailBean.getCardNo();
        bankAndnum_tv.setText("(" + cardDetailBean.getCardBankName() + cardDetailBean.getCardNo().substring(cardNo.length() - 4) + ")");

        successProgressDialog=new SuccessProgressDialog(this);

        if(cardDetailBean.getState().equals("VALID")){//正常
            card_state_tv.setText("正常");
            card_state_tv.setTextColor(Color.parseColor("#3F51B5"));
        }else if(cardDetailBean.getState().equals("EXPIRE")){
            card_state_tv.setText("卡片过期");
            card_state_tv.setTextColor(Color.RED);
        }


    }

    @OnClick({R.id.title_back_tv,R.id.sure_rtv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_back_tv:
                cancel();
                break;

            case R.id.sure_rtv:

                break;
        }
    }

    //卡片续期
    private void renewalCard(){
        if(cardDetailBean.getState().equals("VALID")){//正常
        }else if(cardDetailBean.getState().equals("EXPIRE")){//过期
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
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancel();
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
                finish();
            }
        },2000);
    }
}
