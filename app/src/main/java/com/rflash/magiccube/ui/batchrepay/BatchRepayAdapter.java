package com.rflash.magiccube.ui.batchrepay;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rflash.basemodule.adapter.BaseAdp;
import com.rflash.basemodule.adapter.BaseHolder;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yangfan on 2017/11/20.
 */

public class BatchRepayAdapter extends BaseAdp<BatchRepay.ResultBean> {


    private BatchOperationListener listener;


    public void setListener(BatchOperationListener listener) {
        this.listener = listener;
    }


    public BatchRepayAdapter(Context context, List<BatchRepay.ResultBean> data, int layoutId) {
        super(context, data, layoutId);
    }


    @Override
    public void onBind(BaseHolder holder, BatchRepay.ResultBean batchRepay, int position) {
        //日期
        String date = batchRepay.getDate();
        holder.setText(R.id.tv_date, date);

        //银行卡号
        holder.setText(R.id.tv_bankcard, StringUtil.getBankNo(batchRepay.getCardNo()));

        //银行
        holder.setText(R.id.tv_bank, batchRepay.getBankName());

        //姓名
        holder.setText(R.id.tv_name, batchRepay.getCustomerName());

        //卡位
        holder.setText(R.id.tv_num, context.getResources().getString(R.string.card_num, batchRepay.getCardSeqno()));

        String money = StringUtil.getTwoPointString(batchRepay.getAmt());
        //金额
        holder.setText(R.id.tv_money, context.getResources().getString(R.string.money, money + ""));

        //设置checkbox
        CheckBox checkbox = holder.getView(R.id.checkbox);
        boolean checked = batchRepay.isChecked();
        checkbox.setChecked(checked);
        checkbox.setTag(R.id.checkbox_one_tag, position);
        checkbox.setTag(R.id.checkbox_two_tag, batchRepay);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag(R.id.checkbox_one_tag);
                BatchRepay.ResultBean br = (BatchRepay.ResultBean) v.getTag(R.id.checkbox_two_tag);
                boolean isChecked = ((CheckBox) v).isChecked();
                listener.onCheckListener(position, isChecked);
                br.setChecked(isChecked);
                Log.i("----", "checkbox==" + isChecked);
            }
        });
        View view = holder.getView(R.id.rl_item);
        view.setTag(R.id.checkbox_one_tag, position);
        view.setTag(R.id.checkbox_two_tag, batchRepay);
        view.setTag(R.id.checkbox_three_tag, checkbox);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag(R.id.checkbox_one_tag);
                BatchRepay.ResultBean br = (BatchRepay.ResultBean) v.getTag(R.id.checkbox_two_tag);
                CheckBox checkbox = (CheckBox) v.getTag(R.id.checkbox_three_tag);
                boolean isChecked = !checkbox.isChecked();
                checkbox.setChecked(isChecked);
                listener.onCheckListener(position, isChecked);
                br.setChecked(isChecked);
            }
        });

        //代付
        TextView tvHelpPay = holder.getView(R.id.tv_help_pay);
        tvHelpPay.setTag(position);
        holder.setOnClickListener(R.id.tv_help_pay, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                listener.onHelpPay(position);
            }
        });

        //还款
        TextView tvRepay = holder.getView(R.id.tv_repay);
        tvRepay.setTag(position);
        holder.setOnClickListener(R.id.tv_repay, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                listener.onRepay(position);
            }
        });
        try {
            Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
            Calendar instance = Calendar.getInstance();
            instance.set(Calendar.HOUR_OF_DAY, 0);
            instance.set(Calendar.MINUTE, 0);
            instance.set(Calendar.SECOND, 0);
            boolean before = parse.before(instance.getTime());
            if (before) {
                tvRepay.setVisibility(View.GONE);
                tvHelpPay.setBackgroundColor(Color.parseColor("#ffffff"));
                tvHelpPay.setText("已过期");
                tvHelpPay.setTextColor(Color.parseColor("#999999"));
                checkbox.setVisibility(View.INVISIBLE);
                tvHelpPay.setClickable(false);
                view.setClickable(false);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
