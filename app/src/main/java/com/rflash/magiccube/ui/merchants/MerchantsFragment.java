package com.rflash.magiccube.ui.merchants;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.BaseFragment;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.billconfirm.BillConfirmActivity;
import com.rflash.magiccube.ui.collection.CollectionActivity;
import com.rflash.magiccube.ui.main.MainActivity;
import com.rflash.magiccube.ui.mine.MineActivity;
import com.rflash.magiccube.ui.operationtoday.OperationTodayActivity;
import com.rflash.magiccube.ui.presentoperation.PresentOperationActivity;
import com.rflash.magiccube.ui.queryPlanning.QueryPlanningActvity;
import com.rflash.magiccube.ui.refund.RefundActivity;
import com.rflash.magiccube.ui.renewal.RenewalActivity;
import com.rflash.magiccube.ui.renewalmind.RenewalMindActivity;
import com.rflash.magiccube.ui.salesmen.SalesMenActivity;
import com.rflash.magiccube.util.ToolUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by lenovo on 2018/10/6.
 * 商户管理页面  即首页
 */

public class MerchantsFragment extends MVPBaseFragment<MerchantsContract.View,MerchantsPresenter> implements MerchantsContract.View, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.renewal_tips_tv)
    TextView renewal_tips_tv;

    @BindView(R.id.pager)
    ViewPager mPager;

    @BindView(R.id.pre_iv)
    ImageView pre_iv;

    @BindView(R.id.next_iv)
    ImageView next_iv;

    @BindView(R.id.to_collection_cv)
    CardView to_collection_cv;

    @BindView(R.id.to_salesmen_cv)
    CardView to_salesmen_cv;

    @BindView(R.id.bill_sure_tv)
    TextView bill_sure_tv;

    @BindView(R.id.refund_tips_tv)
    TextView refund_tips_tv;

    @BindView(R.id.bill_sure_ll)
    LinearLayout bill_sure_ll;

    @BindView(R.id.refund_tips_ll)
    LinearLayout refund_tips_ll;

    // 包裹小圆点的LinearLayout
    @BindView(R.id.layout_dot)
     ViewGroup group;
    private ImageView[] imageViews;
    private ImageView imageView;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    QBadgeView qBadgeView_xf;
    QBadgeView qBadgeView_hk;
    QBadgeView qBadgeView_zd;
    QBadgeView qBadgeView_tz;

    RefundFragment mRefundFragment;
    ConsumeFragment mConsumeFragment;
    Days3RefundFragment mDays3RefundFragment;
    CardsSituationFragment mCardsSituationFragment;

    SlidePagerAdapter mPagerAdapter;

    int currentItem;

    HomeCountBean mHomeCountBean;
    HomeRemindBean mHomeRemindBean;

    @Override
    protected int getLayout() {
        return R.layout.fragment_merchnts;
    }

    @Override
    protected void initView() {

        mPresenter.getCount();
        mPresenter.getRemindCount();

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);


        initGoodsViewPager();

        qBadgeView_xf = new QBadgeView(getActivity());
        qBadgeView_xf.setGravityOffset(2,-1,true);
        qBadgeView_hk = new QBadgeView(getActivity());
        qBadgeView_hk.setGravityOffset(2,-1,true);
        qBadgeView_zd = new QBadgeView(getActivity());
        qBadgeView_zd.setGravityOffset(2,-1,true);


    }

    private void initGoodsViewPager() {
        /**
         * 有几张图片下面就显示几个小圆点
         */
        imageViews = new ImageView[4];
        LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置每个小圆点距离左边的间距
        margin.setMargins(15, 0, 0, 0);

        for (int i = 0; i < 4; i++) {

            imageView = new ImageView(getActivity());

            // 设置每个小圆点的宽高
            imageView.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT));
            imageViews[i] = imageView;

            if (i == 0) {

                // 默认选中第一张图片
                imageViews[i]
                        .setBackgroundResource(R.drawable.goods_indicator_focused);
            } else {
                // 其他图片都设置未选中状态
                imageViews[i]
                        .setBackgroundResource(R.drawable.goods_indicator_unfocused);
            }
            group.addView(imageViews[i], margin);
        }
        // 给viewpager设置监听事件
        mPager.setOnPageChangeListener(new GuidePageChangeListener());
    }

    /* 头部viewpager的页面转换监听 */
    private class GuidePageChangeListener implements
            ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            if(arg0==0){
                refresh_layout.setEnabled(true);
            }else{
                refresh_layout.setEnabled(false);
            }

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            // 遍历数组让当前选中图片下的小圆点设置颜色
            for (int i = 0; i < imageViews.length; i++) {

                imageViews[arg0].setBackgroundResource(R.drawable.goods_indicator_focused);
                if (arg0 != i) {
                    imageViews[i].setBackgroundResource(R.drawable.goods_indicator_unfocused);
                }
            }
        }
    }


    @OnClick({R.id.pre_iv, R.id.next_iv,R.id.mine_tv,R.id.to_collection_cv,R.id.to_salesmen_cv,R.id.renewal_mind_ll,
            R.id.bill_sure_ll,R.id.refund_tips_ll,R.id.to_optionToday_cv,R.id.to_query_planning})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.pre_iv:
                if (currentItem > 0) {
                    currentItem--;
                    mPager.setCurrentItem(currentItem);
                }
                break;

            case R.id.next_iv:
                if (currentItem < 3) {
                    currentItem++;
                    mPager.setCurrentItem(currentItem);
                }
                break;

            case R.id.to_collection_cv:
                ActivityIntent.readyGo(getActivity(), CollectionActivity.class);
                break;

            case R.id.to_salesmen_cv:
                ActivityIntent.readyGo(getActivity(), SalesMenActivity.class);
                break;

            case R.id.bill_sure_ll:
                ActivityIntent.readyGo(getActivity(), BillConfirmActivity.class);
                break;

            case  R.id.refund_tips_ll:
                ActivityIntent.readyGo(getActivity(), RefundActivity.class);
                break;

            case R.id.to_optionToday_cv:
                ActivityIntent.readyGo(getActivity(), PresentOperationActivity.class);
                break;

            case R.id.to_query_planning:
                ActivityIntent.readyGo(getActivity(), QueryPlanningActvity.class);
                break;

            case R.id.renewal_mind_ll:
                ActivityIntent.readyGo(getActivity(), RenewalMindActivity.class);
                break;

            case R.id.mine_tv:
                ActivityIntent.readyGo(getActivity(), MineActivity.class);
                break;
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getCount();
        mPresenter.getRemindCount();
    }

    @Override
    public void getDataFail(String msg) {
    }

    @Override
    public void showRefresh() {
        refresh_layout.setRefreshing(true);
    }

    @Override
    public void finishRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh_layout.setRefreshing(false);
            }
        },2000);

    }


    @Override
    public void getDataSuccess(Object response) {

        if(response!=null){
            mHomeCountBean= (HomeCountBean) response;
            bindCountData(mHomeCountBean);
        }
    }

    /**
     * 獲取首頁統計數據
     * */
    private void bindCountData(HomeCountBean mHomeCountBean){


        mRefundFragment = new RefundFragment(mHomeCountBean.getResult().getRevertibleInfo());
        mConsumeFragment = new ConsumeFragment(mHomeCountBean.getResult().getPayableInfo());
        mDays3RefundFragment = new Days3RefundFragment(mHomeCountBean.getResult().getNearly3daysInfo());
        mCardsSituationFragment = new CardsSituationFragment(mHomeCountBean);

        mPagerAdapter = new SlidePagerAdapter(getChildFragmentManager());
        mPagerAdapter.notifyDataSetChanged();
        mPager.setAdapter(mPagerAdapter);
    }


    @Override
    public void getRemindDataSuccess(Object response) {
        if(response!=null){
            mHomeRemindBean= (HomeRemindBean) response;
            bindHomeRemindData(mHomeRemindBean);
        }
    }

    private void bindHomeRemindData(HomeRemindBean mHomeRemindBean) {
        qBadgeView_xf.bindTarget(renewal_tips_tv).setBadgeNumber(mHomeRemindBean.getResult().getCARD_FEE_EXPIRES());
        qBadgeView_hk.bindTarget(refund_tips_tv).setBadgeNumber(mHomeRemindBean.getResult().getNO_REPAY());
        qBadgeView_zd.bindTarget(bill_sure_tv).setBadgeNumber(mHomeRemindBean.getResult().getBILL_CONFIRM());
    }

    /* PagerAdapter class */
    public class SlidePagerAdapter extends FragmentStatePagerAdapter {
        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /*
             * IMPORTANT: This is the point. We create a RootFragment acting as
			 * a container for other fragments
			 */
            if (position == 0) {
                if(!mRefundFragment.isAdded())
                    return mRefundFragment;
                else
                    return null;
            } else if (position == 1) {
                return mConsumeFragment;
            } else if (position == 2) {
                return mDays3RefundFragment;
            } else
                return mCardsSituationFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
