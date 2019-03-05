package com.rflash.magiccube.ui.cardmanager.addplan;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.flyco.roundview.RoundTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.newmain.DirtData;
import com.rflash.magiccube.ui.shanghu.ShanghuBean;
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

    @BindView(R.id.isNeedT0_sp)
    MaterialSpinner isNeedT0_sp;

    @BindView(R.id.repayDate_et)
    TextView repayDate_et;//还款时间

    @BindView(R.id.swipecardDate_et)
    TextView swipecardDate_et;//刷卡时间

    @BindView(R.id.add_plan_rtv)
    RoundTextView add_plan_rtv;

    @BindView(R.id.repayDate_cd)
    CardView repayDate_cd;

    @BindView(R.id.swipecardDate_cd)
    CardView swipecardDate_cd;

    @BindView(R.id.merchantName_cd)
            CardView merchantName_cd;
    @BindView(R.id.merchantName_sp)
            MaterialSpinner merchantName_sp;
    @BindView(R.id.merchantCode_cd)
            CardView merchantCode_cd;
    @BindView(R.id.merchantCode_et)
            EditText merchantCode_et;
    @BindView(R.id.mcc_cd)
            CardView mcc_cd;
    @BindView(R.id.mcc_et)
            EditText mcc_et;

    @BindView(R.id.mcc_sp)
    MaterialSpinner mcc_sp;
    List<String> MccTypeList=new ArrayList<>();
    String mccId="";

    @BindView(R.id.isNeedT0_cd)
            CardView isNeedT0_cd;

    SuccessProgressDialog successProgressDialog;

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    CardBean.ResultBean cardDetailBean;
    String tranType="";
    String channel="";
    String merchantCode="";
    String termCode="";
    String amt="";
    String isNeedT0="";
    String plandate="";
    String state="";
    String cardNo = "";

    DirtData dirtData;
    List<String> stateList = new ArrayList<>();

    List<String> merchantList01=new ArrayList<>();//盛01商户
    List<String> merchantList02=new ArrayList<>();//汇02商户
    List<String> merchantList04=new ArrayList<>();//中04商户

    List<ShanghuBean.ResultBean>  shanghuList01=new ArrayList<>();///盛01商户
    List<ShanghuBean.ResultBean>  shanghuList02=new ArrayList<>();////汇02商户
    List<ShanghuBean.ResultBean>  shanghuList04=new ArrayList<>();//中04商户

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        initView();
        getMerchantData();
    }

    private void initView() {
        cardDetailBean = (CardBean.ResultBean) getIntent().getSerializableExtra("cardDetail");
        cardNo = cardDetailBean.getCardNo();
        bankAndnum_tv.setText("(" + cardDetailBean.getCardBankName() + cardDetailBean.getCardNo().substring(cardNo.length() - 4) + ")");

        successProgressDialog = new SuccessProgressDialog(this);

        dirtData = new DirtData(this);

        MccTypeList=dirtData.getMccList();
        MccTypeList.add(0,"请选择商户类型");
        mcc_sp.setItems("请选择商户类型");

        tranType_sp.setItems(dirtData.tranTypeArr);
        tranType_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if (i == 0) {//消费
                    repayDate_cd.setVisibility(View.GONE);
                    swipecardDate_cd.setVisibility(View.VISIBLE);
                    merchantName_cd.setVisibility(View.VISIBLE);
                    merchantCode_cd.setVisibility(View.VISIBLE);
                    mcc_cd.setVisibility(View.VISIBLE);
                    isNeedT0_cd.setVisibility(View.VISIBLE);
                    channel_sp.setItems(dirtData.ChannelArr);

                    channel_sp.setSelectedIndex(0);
                    mcc_sp.setSelectedIndex(0);
                    merchantName_sp.setSelectedIndex(0);
                    merchantCode_et.setText("");
                    isNeedT0_sp.setSelectedIndex(0);
                } else if (i == 1) {//还款
                    repayDate_cd.setVisibility(View.VISIBLE);
                    swipecardDate_cd.setVisibility(View.GONE);
                    merchantName_cd.setVisibility(View.GONE);
                    merchantCode_cd.setVisibility(View.GONE);
                    mcc_cd.setVisibility(View.GONE);
                    isNeedT0_cd.setVisibility(View.GONE);
                    channel_sp.setItems(dirtData.ChannelArr2);

                    channel_sp.setSelectedIndex(0);
                    mcc_sp.setSelectedIndex(0);
                    merchantName_sp.setSelectedIndex(0);
                    merchantCode_et.setText("");
                    isNeedT0_sp.setSelectedIndex(0);
                }
            }
        });

        state_sp.setItems(dirtData.planStateArr);
//        state_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
//
//            }
//        });

        channel_sp.setItems(dirtData.ChannelArr);
        channel_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if(i!=0){
                    mcc_sp.setItems(MccTypeList);
                    if(tranType_sp.getSelectedIndex()==0) {
                        channel = dirtData.ChannelIDOptions[i];
                        if(i==1){//盛01
                            if(!getMerchantList(channel,mccId,shanghuList01).isEmpty()) {
                                merchantName_sp.setItems(MerchantList);
                                if(!shanghuList01.isEmpty()) {
                                    merchantCode=getMerchantCode(shanghuList01,MerchantList.get(0))+"";
                                    merchantCode_et.setText(merchantCode);
//                                    merchantCode_et.setText(shanghuList01.get(0).getMerchantCode() + "");
                                    mcc_et.setText(shanghuList01.get(0).getMerchantMccSmallClassName());
                                }
                            }else{
                                merchantName_sp.setItems("");
                                merchantCode_et.setText("");
                                mcc_et.setText("");
                            }
                        }else if(i==2){//汇02
                            if(!getMerchantList(channel,mccId,shanghuList02).isEmpty()) {
                                merchantName_sp.setItems(MerchantList);
                                if(!shanghuList02.isEmpty()) {
                                    merchantCode=getMerchantCode(shanghuList02,MerchantList.get(0))+"";
                                    merchantCode_et.setText(merchantCode);
//                                    merchantCode_et.setText(shanghuList02.get(0).getMerchantCode() + "");
                                    mcc_et.setText(shanghuList02.get(0).getMerchantMccSmallClassName());
                                }
                            }else{
                                merchantName_sp.setItems("");
                                merchantCode_et.setText("");
                                mcc_et.setText("");
                            }
                        }else if(i==3){//中04
                            if(!getMerchantList(channel,mccId,shanghuList04).isEmpty()) {
                                merchantName_sp.setItems(MerchantList);
                                if(!shanghuList04.isEmpty()) {
                                    merchantCode=getMerchantCode(shanghuList04,MerchantList.get(0))+"";
                                    merchantCode_et.setText(merchantCode);
//                                    merchantCode_et.setText(shanghuList04.get(0).getMerchantCode() + "");
                                    mcc_et.setText(shanghuList04.get(0).getMerchantMccSmallClassName());
                                }
                            }else{
                                merchantName_sp.setItems("");
                                merchantCode_et.setText("");
                                mcc_et.setText("");
                            }
                        }
                    }else {
                        channel = dirtData.ChannelIDOptions2[i];
                        merchantCode="";
                    }
                }else{
                    channel="";

                }

            }
        });

        mcc_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if(i==0){
                    mccId="";
                }else{
                    mccId=dirtData.mccOptions.get(i-1).getDictId()+"";
                    if(channel.equals("38")){//盛01
                        if(!getMerchantList(channel,mccId,shanghuList01).isEmpty()) {
                            merchantName_sp.setItems(getMerchantList(channel, mccId, shanghuList01));
//                            merchantCode_et.setText(shanghuList01.get(0).getMerchantCode() + "");
                            merchantCode=getMerchantCode(shanghuList01,MerchantList.get(0))+"";
                            merchantCode_et.setText(merchantCode);
                            mcc_et.setText(shanghuList01.get(0).getMerchantMccSmallClassName());
                        }else{
                            merchantName_sp.setItems("");
                            merchantCode_et.setText("");
                            mcc_et.setText("");
                        }
                    }else if(channel.equals("17")){//汇02
                        if(!getMerchantList(channel,mccId,shanghuList02).isEmpty()) {
                            merchantName_sp.setItems(getMerchantList(channel, mccId, shanghuList02));
//                            merchantCode_et.setText(shanghuList02.get(0).getMerchantCode() + "");
                            merchantCode=getMerchantCode(shanghuList02,MerchantList.get(0))+"";
                            merchantCode_et.setText(merchantCode);
                            mcc_et.setText(shanghuList02.get(0).getMerchantMccSmallClassName());
                        }else{
                            merchantName_sp.setItems("");
                            merchantCode_et.setText("");
                            mcc_et.setText("");
                        }
                    }else if(channel.equals("42")){//中04
                        if(!getMerchantList(channel,mccId,shanghuList04).isEmpty()) {
                            merchantName_sp.setItems(getMerchantList(channel, mccId, shanghuList04));
//                            merchantCode_et.setText(shanghuList04.get(0).getMerchantCode() + "");
                            merchantCode=getMerchantCode(shanghuList04,MerchantList.get(0))+"";
                            merchantCode_et.setText(merchantCode);
                            mcc_et.setText(shanghuList04.get(0).getMerchantMccSmallClassName());
                        }else{
                            merchantName_sp.setItems("");
                            merchantCode_et.setText("");
                            mcc_et.setText("");
                        }
                    }
                }
            }
        });

        merchantName_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if(channel_sp.getSelectedIndex()==1){
                    mcc_et.setText(shanghuList01.get(i).getMerchantMccSmallClassName());
                    merchantCode=getMerchantCode(shanghuList01,MerchantList.get(i))+"";
                    merchantCode_et.setText(merchantCode);
                }else if(channel_sp.getSelectedIndex()==2){
                    mcc_et.setText(shanghuList02.get(i).getMerchantMccSmallClassName());
                    merchantCode=getMerchantCode(shanghuList02,MerchantList.get(i))+"";
                    merchantCode_et.setText(merchantCode);
                }else if (channel_sp.getSelectedIndex()==3){
                    mcc_et.setText(shanghuList04.get(i).getMerchantMccSmallClassName());
                    merchantCode=getMerchantCode(shanghuList04,MerchantList.get(i))+"";
                    merchantCode_et.setText(merchantCode);
                }
            }
        });

        isNeedT0_sp.setItems(dirtData.isNeedT0Arr);
//        isNeedT0_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
//
//            }
//        });

    }

    List<String> MerchantList=new ArrayList<>();
    private List<String> getMerchantList(String channelId,String mccId,List<ShanghuBean.ResultBean> shanghuList){
        MerchantList.clear();
        for (ShanghuBean.ResultBean resultBean:shanghuList){
            Log.i("lys","channelId is："+channelId+"\n"+"resultBean.getChannel() is:"+resultBean.getChannel());
            Log.i("lys","mccId is："+mccId+"\n"+"resultBean.getMcc() is:"+resultBean.getMcc());
           if(resultBean.getChannel().equals(channelId)&&mccId.equals(resultBean.getMerchantMccSmallClass()+"")){
                MerchantList.add(resultBean.getMerchantName());
            }
        }
        Log.i("lys","MerchantList 一共有多少条数据： "+MerchantList.size());
        return  MerchantList;
    }

    private String getMerchantCode(List<ShanghuBean.ResultBean> shanghuList,String merchantName){
        for(ShanghuBean.ResultBean resultBean:shanghuList){
            if(merchantName.equals(resultBean.getMerchantName())){
                return resultBean.getMerchantCode()+"";
            }else{
                continue;
            }
        }
        return  "";
    }


    @OnClick({R.id.title_back_tv, R.id.add_plan_rtv, R.id.cancel_rtv, R.id.repayDate_et, R.id.swipecardDate_et})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_back_tv:
                cancel();
                break;

            case R.id.repayDate_et:
                mTimePikerView = TimerPikerTools.creatTimePickerView(AddPlanActivity.this, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        plandate = simpleDateFormat.format(date);
                        repayDate_et.setText(plandate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.swipecardDate_et:
                if(tranType_sp.getSelectedIndex()==0&&state_sp.getSelectedIndex()==0){
                mTimePikerView = TimerPikerTools.creatTimePickerView3(AddPlanActivity.this, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        plandate = simpleDateFormat.format(date);
                        swipecardDate_et.setText(plandate + "");
                    }
                });
                mTimePikerView.show();
                }else{
                    mTimePikerView = TimerPikerTools.creatTimePickerView(AddPlanActivity.this, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            plandate = simpleDateFormat.format(date);
                            swipecardDate_et.setText(plandate + "");
                        }
                    });
                    mTimePikerView.show();
                }
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
        }else if(channel_sp.getSelectedIndex()==0){
            Toast.makeText(AddPlanActivity.this, "请选择交易渠道号", Toast.LENGTH_SHORT).show();
        } else {
            amt = StringUtil.getFen(amt_et.getText().toString().trim());
            tranType = dirtData.tranTypeOptions[tranType_sp.getSelectedIndex()];
            state = dirtData.planStateOptionArr[state_sp.getSelectedIndex()];

            merchantCode=merchantCode_et.getText().toString().trim();
//            if(tranType_sp.getSelectedIndex()==0) {//消费
//                channel = dirtData.channelOptions.get(channel_sp.getSelectedIndex()).getDictId();
//            }else if(tranType_sp.getSelectedIndex()==1){//还款
//
//            }
//            merchantCode = dirtData.merchantsTypeOptions.get(merchantCode_sp.getSelectedIndex()).getDictId();
//            termCode = SpUtil.getString(AddPlanActivity.this, Config.TERMCODE, "");
            isNeedT0 = dirtData.isNeedOptions[isNeedT0_sp.getSelectedIndex()];
            if (tranType.equals("SALE")) {
                plandate = swipecardDate_et.getText().toString().trim();
            } else if (tranType.equals("REPAY")) {
                plandate = repayDate_et.getText().toString().trim();
            }

            getTermCode(channel,merchantCode);//先获取终端号，成功后再调用添加规划的接口

//            mPresenter.addPlan(tranType, cardNo, channel, merchantCode, termCode, amt, isNeedT0, plandate.replace("-",""), state);
        }

    }

    //终端查询 获取 termCode
    private void getTermCode(String channel,String merchantCode ){
        mPresenter.queryTerm(channel,merchantCode);
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
        mMaterialDialog.show();
    }

    private void getMerchantData(){
        mPresenter.queryShanghu("","","","","","","","Y","","500");
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

    @Override
    public void queryTermSuccess(TermBean data) {
        if(data!=null) {
            termCode = data.getTermCode() + "";
            Log.i("lys","終端號為："+termCode);
            mPresenter.addPlan(tranType, cardNo, channel, merchantCode_et.getText().toString().trim(), termCode, amt, isNeedT0, plandate.replace("-", ""), state);
        }
    }

    @Override
    public void queryShanghuResult(ShanghuBean response) {
        if(response!=null){
            shanghuList01.clear();
            shanghuList02.clear();
            shanghuList04.clear();
            merchantList01.clear();
            merchantList02.clear();
            merchantList04.clear();
            for(ShanghuBean.ResultBean resultBean:response.getResult()){
                if(resultBean.getChannel().equals("38")){
                    shanghuList01.add(resultBean);
                    merchantList01.add(resultBean.getMerchantName()+"");
                }else if(resultBean.getChannel().equals("17")){
                    shanghuList02.add(resultBean);
                    merchantList02.add(resultBean.getMerchantName()+"");
                }else if(resultBean.getChannel().equals("42")){
                    shanghuList04.add(resultBean);
                    merchantList04.add(resultBean.getMerchantName()+"");
                }
            }
        }
    }
}
