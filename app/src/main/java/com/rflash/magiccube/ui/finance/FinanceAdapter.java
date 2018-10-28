package com.rflash.magiccube.ui.finance;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by lenovo on 2018/10/9.
 */
public class FinanceAdapter extends BaseQuickAdapter<FinanceBean,BaseViewHolder> {

    public FinanceAdapter(List<FinanceBean> data) {
        super(R.layout.item_finance_card, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinanceBean item) {
//        ((TextView)helper.getView(R.id.bank_card_num_tv)).setText("");
        ((TextView)helper.getView(R.id.bank_name_tv)).setText("中国农业银行");
    }
}
