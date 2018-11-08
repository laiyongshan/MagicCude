package com.rflash.magiccube.ui.cardmanager.cardbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.flyco.roundview.RoundTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.newmain.DirtData;
import com.rflash.magiccube.ui.renewal.RenewalActivity;
import com.rflash.magiccube.util.AESUtil;
import com.rflash.magiccube.util.ToolUtils;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * @author lys
 * @time 2018/10/11 16:05
 * @desc:卡片详情
 */

public class CardBaseInforActivity extends MVPBaseActivity<CardBaseInfoContract.View,CardBaseInfoPresenter> implements CardBaseInfoContract.View,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.cancel_rtv)
    RoundTextView cancel_rtv;

    @BindView(R.id.updata_card_rtv)
    RoundTextView updata_card_rtv;

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.bankAndnum_tv)
    TextView bankAndnum_tv;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.card_renewal_tv)
    RoundTextView card_renewal_tv;

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
    @BindView(R.id.billDate_et)
    EditText billDate_et;
    @BindView(R.id.repayDateType_sp)
    MaterialSpinner repayDateType_sp;
    RelativeLayout appointed_day_rl;
    @BindView(R.id.appointed_day_et)
    EditText appointed_day_et;//指定還款天數
    @BindView(R.id.repayDate_et)
    TextView repayDate_et;
    @BindView(R.id.salesMan_et)
    EditText salesMan_et;
    @BindView(R.id.cardMedia_sp)
    MaterialSpinner cardMedia_sp;


    @BindView(R.id.verify_ll)
    LinearLayout verify_ll;

    //额度信息
    @BindViews({R.id.fixedLimit_et,R.id.availableAmt_et,R.id.currentRepayAmt_et,R.id.initAmt_et,R.id.tempLimit_et,R.id.tempLimitDate_et})
    EditText[] linesEts;

    //规划策略
    @BindView(R.id.isHolidayPlan_sp)
    MaterialSpinner isHolidayPlan_sp;
    @BindView(R.id.isFreePlan_sp)
    MaterialSpinner isFreePlan_sp;
    @BindView(R.id.freePlanRate_et)
    EditText freePlanRate_et;

    //服务费用
    @BindView(R.id.serviceType_sp)
    MaterialSpinner serviceType_sp;
    @BindViews({R.id.serviceAmt_et,R.id.serviceRate_et,R.id.serviceStartDate_et,R.id.serviceEndDate_et,R.id.paidAmt_et})
    EditText[] serviceEts;

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

    //网银信息
    @BindViews({R.id.loginUsername_et,R.id.loginPasswd_et,R.id.tranPasswd_et,R.id.queryPasswd_et})
    EditText[] ebankEts;
    @BindView(R.id.isHaveKey_sp)
    MaterialSpinner isHaveKey_sp;

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");

    String cardNo="";
    CardBean.ResultBean cardDetailBean;

    DirtData dirtData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_base_info);
        initView();
        dirtData=new DirtData(this);
        getCardInfo(cardNo);
    }

    private void initView(){
        cardDetailBean= (CardBean.ResultBean) getIntent().getSerializableExtra("cardDetail");
        cardNo=cardDetailBean.getCardNo();
        bankAndnum_tv.setText("("+cardDetailBean.getCardBankName()+cardDetailBean.getCardNo().substring(cardNo.length()-4)+")");

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        verify_ll.setVisibility(View.GONE);

        aging_setting_rtv.setVisibility(View.GONE);
        aging_emty_tv.setVisibility(View.VISIBLE);
        total_aging_size_cd.setVisibility(View.GONE);
        total_aging_amt_cd.setVisibility(View.GONE);
    }

    @OnClick({R.id.title_back_tv,R.id.card_renewal_tv,R.id.base_info_rl,R.id.lines_rl,R.id.plan_rl,R.id.service_cost_rl,R.id.aging_rl,R.id.ebank_rl,
        R.id.updata_card_rtv,R.id.cancel_rtv
    })
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.card_renewal_tv:
                ActivityIntent.readyGo(CardBaseInforActivity.this, RenewalActivity.class);
                break;

            case R.id.cancel_rtv://取消修改
                final MaterialDialog mMaterialDialog = new MaterialDialog(CardBaseInforActivity.this);
                mMaterialDialog.setTitle("提示");
                mMaterialDialog.setMessage("确定离开该页面？");
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

            case R.id.updata_card_rtv://确定修改

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

    private void getCardInfo(String cardNo){
        mPresenter.queryCardDetail(cardNo);
    }

    @Override
    public void onRefresh() {
        getCardInfo(cardNo);
    }

    @Override
    public void showRefresh() {
        refresh_layout.setRefreshing(true);
    }

    @Override
    public void finishRefresh() {
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void getDataFail(String msg) {
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void getDataSuccess(BaseInfoBean response) {
        if(response!=null){
            bindData(response.getResult());
        }
    }

    @Override
    public void verifyResult() {

    }

    private void bindData(BaseInfoBean.ResultBean resultBean){
        cardSeqno_et.setText(resultBean.getCardSeqno()+"");
        bankName_tv.setText(resultBean.getCardBankName()+"");
        cardNo_et.setText(resultBean.getCardNo()+"");
        customerName_et.setText(resultBean.getCustomerName()+"");
        customerID_et.setText(resultBean.getCustomerID()+"");
        phone_et.setText(resultBean.getPhone()+"");
        billDate_et.setText(resultBean.getBillDate()+"");
        if(resultBean.getRepayDateType().equals("FIXED")){//固定还款日
            repayDate_et.setText(resultBean.getRepayDate()+"");
        }else if(resultBean.getRepayDateType().equals("APPOINTED_DAYS")){//指定天数
            appointed_day_et.setText(resultBean.getRepayDate()+"");
        }
//        salesMan_tv.setText(resultBean.getSalesMan()+"");
//        cardMedia_et.setText(resultBean.getCardMedia()+"");
        if(resultBean.getRepayDateType().equals("FIXED")) {
            repayDateType_sp.setItems(dirtData.repayDateArr[0]);
        }else if(resultBean.getRepayDateType().equals("APPOINTED_DAYS")){
            repayDateType_sp.setItems(dirtData.repayDateArr[1]);
        }
        if(resultBean.getCardMedia().equals("IC_CARD")) {
            cardMedia_sp.setItems(dirtData.cardMediaArr[0]);
        }else{
            cardMedia_sp.setItems(dirtData.cardMediaArr[1]);
        }
        if(resultBean.getIsHolidayPlan().equals("Y")) {
            isHolidayPlan_sp.setItems(dirtData.isHolidayArr[0]);
        }else{
            isHolidayPlan_sp.setItems(dirtData.isHolidayArr[1]);
        }
        if(resultBean.getIsFreePlan().equals("Y")) {
            isFreePlan_sp.setItems(dirtData.isFreePlanArr[0]);
        }else{
            isFreePlan_sp.setItems(dirtData.isFreePlanArr[1]);
        }
        if(resultBean.getServiceType().equals(dirtData.serviceTypeOptions[0])) {
            serviceType_sp.setItems(dirtData.serviceTypeArr[0]);
        }else if(resultBean.getServiceType().equals(dirtData.serviceTypeOptions[1])){
            serviceType_sp.setItems(dirtData.serviceTypeArr[1]);
        }else if(resultBean.getServiceType().equals(dirtData.serviceTypeOptions[2])){
            serviceType_sp.setItems(dirtData.serviceTypeArr[2]);
        }
        if(resultBean.getIsHaveKey().equals("Y")) {
            isHaveKey_sp.setItems(dirtData.isHaveKeyArr[1]);
        }else if(resultBean.getIsHaveKey().equals("N")){
            isHaveKey_sp.setItems(dirtData.isHaveKeyArr[2]);
        }else{
            isHaveKey_sp.setItems(dirtData.isHaveKeyArr[0]);
        }

        linesEts[0].setText(resultBean.getFixedLimit()/100+"");
        linesEts[1].setText(resultBean.getAvailableAmt()/100+"");
        linesEts[2].setText(resultBean.getCurrentRepayAmt()/100+"");
        linesEts[3].setText(resultBean.getInitAmt()/100+"");
        linesEts[4].setText(resultBean.getTempLimit()/100+"");
//        linesEts[5].setText(resultBean.getT);

        //规划策略
        freePlanRate_et.setText(getFloatValue(resultBean.getFreePlanRate())*100+"");

        //服务费用
        serviceEts[0].setText(resultBean.getServiceAmt()+"");
        serviceEts[1].setText(resultBean.getServiceRate()+"");
        serviceEts[2].setText(resultBean.getServiceStartDate()+"");
        serviceEts[3].setText(resultBean.getServiceEndDate()+"");
        serviceEts[4].setText(resultBean.getPaidAmt()/100+"");

        //分期设置

        //网银信息
        ebankEts[0].setText(AESUtil.decrypt(resultBean.getLoginUsername()+"",Config.AES));
        ebankEts[1].setText(AESUtil.decrypt(resultBean.getLoginPasswd()+"",Config.AES));
        ebankEts[2].setText(AESUtil.decrypt(resultBean.getTranPasswd()+"",Config.AES));
        ebankEts[3].setText(AESUtil.decrypt(resultBean.getQueryPasswd()+"",Config.AES));
    }

    private float getFloatValue(String value){
        Float f = null;
        try {
            f=Float.valueOf(value);
            return f;
        }catch (Exception e){
        }
        return 0;
    }

}
