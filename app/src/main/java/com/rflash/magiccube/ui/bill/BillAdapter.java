package com.rflash.magiccube.ui.bill;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rflash.basemodule.adapter.BaseAdp;
import com.rflash.basemodule.adapter.BaseHolder;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yangfan on 2017/11/15.
 */

public class BillAdapter extends BaseAdp<Bill.ResultBean> {
    public BillAdapter(Context context, List<Bill.ResultBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    private SureMoneyListener listener;

    public void setListener(SureMoneyListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBind(BaseHolder holder, Bill.ResultBean bill, int position) {
        //卡
        holder.setText(R.id.tv_num, context.getResources().getString(R.string.card_num, bill.getCardSeqno()));
        //银行卡号
        holder.setText(R.id.tv_bankcard, StringUtil.getBankNo(bill.getCardNo()));

        //姓名
        holder.setText(R.id.tv_name, bill.getCustomerName());

        //银行
        holder.setText(R.id.tv_bank, bill.getBankName());
        //账单月份和金额
        TextView tvBill = holder.getView(R.id.tv_bill);
        String billAmt = bill.getBillAmt();
        String billAmtYuan = StringUtil.getTwoPointString(billAmt);
        //格式化时间
        String format = bill.getBillMonth();
        try {
            Date date = new SimpleDateFormat("yyyyMM").parse(format);
            format = new SimpleDateFormat("yyyy-MM").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvBill.setText(context.getResources().getString(R.string.main_bill, format, billAmtYuan));
        //实际金额输入框
        EditText edtActualMoney = holder.getView(R.id.edt_actual_money);
        edtActualMoney.setText(billAmtYuan);
        TextView tvSure = holder.getView(R.id.tv_sure);
        tvSure.setTag(R.id.tv_one_tag, edtActualMoney);
        tvSure.setTag(R.id.tv_two_tag, position);
        //确认金额  监听
        holder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtActualMoney = (EditText) v.getTag(R.id.tv_one_tag);
                int position = (int) v.getTag(R.id.tv_two_tag);
                String money = edtActualMoney.getText().toString();
                String fenMoney = StringUtil.getFen(money);
                if (listener != null) {
                    listener.onSureMoney(position, fenMoney);
                }
            }
        });
    }


    interface SureMoneyListener {
        void onSureMoney(int position, String fenMoney);
    }
}
