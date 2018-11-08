package com.rflash.magiccube.ui.cardmanager.addplan;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.flyco.roundview.RoundTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.newmain.DirtData;
import com.rflash.magiccube.util.TimerPikerTools;
import com.rflash.magiccube.view.SuccessProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by lenovo on 2018/10/27.
 */

public class AddPlanActivity extends MVPBaseActivity<AddPlanContract.View, AddPlanPresenter> implements AddPlanContract.View {

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.bankAndnum_tv)
    TextView bankAndnum_tv;

    @BindView(R.id.tranType_sp)
    MaterialSpinner tranType_sp;//交易类型

    @BindView(R.id.amt_et)
    EditText amt_et;//交易金额

    @BindView(R.id.state_sp)
    MaterialSpinner state_sp;//规划状态

    @BindView(R.id.channel_sp)
    MaterialSpinner channel_sp;

    @BindView(R.id.merchantCode_sp)
    MaterialSpinner merchantCode_sp;

//    @BindView(R.id.mcc_sp)
//    MaterialSpinner mcc_sp;

    @BindView(R.id.isNeedT0_sp)
    MaterialSpinner isNeedT0_sp;

    @BindView(R.id.repayDate_et)
    EditText repayDate_et;//还款时间

    @BindView(R.id.swipecardDate_et)
    EditText swipecardDate_et;//刷卡时间

    @BindView(R.id.add_plan_rtv)
    RoundTextView add_plan_rtv;

    @BindView(R.id.repayDate_cd)
    CardView repayDate_cd;

    @BindView(R.id.swipecardDate_cd)
    CardView swipecardDate_cd;

    SuccessProgressDialog successProgressDialog;

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    CardBean.ResultBean cardDetailBean;
    String tranType;
    String channel;
    String merchantCode;
    String termCode;
    String amt;
    String isNeedT0;
    String plandate;
    String state;
    String cardNo = "";

    DirtData dirtData;
    List<String> stateList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        initView();
    }

    private void initView() {
        cardDetailBean = (CardBean.ResultBean) getIntent().getSerializableExtra("cardDetail");
        cardNo = cardDetailBean.getCardNo();
        bankAndnum_tv.setText("(" + cardDetailBean.getCardBankName() + cardDetailBean.getCardNo().substring(cardNo.length() - 4) + ")");

        successProgressDialog = new SuccessProgressDialog(this);

        dirtData = new DirtData(this);

        tranType_sp.setItems(dirtData.tranTypeArr);
        tranType_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if (i == 0) {//消费
                    repayDate_cd.setVisibility(View.GONE);
                    swipecardDate_cd.setVisibility(View.VISIBLE);
                } else if (i == 1) {//还款
                    repayDate_cd.setVisibility(View.VISIBLE);
                    swipecardDate_cd.setVisibility(View.GONE);
                }
            }
        });

        state_sp.setItems(dirtData.getPlanStateList());
//        state_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
//
//            }
//        });

        channel_sp.setItems(dirtData.getChannelList());
//        channel_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
//
//            }
//        });

        merchantCode_sp.setItems(dirtData.getMerchantsTypeList());
//        merchantCode_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
//
//            }
//        });

        isNeedT0_sp.setItems(dirtData.isNeedT0Arr);
//        isNeedT0_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
//
//            }
//        });

    }


    @OnClick({R.id.title_back_tv, R.id.add_plan_rtv, R.id.cancel_rtv, R.id.repayDate_et, R.id.swipecardDate_et})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_back_tv:
                cancel();
                break;

            case R.id.repayDate_et:
<<<<<<< HEAD
                mTimePikerView = TimerPikerTools.creatTimePickerView(AddPlanActivity.this, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
=======
                mTimePikerView = TimerPikerTools.creatTimePickerView(AddPlanActivity.this, "选择日期", true, true, false, new TimePickerView.OnTimeSelectListener() {
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        plandate = simpleDateFormat.format(date);
                        repayDate_et.setText(plandate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.swipecardDate_et:
<<<<<<< HEAD
                mTimePikerView = TimerPikerTools.creatTimePickerView(AddPlanActivity.this, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        plandate = simpleDateFormat.format(date);
                        swipecardDate_et.setText(plandate + "");
=======
                mTimePikerView = TimerPikerTools.creatTimePickerView(AddPlanActivity.this, "选择日期", true, true, false, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        plandate = simpleDateFormat.format(date);
                        repayDate_et.setText(plandate + "");
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.cancel_rtv://取消修改
                cancel();
                break;

            case R.id.add_plan_rtv://确定修改
                addPlan();
                break;
        }
    }

    private void addPlan() {
        if (amt_et.getText().toString().trim().equals("")) {
            Toast.makeText(AddPlanActivity.this, "请输入交易金额", Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
=======
        } else if ((repayDate_et.getText().toString().trim().equals("")) || swipecardDate_et.getText().toString().trim().equals("")) {
            Toast.makeText(AddPlanActivity.this, "请选择交易时间", Toast.LENGTH_SHORT).show();
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
        } else {
            amt = amt_et.getText().toString().trim();
            tranType = dirtData.tranTypeOptions[tranType_sp.getSelectedIndex()];
            state = dirtData.planStateOptions.get(state_sp.getSelectedIndex()).getDictId();
            channel = dirtData.channelOptions.get(channel_sp.getSelectedIndex()).getDictId();
            merchantCode = dirtData.merchantsTypeOptions.get(merchantCode_sp.getSelectedIndex()).getDictId();
            termCode = SpUtil.getString(AddPlanActivity.this, Config.TERMCODE, "");
            isNeedT0 = dirtData.isNeedOptions[isNeedT0_sp.getSelectedIndex()];
            if (tranType.equals("SALE")) {
                plandate = swipecardDate_et.getText().toString().trim();
            } else if (tranType.equals("REPAY")) {
                plandate = repayDate_et.getText().toString().trim();
            }

<<<<<<< HEAD
            mPresenter.addPlan(tranType, cardNo, channel, merchantCode, termCode, amt, isNeedT0, plandate.replace("-",""), state);
=======
            mPresenter.addPlan(tranType, cardNo, channel, merchantCode, termCode, amt, isNeedT0, plandate, state);
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
        }

    }


    private void cancel() {
        final MaterialDialog mMaterialDialog = new MaterialDialog(AddPlanActivity.this);
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
<<<<<<< HEAD
        mMaterialDialog.show();
=======
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
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
    public void getResult() {
        successProgressDialog.showDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                successProgressDialog.dismiss();
                finish();
            }
        }, 1500);
    }
}
