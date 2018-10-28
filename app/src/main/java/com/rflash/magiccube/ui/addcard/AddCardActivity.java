package com.rflash.magiccube.ui.addcard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.rflash.basemodule.BaseActivity;
import com.rflash.magiccube.R;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.addplan.AddPlanActivity;
import com.rflash.magiccube.ui.cardmanager.addplan.AddPlanContract;
import com.rflash.magiccube.ui.cardmanager.cardbase.CardBaseInforActivity;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by lenovo on 2018/10/13.
 */

public class AddCardActivity extends MVPBaseActivity<AddCardContract.View,AddCardPresenter> implements AddCardContract.View {
    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.card_drawerlayout)
    DrawerLayout card_drawerlayout;

    @BindView(R.id.cancel_insert_rtv)
    RoundTextView cancel_insert_rtv;

    @BindView(R.id.insert_card_rtv)
    RoundTextView insert_card_rtv;

    //基本信息
    @BindView(R.id.bankName_tv)
    TextView bankName_tv;

    //分期设置
    @BindView(R.id.aging_setting_rtv)
    RoundTextView aging_setting_rtv;


    String ebankinfo;//网银信息
    String stagesList;//分期信息
    String cardMedia;//卡介质 IC_CARD：IC卡  STRIP_CARD：磁条卡
    String serviceType;//费用基数类型 FIXED_LIMIT：固定额度 REPAY_LIMIT：还款额  USER_DEFINED：自定义
    String isFreePlan;//空闲时段是否规划 Y：参与 N：不参与
    String isHolidayPlan;//isHolidayPlan Y：参与 N：不参与
    String repayDateType;//还款日类型  FIXED: 固定还款日 APPOINTED_DAYS： 指定天数


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        initView();
    }

    private void initView(){
        bankName_tv.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.title_back_tv,R.id.aging_setting_rtv,R.id.cancel_insert_rtv,R.id.insert_card_rtv})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.aging_setting_rtv:
                card_drawerlayout.openDrawer(Gravity.RIGHT);
                break;

            case R.id.cancel_insert_rtv:
                final MaterialDialog mMaterialDialog = new MaterialDialog(AddCardActivity.this);
                mMaterialDialog.setTitle("提示");
                mMaterialDialog.setMessage("确定退出该页面？");
                mMaterialDialog.setPositiveButton("确定",new View.OnClickListener() {
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
                break;

            case R.id.insert_card_rtv:
                insertCard();
                break;
        }
    }

    //添加卡片
    private void insertCard(){

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void finishinsertCard() {

    }

    @Override
    public void insertCardFail(String msg) {

    }

    @Override
    public void insertCardSuccess(BaseBean response) {

    }

    @Override
    public void verifyResult() {

    }
}
