package com.rflash.magiccube.ui.consumemodify;

import android.content.Context;
import android.graphics.Color;

import com.rflash.basemodule.adapter.BaseAdp;
import com.rflash.basemodule.adapter.BaseHolder;
import com.rflash.basemodule.utils.StringUtil;

import java.util.List;

/**
 * Created by yangfan on 2018/1/15.
 */

public class MerchantAdapter extends BaseAdp<Merchant.ResultBean> {


    private ItemClick itemClick;

    private String highLightText;

    public void setHighLightText(String highLightText) {
        this.highLightText = highLightText;
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public MerchantAdapter(Context context, List<Merchant.ResultBean> data) {
        super(context, data, android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void onBind(BaseHolder holder, Merchant.ResultBean resultBean, int position) {

        holder.setText(android.R.id.text1, StringUtil.setKeyWordColor(resultBean.getMerchantName(), highLightText, Color.parseColor("#34c0e3")));
        holder.setOnClickListener(android.R.id.text1, v -> {
            if (itemClick != null) {
                itemClick.selectItem(resultBean);
            }
        });
    }
}
