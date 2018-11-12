package com.rflash.magiccube.ui.queryPlanning;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.addplan.AddPlanActivity;
import com.rflash.magiccube.ui.cardmanager.carddetail.CardDetailActivity;
import com.rflash.magiccube.ui.newmain.DirtData;
import com.rflash.magiccube.util.TimerPikerTools;
import com.rflash.magiccube.util.ToolUtils;
import com.rflash.magiccube.view.SuccessProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * @author lys
 * @time 2018/11/6 16:36
 * @desc:
 */

public class QueryPlanningActvity extends MVPBaseActivity<QueryPlanningContract.View, QueryPlanningPresenter> implements QueryPlanningContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.planning_rv)
    RecyclerView planning_rv;

    @BindView(R.id.filtrate_img)
    ImageView filtrate_img;

    @BindView(R.id.planning_drawerlayout)
    DrawerLayout planning_drawerlayout;

    @BindView(R.id.clear_filter_tv)
    TextView clear_filter_tv;

    @BindView(R.id.sure_filter_tv)
    TextView sure_filter_tv;

    @BindViews({R.id.notOperatorSaleTranCost_tv,R.id.dealSaleTranCost_tv,R.id.notOperatorSale_tv,
            R.id.dealSale_tv,R.id.notOperatorRepay_tv,R.id.dealRepay_tv})
            TextView[] countTvs;

    @BindView(R.id.cardSeqno_et)
    EditText cardSeqno_et;

    @BindView(R.id.cardNo_et)
    EditText cardNo_et;

    @BindView(R.id.customerName_et)
    EditText customerName_et;

    @BindView(R.id.state_sp)
    MaterialSpinner state_sp;

    @BindView(R.id.tranType_sp)
    MaterialSpinner tranType_sp;

    @BindView(R.id.accountType_sp)
    MaterialSpinner accountType_sp;

    @BindView(R.id.startDate_tv)
    TextView startDate_tv;

    @BindView(R.id.endDate_tv)
    TextView endDate_tv;

    @BindView(R.id.fail_syncState_rtv)
    RoundTextView fail_syncState_rtv;

    SuccessProgressDialog successProgressDialog;

    private View notDataView;

    PlanningAdapter planningAdapter;
    List<PlaningBean.ResultBean> planingList = new ArrayList<>();

    private int pageNum = 1;
    private int TOTAL_COUNTER; //所有的数据总数
    String startDate = "",
     endDate = "", cardSeqno = "", customerName = "", cardNo = "", state = "", tranType = "", accountType = "", syncState = "",count="Y";

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    boolean isOption;

    DirtData dirtData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_planning);
        initView();

        queryPlanning();
    }

    private void initView() {

        dirtData=new DirtData(this);

        state_sp.setItems(dirtData.stateArr);
        tranType_sp.setItems(dirtData.tranTypeArr1);
        accountType_sp.setItems(dirtData.accountTypeArr1);

        successProgressDialog=new SuccessProgressDialog(this);

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) planning_rv.getParent(), false);

        refreshLayout.setColorSchemeColors(ToolUtils.Colors);
        refreshLayout.setOnRefreshListener(this);

        planning_rv.setLayoutManager(new LinearLayoutManager(this));
        planningAdapter = new PlanningAdapter(isOption,planingList);
        planningAdapter.setOnLoadMoreListener(this, planning_rv);
        planningAdapter.disableLoadMoreIfNotFullPage();
        planning_rv.setAdapter(planningAdapter);
    }

    @OnClick({R.id.title_back_tv,R.id.filtrate_img, R.id.clear_filter_tv, R.id.sure_filter_tv,
            R.id.get_count_tv,R.id.syn_planning_rtv,R.id.fail_syncState_rtv,
            R.id.startDate_tv,R.id.endDate_tv
    })
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.filtrate_img:
                planning_drawerlayout.openDrawer(Gravity.RIGHT);
                break;

            case R.id.startDate_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView(QueryPlanningActvity.this, "选择开始日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        startDate = simpleDateFormat.format(date);
                        startDate_tv.setText(startDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.endDate_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView(QueryPlanningActvity.this, "选择结束日期", true, true, true, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        endDate = simpleDateFormat.format(date);
                        endDate_tv.setText(endDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.clear_filter_tv:
                clearParams();
                break;

            case R.id.sure_filter_tv:
                syncState="";
                pageNum=1;
                queryPlanning();
                planning_drawerlayout.closeDrawer(Gravity.RIGHT);
                break;

            case R.id.get_count_tv://批量统计

                break;

            case R.id.syn_planning_rtv://同步规划
                final MaterialDialog mMaterialDialog = new MaterialDialog(QueryPlanningActvity.this);
                mMaterialDialog.setTitle("提示");
                mMaterialDialog.setMessage("确定同步规划吗？");
                mMaterialDialog.setPositiveButton("确定",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        syncPlan();
                        mMaterialDialog.dismiss();
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

            case R.id.fail_syncState_rtv://查询失败规划
                syncState="FAIL";
                pageNum=1;
                planning_drawerlayout.closeDrawer(Gravity.RIGHT);
                queryPlanning();
                break;
        }
    }


    private void clearParams(){
        startDate = "";
        endDate = "";
        cardSeqno = "";
        customerName = "";
        cardNo = "";
        state = "";
        tranType = "";
        accountType = "";
        syncState = "";
        startDate_tv.setText("");
        endDate_tv.setText("");
        cardNo_et.setText("");
        cardSeqno_et.setText("");
        customerName_et.setText("");
        state_sp.setSelectedIndex(0);
        tranType_sp.setSelectedIndex(0);
        accountType_sp.setSelectedIndex(0);
    }

    //规划查询
    private void queryPlanning() {
        startDate=startDate_tv.getText().toString().trim().replace("-","");
        endDate=endDate_tv.getText().toString().trim().replace("-","");
        cardNo=cardNo_et.getText().toString().trim();
        cardSeqno=cardSeqno_et.getText().toString().trim();
        customerName=customerName_et.getText().toString().trim();
        state=dirtData.stateOptions[state_sp.getSelectedIndex()];
        tranType=dirtData.tranTypeOptions1[tranType_sp.getSelectedIndex()];
        accountType=dirtData.accountTypeOptions1[accountType_sp.getSelectedIndex()];
        mPresenter.queryPlan(startDate, endDate, cardSeqno, customerName, cardNo, state, tranType, accountType, syncState, pageNum + "",count);
    }

    //同步规划
    private void syncPlan(){
        mPresenter.syncPlan();
    }

    @Override
    public void onRefresh() {
        pageNum = 1;
        queryPlanning();
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
    public void getDataFail(Object msg) {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void getDataSuccess(PlaningBean response) {
        if (response != null) {
            TOTAL_COUNTER = response.getTotalNum();
            if (pageNum == 1) {
                planingList.clear();
                planingList = response.getResult();
                planningAdapter.setNewData(planingList);
                if(planingList.isEmpty())
                    planningAdapter.setEmptyView(notDataView);
            } else {
                planingList.addAll(response.getResult());
                planningAdapter.notifyDataSetChanged();
                planningAdapter.loadMoreComplete();
            }

            bindCountData(response);
        }
    }

    @Override
    public void syncPlanSuccess() {
        successProgressDialog.showDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                successProgressDialog.dismiss();
                queryPlanning();
            }
        },1500);
    }

    private void bindCountData(PlaningBean response) {
        countTvs[0].setText("未消费成本:￥"+ StringUtil.getTwoPointString(response.getNotOperatorSaleTranCost()));
        countTvs[1].setText("已消费成本:￥"+StringUtil.getTwoPointString(response.getDealSaleTranCost()));
        countTvs[2].setText("未消费总金额:￥"+StringUtil.getTwoPointString(response.getNotOperatorSale()));
        countTvs[3].setText("已消费总金额:￥"+StringUtil.getTwoPointString(response.getDealSale()));
        countTvs[4].setText("未还款总金额:￥"+StringUtil.getTwoPointString(response.getNotOperatorSaleTranCost()));
        countTvs[5].setText("已还款总金额:￥"+StringUtil.getTwoPointString(response.getDealRepay()));
    }


    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (planningAdapter.getData().size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    planningAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    queryPlanning();
                }
                if (refreshLayout != null)
                    refreshLayout.setEnabled(true);
            }
        }, 1500);
    }
}
