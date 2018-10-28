package com.rflash.magiccube.ui.shanghu;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * @author lys
 * @time 2018/10/11 14:24
 * @desc:
 */

public class ShanghuAdapter extends BaseQuickAdapter<ShanghuBean.ResultBean,BaseViewHolder> {

    boolean isOption;

    public ShanghuAdapter(boolean isOption,List<ShanghuBean.ResultBean> data) {
        super(R.layout.item_shanghu_rv, data);
        this.isOption=isOption;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShanghuBean.ResultBean item) {
        if(isOption)
            ((CheckBox)helper.getView(R.id.shanghu_cb)).setVisibility(View.VISIBLE);
        else
            ((CheckBox)helper.getView(R.id.shanghu_cb)).setVisibility(View.GONE);

        ((TextView)helper.getView(R.id.merchantName_tv)).setText(item.getMerchantName()+"");
        if(item.getState().equals("valid"))
            ((TextView)helper.getView(R.id.state_tv)).setText("正常");
        else if(item.getState().equals("freeze"))
            ((TextView)helper.getView(R.id.state_tv)).setText("冻结");
        ((TextView)helper.getView(R.id.mcc_tv)).setText(item.getMcc()+"");
        ((TextView)helper.getView(R.id.channelName_tv)).setText(item.getChannelName()+"");
        ((TextView)helper.getView(R.id.merchantType_tv)).setText(item.getMerchantType()+"");
        ((TextView)helper.getView(R.id.merchantCode_tv)).setText(item.getMerchantCode()+"");
        ((TextView)helper.getView(R.id.download_date_tv)).setText(item.getDate()+"");
    }
}
