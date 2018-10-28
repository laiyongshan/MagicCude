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
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/10/7.
 * 消费情况
 */

@SuppressLint("ValidFragment")
public class ConsumeFragment extends BaseFragment {

    @BindView(R.id.consume_pieChart)
    PieChart consume_pieChart;

    @BindViews({R.id.total_billAmt_tv,R.id.nonConsumeAmt_tv,R.id.consumeAmt_tv})
    TextView[] tvs;

    int[] date;

    HomeCountBean.ResultBean.PayableInfoBean payableInfoBean;
    public ConsumeFragment(HomeCountBean.ResultBean.PayableInfoBean payableInfoBean){
        this.payableInfoBean=payableInfoBean;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_consume;
    }


    @Override
    protected void initView() {
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        date=new int[]{payableInfoBean.getNonConsumeNum(),payableInfoBean.getConsumeNum()};
        initPieChat(date);

        tvs[0].setText("￥"+payableInfoBean.getBillAmt());
        tvs[1].setText("￥"+payableInfoBean.getNonConsumeAmt());
        tvs[2].setText("￥"+payableInfoBean.getConsumeAmt());
    }



    private void initPieChat(int[] date) {
        int sum = date[0] + date[1];

        consume_pieChart.setDescription("");
        consume_pieChart.animateXY(1000, 1000);//设置动画效果
        consume_pieChart.setDrawSliceText(false);//圆环上不绘制图例文字
        consume_pieChart.setHoleRadius(64f);//设置内圆环半径
        consume_pieChart.setCenterTextSize(10f);//设置中间文字中大小
        consume_pieChart.setCenterText(generateCenterText(sum));
        Legend legend = consume_pieChart.getLegend();//获取图例

        if (sum == 0) {
            consume_pieChart.setData(generateEmptyPieData());
            consume_pieChart.setHighlightPerTapEnabled(false);//点击不响应
            legend.setEnabled(false);//图例隐藏
            return;
        }

        if (sum == 0) {
            date[0] = 50;
            date[1]=50;
            sum = 100;
        }


        consume_pieChart.setData(generatePieData(date));

        legend.setEnabled(false);//图例显示
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);//图例显示位置设置

        consume_pieChart.setHighlightPerTapEnabled(true);//点击响应
        consume_pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {//圆盘点击事件
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
        SpannableString s = new SpannableString(total +"/"+total+ "\n 笔数");
        s.setSpan(new RelativeSizeSpan(3f), 0, total.length()+2, 2);
        s.setSpan(new ForegroundColorSpan(Color.rgb(153, 153, 153)), 0, total.length()+2, 0);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6C5B")), total.length(), s.length(), 0);
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

//        int i1 = 222;
//        int i2 = 222;
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
        colors.add(Color.rgb(245, 166, 35));
        colors.add(Color.parseColor("#FF6C5B"));
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
        colors.add(Color.parseColor("#FF6C5B"));
        colors.add(Color.rgb(153, 153, 153));
        pieDataSet.setColors(colors);

        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);

        PieData pieData = new PieData(xVals, pieDataSet);

        return pieData;
    }
}