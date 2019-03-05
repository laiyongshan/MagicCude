package com.rflash.magiccube.ui.cardmanager.increase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.addcard.AddCardActivity;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.cardmanager.cardcharge.CardChargeAdapter;
import com.rflash.magiccube.ui.cardmanager.cardcharge.CardChargerBean;
import com.rflash.magiccube.ui.cardmanager.carddetail.CardDetailActivity;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/11 16:07
 * @desc:
 */

public class CardIncreaseActivity extends MVPBaseActivity<CardIncreaseContract.View,CardIncreasePresenter> implements CardIncreaseContract.View, SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.bankAndnum_tv)
    TextView bankAndnum_tv;


    @BindView(R.id.add_increase_iv)
    ImageView add_increase_iv;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.card_increase_rv)
    RecyclerView card_increase_rv;

    private View notDataView;

    String cardNo="";
    String LIMIT_UP="LIMIT_UP";
    String LIMIT_DOWN="LIMIT_DOWN";
    String chageType="";
    int pageNum=1;

    private int UP_TOTAL_COUNTER ; //提额的所有的数据总数
    private int DOWN_TOTAL_COUNTER;//降额的所有的数据总数

    CardIncreaseAdapter cardIncreaseAdapter;
    List<IncreaseBean.ResultBean> increaseBeanList=new ArrayList<>() ;

    CardBean.ResultBean cardDetailBean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_increase);
        initView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        chageType=LIMIT_UP;
        pageNum=1;
        queryLimitChange(LIMIT_UP);
    }

    private void initView(){

        cardDetailBean= (CardBean.ResultBean) getIntent().getSerializableExtra("cardDetail");
        cardNo=cardDetailBean.getCardNo();

        bankAndnum_tv.setText("("+cardDetailBean.getCardBankName()+cardDetailBean.getCardNo().substring(cardNo.length()-4)+")");

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) card_increase_rv.getParent(), false);

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);
        card_increase_rv.setLayoutManager(new LinearLayoutManager(this));

        cardIncreaseAdapter=new CardIncreaseAdapter(increaseBeanList);
        cardIncreaseAdapter.setOnLoadMoreListener(this,card_increase_rv);
        cardIncreaseAdapter.disableLoadMoreIfNotFullPage();
        card_increase_rv.setAdapter(cardIncreaseAdapter);
    }

    @OnClick({R.id.title_back_tv,R.id.add_increase_iv})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.add_increase_iv:
                Intent intent=new Intent(CardIncreaseActivity.this,AddIncreaseActivity.class);
                intent.putExtra("cardDetail",cardDetailBean);
                startActivity(intent);
                break;
        }
    }

    private void queryLimitChange(String changeType){
        mPresenter.queryLimitChange(cardNo,changeType,pageNum+"");
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        chageType=LIMIT_UP;
        queryLimitChange(LIMIT_UP);
    }

    @Override
    public void onLoadMoreRequested() {
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(chageType.equals(LIMIT_UP)) {
                    if (cardIncreaseAdapter.getData().size() >= UP_TOTAL_COUNTER) {
                        pageNum = 1;
                        chageType = LIMIT_DOWN;
                        queryLimitChange(LIMIT_DOWN);
                    } else {
                        //获取更多数据
                        pageNum++;
                        queryLimitChange(LIMIT_UP);
                    }
                }else if(chageType.equals(LIMIT_DOWN)){
                    if (cardIncreaseAdapter.getData().size() >=UP_TOTAL_COUNTER+DOWN_TOTAL_COUNTER) {
                        //数据全部加载完毕
                            cardIncreaseAdapter.loadMoreEnd();
                    } else {
                        //获取更多数据
                        pageNum++;
                        queryLimitChange(LIMIT_DOWN);
                    }
                }
                if(refresh_layout!=null)
                    refresh_layout.setEnabled(true);
            }
        },1500);
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

    IncreaseBean increaseBean;
    @Override
    public void getDataSuccess(Object response) {
        if(response!=null){
            increaseBean= (IncreaseBean) response;
            if (chageType.equals(LIMIT_UP)) {
                UP_TOTAL_COUNTER = increaseBean.getTotalNum();
                if(pageNum==1){
                    cardIncreaseAdapter.setNewData(increaseBean.getResult());
                    cardIncreaseAdapter.notifyDataSetChanged();
                    if(increaseBean.getResult().isEmpty())
                        cardIncreaseAdapter.setEmptyView(notDataView);
                }else{
                    increaseBeanList.addAll(increaseBean.getResult());
                    cardIncreaseAdapter.addData(increaseBean.getResult());
                    cardIncreaseAdapter.loadMoreComplete();
                    Log.i("lys","addData");
                }
            }else if(chageType.equals(LIMIT_DOWN)){
                DOWN_TOTAL_COUNTER=increaseBean.getTotalNum();
                cardIncreaseAdapter.addData(increaseBean.getResult());
                cardIncreaseAdapter.loadMoreComplete();
                Log.i("lys","LIMIT_DOWN");
            }
        }
    }

    @Override
    public void increaseSuccess() {

    }

}
