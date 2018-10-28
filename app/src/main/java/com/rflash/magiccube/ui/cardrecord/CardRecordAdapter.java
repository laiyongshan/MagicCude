package com.rflash.magiccube.ui.cardrecord;

import android.content.Context;
import android.widget.TextView;

import com.rflash.basemodule.adapter.BaseAdp;
import com.rflash.basemodule.adapter.BaseHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by yangfan on 2017/11/17.
 */

public class CardRecordAdapter extends BaseAdp<CardRecord> {




    public CardRecordAdapter(Context context, List<CardRecord> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void onBind(BaseHolder holder, CardRecord cardRecord, int position) {
        //  日期
        ((TextView)holder.getView(R.id.tv_date)).setText(cardRecord.getDate());

        //金额
        ((TextView)holder.getView(R.id.tv_money)).setText(context.getResources().getString(R.string.money,cardRecord.getMoney()));

        //银行卡号
        ((TextView)holder.getView(R.id.tv_bankcard)).setText(cardRecord.getBankcard());


    }
}
