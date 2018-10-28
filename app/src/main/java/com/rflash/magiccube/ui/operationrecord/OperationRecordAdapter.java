package com.rflash.magiccube.ui.operationrecord;

import android.content.Context;
import android.widget.TextView;

import com.rflash.basemodule.adapter.BaseAdp;
import com.rflash.basemodule.adapter.BaseHolder;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by yangfan on 2017/11/21.
 */

public class OperationRecordAdapter extends BaseAdp<OperationRecord.ResultBean> {


    public OperationRecordAdapter(Context context, List<OperationRecord.ResultBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void onBind(BaseHolder holder, OperationRecord.ResultBean operationRecord, int position) {
        //左上角日期
        holder.setText(R.id.tv_date, operationRecord.getDate());

        //银行卡号
        holder.setText(R.id.tv_bankcard, StringUtil.getBankNo(operationRecord.getCardNo()));

        //银行
        holder.setText(R.id.tv_bank, operationRecord.getBankName());

        //姓名
        holder.setText(R.id.tv_name, operationRecord.getCustomerName());

        //卡位

        holder.setText(R.id.tv_num, context.getResources().getString(R.string.card_num, operationRecord.getCardSeqno()));

        //操作类型
        String type = "";
        String state = operationRecord.getState();
        if (state.equals(Config.DEAL)) {
            type = "已操作";
        } else if (state.equals(Config.WAIT_CONFIRM)) {
            type = "正在受理";
        } else if (state.equals(Config.ACCEPTED_FAILURE)) {
            type = "受理失败";
        }
        holder.setText(R.id.tv_operation_type, type);

        //操作时间
        holder.setText(R.id.tv_operation_date, operationRecord.getOperatorTime());

        //金额
        holder.setText(R.id.tv_money, context.getResources().getString(R.string.money, StringUtil.getTwoPointString(operationRecord.getAmt())));


    }
}
