package com.rflash.magiccube.ui.shanghu.download;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;
import com.rflash.magiccube.event.PositionMessage;
import com.rflash.magiccube.event.PositionMessage2;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lenovo on 2018/10/11.
 */

public class DownloadAdapter extends BaseQuickAdapter<DownloadBean.ResultBean,BaseViewHolder> {


    boolean isOption;

    /**
     * 全选
     */
    public void selectAll() {
        notifyDataSetChanged();
    }

    public DownloadAdapter(boolean isOption,List<DownloadBean.ResultBean> data) {
        super(R.layout.item_download_shanghu, data);
        this.isOption=isOption;
    }

    @Override
    protected void convert(BaseViewHolder helper, DownloadBean.ResultBean item) {
        if(isOption)
            ((CheckBox)helper.getView(R.id.shanghu_cb)).setVisibility(View.VISIBLE);
        else
            ((CheckBox)helper.getView(R.id.shanghu_cb)).setVisibility(View.GONE);

        ((CheckBox)helper.getView(R.id.shanghu_cb)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //刷新适配器
//                notifyDataSetChanged();
                EventBus.getDefault().post(new PositionMessage2(helper.getPosition(),b));
            }
        });
        //从map集合获取状态
        ((CheckBox)helper.getView(R.id.shanghu_cb)).setChecked(item.getSelected());;
        ((TextView)helper.getView(R.id.address_tv)).setText(item.getRegion()+"");
        ((TextView)helper.getView(R.id.merchantName_tv)).setText(item.getMerchantName()+"");
        ((TextView)helper.getView(R.id.channelName_tv)).setText(item.getChannelName()+"");
        ((TextView)helper.getView(R.id.merchantType_tv)).setText(item.getMerchantTypeName()+"");
        ((TextView)helper.getView(R.id.merchantCode_tv)).setText(item.getMerchantCode()+"");
        ((TextView)helper.getView(R.id.download_date_tv)).setText(item.getDate()+"");
    }
}
