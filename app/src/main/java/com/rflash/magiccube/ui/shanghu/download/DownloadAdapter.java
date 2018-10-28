package com.rflash.magiccube.ui.shanghu.download;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by lenovo on 2018/10/11.
 */

public class DownloadAdapter extends BaseQuickAdapter<DownloadBean.ResultBean,BaseViewHolder> {

    boolean isOption;

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

        ((TextView)helper.getView(R.id.merchantName_tv)).setText(item.getMerchantName()+"");
        ((TextView)helper.getView(R.id.channelName_tv)).setText(item.getChannelName()+"");
        ((TextView)helper.getView(R.id.merchantType_tv)).setText(item.getMerchantType()+"");
        ((TextView)helper.getView(R.id.merchantCode_tv)).setText(item.getMerchantCode()+"");
        ((TextView)helper.getView(R.id.download_date_tv)).setText(item.getDate()+"");
    }
}
