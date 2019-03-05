package com.rflash.magiccube.ui.cardmanager.cardcharge;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.util.TimerPikerTools;
import com.rflash.magiccube.util.ToolUtils;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/11 16:08
 * @desc:
 */

public class CardChargeActivity extends MVPBaseActivity<CardChargeContract.View,CardChargePresenter> implements CardChargeContract.View,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.charge_drawelayout)
    DrawerLayout charge_drawelayout;

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.bankAndnum_tv)
    TextView bankAndnum_tv;

    @BindView(R.id.filtrate_img)
    ImageView filtrate_img;

    @BindView(R.id.clear_filter_tv)
    TextView clear_filter_tv;

    @BindView(R.id.sure_filter_tv)
    TextView sure_filter_tv;

    @BindView(R.id.service_start_time_tv)
    TextView service_start_time_tv;

    private View notDataView;

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.card_chaege_rv)
    RecyclerView card_chaege_rv;
    CardChargeAdapter cardChargeAdapter;
    List<CardChargerBean.ResultBean> cardChargerBeanList =new ArrayList<>();

    String cardNo;
    String startTime;
    int pageNum=1;

    private int TOTAL_COUNTER ; //所有的数据总数

    CardBean.ResultBean cardDetailBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_charge);

        initView();

        queryCardfee();
    }

    private void initView(){

        cardDetailBean= (CardBean.ResultBean) getIntent().getSerializableExtra("cardDetail");
        cardNo=cardDetailBean.getCardNo();

        bankAndnum_tv.setText("("+cardDetailBean.getCardBankName()+cardNo.substring(cardNo.length()-4)+")");

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) card_chaege_rv.getParent(), false);


        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        card_chaege_rv.setLayoutManager(new LinearLayoutManager(this));
        cardChargeAdapter=new CardChargeAdapter(cardChargerBeanList);
        cardChargeAdapter.setOnLoadMoreListener(this,card_chaege_rv);
        cardChargeAdapter.disableLoadMoreIfNotFullPage();
        card_chaege_rv.setAdapter(cardChargeAdapter);
    }

    @OnClick({R.id.title_back_tv,R.id.filtrate_img,R.id.service_start_time_tv,R.id.clear_filter_tv,R.id.sure_filter_tv})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.filtrate_img:
                charge_drawelayout.openDrawer(Gravity.RIGHT);
                break;

            case R.id.service_start_time_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView(CardChargeActivity.this, "服务开始时间", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        startTime = simpleDateFormat.format(date).replace("-","");
                        service_start_time_tv.setText(simpleDateFormat.format(date) + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.clear_filter_tv:
                startTime="";
                service_start_time_tv.setText(startTime + "");
                break;

            case R.id.sure_filter_tv:
                pageNum=1;
                queryCardfee();
                charge_drawelayout.closeDrawer(Gravity.RIGHT);
                break;
        }

    }

    private void queryCardfee(){
        mPresenter.queryCardfee(cardNo,startTime,pageNum+"");
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        queryCardfee();
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
    public void getDataSuccess(CardChargerBean response) {
        if(response!=null){
            TOTAL_COUNTER=response.getTotalNum();
            if(pageNum==1){
                cardChargerBeanList=response.getResult();
                cardChargeAdapter.setNewData(response.getResult());
                cardChargeAdapter.notifyDataSetChanged();
                if(response.getResult().isEmpty())
                    cardChargeAdapter.setEmptyView(notDataView);
            }else{
                cardChargerBeanList.addAll(response.getResult());
                cardChargeAdapter.addData(response.getResult());
                cardChargeAdapter.notifyDataSetChanged();
                cardChargeAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void onLoadMoreRequested() {
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (cardChargeAdapter.getData().size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    cardChargeAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    queryCardfee();
                }
                if(refresh_layout!=null)
                    refresh_layout.setEnabled(true);
            }
        },1500);
    }
}
