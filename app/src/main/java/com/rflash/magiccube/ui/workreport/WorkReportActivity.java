package com.rflash.magiccube.ui.workreport;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.view.BarChartView;
import com.rflash.magiccube.view.CircleGraphView;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 工作报表界面
 */

public class WorkReportActivity extends MVPBaseActivity<WorkReportContract.View, WorkReportPresenter> implements WorkReportContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewpager)
    ViewPager viewPager;


    @BindView(R.id.ll_1)
    LinearLayout ll1;

    @BindView(R.id.ll_2)
    LinearLayout ll2;

    @BindView(R.id.ll_3)
    LinearLayout ll3;

    @BindView(R.id.tv_repay)
    TextView tvRepay;

    @BindView(R.id.tv_consume)
    TextView tvConsume;

    @BindView(R.id.tv_tree_repay)
    TextView tvTreeRepay;


    private int currentPage = 0;


    private WorkReportPageAdapter adapter;

    private List<View> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_report);

        initView();
        mPresenter.threeDayCount();


    }

    private void initView() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkReportActivity.this.finish();
            }
        });

        View repay = LayoutInflater.from(this).inflate(R.layout.viewpager_repay, null);
        View consume = LayoutInflater.from(this).inflate(R.layout.viewpager_consume, null);
        View threeRepay = LayoutInflater.from(this).inflate(R.layout.viewpager_three_repay, null);

        list.add(repay);
        list.add(consume);
        list.add(threeRepay);

        adapter = new WorkReportPageAdapter(list);
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(listener);

    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentPage = position;
            View view = list.get(position);

            if (position == 0) {
                click1Page();
                //已还款
                TextView tvRepayAmt = view.findViewById(R.id.tv_repayAmt);
                if (tvRepayAmt != null) {
                    tvRepayAmt.setText(getResources().getString(R.string.money, StringUtil.getTwoPointString(repayAmt)));
                }
                //未还款
                TextView tvNonRepayAmt = view.findViewById(R.id.tv_nonRepayAmt);
                if (tvNonRepayAmt != null) {
                    tvNonRepayAmt.setText(getResources().getString(R.string.money, StringUtil.getTwoPointString(nonRepayAmt)));
                }
                //应还总金额
                TextView tvBillAmt = view.findViewById(R.id.tv_bill_amt);
                if (tvBillAmt != null) {
                    tvBillAmt.setText(getResources().getString(R.string.money, StringUtil.getTwoPointString(billAmt)));
                }
                CircleGraphView repayView = view.findViewById(R.id.circleGraphView_repay);
                if (repayView != null) {
                    if (billAmt == 0) {//不需要还款
                        repayView.setProgress(0);
                    } else if (repayAmt == billAmt) {//全部还完
                        repayView.setProgress(0);
                    } else if (nonRepayAmt == billAmt) {//完全没有还款
                        repayView.setProgress(360);
                    } else {
                        repayView.setProgress((int) (360 * ((float) nonRepayAmt / repayAmt)));
                    }
                    setCircleGraphAnimate(repayView, repayNum, total);
                }
            } else if (position == 1) {
                click2Page();
                //未消费
                TextView tvNonConsumeAmt = view.findViewById(R.id.tv_nonConsumeAmt);
                if (tvNonConsumeAmt != null) {
                    tvNonConsumeAmt.setText(getResources().getString(R.string.money, StringUtil.getTwoPointString(nonConsumeAmt)));
                }
                //已消费
                TextView tvConsumeAmt = view.findViewById(R.id.tv_consumeAmt);
                if (tvConsumeAmt != null) {
                    tvConsumeAmt.setText(getResources().getString(R.string.money, StringUtil.getTwoPointString(consumeAmt)));
                }
                //总消费
                TextView tvBillAmt = view.findViewById(R.id.tv_billAmt);
                if (tvBillAmt != null) {
                    tvBillAmt.setText(getResources().getString(R.string.money, StringUtil.getTwoPointString(consumeTotalAmt)));
                }
                CircleGraphView consumeView = view.findViewById(R.id.circleGraphView_consume);
                if (consumeView != null) {
                    if (consumeTotalAmt == 0) {//不需要消费
                        consumeView.setProgress(0);
                    } else if (consumeAmt == consumeTotalAmt) {//全部消费
                        consumeView.setProgress(0);
                    } else if (nonConsumeAmt == consumeTotalAmt) {//完全没有消费
                        consumeView.setProgress(360);
                    } else {
                        consumeView.setProgress((int) (360 * ((float) nonConsumeAmt / consumeTotalAmt)));
                    }
                    setCircleGraphAnimate(consumeView, consumeNum, consumeTotalNum);
                }

            } else if (position == 2) {
                click3Page();
                TextView tvTotalAmt = view.findViewById(R.id.tv_total_amt);
                int total = firstMoney + secondeMoney + thirdMoney;
                if (tvTotalAmt != null) {
                    tvTotalAmt.setText(getResources().getString(R.string.money, StringUtil.getTwoPointString(total)));
                }
                BarChartView barChartView = view.findViewById(R.id.barChartView);
                if (barChartView != null) {
                    float a = firstMoney / 100;
                    float b = secondeMoney / 100;
                    float c = thirdMoney / 100;
                    barChartView.setMax(Math.max(Math.max(a, b), c));
                    barChartView.setData1(firstDate, secondeDate, thirdDate);
                    setBarChartAnimate(barChartView, a, b, c);
                }
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 给CircleGraphView添加动画
     */
    private void setCircleGraphAnimate(CircleGraphView view, int part1, int part2) {
        ObjectAnimator part1Animator = ObjectAnimator.ofInt(view, "part1", 0, part1);
        ObjectAnimator part2Animator = ObjectAnimator.ofInt(view, "part2", 0, part2);
        ObjectAnimator moneyAnimator = ObjectAnimator.ofInt(view, "drawProgress", 0, 360);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(part1Animator, part2Animator, moneyAnimator);
        set.setDuration(1000);
        set.start();
    }

    /**
     * 给BarChartView添加动画
     */
    private void setBarChartAnimate(BarChartView view, float money1, float money2, float money3) {
        ObjectAnimator money1Animator = ObjectAnimator.ofFloat(view, "money1", 0.00f, money1);
        ObjectAnimator money2Animator = ObjectAnimator.ofFloat(view, "money2", 0.00f, money2);
        ObjectAnimator money3Animator = ObjectAnimator.ofFloat(view, "money3", 0.00f, money3);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(money1Animator, money2Animator, money3Animator);
        set.setDuration(1000);
        set.start();

    }


    @OnClick(R.id.ll_1)
    public void onClick1Page() {
        click1Page();
    }

    @OnClick(R.id.ll_2)
    public void onClick2Page() {
        click2Page();
    }

    @OnClick(R.id.ll_3)
    public void onClick3Page() {
        click3Page();
    }


    @OnClick(R.id.iv_left)
    public void onClickLeft() {
        currentPage--;

        if (currentPage == -1) {
            currentPage = 2;
        }

        viewPager.setCurrentItem(currentPage);

    }


    @OnClick(R.id.iv_right)
    public void onClickRight() {
        currentPage++;

        if (currentPage == 3) {
            currentPage = 0;
        }

        viewPager.setCurrentItem(currentPage);
    }

    /**
     * 选中第一个page
     */
    private void click1Page() {
        ll1.setBackgroundResource(R.drawable.viewpager_click);
        ll2.setBackgroundResource(R.color.white);
        ll3.setBackgroundResource(R.color.white);
        tvRepay.setTextColor(getResources().getColor(R.color.blueBackground));
        tvConsume.setTextColor(getResources().getColor(R.color.loginHint));
        tvTreeRepay.setTextColor(getResources().getColor(R.color.loginHint));
        viewPager.setCurrentItem(0);
    }

    /**
     * 选中第二个page
     */
    private void click2Page() {
        ll2.setBackgroundResource(R.drawable.viewpager_click);
        ll1.setBackgroundResource(R.color.white);
        ll3.setBackgroundResource(R.color.white);
        tvConsume.setTextColor(getResources().getColor(R.color.blueBackground));
        tvRepay.setTextColor(getResources().getColor(R.color.loginHint));
        tvTreeRepay.setTextColor(getResources().getColor(R.color.loginHint));
        viewPager.setCurrentItem(1);
    }


    /**
     * 选中第三个page
     */
    private void click3Page() {
        ll3.setBackgroundResource(R.drawable.viewpager_click);
        ll1.setBackgroundResource(R.color.white);
        ll2.setBackgroundResource(R.color.white);
        tvTreeRepay.setTextColor(getResources().getColor(R.color.blueBackground));
        tvConsume.setTextColor(getResources().getColor(R.color.loginHint));
        tvRepay.setTextColor(getResources().getColor(R.color.loginHint));
        viewPager.setCurrentItem(2);
    }


    //已还款
    private int repayAmt;
    //未还款
    private int nonRepayAmt;
    //应还总金额
    private int billAmt;
    //已还笔数
    private int repayNum;
    //应还款总笔数
    private int total;
    //未消费
    private int nonConsumeAmt;
    //已消费
    private int consumeAmt;
    //应该消费总额
    private int consumeTotalAmt;
    //已消费笔数
    private int consumeNum;
    //应该消费总笔数
    private int consumeTotalNum;

    //前天日期
    private String firstDate;
    //昨天日期
    private String secondeDate;
    //今天日期
    private String thirdDate;

    //前天的金额
    private int firstMoney;
    //昨天的金额
    private int secondeMoney;
    //的金额
    private int thirdMoney;

    /**
     * 获取三天的统计报表
     *
     * @param threeDayCount
     */
    @Override
    public void getThreeDayCount(ThreeDayCount threeDayCount) {
        ThreeDayCount.ResultBean result = threeDayCount.getResult();
        if (result != null) {
            //应还信息
            ThreeDayCount.ResultBean.RevertibleInfoBean revertibleInfo = result.getRevertibleInfo();
            repayAmt = revertibleInfo.getRepayAmt();
            nonRepayAmt = revertibleInfo.getNonRepayAmt();
            billAmt = revertibleInfo.getBillAmt();
            repayNum = revertibleInfo.getRepayNum();
            total = revertibleInfo.getTotal();
            //消费信息
            ThreeDayCount.ResultBean.PayableInfoBean payableInfo = result.getPayableInfo();
            nonConsumeAmt = payableInfo.getNonConsumeAmt();
            consumeAmt = payableInfo.getConsumeAmt();
            consumeTotalAmt = payableInfo.getBillAmt();
            consumeNum = payableInfo.getConsumeNum();
            consumeTotalNum = payableInfo.getTotal();
            //最近三天还款信息
            List<ThreeDayCount.ResultBean.Nearly3daysInfoBean> nearly3daysInfo = result.getNearly3daysInfo();

            //给list排序  从小到大
            Collections.sort(nearly3daysInfo, new Comparator<ThreeDayCount.ResultBean.Nearly3daysInfoBean>() {
                @Override
                public int compare(ThreeDayCount.ResultBean.Nearly3daysInfoBean o1, ThreeDayCount.ResultBean.Nearly3daysInfoBean o2) {
                    return o1.getCurrentDate() - o2.getCurrentDate();
                }
            });

            firstMoney = nearly3daysInfo.get(0).getAmt();
            firstDate = StringUtil.getDate(nearly3daysInfo.get(0).getCurrentDate());
            secondeMoney = nearly3daysInfo.get(1).getAmt();
            secondeDate = StringUtil.getDate(nearly3daysInfo.get(1).getCurrentDate());
            thirdMoney = nearly3daysInfo.get(2).getAmt();
            thirdDate = StringUtil.getDate(nearly3daysInfo.get(2).getCurrentDate());
        }
        listener.onPageSelected(viewPager.getCurrentItem());
    }


}
