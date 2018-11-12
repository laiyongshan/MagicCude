package com.rflash.magiccube.ui.cardmanager.cardbill;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.roundview.RoundTextView;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.util.DateUtil;

import java.util.List;

/**
 * Created by lenovo on 2018/10/13.
 */

public class CardBillAdapter extends BaseQuickAdapter<CardBillBean.ResultBean,BaseViewHolder> {
    CardBillPresenter mCardBillPresenter;
    public CardBillAdapter( List<CardBillBean.ResultBean> data,CardBillPresenter mCardBillPresenter) {
        super(R.layout.item_cardbill_rv, data);
        this.mCardBillPresenter=mCardBillPresenter;
    }

    @Override
    protected void convert(BaseViewHolder helper, CardBillBean.ResultBean item) {
        if(item.getBillType().equals("REPAY")) {
            ((TextView) helper.getView(R.id.billType_amtv)).setText("还款账单");
        }else if(item.getBillType().equals("CARD_MANAGE")){
            ((TextView) helper.getView(R.id.billType_amtv)).setText("精养卡账单");
        }
        if(item.getIsConfirm().equals("Y")) {
            ((TextView) helper.getView(R.id.isConfirm_tv)).setText("已确认");
            ((TextView) helper.getView(R.id.isConfirm_tv)).setTextColor(Color.RED);
        }else if(item.getIsConfirm().equals("N")){
            ((TextView) helper.getView(R.id.isConfirm_tv)).setText("未确认 ");
            ((TextView) helper.getView(R.id.isConfirm_tv)).setTextColor(Color.parseColor("#3F51B5"));
        }
        ((TextView) helper.getView(R.id.billMonth_tv)).setText(DateUtil.formatDate2(item.getBillMonth())+"");
        ((TextView) helper.getView(R.id.billStartToEndDate)).setText(DateUtil.formatDate1(item.getBillStartDate())+"至"+DateUtil.formatDate1(item.getBillEndDate()));
        ((TextView) helper.getView(R.id.consumeAmt_tv)).setText("￥"+ StringUtil.getTwoPointString(item.getConsumeAmt())+"");
        ((TextView) helper.getView(R.id.repayAmt_tv)).setText("￥"+StringUtil.getTwoPointString(item.getRepayAmt())+"");
        ((TextView) helper.getView(R.id.billAmt_tv)).setText("￥"+StringUtil.getTwoPointString(item.getBillAmt())+"");
        ((EditText) helper.getView(R.id.factAmt_et)).setText(StringUtil.getTwoPointString(item.getBillAmt())+"");
        ((RoundTextView)helper.getView(R.id.update_rtv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardBillPresenter.updateCardBill(item.getBillId()+"",item.getCardNo()+"",item.getBillMonth()+"",((EditText) helper.getView(R.id.factAmt_et)).getText().toString().trim()+"","UPDATE");
            }
        });
    }
}
