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
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.basemodule.BaseFragment;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.addcard.AddCardActivity;
import com.rflash.magiccube.ui.cardmanager.carddetail.CardDetailActivity;
import com.rflash.magiccube.ui.newmain.DirtData;
import com.rflash.magiccube.util.TimerPikerTools;
import com.rflash.magiccube.util.ToolUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindFloat;
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

    @BindView(R.id.sure_filter_tv)
    TextView sure_filter_tv;
    @BindView(R.id.clear_filter_tv)
    TextView clear_filter_tv;
    @BindView(R.id.repayDate_et)
    EditText repayDate_et;
    @BindView(R.id.billDate_et)
    EditText billDate_et;
    @BindView(R.id.state_sp)
    MaterialSpinner state_sp;
    @BindView(R.id.salesMan_sp)
    MaterialSpinner salesMan_sp;
    @BindView(R.id.cardNo_et)
    EditText cardNo_et;
    @BindView(R.id.cardSeqno_et)
    EditText cardSeqno_et;

    private View notDataView;

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

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");

    boolean isOption;

    private int TOTAL_COUNTER ; //所有的数据总数

    DirtData dirtData;

    @Override
    protected int getLayout() {
        return R.layout.fragment_card_mananger;
    }


    @Override
    protected void initView() {

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        dirtData=new DirtData(getActivity());

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) card_manager_rv.getParent(), false);


        card_manager_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        cardManagerAdapter=new CardManagerAdapter(isOption,cardBeanList);
        cardManagerAdapter.setOnItemClickListener(onItemClickListener);
        cardManagerAdapter.setOnLoadMoreListener(this,card_manager_rv);
        cardManagerAdapter.disableLoadMoreIfNotFullPage();
        card_manager_rv.setAdapter(cardManagerAdapter);

        state_sp.setItems(dirtData.cardStateArr);

        salesMan_sp.setItems(dirtData.getSalesMenList());
    }

    @Override
    public void onResume() {
        super.onResume();
        pageNum=1;
        queryCards();
    }

    //获取卡片数据
    private void queryCards(){
        showRefresh();
        cardNo=cardNo_et.getText().toString().trim();
        cardSeqno=cardSeqno_et.getText().toString().trim();
        salesMan=dirtData.getSalesIdList().get(salesMan_sp.getSelectedIndex());
        billDate=billDate_et.getText().toString().trim();
        repayDate=repayDate_et.getText().toString().trim();
        state=dirtData.cardStateOptions[state_sp.getSelectedIndex()];
        mPresenter.queryCard(cardNo,cardSeqno,salesMan,billDate,repayDate,state,pageNum+"",count);
    }

    @OnClick({R.id.card_option_tv,R.id.add_card_img,R.id.filtrate_img,
            R.id.sure_filter_tv,R.id.clear_filter_tv,R.id.repayDate_et,R.id.billDate_et})
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

            case R.id.sure_filter_tv:
                queryCards();
                cardmanager_drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.clear_filter_tv:
                repayDate_et.setText("");
                billDate_et.setText("");
                salesMan_sp.setSelectedIndex(0);
                cardNo_et.setText("");
                cardSeqno_et.setText("");
                state_sp.setSelectedIndex(0);
                cardNo="";
                 cardSeqno="";
                 salesMan="";
                 billDate="";
                 repayDate="";
                 state="";
                break;
            case R.id.repayDate_et:
                mTimePikerView = TimerPikerTools.creatTimePickerView(getActivity(), "选择账单日", false, false, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        repayDate = simpleDateFormat.format(date);
                        repayDate_et.setText(repayDate + "");
                    }
                });
                mTimePikerView.show();
                break;
            case R.id.billDate_et:
                mTimePikerView = TimerPikerTools.creatTimePickerView(getActivity(), "选择还款日", false, false, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        billDate = simpleDateFormat.format(date);
                        billDate_et.setText(billDate + "");
                    }
                });
                mTimePikerView.show();
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
                countTvs[1].setText("可用总余额：￥"+ StringUtil.getTwoPointString(response.getAvailableAmt()));
                countTvs[2].setText("初始金额总额：￥"+StringUtil.getTwoPointString(response.getInitAmt()));
                countTvs[3].setText("固定额度总额：￥"+StringUtil.getTwoPointString(response.getFixedLimit()));
                cardBeanList=response.getResult();
                cardManagerAdapter.setNewData(response.getResult());
                cardManagerAdapter.notifyDataSetChanged();
                if(cardBeanList.isEmpty())
                    cardManagerAdapter.setEmptyView(notDataView);
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
