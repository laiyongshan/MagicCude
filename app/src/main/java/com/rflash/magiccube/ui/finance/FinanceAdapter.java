package com.rflash.magiccube.ui.finance;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by lenovo on 2018/10/9.
 */
public class FinanceAdapter extends BaseQuickAdapter<FinanceBean.ResultBean,BaseViewHolder> {

    public FinanceAdapter(List<FinanceBean.ResultBean> data) {
        super(R.layout.item_finance_card, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinanceBean.ResultBean item) {
//        ((ImageView)helper.getView(R.id.bank_icon)).setImageResource();
        ((TextView)helper.getView(R.id.bank_name_tv)).setText(item.getBankName()+"");
        ((TextView)helper.getView(R.id.cardowner_name_tv)).setText("持卡人："+item.getCustomerName()+"");
        ((TextView)helper.getView(R.id.bank_card_num_tv)).setText(item.getCardNo()+"");
        ((TextView)helper.getView(R.id.reportDate_tv)).setText("服务月份："+item.getReportDate()+"");
        ((TextView)helper.getView(R.id.card_seri_num_tv)).setText(item.getCardSeqno()+"");
        ((TextView)helper.getView(R.id.sales_man_name_tv)).setText("无");
        ((TextView)helper.getView(R.id.sales_man_profit_tv)).setText("无");
        ((TextView)helper.getView(R.id.tranCost_tv)).setText(item.getTranCost()+"");
        ((TextView)helper.getView(R.id.revenue_tv)).setText(item.getRevenue()+"");


    }
}
