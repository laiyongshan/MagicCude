package com.rflash.magiccube.ui.addcard;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.flyco.roundview.RoundTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.event.DeleteAdingMessage;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.newmain.DirtData;
import com.rflash.magiccube.util.AESUtil;
import com.rflash.magiccube.util.TimerPikerTools;
import com.rflash.magiccube.view.SuccessProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
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


    @BindViews({R.id.base_info_rl,R.id.lines_rl,R.id.plan_rl,R.id.service_cost_rl,R.id.aging_rl,R.id.ebank_rl})
    RelativeLayout[] rlArr;

    @BindViews({R.id.card_base_info_layout,R.id.card_lines_info_layout,R.id.card_plan_info_layout,R.id.card_service_cost_layout,R.id.card_aging_info_layout,R.id.card_ebank_info_layout})
    LinearLayout[] llArr;

    @BindViews({R.id.base_info_arrow_iv,R.id.lines_info_arrow_iv,R.id.plan_info_arrow_iv,R.id.service_cost_arrow_iv,R.id.aging_info_arrow_iv,R.id.ebank_info_arrow_iv})
    ImageView[] arrowIvs;

    boolean isShowBaseArrow,isShowLinesArrow,isShowPlanArow,isShowServiceArrow,isShowAging,isShowEbank;


    //基本信息
    @BindView(R.id.cardSeqno_et)
    EditText cardSeqno_et;
    @BindView(R.id.bankName_tv)
    TextView bankName_tv;
    @BindView(R.id.cardNo_et)
    EditText cardNo_et;
    @BindView(R.id.customerName_et)
    EditText customerName_et;
    @BindView(R.id.customerID_et)
    EditText customerID_et;
    @BindView(R.id.phone_et)
    EditText phone_et;
    @BindView(R.id.repayDateType_sp)//最后还款日
    MaterialSpinner repayDateType_sp;
    @BindView(R.id.appointed_day_rl)
    RelativeLayout appointed_day_rl;
    @BindView(R.id.appointed_day_et)
    EditText appointed_day_et;//指定還款天數
    @BindView(R.id.repayDate_et)//固定還款日
    EditText repayDate_et;
    @BindView(R.id.cardMedia_sp)
    MaterialSpinner cardMedia_sp;//卡介質
    @BindView(R.id.salesMan_et)
    EditText salesMan_et;

    @BindView(R.id.billDate_et)
    EditText billDate_et;

    @BindView(R.id.relCard_rtv)
    RoundTextView relCard_rtv;//四要素驗證
    @BindView(R.id.relcard_times_tv)
    TextView relcard_times_tv;//剩餘次數

    String cardSeqno,cardNo,customerName,customerID,phone,billDate,repayDateType,repayDate,salesMan,cardMedia;
    String bankCode="";


    //額度信息
    @BindViews({R.id.fixedLimit_et,R.id.availableAmt_et,R.id.currentRepayAmt_et,R.id.initAmt_et,R.id.tempLimit_et,R.id.tempLimitDate_et})
    EditText[] LimitEts;

    String fixedLimit,availableAmt,currentRepayAmt,initAmt,tempLimit,tempLimitDate;

    //規劃策略
    @BindView(R.id.isHolidayPlan_sp)
    MaterialSpinner isHolidayPlan_sp;
    @BindView(R.id.isFreePlan_sp)
    MaterialSpinner isFreePlan_sp;
    @BindView(R.id.freePlanRate_et)
    EditText freePlanRate_et;
    @BindView(R.id.freePlanRate_cd)
    CardView freePlanRate_cd;

    String isHolidayPlan,isFreePlan,freePlanRate;

    //服務費用
    @BindView(R.id.serviceType_sp)
    MaterialSpinner serviceType_sp;
    @BindViews({R.id.serviceAmt_et,R.id.serviceRate_et,R.id.serviceStartDate_et,R.id.serviceEndDate_et,R.id.paidAmt_et})
    EditText[] setviceEts;

    String serviceType,serviceAmt,serviceRate,serviceStartDate,serviceEndDate,paidAmt;

    //分期设置
    @BindView(R.id.aging_setting_rtv)
    RoundTextView aging_setting_rtv;
    @BindView(R.id.aging_rv)
    RecyclerView aging_rv;
    @BindView(R.id.AllpartSeqno_et)
    EditText AllpartSeqno_et;
    @BindView(R.id.AllpartTotalAmt_et)
    EditText AllpartTotalAmt_et;
    @BindView(R.id.aging_emty_tv)
    TextView aging_emty_tv;
    @BindView(R.id.total_aging_size_cd)
    CardView total_aging_size_cd;
    @BindView(R.id.total_aging_amt_cd)
    CardView total_aging_amt_cd;

    String stagesList;//分期信息


    //網銀信息
    @BindViews({R.id.loginUsername_et,R.id.loginPasswd_et,R.id.tranPasswd_et,R.id.queryPasswd_et})
    EditText[] ebankInfoEt;
    @BindView(R.id.isHaveKey_sp)
    MaterialSpinner isHaveKey_sp;

    String ebankinfo;//网银信息

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd");

    private View notDataView;
    AgingSettingDialog agingSettingDialog;


    List<AgingBean> agingBeanList=new ArrayList<>();
    AgingAdapter agingAdapter;

    SuccessProgressDialog successProgressDialog;

    DirtData dirtData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        EventBus.getDefault().register(this);//注册
        dirtData=new DirtData(this);
        initView();
    }

    private void initView(){

        successProgressDialog=new SuccessProgressDialog(this);

        bankName_tv.setVisibility(View.INVISIBLE);

        repayDateType_sp.setItems(dirtData.repayDateArr);
        repayDateType_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                    if(i==0){//固定还款日
                        appointed_day_rl.setVisibility(View.GONE);
                        repayDate_et.setVisibility(View.VISIBLE);
                    }else{//指定天数
                        appointed_day_rl.setVisibility(View.VISIBLE);
                        repayDate_et.setVisibility(View.GONE);
                    }
            }
        });

        cardMedia_sp.setItems(dirtData.cardMediaArr);

        isHolidayPlan_sp.setItems(dirtData.isHolidayArr);
        isHolidayPlan_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                    if(i==0&&isFreePlan_sp.getSelectedIndex()==0){
                        freePlanRate_cd.setVisibility(View.VISIBLE);
                    }else{
                        freePlanRate_cd.setVisibility(View.GONE);
                    }
            }
        });

        isFreePlan_sp.setItems(dirtData.isFreePlanArr);
        isFreePlan_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if(i==0&&isHolidayPlan_sp.getSelectedIndex()==0){
                    freePlanRate_cd.setVisibility(View.VISIBLE);
                }else{
                    freePlanRate_cd.setVisibility(View.GONE);
                }
            }
        });

        serviceType_sp.setItems(dirtData.serviceTypeArr);
        serviceType_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {

            }
        });

        isHaveKey_sp.setItems(dirtData.isHaveKeyArr);
        isHaveKey_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {

            }
        });

        //分期设置
        aging_rv.setLayoutManager(new LinearLayoutManager(this));
        agingAdapter=new AgingAdapter(agingBeanList);
        aging_rv.setAdapter(agingAdapter);
    }

    @OnClick({R.id.title_back_tv,
            R.id.base_info_rl,R.id.lines_rl,R.id.plan_rl,R.id.service_cost_rl,R.id.aging_rl,R.id.ebank_rl,
            R.id.billDate_et,R.id.repayDate_et,R.id.relCard_rtv,R.id.tempLimitDate_et,
            R.id.serviceStartDate_et,R.id.serviceEndDate_et,
            R.id.aging_setting_rtv,
            R.id.cancel_insert_rtv,R.id.insert_card_rtv})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                cancel();
                break;

            case R.id.base_info_rl://基本信息
                showOrHide(llArr[0],arrowIvs[0],isShowBaseArrow);
                isShowBaseArrow=!isShowBaseArrow;
                break;

            case R.id.lines_rl://额度信息
                showOrHide(llArr[1],arrowIvs[1],isShowLinesArrow);
                isShowLinesArrow=!isShowLinesArrow;
                break;

            case R.id.plan_rl://规划策略
                showOrHide(llArr[2],arrowIvs[2],isShowPlanArow);
                isShowPlanArow=!isShowPlanArow;
                break;

            case R.id.service_cost_rl://服务费用
                showOrHide(llArr[3],arrowIvs[3],isShowServiceArrow);
                isShowServiceArrow=!isShowServiceArrow;
                break;

            case R.id.aging_rl://分期付款
                showOrHide(llArr[4],arrowIvs[4],isShowAging);
                isShowAging=!isShowAging;
                break;

            case R.id.ebank_rl://网银信息
                showOrHide(llArr[5],arrowIvs[5],isShowEbank);
                isShowEbank=!isShowEbank;
                break;

            case R.id.billDate_et:
                mTimePikerView = TimerPikerTools.creatTimePickerView(AddCardActivity.this, "选择账单日", false, false, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        billDate = simpleDateFormat2.format(date);
                        billDate_et.setText(billDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.repayDate_et:
                mTimePikerView = TimerPikerTools.creatTimePickerView(AddCardActivity.this, "选择还款日", false, false, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        repayDate = simpleDateFormat2.format(date);
                        repayDate_et.setText(repayDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.relCard_rtv://四要素驗證
                relCard();
                break;

            case R.id.tempLimitDate_et:
                mTimePikerView = TimerPikerTools.creatTimePickerView(AddCardActivity.this, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tempLimitDate = simpleDateFormat1.format(date);
                        LimitEts[5].setText(tempLimitDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.serviceStartDate_et:
                mTimePikerView = TimerPikerTools.creatTimePickerView(AddCardActivity.this, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        serviceStartDate = simpleDateFormat1.format(date);
                        setviceEts[2].setText(serviceStartDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.serviceEndDate_et:
                mTimePikerView = TimerPikerTools.creatTimePickerView(AddCardActivity.this, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        serviceEndDate = simpleDateFormat1.format(date);
                        setviceEts[3].setText(serviceEndDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.aging_setting_rtv:
                agingSettingDialog=new AgingSettingDialog(AddCardActivity.this, R.style.Dialog, new AgingSettingDialog.AgingListener() {
                    @Override
                    public void sure(AgingBean agingBean) {
                        double Allamt = 0;
                        agingBeanList.add(agingBean);
                        Log.i("lys","agingBeanList.size() is:"+agingBeanList.size());
                        aging_emty_tv.setVisibility(View.GONE);
                        agingAdapter.notifyDataSetChanged();
                        AllpartSeqno_et.setText(agingBeanList.size()+"");
                        for(AgingBean bean:agingBeanList){
                            Allamt+=Double.valueOf(bean.getAmt());
                        }
                        AllpartTotalAmt_et.setText(Allamt+"");
                    }
                });
                agingSettingDialog.show();
                break;

            case R.id.cancel_insert_rtv:
                cancel();
                break;

            case R.id.insert_card_rtv:
                insertCard();
                break;
        }
    }

    private void showOrHide(LinearLayout linearLayout, ImageView arrow_iv,boolean isShowLayout){
        if(isShowLayout){
            linearLayout.setVisibility(View.VISIBLE);
            arrow_iv.setImageResource(R.mipmap.icon_arrow_down);
        }else{
            linearLayout.setVisibility(View.GONE);
            arrow_iv.setImageResource(R.mipmap.icon_arrow_up);
        }
    }


    //取消
    private void cancel(){
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
    }

    //四要素验证
    private void  relCard(){
        if(cardNo_et.getText().toString().trim().equals("")){
            Toast.makeText(AddCardActivity.this,"请填写卡号",Toast.LENGTH_SHORT).show();
        }else if(customerName_et.getText().toString().trim().equals("")){
            Toast.makeText(AddCardActivity.this,"请填写持卡人姓名",Toast.LENGTH_SHORT).show();
        }else if(customerID_et.getText().toString().trim().equals("")){
            Toast.makeText(AddCardActivity.this,"请填写持卡人身份证号码",Toast.LENGTH_SHORT).show();
        }else if(phone_et.getText().toString().trim().equals("")){
            Toast.makeText(AddCardActivity.this,"请填写银行预留手机号码",Toast.LENGTH_SHORT).show();
        }else {
            mPresenter.relCard(cardNo_et.getText().toString().trim(),customerName_et.getText().toString().trim(),
                    customerID_et.getText().toString().trim(),phone_et.getText().toString().trim(),dirtData.cardMediaOptions[cardMedia_sp.getSelectedIndex()]
                    );
        }

    }

    //添加卡片
    private void insertCard(){
        if(cardSeqno_et.getText().toString().trim().equals("")){
            Toast.makeText(AddCardActivity.this,"请填写卡位",Toast.LENGTH_SHORT).show();
        } else if(cardNo_et.getText().toString().trim().equals("")){
            Toast.makeText(AddCardActivity.this,"请填写卡号",Toast.LENGTH_SHORT).show();
        }else if(customerName_et.getText().toString().trim().equals("")){
            Toast.makeText(AddCardActivity.this,"请填写持卡人姓名",Toast.LENGTH_SHORT).show();
        }else if(customerID_et.getText().toString().trim().equals("")){
            Toast.makeText(AddCardActivity.this,"请填写持卡人身份证号码",Toast.LENGTH_SHORT).show();
        }else if(phone_et.getText().toString().trim().equals("")){
            Toast.makeText(AddCardActivity.this,"请填写银行预留手机号码",Toast.LENGTH_SHORT).show();
        }else if(billDate_et.getText().toString().trim().equals("")){
            Toast.makeText(AddCardActivity.this,"请选择账单日",Toast.LENGTH_SHORT).show();
        }else {
            getParams();
            mPresenter.insertCard(cardNo,cardSeqno,bankCode,customerName,customerID,phone,
                    billDate,repayDateType,repayDate,salesMan,
                    Double.valueOf(fixedLimit)*100+"",
                    Double.valueOf(availableAmt)*100+"",
                    Double.valueOf(currentRepayAmt)*100+"",
                    Double.valueOf(initAmt)*100+"",
                    Double.valueOf(tempLimit)+"",
                    tempLimitDate,
                    isHolidayPlan,isFreePlan,
                    Double.valueOf(freePlanRate)/100+"",
                    serviceType, Double.valueOf(serviceAmt)*100+"",
                    Double.valueOf(serviceRate)/100+"",
                    serviceStartDate,serviceEndDate,
                    Double.valueOf(paidAmt)*100+"",
                    ebankinfo,
                    stagesList,cardMedia);
        }
    }

    //获取参数
    private void getParams(){
        cardNo=cardNo_et.getText().toString().trim();
        cardSeqno=cardSeqno_et.getText().toString().trim();
        bankCode="";
        customerName=customerName_et.getText().toString().trim();
        customerID= customerID_et.getText().toString().trim();
        phone=phone_et.getText().toString().trim();
        billDate=billDate_et.getText().toString().trim();
        repayDateType=dirtData.repayDateOptions[repayDateType_sp.getSelectedIndex()];
        if(repayDateType.equals("FIXED")){
            repayDate=repayDate_et.getText().toString().trim();
        }else{
            repayDate=appointed_day_et.getText().toString().trim();
        }
        salesMan=salesMan_et.getText().toString().trim();
        cardMedia=dirtData.cardMediaOptions[cardMedia_sp.getSelectedIndex()];

        fixedLimit=LimitEts[0].getText().toString().trim();
        availableAmt=LimitEts[1].getText().toString().trim();
        currentRepayAmt=LimitEts[2].getText().toString().trim();
        initAmt=LimitEts[3].getText().toString().trim();
        tempLimit=LimitEts[4].getText().toString().trim();
        tempLimitDate=LimitEts[5].getText().toString().trim().replace("-","");

        isHolidayPlan=dirtData.isHolidayOptions[isHolidayPlan_sp.getSelectedIndex()];
        isFreePlan=dirtData.isFreePlanOptions[isFreePlan_sp.getSelectedIndex()];
        freePlanRate=freePlanRate_et.getText().toString().trim();

        serviceType=dirtData.serviceTypeOptions[serviceType_sp.getSelectedIndex()];
        serviceAmt=setviceEts[0].getText().toString().trim();
        serviceRate=setviceEts[1].getText().toString().trim();
        serviceStartDate=setviceEts[2].getText().toString().trim().replace("-","");
        serviceEndDate=setviceEts[3].getText().toString().trim().replace("-","");
        paidAmt=setviceEts[4].getText().toString().trim();

        ebankinfo=getEbankInfo();
        stagesList=getStagesList();
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
    public void insertCardSuccess() {
        successProgressDialog.showDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                successProgressDialog.dismiss();
                finish();
            }
        },1500);
    }

    @Override
    public void verifyResult(VerfyBean data) {

        relcard_times_tv.setText("剩余次数："+data.getTimes()+"");
        String result="";
        if(data.getValidateStatus().equals("01")){
            result="通过";
        }else if(data.getValidateStatus().equals("02")){
            result="不通过";
        }else if(data.getValidateStatus().equals("03")){
            result="不确定";
        }else if(data.getValidateStatus().equals("04")){
            result="失败";
        }

        final MaterialDialog mMaterialDialog = new MaterialDialog(AddCardActivity.this);
        mMaterialDialog.setTitle("卡片验证");
        mMaterialDialog.setMessage("四要素验证结果："+result);
        mMaterialDialog.setPositiveButton("确定",new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();
            }
        });
        mMaterialDialog.show();
    }

    //獲取分期設置信息
    private String getStagesList(){

        if(null == agingBeanList){
            return "";
        }
        try {
        JSONArray json = new JSONArray();
        for(AgingBean a : agingBeanList){
            JSONObject jo = new JSONObject();
            jo.put("partTotalAmt", a.getPartTotalAmt()+"");
            jo.put("partSeqno", a.getPartSeqno()+"");
            jo.put("startTime", a.getStartTime()+"");
            jo.put("endTime", a.getEndTime()+"");
            jo.put("amt", a.getAmt()+"");
            jo.put("fee", a.getFee()+"");
            jo.put("isRepaied", a.getIsRepaied()+"");
            jo.put("createUser", a.getCreateUser()+"");
            json.put(jo);
        }
        return json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    //获取网银信息
    private String getEbankInfo(){
        ebankinfo=ebankInfoEt[0].getText().toString().trim()+"|"+ebankInfoEt[1].getText().toString().trim()+"|"+
                ebankInfoEt[2].getText().toString().trim()+"|"+ebankInfoEt[3].getText().toString().trim()+"|";
        if(isHaveKey_sp.getSelectedIndex()!=0){
            ebankinfo+=dirtData.isHaveKeyOptions[isHaveKey_sp.getSelectedIndex()];
        }else{
            ebankinfo+="";
        }
        return AESUtil.encrypt(ebankinfo, Config.AES);
    }

    //ui主线程中执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(DeleteAdingMessage msg) {
        double Allamt=0;
       agingBeanList.remove(msg.getPosition());
        agingAdapter=new AgingAdapter(agingBeanList);
        aging_rv.setAdapter(agingAdapter);
        AllpartSeqno_et.setText(agingBeanList.size()+"");
        for(AgingBean bean:agingBeanList){
            Allamt+=Double.valueOf(bean.getAmt());
        }
        AllpartTotalAmt_et.setText(Allamt+"");

       if(agingBeanList.isEmpty())
           aging_emty_tv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解除注册
    }

}
