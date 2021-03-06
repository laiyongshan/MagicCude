package com.rflash.magiccube.ui.cardmanager;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.util.DateUtil;
import com.rflash.magiccube.util.ToolUtils;

import java.util.List;

/**
 * Created by lenovo on 2018/10/12.
 */

public class CardManagerAdapter extends BaseQuickAdapter<CardBean.ResultBean,BaseViewHolder> {
    boolean isOption;
    public CardManagerAdapter(boolean isOption, List<CardBean.ResultBean> data) {
        super(R.layout.item_card_rv, data);
        this.isOption=isOption;
    }

    @Override
    protected void convert(BaseViewHolder helper, CardBean.ResultBean item) {
        if(isOption){
            ((TextView)(helper.getView(R.id.card_serial_tv))).setVisibility(View.GONE);
            ((CheckBox)helper.getView(R.id.card_item_cb)).setVisibility(View.VISIBLE);
        }else{
            ((TextView)(helper.getView(R.id.card_serial_tv))).setVisibility(View.VISIBLE);
            ((CheckBox)helper.getView(R.id.card_item_cb)).setVisibility(View.GONE);
        }

        if(ToolUtils.getBankIcon(item.getCardBankName()+"")!=0) {
            ((ImageView) (helper.getView(R.id.bank_icon))).setImageResource(ToolUtils.getBankIcon(item.getCardBankName() + ""));
            ((ImageView) (helper.getView(R.id.bank_icon))).setVisibility(View.VISIBLE);
        }else {
            ((ImageView) (helper.getView(R.id.bank_icon))).setVisibility(View.INVISIBLE);
        }

        ((LinearLayout)helper.getView(R.id.bankcard_ll)).setBackgroundResource(ToolUtils.getBankCardBg(item.getCardBankName()+""));

        ((TextView)(helper.getView(R.id.card_serial_tv))).setText((helper.getPosition()+1)+"");

        ((TextView)helper.getView(R.id.bank_name_tv)).setText(item.getCardBankName()+"");
        ((TextView)helper.getView(R.id.cardowner_name_tv)).setText("持卡人:"+item.getCustomerName()+"");
        ((TextView)helper.getView(R.id.bank_card_num_tv)).setText(item.getCardNo()+"");
        ((TextView)helper.getView(R.id.card_seri_num_tv)).setText(item.getCardSeqno()+"");
        ((TextView)helper.getView(R.id.billDate_tv)).setText(DateUtil.dayFormart(item.getBillDate())+"");
        ((TextView)helper.getView(R.id.repayDate_tv)).setText(DateUtil.dayFormart(item.getRepayDate())+"");
        ((TextView)helper.getView(R.id.availableAmt_tv)).setText(StringUtil.getTwoPointString(item.getAvailableAmt())+"");
        ((TextView)helper.getView(R.id.salesMan_tv)).setText(item.getSalesMan()+"");
        ((TextView)helper.getView(R.id.serviceEndDate_tv)).setText("服务到期时间："+DateUtil.formatDate1(item.getServiceEndDate())+"");
        ((TextView)helper.getView(R.id.fixedLimit_tv)).setText("固定额度：￥"+ StringUtil.getTwoPointString(item.getFixedLimit())+"");
        ((TextView)helper.getView(R.id.initAmt_tv)).setText("初始金额：￥"+StringUtil.getTwoPointString(item.getInitAmt())+"");

        if(item.getState().equals("VALID")){//正常
            ((ImageView)helper.getView(R.id.card_type_bg)).setVisibility(View.GONE);
        }else if(item.getState().equals("FREEZE")){//冻结
            ((ImageView)helper.getView(R.id.card_type_bg)).setVisibility(View.VISIBLE);
            ((ImageView)helper.getView(R.id.card_type_bg)).setImageResource(R.mipmap.bg_card_dongjie);
        }else if(item.getState().equals("EXPIRE")){//过期
            ((ImageView)helper.getView(R.id.card_type_bg)).setVisibility(View.VISIBLE);
            ((ImageView)helper.getView(R.id.card_type_bg)).setImageResource(R.mipmap.bg_card_guoqi);
        }

    }
}
