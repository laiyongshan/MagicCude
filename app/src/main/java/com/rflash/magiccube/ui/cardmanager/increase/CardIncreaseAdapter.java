package com.rflash.magiccube.ui.cardmanager.increase;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by lenovo on 2018/10/13.
 */

public class CardIncreaseAdapter extends BaseQuickAdapter<IncreaseBean.ResultBean,BaseViewHolder> {
    public CardIncreaseAdapter( List<IncreaseBean.ResultBean> data) {
        super(R.layout.item_card_increase_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IncreaseBean.ResultBean item) {
        if(item.getChangeType().equals("LIMIT_UP")){
            ((TextView)helper.getView(R.id.increase_Amt_tv)).setTextColor(Color.RED);
            ((TextView)helper.getView(R.id.increase_Amt_tv)).setText("+"+item.getAmt()/100);
            ((TextView)helper.getView(R.id.increase_type_tv)).setText("提额");
        }else if(item.getChangeType().equals("LIMIT_DOWN")){
            ((TextView)helper.getView(R.id.increase_Amt_tv)).setTextColor(Color.parseColor("#3F51B5"));
            ((TextView)helper.getView(R.id.increase_Amt_tv)).setText("-"+item.getAmt()/100);
            ((TextView)helper.getView(R.id.increase_type_tv)).setText("降额");
        }
        ((TextView)helper.getView(R.id.increase_time_tv)).setText(item.getChangeTime()+"");
        ((TextView)helper.getView(R.id.originalLimit_newLimit_tv)).setText("原始固定额度：￥"+item.getOriginalLimit()/100+" | "+"当前固定额度：￥"+item.getNewLimit()/100);

    }
}
