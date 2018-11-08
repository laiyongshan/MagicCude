package com.rflash.magiccube.ui.finance;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.basemodule.BaseFragment;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.finance.financedetail.FinanceDetailActivity;
import com.rflash.magiccube.ui.shanghu.ShanghuAdapter;
import com.rflash.magiccube.ui.shanghu.ShanghuBean;
import com.rflash.magiccube.util.TimerPikerTools;
import com.rflash.magiccube.util.ToolUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/8 14:30
 * @desc:
 */

public class FinanceManagerFragment extends MVPBaseFragment<FinanceManagerContract.View,FinanceManagerPresenter> implements BaseQuickAdapter.RequestLoadMoreListener,FinanceManagerContract.View, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.tranCost_tv)
    TextView tranCost_tv;

    @BindView(R.id.revenue_tv)
    TextView revenue_tv;

    @BindView(R.id.serviceFee_tv)
    TextView serviceFee_tv;

    @BindView(R.id.saleAmt_tv)
    TextView saleAmt_tv;

    @BindView(R.id.finance_card_rv)
    RecyclerView finance_card_rv;

    @BindView(R.id.finance_drawerlayout)
    DrawerLayout finance_drawerlayout;

    @BindView(R.id.clear_filter_tv)
    TextView clear_filter_tv;

    @BindView(R.id.sure_filter_tv)
    TextView sure_filter_tv;

    @BindView(R.id.cardSeqno_et)
    EditText cardSeqno_et;

    @BindView(R.id.cardNo_et)
    EditText cardNo_et;

    @BindView(R.id.customerNmae_et)
    EditText customerNmae_et;

    @BindView(R.id.salesMan_et)
    EditText salesMan_et;

    @BindView(R.id.startDate_tv)
    TextView startDate_tv;

    @BindView(R.id.endDate_tv)
    TextView endDate_tv;

    private View notDataView;

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

    String cardSeqno="";
    String cardNo="";
    String customerNmae="";
    String salesMan="";
    String startDate="";
    String endDate="";

    private int pageNum=1;
    private int TOTAL_COUNTER ; //所有的数据总数

    FinanceBean financeBean;
    FinanceAdapter financeAdapter;
    List<FinanceBean.ResultBean> financeBeanList=new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.fragment_finance_manager;
    }

    @Override
    protected void initView() {

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) finance_card_rv.getParent(), false);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setColorSchemeColors(ToolUtils.Colors);

        finance_card_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        financeAdapter = new FinanceAdapter(financeBeanList);
        financeAdapter.setOnLoadMoreListener(this,finance_card_rv);
        financeAdapter.disableLoadMoreIfNotFullPage();
        financeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent i=new Intent(getActivity(),FinanceDetailActivity.class);
                i.putExtra("financeBean",financeBeanList.get(position));
                startActivity(i);
            }
        });
        finance_card_rv.setAdapter(financeAdapter);

        getFinanceList();
    }

    @OnClick({R.id.filtrate_img,R.id.startDate_tv,R.id.endDate_tv,R.id.clear_filter_tv,R.id.sure_filter_tv})
    public void click(View view){
        switch (view.getId()){
            case R.id.filtrate_img:
                finance_drawerlayout.openDrawer(Gravity.RIGHT);
                break;

            case R.id.startDate_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView(getActivity(), "选择服务月份", true, true, false, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        startDate = simpleDateFormat.format(date);
                        startDate_tv.setText(startDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.endDate_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView(getActivity(), "选择服务月份", true, true, false, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        endDate = simpleDateFormat.format(date);
                        endDate_tv.setText(endDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.clear_filter_tv:
                cardSeqno_et.setText("");
                cardNo_et.setText("");
                customerNmae_et.setText("");
                salesMan_et.setText("");
                startDate_tv.setText("");
                endDate_tv.setText("");
                break;

            case R.id.sure_filter_tv:
                getFinanceList();
                finance_drawerlayout.closeDrawer(Gravity.RIGHT);
                break;
        }
    }

    //获取财务列表
    private void getFinanceList(){
        cardSeqno=cardSeqno_et.getText().toString().trim();
        cardNo=cardNo_et.getText().toString().trim();
        customerNmae=customerNmae_et.getText().toString().trim();
        salesMan=salesMan_et.getText().toString().trim();
        startDate=startDate_tv.getText().toString().trim().replace("-","");
        endDate=endDate_tv.getText().toString().trim().replace("-","");
        mPresenter.queryReport(cardSeqno,cardNo,customerNmae,salesMan,startDate,endDate,pageNum+"");
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        getFinanceList();
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
    public void getDataFail(Object msg) {
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void getDataSuccess(Object response) {
        if(response!=null) {
            financeBean= (FinanceBean) response;
            TOTAL_COUNTER=financeBean.getTotalNum();
//            data_count_tv.setText("共"+financeBean.getTotalNum()+"条数据");
            tranCost_tv.setText("总成本：￥"+ StringUtil.getTwoPointString(financeBean.getTranCost())+"");
            revenue_tv.setText("总收益：￥"+StringUtil.getTwoPointString(financeBean.getRevenue())+"");

            serviceFee_tv.setText("总服务费：￥"+ StringUtil.getTwoPointString(financeBean.getServiceFee())+"");
            saleAmt_tv.setText("总刷卡金额：￥"+StringUtil.getTwoPointString(financeBean.getSaleAmt())+"");

            if(pageNum==1){
                financeBeanList=financeBean.getResult();
                financeAdapter.setNewData(financeBeanList);
                if(financeBeanList.isEmpty())
                    financeAdapter.setEmptyView(notDataView);
            }else {
                financeBeanList.addAll(financeBean.getResult());
                financeAdapter.addData(financeBean.getResult());
                financeAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void onLoadMoreRequested() {
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (financeAdapter.getData().size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    financeAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    getFinanceList();
                }
                refresh_layout.setEnabled(true);
            }
        },1500);
    }
}
