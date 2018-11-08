package com.rflash.magiccube.ui.merchants;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rflash.basemodule.BaseFragment;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.util.DateUtil;
import com.rflash.magiccube.util.ToolUtils;
import com.rflash.magiccube.view.kjchart.ChartView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/10/7.
 * 最近3天还款情况
 */

@SuppressLint("ValidFragment")
public class Days3RefundFragment extends BaseFragment {

    @BindView(R.id.days3_chart)
    ChartView barChart;

    @BindView(R.id.total_refund_tv)
    TextView total_refund_tv;

    float totalAmt;

    List<String > mYvalue;
    List<String > mXvalue;
    List<Integer> mBarColors;

    List<HomeCountBean.ResultBean.Nearly3daysInfoBean> nearly3daysInfo=new ArrayList<>();

    public Days3RefundFragment(List<HomeCountBean.ResultBean.Nearly3daysInfoBean> nearly3daysInfo){
            this.nearly3daysInfo=nearly3daysInfo;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_3days_refund;
    }

    @Override
    protected void initView(){



        mYvalue=new ArrayList<>();
        mXvalue=new ArrayList<>();
        mBarColors=new ArrayList<>();

        bindBarChartData();

    }


    public void bindBarChartData() {
        mYvalue.clear();
        mXvalue.clear();
        totalAmt=0;
        for(HomeCountBean.ResultBean.Nearly3daysInfoBean nearly3daysInfoBean:nearly3daysInfo){
            mXvalue.add(DateUtil.formatDate1(nearly3daysInfoBean.getCurrentDate())+"");
            mYvalue.add(nearly3daysInfoBean.getAmt()+"");
            totalAmt+=Float.valueOf(nearly3daysInfoBean.getAmt());
        }

        total_refund_tv.setText("￥"+totalAmt);

        barChart.bindBarChart(mYvalue, mXvalue);
        // holder.barChart.chartEnum = ChartEnum.BarChart;
        barChart.mIs_AccordingTo_List_SetMin = true;
        barChart.isNeedMinValueMoreSmall = true;
        barChart.mYLineColor = Color.WHITE;
        barChart.mXLineColor = Color.GRAY;

        barChart.mYAxisTextColor=Color.WHITE;
        barChart.mDefaultPointColor=Color.parseColor("#34C0E3");

        barChart.mIsShowPointColor = true;

        barChart.mIs_AccordingTo_PointLabelValue_JudgmentColor = false;
        barChart.mdefaulYValueTextColor = Color.GRAY;

        barChart.mXAxisEveryFewParagraphs = 0;
        barChart.mIsBarChartRotatePointText90=false;

        barChart.mBarChartSize = barChart.dp2px(26);
        barChart.mXScaleWidth = barChart.dp2px(68);

        barChart.mXAxisTextColor = Color.GRAY;
        barChart.refreshChartView();

    }

}
