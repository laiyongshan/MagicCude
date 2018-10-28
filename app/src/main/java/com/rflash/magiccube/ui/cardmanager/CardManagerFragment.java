package com.rflash.magiccube.ui.cardmanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundTextView;
import com.rflash.basemodule.BaseFragment;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.addcard.AddCardActivity;
import com.rflash.magiccube.ui.cardmanager.carddetail.CardDetailActivity;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/11 15:15
 * @desc:
 */

public class CardManagerFragment extends MVPBaseFragment<CardManagerContract.View,CardManagerPresenter> implements  BaseQuickAdapter.RequestLoadMoreListener, CardManagerContract.View, SwipeRefreshLayout.OnRefreshListener{

    @BindViews({R.id.card_count_tv,R.id.availableAmt_tv,R.id.initAmt_tv,R.id.fixedLimit_tv})
    TextView[] countTvs;

    @BindView(R.id.card_option_tv)
    RoundTextView card_option_tv;

    @BindView(R.id.add_card_img)
    ImageView add_card_img;

    @BindView(R.id.filtrate_img)
    ImageView filtrate_img;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.card_manager_rv)
    RecyclerView card_manager_rv;

    @BindView(R.id.option_rl)
    RelativeLayout option_rl;

    @BindView(R.id.all_selected_cb)
    CheckBox all_selected_cb;

    @BindView(R.id.cardmanager_drawerLayout)
    DrawerLayout cardmanager_drawerLayout;

    String cardNo="";
    String cardSeqno="";
    String salesMan="";
    String billDate="";
    String repayDate="";
    String state="";
    int pageNum=1;
    String count="Y";

    CardManagerAdapter cardManagerAdapter;
    List<CardBean.ResultBean> cardBeanList=new ArrayList<>();

    boolean isOption;

    private int TOTAL_COUNTER ; //所有的数据总数


    @Override
    protected int getLayout() {
        return R.layout.fragment_card_mananger;
    }


    @Override
    protected void initView() {

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        queryCards();

        card_manager_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        cardManagerAdapter=new CardManagerAdapter(isOption,cardBeanList);
        cardManagerAdapter.setOnItemClickListener(onItemClickListener);
        cardManagerAdapter.setOnLoadMoreListener(this,card_manager_rv);
        cardManagerAdapter.disableLoadMoreIfNotFullPage();
        card_manager_rv.setAdapter(cardManagerAdapter);
    }

    //获取卡片数据
    private void queryCards(){
        showRefresh();
        mPresenter.queryCard(cardNo,cardSeqno,salesMan,billDate,repayDate,state,pageNum+"",count);
    }

    @OnClick({R.id.card_option_tv,R.id.add_card_img,R.id.filtrate_img})
    public void click(View view){
        switch (view.getId()){
            case R.id.card_option_tv:
                isOption=!isOption;
                if(isOption){
                    card_option_tv.setText("完成");
                    cardManagerAdapter=new CardManagerAdapter(isOption,cardBeanList);
                    cardManagerAdapter.setOnItemClickListener(onItemClickListener);
                    card_manager_rv.setAdapter(cardManagerAdapter);
                    option_rl.setVisibility(View.VISIBLE);
                }else{
                    card_option_tv.setText("下载");
                    cardManagerAdapter=new CardManagerAdapter(isOption,cardBeanList);
                    cardManagerAdapter.setOnItemClickListener(onItemClickListener);
                    card_manager_rv.setAdapter(cardManagerAdapter);
                    option_rl.setVisibility(View.GONE);
                }
                break;

            case R.id.add_card_img:
                ActivityIntent.readyGo(getActivity(), AddCardActivity.class);
                break;

            case R.id.filtrate_img:
                cardmanager_drawerLayout.openDrawer(Gravity.RIGHT);
                break;

        }
    }

    BaseQuickAdapter.OnItemClickListener onItemClickListener=new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent=new Intent(getActivity(),CardDetailActivity.class);
            intent.putExtra("cardDetail",cardBeanList.get(position));
            startActivity(intent);
        }
    };

    @Override
    public void onRefresh() {
        queryCards();
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
        if(pageNum!=1) {
            //获取更多数据失败
            Toast.makeText(getActivity(), "获取更多数据失败", Toast.LENGTH_LONG).show();
            cardManagerAdapter.loadMoreFail();
        }
    }

    @Override
    public void getDataSuccess(CardBean response) {
        if(response!=null){
            TOTAL_COUNTER=response.getTotalNum();
            if(pageNum==1){
                countTvs[0].setText("卡片数量："+response.getTotalNum());
                countTvs[1].setText("可用总余额：￥"+response.getAvailableAmt());
                countTvs[2].setText("初始金额总额：￥"+response.getInitAmt());
                countTvs[3].setText("固定额度总额：￥"+response.getFixedLimit());
                cardBeanList=response.getResult();
                cardManagerAdapter.setNewData(response.getResult());
                cardManagerAdapter.notifyDataSetChanged();
            }else{
                cardBeanList.addAll(response.getResult());
                cardManagerAdapter.addData(response.getResult());
                cardManagerAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void onLoadMoreRequested() {
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (cardManagerAdapter.getData().size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    cardManagerAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                   queryCards();
                }
                refresh_layout.setEnabled(true);
            }
        },1500);
    }
}
