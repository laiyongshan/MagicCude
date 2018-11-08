package com.rflash.magiccube.ui.refund;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.util.DateUtil;

import java.util.List;

/**
 * Created by lenovo on 2018/10/11.
 */

public class RefundAdapter extends BaseQuickAdapter<RefundBean.ResultBean,BaseViewHolder> {
    public RefundAdapter(List<RefundBean.ResultBean> data) {
        super(R.layout.iten_refund_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RefundBean.ResultBean item) {
        if(item.getRepayState().equals("NO_REPAID")) {
            ((TextView) helper.getView(R.id.refund_type_tv)).setText("未还清");
            ((TextView) helper.getView(R.id.refund_type_tv)).setTextColor(Color.RED);
        }else if(item.getRepayState().equals("REPAID")) {
            ((TextView) helper.getView(R.id.refund_type_tv)).setText("已还清");
            ((TextView) helper.getView(R.id.refund_type_tv)).setTextColor(Color.parseColor("#3F51B5"));
        }else if(item.getRepayState().equals("OVERDUE")) {
            ((TextView) helper.getView(R.id.refund_type_tv)).setText("已逾期");
            ((TextView) helper.getView(R.id.refund_type_tv)).setTextColor(Color.RED);
        }

        ((TextView)helper.getView(R.id.bankcard_name_tv)).setText(item.getBankName()+""+item.getCardNo().substring(item.getCardNo().length()-4));
        ((TextView)helper.getView(R.id.cardSeqno_tv)).setText(item.getCardSeqno()+"");
        ((TextView)helper.getView(R.id.customerName_tv)).setText(item.getCustomerName()+"");
        ((TextView)helper.getView(R.id.billAmt_tv)).setText("￥"+ StringUtil.getTwoPointString(item.getBillAmt())+"");
        ((TextView)helper.getView(R.id.noRepayAmt_tv)).setText("￥"+StringUtil.getTwoPointString(item.getNoRepayAmt())+"");
        ((TextView)helper.getView(R.id.bill_end_date_tv)).setText(DateUtil.formatDate1(item.getBillEndDate())+"");

    }
}
