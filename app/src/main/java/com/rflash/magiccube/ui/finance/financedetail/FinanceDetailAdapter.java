package com.rflash.magiccube.ui.finance.financedetail;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by lenovo on 2018/10/9.
 */

public class FinanceDetailAdapter extends BaseQuickAdapter<FinanceDetailBean.ResultBean,BaseViewHolder> {



    public FinanceDetailAdapter(List<FinanceDetailBean.ResultBean> data) {
        super(R.layout.item_financedetail_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinanceDetailBean.ResultBean item) {
        ((TextView)helper.getView(R.id.merchantName_tv)).setText(item.getMerchantName()+"");
        if(item.getTranType().equals("SALE")) {
            ((TextView) helper.getView(R.id.tranType_tv)).setText("消费");
            ((TextView) helper.getView(R.id.tranType_tv)).setTextColor(Color.RED);
        }else if(item.getTranType().equals("REPAY")) {
            ((TextView) helper.getView(R.id.tranType_tv)).setText("还款");
            ((TextView) helper.getView(R.id.tranType_tv)).setTextColor(Color.parseColor("#3F51B5"));
        }
        ((TextView)helper.getView(R.id.channelName_tv)).setText(item.getChannelName()+"");
        ((TextView)helper.getView(R.id.tranCost_tv)).setText(item.getTranCost()+"");
        ((TextView)helper.getView(R.id.amt_tv)).setText("￥"+Double.valueOf(item.getAmt())/100+"");
        ((TextView)helper.getView(R.id.operatorTime_tv)).setText(item.getModifyTime()+"");
    }
}
