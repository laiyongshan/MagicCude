package com.rflash.magiccube.ui.cardmanager.cardbill;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.basemodule.BaseActivity;
import com.rflash.magiccube.R;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.cardmanager.increase.AddIncreaseActivity;
import com.rflash.magiccube.ui.cardmanager.increase.CardIncreaseAdapter;
import com.rflash.magiccube.ui.refund.RefundBean;
import com.rflash.magiccube.util.TimerPikerTools;
import com.rflash.magiccube.util.ToolUtils;
import com.rflash.magiccube.view.SuccessProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/11 16:05
 * @desc:
 */

public class CardBillActivity extends MVPBaseActivity<CardBillContract.View,CardBillPresenter> implements CardBillContract.View,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.bankAndnum_tv)
    TextView bankAndnum_tv;

    @BindView(R.id.cardbill_drawerlayout)
    DrawerLayout cardbill_drawerlayout;

    @BindView(R.id.filtrate_img)
    ImageView filtrate_img;

    @BindView(R.id.bill_month_tv)
    TextView bill_month_tv;

    @BindView(R.id.clear_filter_tv)
    TextView clear_filter_tv;

    @BindView(R.id.sure_filter_tv)
    TextView sure_filter_tv;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.card_bill_rv)
    RecyclerView card_bill_rv;

    SuccessProgressDialog successProgressDialog;

    String cardNo="";
    String billMonth="";
    String available="";
    int pageNum=1;
    int TOTAL_COUNTER;

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

    CardBillAdapter cardBillAdapter;
    List<CardBillBean.ResultBean> cardBillBeanList=new ArrayList<>();

    CardBean.ResultBean cardDetailBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_bill);
        initView();

        queryBill();
    }

    private void initView(){

        cardDetailBean= (CardBean.ResultBean) getIntent().getSerializableExtra("cardDetail");
        cardNo=cardDetailBean.getCardNo();
        bankAndnum_tv.setText("("+cardDetailBean.getCardBankName()+cardDetailBean.getCardNo().substring(cardNo.length()-4)+")");

        refreshLayout.setColorSchemeColors(ToolUtils.Colors);
        refreshLayout.setOnRefreshListener(this);

        successProgressDialog=new SuccessProgressDialog(this);

        card_bill_rv.setLayoutManager(new LinearLayoutManager(this));

        cardBillAdapter=new CardBillAdapter(cardBillBeanList,mPresenter);
        cardBillAdapter.setOnLoadMoreListener(this,card_bill_rv);
        cardBillAdapter.disableLoadMoreIfNotFullPage();
        card_bill_rv.setAdapter(cardBillAdapter);
    }

    @OnClick({R.id.title_back_tv,R.id.filtrate_img,R.id.bill_month_tv,R.id.clear_filter_tv,R.id.sure_filter_tv})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.filtrate_img:
                cardbill_drawerlayout.openDrawer(Gravity.RIGHT);
                break;

            case R.id.bill_month_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView(CardBillActivity.this, "选择时间", true, true, false, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        billMonth = simpleDateFormat.format(date);
                        bill_month_tv.setText(billMonth + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.clear_filter_tv:
                billMonth="";
                bill_month_tv.setText("");
                break;

            case R.id.sure_filter_tv:
                queryBill();
                cardbill_drawerlayout.closeDrawer(Gravity.RIGHT);
                break;
        }
    }

    private void queryBill(){
        mPresenter.queryBill(cardNo,billMonth,available,pageNum+"");
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        queryBill();
    }

    @Override
    public void showRefresh() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void finishRefresh() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void getDataFail(String msg) {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void getDataSuccess(CardBillBean response) {
        if (response != null) {
            TOTAL_COUNTER = response.getTotalNum();
            cardBillBeanList.clear();
            cardBillBeanList=response.getResult();
            if (pageNum == 1) {
                cardBillAdapter.setNewData(cardBillBeanList);
            } else {
                cardBillAdapter.addData(cardBillBeanList);
                cardBillAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void updateCardBillSuccess() {
        successProgressDialog.showDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                successProgressDialog.dismiss();
                pageNum=1;
                queryBill();
            }
        },1500);

    }


    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (cardBillAdapter.getData().size() >= TOTAL_COUNTER) {
                    pageNum=0;
                    //数据全部加载完毕
                    cardBillAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    queryBill();
                }
                refreshLayout.setEnabled(true);
            }
        },1500);
    }
}
