package com.rflash.magiccube.ui.finance.financedetail;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by lenovo on 2018/10/9.
 */

public class FinanceDetailAdapter extends BaseQuickAdapter<FinanceDetailBean,BaseViewHolder> {

    int financeTye;

    public FinanceDetailAdapter( int financeType,List<FinanceDetailBean> data) {
        super(R.layout.item_financedetail_rv, data);
        this.financeTye=financeType;
    }

    @Override
    protected void convert(BaseViewHolder helper, FinanceDetailBean item) {
            if(financeTye==0){//消费
                ((TextView)helper.getView(R.id.finance_type_tv)).setText("消费");
                ((TextView)helper.getView(R.id.finance_type_tv)).setTextColor(Color.RED);
            }else if(financeTye==1){//还款
                ((TextView)helper.getView(R.id.finance_type_tv)).setText("还款");
                ((TextView)helper.getView(R.id.finance_type_tv)).setTextColor(Color.parseColor("#3F51B5"));
            }
    }
}
