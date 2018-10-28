package com.rflash.magiccube.ui.cardmanager.addplan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.magiccube.R;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.cardmanager.cardbase.CardBaseInforActivity;
import com.rflash.magiccube.ui.cardmanager.cardbill.CardBillActivity;
import com.rflash.magiccube.util.TimerPikerTools;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by lenovo on 2018/10/27.
 */

public class AddPlanActivity extends MVPBaseActivity<AddPlanContract.View,AddPlanPresenter> implements AddPlanContract.View{

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.bankAndnum_tv)
    TextView bankAndnum_tv;

    @BindView(R.id.tranType_sp)
    MaterialSpinner tranType_sp;

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

    CardBean.ResultBean cardDetailBean;
    String cardNo="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        initView();
    }

    private void initView(){
        cardDetailBean= (CardBean.ResultBean) getIntent().getSerializableExtra("cardDetail");
        cardNo=cardDetailBean.getCardNo();
        bankAndnum_tv.setText("("+cardDetailBean.getCardBankName()+cardDetailBean.getCardNo().substring(cardNo.length()-4)+")");
    }


    @OnClick({R.id.title_back_tv,R.id.add_plan_rtv,R.id.cancel_rtv})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.cancel_rtv://取消修改
                final MaterialDialog mMaterialDialog = new MaterialDialog(AddPlanActivity.this);
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
                break;

            case R.id.add_plan_rtv://确定修改

                break;
        }
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
    public void getResult(BaseBean respon) {

    }
}
