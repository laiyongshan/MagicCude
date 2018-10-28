package com.rflash.magiccube.ui.consumemodify;

import android.content.Context;

import com.rflash.basemodule.adapter.AbstractAdapter;
import com.rflash.basemodule.adapter.ViewHolder;

import java.util.List;

/**
 * Created by yangfan on 2018/1/15.
 */

public class SpinnerAdapter extends AbstractAdapter<Channel.ResultBean> {


    public SpinnerAdapter(Context context, List<Channel.ResultBean> data) {
        super(context, data, android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void convert(ViewHolder holder, Channel.ResultBean bean) {

        holder.setText(android.R.id.text1, bean.getChannelName());

    }


}
