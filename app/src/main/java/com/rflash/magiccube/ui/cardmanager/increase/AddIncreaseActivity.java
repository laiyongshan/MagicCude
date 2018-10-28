package com.rflash.magiccube.ui.cardmanager.increase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.flyco.roundview.RoundTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.basemodule.BaseActivity;
import com.rflash.magiccube.R;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.util.TimerPikerTools;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/11 16:08
 * @desc:添加提额基本信息
 */

public class AddIncreaseActivity extends MVPBaseActivity<CardIncreaseContract.View,CardIncreasePresenter> implements CardIncreaseContract.View {

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.bankAndnum_tv)
    TextView bankAndnum_tv;

    @BindView(R.id.time_tv)
    TextView time_tv;

    @BindView(R.id.creater_et)
    EditText creater_et;

    @BindView(R.id.amt_et)
    EditText amt_et;

    @BindView(R.id.changeType_sp)
    MaterialSpinner changeType_sp;

    @BindView(R.id.clear_rtv)
    RoundTextView clear_rtv;

    @BindView(R.id.add_increase_rtv)
    RoundTextView add_increase_rtv;



    CardBean.ResultBean cardDetailBean;
    String cardNo="";
    String changeType="LIMIT_UP";
    long amt;//变动金额 单位 分
    String time="";//变动时间
    String createUser="";//创建人

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_increase);
        initView();
    }

    private void initView(){
        cardDetailBean= (CardBean.ResultBean) getIntent().getSerializableExtra("cardDetail");
        cardNo=cardDetailBean.getCardNo();
        bankAndnum_tv.setText("("+cardDetailBean.getCardBankName()+cardDetailBean.getCardNo().substring(cardNo.length()-4)+")");

        changeType_sp.setItems("提额","降额");
        changeType_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if(i==0)
                    changeType="LIMIT_UP";
                else
                    changeType="LIMIT_DOWN";
            }
        });


    }

    @OnClick({R.id.title_back_tv,R.id.time_tv,R.id.clear_rtv,R.id.add_increase_rtv})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.time_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView2(AddIncreaseActivity.this, "选择时间", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        time = simpleDateFormat.format(date);
                        time_tv.setText(time + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.clear_rtv:
                time_tv.setText("");
                creater_et.setText("");
                amt_et.setText("");
                break;

            case R.id.add_increase_rtv:
                if(amt_et.getText().toString().trim().equals("")||
                        time_tv.getText().toString().trim().equals("")||
                        creater_et.getText().toString().trim().equals("")){
                    Toast.makeText(AddIncreaseActivity.this,"请填写完整信息",Toast.LENGTH_SHORT).show();
                }else{
                    insertLimitChange();
                }
                break;
        }
    }

    private void insertLimitChange(){
        amt=Integer.parseInt(amt_et.getText().toString().trim())*100;
        createUser=creater_et.getText().toString();
        mPresenter.insertLimitChange(cardNo,time,changeType,amt+"",createUser);
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

    BaseBean baseBean;
    @Override
    public void getDataSuccess(Object response) {
        finish();
    }
}
