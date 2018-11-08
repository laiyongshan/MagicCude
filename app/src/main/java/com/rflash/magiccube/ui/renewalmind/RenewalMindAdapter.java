package com.rflash.magiccube.ui.renewalmind;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * @author lys
 * @time 2018/11/7 09:31
 * @desc:
 */

public class RenewalMindAdapter extends BaseQuickAdapter<RenewalMindBean.ResultBean,BaseViewHolder> {

    public RenewalMindAdapter( List<RenewalMindBean.ResultBean> data) {
        super(R.layout.item_renewalmind_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RenewalMindBean.ResultBean item) {

    }
}
