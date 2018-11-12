package com.rflash.magiccube.ui.cardmanager.cardcharge;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.util.DateUtil;

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
        if(item.getServiceType().equals("FIXED_LIMIT")) {
            ((TextView) helper.getView(R.id.serviceAmt_tv)).setText("固定额度");
        }else if(item.getServiceType().equals("REPAY_LIMIT")){
            ((TextView) helper.getView(R.id.serviceAmt_tv)).setText("需还款金额");
        }else if(item.getServiceType().equals("USER_DEFINED")){
            ((TextView) helper.getView(R.id.serviceAmt_tv)).setText("自定义金额");
        }
        ((TextView)helper.getView(R.id.serviceDate_tv)).setText(DateUtil.formatDate1(item.getServiceStartDate())+"至"+DateUtil.formatDate1(item.getServiceEndDate()));
        ((TextView)helper.getView(R.id.paidTime_tv)).setText(""+DateUtil.mills2Date2(item.getPaidTime()));
        ((TextView)helper.getView(R.id.serviceRate_tv)).setText(""+Double.valueOf(item.getServiceRate())*100+"%");
        ((TextView)helper.getView(R.id.reciveAmt_tv)).setText("￥"+ StringUtil.getTwoPointString(item.getReciveAmt()));
        ((TextView)helper.getView(R.id.paidAmt_tv)).setText("+"+StringUtil.getTwoPointString(item.getPaidAmt()));
    }
}
