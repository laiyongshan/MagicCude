package com.rflash.magiccube.ui.merchants;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.rflash.basemodule.BaseFragment;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/10/7.
 * 还款情况
 */

@SuppressLint("ValidFragment")
public class RefundFragment extends BaseFragment {

    @BindView(R.id.refund_pieChart)
    PieChart refund_pieChart;

    @BindViews({R.id.total_refund_tv,R.id.nonRepayAmt_tv,R.id.repayAmt_tv})
    TextView[] tvs;

    int[] date ;

    HomeCountBean.ResultBean.RevertibleInfoBean revertibleInfoBean;
    public RefundFragment(HomeCountBean.ResultBean.RevertibleInfoBean revertibleInfoBean){
        this.revertibleInfoBean=revertibleInfoBean;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_refund;
    }


    @Override
    protected void initView() {
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();

        tvs[0].setText("￥"+ StringUtil.getTwoPointString(revertibleInfoBean.getBillAmt()));
        tvs[1].setText("￥"+StringUtil.getTwoPointString(revertibleInfoBean.getNonRepayAmt()));
        tvs[2].setText("￥"+StringUtil.getTwoPointString(revertibleInfoBean.getRepayAmt()));

        date=new int[]{revertibleInfoBean.getNonRepayNum(),revertibleInfoBean.getRepayNum()};
        initPieChat(date);
        
    }


    private void initPieChat(int[] date) {
        int sum = date[0] + date[1];

        refund_pieChart.setDescription("");
        refund_pieChart.animateXY(1000, 1000);//设置动画效果
        refund_pieChart.setDrawSliceText(false);//圆环上不绘制图例文字
        refund_pieChart.setHoleRadius(75f);//设置内圆环半径
        refund_pieChart.setCenterTextSize(7f);//设置中间文字中大小
        refund_pieChart.setCenterText(generateCenterText(sum));
        Legend legend = refund_pieChart.getLegend();//获取图例

        if (sum == 0) {
            refund_pieChart.setData(generateEmptyPieData());
            refund_pieChart.setHighlightPerTapEnabled(false);//点击不响应
            legend.setEnabled(false);//图例隐藏
            return;
        }


        refund_pieChart.setData(generatePieData(date));

        legend.setEnabled(false);//图例显示
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);//图例显示位置设置

        refund_pieChart.setHighlightPerTapEnabled(true);//点击响应
        refund_pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {//圆盘点击事件
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                Toast.makeText(getActivity(), "" + entry.getXIndex(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    /**
     * 中间文字绘制
     *
     * @param sum 总数
     * @return
     */
    private SpannableString generateCenterText(int sum) {
        String total = Integer.toString(sum);
        SpannableString s = new SpannableString(date[0] +"/"+date[1]+ "\n 笔数");
        s.setSpan(new RelativeSizeSpan(3f), 0, (date[0]+"").length()+ (date[1]+"").length()+1, 2);
        s.setSpan(new ForegroundColorSpan(Color.rgb(153, 153, 153)), 0, (date[0]+"").length()+1, 0);
        s.setSpan(new ForegroundColorSpan(Color.rgb(88, 146, 240)), (date[1]+"").length(), s.length(), 0);
        return s;
    }

    /**
     * 图表数据设置
     *
     * @param date
     * @return
     */
    protected PieData generatePieData(int[] date) {
        ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();

        xVals.add("");
        xVals.add("");

        yVals.add(new Entry((float) date[0], 0));
        yVals.add(new Entry((float) date[1], 1));
//        yVals.add(new Entry((float) date[2], 2));

        PieDataSet pieDataSet = new PieDataSet(yVals, "");
        pieDataSet.setValueFormatter(new ValueFormatter() {//圆环内文字设置
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int n = (int) value;

//                String str = n + "台";
//                if (n == 0) {
//                    str = "";
//                }
                return "";
            }
        });

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#69B6F3"));
        colors.add(Color.rgb(153, 153, 153));
        pieDataSet.setColors(colors);//颜色设置

        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.YELLOW);
        pieDataSet.setValueTextSize(12f);

        return new PieData(xVals, pieDataSet);
    }

    /**
     * 空图表数据设置
     *
     * @return
     */
    protected PieData generateEmptyPieData() {
        ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();

        xVals.add("");
        xVals.add("");
        yVals.add(new Entry((float) 1, 1));
        yVals.add(new Entry((float) 1, 1));

        PieDataSet pieDataSet = new PieDataSet(yVals, "");
        pieDataSet.setValueFormatter(new ValueFormatter() {//圆环内文字设置为空
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#69B6F3"));
        colors.add(Color.rgb(153, 153, 153));
        pieDataSet.setColors(colors);

        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);

        PieData pieData = new PieData(xVals, pieDataSet);

        return pieData;
    }
}
