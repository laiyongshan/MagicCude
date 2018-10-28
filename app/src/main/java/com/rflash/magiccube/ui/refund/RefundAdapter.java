package com.rflash.magiccube.ui.refund;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by lenovo on 2018/10/11.
 */

public class RefundAdapter extends BaseQuickAdapter<RefundBean,BaseViewHolder> {
    int refundType;
    public RefundAdapter(int refundType,List<RefundBean> data) {
        super(R.layout.iten_refund_rv, data);
        this.refundType=refundType;
    }

    @Override
    protected void convert(BaseViewHolder helper, RefundBean item) {
        if(refundType==0){
            ((TextView)helper.getView(R.id.refund_type_tv)).setText("未还清");
            ((TextView)helper.getView(R.id.refund_type_tv)).setTextColor(Color.RED);
        }else if(refundType==1){
            ((TextView)helper.getView(R.id.refund_type_tv)).setText("已还清");
            ((TextView)helper.getView(R.id.refund_type_tv)).setTextColor(Color.parseColor("#3F51B5"));
        }else if(refundType==2){
            ((TextView)helper.getView(R.id.refund_type_tv)).setText("已逾期");
            ((TextView)helper.getView(R.id.refund_type_tv)).setTextColor(Color.RED);
        }
    }
}
