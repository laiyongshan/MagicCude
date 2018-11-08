package com.rflash.magiccube.ui.addcard;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.roundview.RoundTextView;
import com.rflash.magiccube.R;
import com.rflash.magiccube.event.DeleteAdingMessage;
import com.rflash.magiccube.event.PositionMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by lenovo on 2018/11/3.
 */

public class AgingAdapter extends BaseQuickAdapter<AgingBean,BaseViewHolder> {

    public AgingAdapter( List<AgingBean> data) {
        super(R.layout.item_aging_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AgingBean item) {
        ((TextView)helper.getView(R.id.position_tv)).setText("分期"+(helper.getPosition()+1));
        ((ImageView)helper.getView(R.id.xiala_iv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ImageView)helper.getView(R.id.xiala_iv)).setVisibility(View.GONE);
                ((ImageView)helper.getView(R.id.shangla_iv)).setVisibility(View.VISIBLE);
                ((ScrollView) helper.getView(R.id.content_sv)).setVisibility(View.VISIBLE);
            }
        });

        ((ImageView)helper.getView(R.id.shangla_iv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ImageView)helper.getView(R.id.shangla_iv)).setVisibility(View.GONE);
                ((ImageView)helper.getView(R.id.xiala_iv)).setVisibility(View.VISIBLE);
                ((ScrollView) helper.getView(R.id.content_sv)).setVisibility(View.GONE);
            }
        });

        ((EditText)helper.getView(R.id.partTotalAmt_et)).setText(item.getPartTotalAmt()+"");
        ((EditText)helper.getView(R.id.partSeqno_et)).setText(item.getPartSeqno()+"");
        ((EditText)helper.getView(R.id.startTime_et)).setText(item.getStartTime()+"");
        ((EditText)helper.getView(R.id.endTime_et)).setText(item.getEndTime()+"");
        ((EditText)helper.getView(R.id.amt_et)).setText(item.getAmt()+"");
        ((EditText)helper.getView(R.id.fee_et)).setText(item.getFee()+"");
        if(item.getIsRepaied().equals("Y"))
            ((EditText)helper.getView(R.id.isRepaied_et)).setText("是");
        else
            ((EditText)helper.getView(R.id.isRepaied_et)).setText("否");
        ((EditText)helper.getView(R.id.createUser_et)).setText(item.getCreateUser()+"");
        ((RoundTextView)helper.getView(R.id.delete_aging_rtv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DeleteAdingMessage(helper.getPosition()));
            }
        });

    }
}
