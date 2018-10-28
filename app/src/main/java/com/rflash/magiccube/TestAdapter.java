package com.rflash.magiccube;

import android.content.Context;
import android.widget.TextView;

import com.rflash.basemodule.adapter.BaseAdp;
import com.rflash.basemodule.adapter.BaseHolder;

import java.util.List;

/**
 * Created by yangfan on 2017/11/23.
 */
@Deprecated
public class TestAdapter extends BaseAdp<TestBean> {


    public TestAdapter(Context context, List<TestBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void onBind(BaseHolder orh, TestBean testBean, int position) {
        //时间
        ((TextView) orh.getView(R.id.tv_date)).setText(testBean.getDate());

        //银行卡号
        ((TextView) orh.getView(R.id.tv_bankcard)).setText(testBean.getBankCard());

        //银行
        ((TextView) orh.getView(R.id.tv_bank)).setText(testBean.getBank());

        //姓名
        ((TextView) orh.getView(R.id.tv_name)).setText(testBean.getName());

        //金额
        ((TextView) orh.getView(R.id.tv_money)).setText(context.getResources().getString(R.string.money, testBean.getMoney()));

        //卡号
        ((TextView) orh.getView(R.id.tv_num)).setText(context.getResources().getString(R.string.card_num, testBean.getCardNum()));

        //还款时间
        ((TextView) orh.getView(R.id.tv_date_repay)).setText(context.getResources().getString(R.string.date, testBean.getOperationDate()));

    }
}
