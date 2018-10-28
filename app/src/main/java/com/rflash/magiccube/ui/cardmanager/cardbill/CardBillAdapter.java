package com.rflash.magiccube.ui.cardmanager.cardbill;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by lenovo on 2018/10/13.
 */

public class CardBillAdapter extends BaseQuickAdapter<CardBillBean,BaseViewHolder> {
    public CardBillAdapter( List<CardBillBean> data) {
        super(R.layout.item_cardbill_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardBillBean item) {

    }
}
