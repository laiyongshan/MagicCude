package com.rflash.magiccube.ui.finance.financedetail;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.finance.FinanceBean;
import com.rflash.magiccube.ui.finance.FinanceManagerContract;
import com.rflash.magiccube.ui.finance.FinanceManagerPresenter;
import com.rflash.magiccube.ui.newmain.DirtData;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/8 15:57
 * @desc:
 */

public class FinanceDetailActivity extends MVPBaseActivity<FinanceManagerContract.View, FinanceManagerPresenter> {

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.finance_detail_drawerlayout)
    DrawerLayout finance_detail_drawerlayout;

    @BindView(R.id.channelName_sp)
    MaterialSpinner channelName_sp;

    //    @BindView(R.id.tablayout)
//    TabLayout tabLayout;
    @BindView(R.id.trand_type_stl)
    SlidingTabLayout trand_type_stl;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindViews({R.id.saleNum_tv, R.id.saleAmt_tv, R.id.repayNum_tv, R.id.repayAmt_tv, R.id.serviceType_tv, R.id.serviceAmt_tv, R.id.serviceRate_tv, R.id.serviceFee_tv})
    TextView[] financeInfoTvs;


    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"消费", "还款"};
    private MyPagerAdapter mAdapter;

    String cardNo = "";
    String channel="";

    FinanceBean.ResultBean financeBean;

    RepayFragment repayFragment;
    SaleFragment saleFragment;

    DirtData dirtData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_detail);
        initView();
    }

    private void initView() {
        dirtData=new DirtData(this);

        financeBean = (FinanceBean.ResultBean) getIntent().getSerializableExtra("financeBean");
        if (financeBean != null) {
            cardNo = financeBean.getCardNo() + "";
            financeInfoTvs[0].setText("总消费笔数：" + financeBean.getSaleNum());
            financeInfoTvs[1].setText("总消费金额：￥" + StringUtil.getTwoPointString(financeBean.getSaleAmt()));
            financeInfoTvs[2].setText("总还款笔数：" + financeBean.getRepayNum());
            financeInfoTvs[3].setText("总还款金额：￥" + StringUtil.getTwoPointString(financeBean.getRepayAmt()));
            if (financeBean.getServiceType().equals("FIXED_LIMIT")) {
                financeInfoTvs[4].setText("费用基数类型：" + "固定额度");
            } else if (financeBean.getServiceType().equals("REPAY_LIMIT")) {
                financeInfoTvs[4].setText("费用基数类型：" + "还款额");
            } else if (financeBean.getServiceType().equals("USER_DEFINED")) {
                financeInfoTvs[4].setText("费用基数类型：" + "自定义");
            }
            financeInfoTvs[5].setText("收费基数：￥" + StringUtil.getTwoPointString(financeBean.getServiceAmt()));
            financeInfoTvs[6].setText("收费比例：" + Double.valueOf(financeBean.getServiceRate())*100 + "%");
            financeInfoTvs[7].setText("服务费用：￥" +StringUtil.getTwoPointString(financeBean.getServiceFee()) + "");
        }


        channelName_sp.setItems(dirtData.ChannelArr);
        channelName_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if(i!=0){
                    channel=dirtData.ChannelIDOptions3[i];
                }else{
                    channel="";
                }

                if(saleFragment!=null)
                    saleFragment.queryPlanByChannelId(channel);
                if(repayFragment!=null)
                    repayFragment.queryPlanByChannelId(channel);
                if(finance_detail_drawerlayout!=null)
                    finance_detail_drawerlayout.closeDrawer(Gravity.RIGHT);
            }
        });

        saleFragment=SaleFragment.getInstance(financeBean);
        repayFragment=RepayFragment.getInstance(financeBean);
        mFragments.add(saleFragment);
        mFragments.add(repayFragment);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mAdapter);
        trand_type_stl.setViewPager(viewpager, mTitles);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick({R.id.title_back_tv, R.id.filtrate_img, R.id.clear_filter_tv, R.id.sure_filter_tv})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.filtrate_img:
                finance_detail_drawerlayout.openDrawer(Gravity.RIGHT);
                break;

            case R.id.clear_filter_tv:

                break;

            case R.id.sure_filter_tv:
                finance_detail_drawerlayout.closeDrawer(Gravity.RIGHT);
                break;
        }
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
