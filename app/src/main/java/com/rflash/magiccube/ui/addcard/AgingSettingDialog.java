package com.rflash.magiccube.ui.addcard;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.flyco.roundview.RoundTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.magiccube.R;
import com.rflash.magiccube.ui.newmain.DirtData;
import com.rflash.magiccube.util.TimerPikerTools;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2018/11/3.
 */

public class AgingSettingDialog extends Dialog {

    @BindViews({R.id.partTotalAmt_et,R.id.partSeqno_et,R.id.startTime_et,R.id.endTime_et,R.id.amt_et,R.id.fee_et,R.id.createUser_et,})
    EditText[] agingEts;

    @BindView(R.id.isRepaied_sp)
    MaterialSpinner isRepaied_sp;

    @BindView(R.id.cancel_aging_rtv)
    RoundTextView cancel_aging_rtv;

    @BindView(R.id.sure_aging_rtv)
    RoundTextView sure_aging_rtv;

    String startTime,endTime;

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");

    Context context;
    DirtData dirtData;

    AgingBean agingBean;

    interface AgingListener{
        void sure(AgingBean agingBean);
    }

    AgingListener agingListener;

    public AgingSettingDialog(@NonNull Context context, int themeResId,AgingListener agingListener) {
        super(context, themeResId);
        this.agingListener=agingListener;
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_aging_setting);
        ButterKnife.bind(this);
        dirtData=new DirtData(context);
        initView();
        setCanceledOnTouchOutside(false);
    }

    private void initView(){
        isRepaied_sp.setItems(dirtData.isOrNoArr);
    }

    @OnClick({R.id.cancel_aging_rtv,R.id.sure_aging_rtv,R.id.startTime_et,R.id.endTime_et})
    public void click(View view){
        switch (view.getId()){
            case R.id.cancel_aging_rtv:
                dismiss();
                break;

            case R.id.sure_aging_rtv:
                sureAging();
                dismiss();
                break;

            case R.id.startTime_et:
                mTimePikerView = TimerPikerTools.creatTimePickerView(context, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        startTime = simpleDateFormat.format(date);
                        agingEts[2].setText(startTime + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.endTime_et:
                mTimePikerView = TimerPikerTools.creatTimePickerView(context, "选择日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        endTime = simpleDateFormat.format(date);
                        agingEts[3].setText(endTime + "");
                    }
                });
                mTimePikerView.show();
                break;
        }
    }

    private void sureAging(){
        if(agingEts[0].getText().toString().trim().equals("")){
            Toast.makeText(context,"请填写分期总金额",Toast.LENGTH_SHORT).show();
        }else  if(agingEts[1].getText().toString().trim().equals("")){
            Toast.makeText(context,"请输入期数",Toast.LENGTH_SHORT).show();
        }else  if(agingEts[2].getText().toString().trim().equals("")){
            Toast.makeText(context,"请选择分期开始日期",Toast.LENGTH_SHORT).show();
        }else  if(agingEts[3].getText().toString().trim().equals("")){
            Toast.makeText(context,"请选择分期结束日期",Toast.LENGTH_SHORT).show();
        }else  if(agingEts[4].getText().toString().trim().equals("")){
            Toast.makeText(context,"请填写分期金额",Toast.LENGTH_SHORT).show();
        }else  if(agingEts[5].getText().toString().trim().equals("")){
            Toast.makeText(context,"请填写分期手续费",Toast.LENGTH_SHORT).show();
        }else  if(agingEts[6].getText().toString().trim().equals("")){
            Toast.makeText(context,"请填写录入人",Toast.LENGTH_SHORT).show();
        }else{
            agingBean=new AgingBean();
            agingBean.setPartTotalAmt(agingEts[0].getText().toString().trim());
            agingBean.setPartSeqno(agingEts[1].getText().toString().trim());
            agingBean.setStartTime(agingEts[2].getText().toString().trim());
            agingBean.setEndTime(agingEts[3].getText().toString().trim());
            agingBean.setAmt(agingEts[4].getText().toString().trim());
            agingBean.setFee(agingEts[5].getText().toString().trim());
            agingBean.setCreateUser(agingEts[6].getText().toString().trim());
            agingBean.setIsRepaied(dirtData.isOrNoOptions[isRepaied_sp.getSelectedIndex()]);
            agingListener.sure(agingBean);
        }
    }
}

