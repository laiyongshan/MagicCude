package com.rflash.magiccube.ui.finance;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.util.DateUtil;
import com.rflash.magiccube.util.ToolUtils;

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

        if(ToolUtils.getBankIcon(item.getBankName()+"")!=0) {
            ((ImageView) (helper.getView(R.id.bank_icon))).setImageResource(ToolUtils.getBankIcon(item.getBankName() + ""));
            ((ImageView) (helper.getView(R.id.bank_icon))).setVisibility(View.VISIBLE);
        }else {
            ((ImageView) (helper.getView(R.id.bank_icon))).setVisibility(View.INVISIBLE);
        }
        ((RelativeLayout)helper.getView(R.id.finance_card_rl)).setBackgroundResource(ToolUtils.getFinanceBankCardBg(item.getBankName()+""));

        ((TextView)helper.getView(R.id.bank_name_tv)).setText(item.getBankName()+"");
        ((TextView)helper.getView(R.id.cardowner_name_tv)).setText("持卡人："+item.getCustomerName()+"");
        ((TextView)helper.getView(R.id.bank_card_num_tv)).setText(item.getCardNo()+"");
        ((TextView)helper.getView(R.id.reportDate_tv)).setText("服务月份："+ DateUtil.formatDate2(item.getReportDate())+"");
        ((TextView)helper.getView(R.id.card_seri_num_tv)).setText(item.getCardSeqno()+"");
        ((TextView)helper.getView(R.id.sales_man_name_tv)).setText("无");
        ((TextView)helper.getView(R.id.sales_man_profit_tv)).setText("无");
        ((TextView)helper.getView(R.id.tranCost_tv)).setText(StringUtil.getTwoPointString(item.getTranCost())+"");
        ((TextView)helper.getView(R.id.revenue_tv)).setText(StringUtil.getTwoPointString(item.getRevenue())+"");
    }
}
