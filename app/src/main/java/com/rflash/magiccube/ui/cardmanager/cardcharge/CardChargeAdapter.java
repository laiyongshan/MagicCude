package com.rflash.magiccube.ui.cardmanager.cardcharge;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by lenovo on 2018/10/13.
 */

public class CardChargeAdapter extends BaseQuickAdapter<CardChargerBean.ResultBean,BaseViewHolder> {
    public CardChargeAdapter(List<CardChargerBean.ResultBean> data) {
        super(R.layout.item_cardcharge_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardChargerBean.ResultBean item) {
        ((TextView)helper.getView(R.id.serviceAmt_tv)).setText("￥"+item.getServiceAmt()/100);
        ((TextView)helper.getView(R.id.serviceDate_tv)).setText(item.getServiceStartDate()+"至"+item.getServiceEndDate());
        ((TextView)helper.getView(R.id.paidTime_tv)).setText(""+item.getPaidTime());
        ((TextView)helper.getView(R.id.serviceRate_tv)).setText(""+item.getServiceRate());
        ((TextView)helper.getView(R.id.reciveAmt_tv)).setText("￥"+item.getReciveAmt()/100);
        ((TextView)helper.getView(R.id.paidAmt_tv)).setText("+"+item.getPaidAmt()/100);
    }
}
